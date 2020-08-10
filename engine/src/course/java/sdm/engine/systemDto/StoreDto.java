package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.Order;
import course.java.sdm.engine.Store;
import course.java.sdm.engine.StoreItem;

import java.util.ArrayList;
import java.util.Collection;

public class StoreDto {
    private final int id;
    private final String name;
    private final float ppk;
    private final float totalDeliveriesRevenue;
    private final Collection<StoreItemDto> storeItemsDto;
    private Collection<OrderDto> ordersDto;

    public StoreDto(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.ppk = store.getPpk();
        this.totalDeliveriesRevenue = store.getTotalDeliveriesRevenue();
        storeItemsDto = new ArrayList<>();
        copyStoreItemsDto(store);
        copyOrdersDto(store);
    }

    private void copyStoreItemsDto(Store store) {
        Collection<StoreItem> storeItems = store.getStoreItems();
        for (StoreItem storeItem : storeItems) {
            StoreItemDto storeItemDto = new StoreItemDto(storeItem);
            storeItemsDto.add(storeItemDto);
        }
    }

    private void copyOrdersDto(Store store) {
        Collection<Order> orders = store.getOrders();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto(order);
            ordersDto.add(orderDto);
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPpk() {
        return ppk;
    }

    public float getTotalDeliveriesRevenue() {
        return totalDeliveriesRevenue;
    }

    public Collection<StoreItemDto> getStoreItemsDto() {
        return storeItemsDto;
    }

    public Collection<OrderDto> getOrdersDto() {
        return ordersDto;
    }
}


