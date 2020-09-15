package course.java.sdm.engine.dto;
import course.java.sdm.engine.engine.*;

import java.util.*;

public class OrderDto {

    private final int id;
    private final Date date;
    private final BasicCustomerDto basicCustomerDto;
    private final Map<Integer, StoreOrderDto> storesOrderDto;   // The key is store id
    private final float itemsCost;
    private final float deliveryCost;
    private final int totalItems;
    private final String orderCategory;


    public OrderDto(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.basicCustomerDto = new BasicCustomerDto(order.getCustomer());
        this.storesOrderDto = new HashMap<>();
        copyStoreOrdersDto(order);
        this.itemsCost = order.getItemsCost();
        this.deliveryCost = order.getDeliveryCost();
        this.totalItems = order.getTotalItems();
        this.orderCategory = order.getOrderCategory().getOrderCategoryStr();
    }

    private void copyStoreOrdersDto(Order order) {
        order.getStoresOrder().forEach((storeId,storeOrder) -> {
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

    public BasicCustomerDto getBasicCustomerDto() {
        return basicCustomerDto;
    }

    public Collection<StoreOrderDto> getStoresOrderDto() {
        return storesOrderDto.values();
    }

    public float getItemsCost() {
        return itemsCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public float getTotalCost() {
        return (itemsCost + deliveryCost);
    }

    public int getTotalItems() {
        return totalItems;
    }

    public String getOrderCategory() {
        return orderCategory;
    }

    public StoreOrderDto getStoreOrderDto(int storeId) {
        return storesOrderDto.get(storeId);
    }
}
