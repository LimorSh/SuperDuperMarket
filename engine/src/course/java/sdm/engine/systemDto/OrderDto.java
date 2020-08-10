package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class OrderDto {
    private final int id;
    private final Date date;
//    private final Store store;
    private final Map<ItemDto, Float> itemsDto;
    private final float itemsCost;
    private final float deliveryCost;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.itemsCost = order.getItemsCost();
        this.deliveryCost = order.getDeliveryCost();
        this.itemsDto = new HashMap<>();
        copyOrderItems(order);
    }

    private void copyOrderItems(Order order) {
        Map<Item, Float> items = order.getItems();

        for (Item item : items.keySet()) {
            itemsDto.put(new ItemDto(item), items.get(item));
        }
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Map<ItemDto, Float> getItemsDto() {
        return itemsDto;
    }

    public float getItemsCost() {
        return itemsCost;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }
}
