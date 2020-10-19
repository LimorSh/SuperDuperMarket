package course.java.sdm.engine.dto;
import course.java.sdm.engine.Utils;
import course.java.sdm.engine.engine.*;

import java.util.*;

public class OrderDto {

    private final int id;
    private final Date date;
    private final int customerXLocation;
    private final int customerYLocation;
    private final int totalStores;
    private final int totalItems;
    private final float itemsCost;
    private final float deliveryCost;
    private final float totalCost;
    private ArrayList<PurchasedItemDto> purchasedItemsDto;
    private Map<Integer, StoreOrderDto> storesOrderDto;   // The key is store id


    public OrderDto(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.customerXLocation = order.getCustomerLocation().getCoordinate().x;
        this.customerYLocation = order.getCustomerLocation().getCoordinate().y;
        this.totalStores = order.getTotalStores();
        this.totalItems = order.getTotalItems();
        this.itemsCost = Utils.roundNumberWithTwoDigitsAfterPoint(order.getItemsCost());
        this.deliveryCost = Utils.roundNumberWithTwoDigitsAfterPoint(order.getDeliveryCost());
        this.totalCost = Utils.roundNumberWithTwoDigitsAfterPoint(order.getTotalCost());
        copyStoreOrdersDto(order);
    }

    private void copyStoreOrdersDto(Order order) {
        order.getStoresOrderMap().forEach((storeId,storeOrder) -> {
            StoreOrderDto storeOrderDto = new StoreOrderDto(storeOrder);
            storesOrderDto.put(storeId, storeOrderDto);
        });
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getCustomerXLocation() {
        return customerXLocation;
    }

    public int getCustomerYLocation() {
        return customerYLocation;
    }

    public int getTotalStores() {
        return totalStores;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public float getItemsCost() {
        return itemsCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public ArrayList<PurchasedItemDto> getPurchasedItemsDto() {
        return purchasedItemsDto;
    }

    public void setPurchasedItemsDto(ArrayList<PurchasedItemDto> purchasedItemsDto) {
        this.purchasedItemsDto = purchasedItemsDto;
    }

    public Collection<StoreOrderDto> getStoresOrderDto() {
        return storesOrderDto.values();
    }
}
