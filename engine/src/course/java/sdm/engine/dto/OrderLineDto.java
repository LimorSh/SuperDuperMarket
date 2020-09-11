package course.java.sdm.engine.dto;

import course.java.sdm.engine.engine.OrderLine;

public class OrderLineDto {

    private final BasicItemDto basicItemDto;
    private final float quantity;
    private final float cost;   //the price of one item/kg in the order
    private final float totalCost;
//    private final boolean discount = false;

    public OrderLineDto(OrderLine orderLine) {
        this.basicItemDto = new BasicItemDto(orderLine.getItem());
        this.quantity = orderLine.getQuantity();
        this.cost = orderLine.getCost();
        this.totalCost = orderLine.getTotalCost();
    }

    public BasicItemDto getBasicItemDto() {
        return basicItemDto;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getCost() {
        return cost;
    }

    public float getTotalCost() {
        return totalCost;
    }
}
