package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderDto {
    private final int id;
    private final Date date;
//    private final Store store;
    private final Map<Integer, OrderLineDto> orderLinesDto;
    private final float itemsCost;
    private final float deliveryCost;
    private final int totalItems;
    private final float totalCost;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.itemsCost = order.getItemsCost();
        this.deliveryCost = order.getDeliveryCost();
        this.totalItems = order.getTotalItems();
        this.totalCost = order.getTotalCost();
        this.orderLinesDto = new HashMap<>();
        copyOrderItems(order);
    }

    private void copyOrderItems(Order order) {
        Map<Integer, OrderLine> orderLines = order.getOrderLines();
        orderLines.forEach((itemId,orderLine) ->  {
            OrderLineDto orderLineDto = new OrderLineDto(itemId, orderLine.getQuantity());
            orderLinesDto.put(itemId, orderLineDto);
        });
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Map<Integer, OrderLineDto> getOrderLinesDto() {
        return orderLinesDto;
    }

    public float getItemsCost() {
        return itemsCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public float getTotalCost() {
        return totalCost;
    }
}
