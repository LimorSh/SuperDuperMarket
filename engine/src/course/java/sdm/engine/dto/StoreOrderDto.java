package course.java.sdm.engine.dto;

import course.java.sdm.engine.engine.Offer;
import course.java.sdm.engine.engine.OrderLine;
import course.java.sdm.engine.engine.StoreOrder;

import java.util.*;
import java.util.stream.Collectors;

public class StoreOrderDto {

    private final Date date;
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

    public StoreOrderDto(StoreOrder storeOrder) {
        this.date = storeOrder.getDate();
        this.storeId = storeOrder.getStore().getId();
        this.storeName = storeOrder.getStore().getName();
        this.storePpk = storeOrder.getStore().getPpk();
        this.orderLinesDto = new ArrayList<>();
        copyOrderLinesDto(storeOrder);
        this.appliedOffersDto = new HashMap<>();
        copyAppliedOffersDto(storeOrder);
        this.totalItems = storeOrder.getTotalItems();
        this.itemsCost = storeOrder.getItemsCost();
        this.deliveryCost = storeOrder.getDeliveryCost();
        this.totalCost = storeOrder.getTotalCost();
        this.distanceFromCustomer = storeOrder.getDistanceFromCustomer();
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

    public Date getDate() {
        return date;
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
}
