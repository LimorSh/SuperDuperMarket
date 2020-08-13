package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.*;

import java.util.Date;

public class OrderDto {
    private final int id;
    private final Date date;
    private final int storeId;
    private final String storeName;
    private final int totalItemsTypes;
    private final int totalItems;
//    private final Map<Integer, OrderLineDto> orderLinesDto;
    private final float itemsCost;
    private final float deliveryCost;
    private final float totalCost;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        Store store = order.getStore();
        this.storeId = store.getId();
        this.storeName = store.getName();
        this.totalItemsTypes = order.getTotalItemsTypes();
        this.totalCost = order.getTotalCost();
        this.itemsCost = order.getItemsCost();
        this.deliveryCost = order.getDeliveryCost();
        this.totalItems = order.getTotalItems();
//        this.orderLinesDto = new HashMap<>();
//        copyOrderItems(order);
    }

//    private void copyOrderItems(Order order) {
//        Map<Integer, OrderLine> orderLines = order.getOrderLines();
//        orderLines.forEach((itemId,orderLine) ->  {
//            OrderLineDto orderLineDto = new OrderLineDto(itemId, orderLine.getQuantity());
//            orderLinesDto.put(itemId, orderLineDto);
//        });
//    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

//    public Map<Integer, OrderLineDto> getOrderLinesDto() {
//        return orderLinesDto;
//    }

    public float getItemsCost() {
        return itemsCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public int getTotalItemsTypes() {
        return totalItemsTypes;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }
}
