package course.java.sdm.engine.dto;

import course.java.sdm.engine.Utils;
import course.java.sdm.engine.engine.Location;
import course.java.sdm.engine.engine.Offer;
import course.java.sdm.engine.engine.OrderLine;
import course.java.sdm.engine.engine.StoreOrder;

import java.util.*;

public class StoreOrderDto {

    private final int orderId;
    private final Date date;
    private final String customerName;
    private final String customerLocation;
    private final int storeId;
    private final String storeName;
    private final int storePpk;
    private final Collection<OrderLineDto> orderLinesDto; //the key is itemId
    private final Map<String, ArrayList<OfferDto>> appliedOffersDto;  //the key is discount name
    private final int totalItems;
    private final float itemsCost;
    private final float deliveryCost;
    private final float totalCost;
    private final double distanceFromCustomer;
    private ArrayList<PurchasedItemStoreOrderDto> purchasedItemsStoreOrderDto;

    public StoreOrderDto(StoreOrder storeOrder) {
        this.orderId = storeOrder.getOrderId();
        this.date = storeOrder.getDate();
        this.customerName = storeOrder.getCustomerName();
        this.customerLocation = Location.getLocationStr(storeOrder.getCustomerLocation());
        this.storeId = storeOrder.getStore().getId();
        this.storeName = storeOrder.getStore().getName();
        this.storePpk = storeOrder.getStore().getPpk();
        this.orderLinesDto = new ArrayList<>();
        copyOrderLinesDto(storeOrder);
        this.appliedOffersDto = new HashMap<>();
        copyAppliedOffersDto(storeOrder);
        this.totalItems = storeOrder.getTotalItems();
        this.itemsCost = Utils.roundNumberWithTwoDigitsAfterPoint(storeOrder.getItemsCost());
        this.deliveryCost = Utils.roundNumberWithTwoDigitsAfterPoint(storeOrder.getDeliveryCost());
        this.totalCost = Utils.roundNumberWithTwoDigitsAfterPoint(storeOrder.getTotalCost());
        this.distanceFromCustomer = Utils.roundNumberWithTwoDigitsAfterPoint(storeOrder.getDistanceFromCustomer());
    }

    private void copyOrderLinesDto(StoreOrder storeOrder) {
        Collection<OrderLine> orderLines = storeOrder.getOrderLines().values();
        for (OrderLine orderLine : orderLines) {
            OrderLineDto orderLineDto = new OrderLineDto(orderLine);
            orderLinesDto.add(orderLineDto);
        }
    }

    private void copyAppliedOffersDto(StoreOrder storeOrder) {
        Map<String, ArrayList<Offer>> appliedOffers = storeOrder.getAppliedOffers();
        appliedOffers.forEach((discountName, offers) -> {
            ArrayList<OfferDto> appliedOffersDto = new ArrayList<>();
            for (Offer offer : offers) {
                OfferDto offerDto = new OfferDto(offer);
                appliedOffersDto.add(offerDto);
            }
            this.appliedOffersDto.put(discountName, appliedOffersDto);
        });
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public int getStorePpk() {
        return storePpk;
    }

    public Collection<OrderLineDto> getOrderLinesDto() {
        return orderLinesDto;
    }

    public Collection<OfferDto> getAppliedOffersDto() {
        Collection<OfferDto> newOffersDto = new ArrayList<>();
        this.appliedOffersDto.forEach((discountName, offersDto) -> {
            newOffersDto.addAll(offersDto);
        });
        return newOffersDto;
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

    public double getDistanceFromCustomer() {
        return distanceFromCustomer;
    }

    public ArrayList<PurchasedItemStoreOrderDto> getPurchasedItemsStoreOrderDto() {
        return purchasedItemsStoreOrderDto;
    }

    public void setPurchasedItemStoreOrderDto(
            ArrayList<PurchasedItemStoreOrderDto> purchasedItemsStoreOrderDto) {
        this.purchasedItemsStoreOrderDto = purchasedItemsStoreOrderDto;
    }
}
