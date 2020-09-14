package course.java.sdm.engine.dto;
import course.java.sdm.engine.engine.Order;
import course.java.sdm.engine.engine.Store;
import course.java.sdm.engine.engine.StoreItem;
import java.util.ArrayList;
import java.util.Collection;

public class StoreDto {

    private final int id;
    private final String name;
    private final int ppk;
    private final float totalDeliveriesRevenue;
    private final int xLocation;
    private final int yLocation;
    private final boolean hasDiscounts;
    private final Collection<StoreItemDto> storeItemsDto;
    private final Collection<OrderDto> ordersDto;

    public StoreDto(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.ppk = store.getPpk();
        this.totalDeliveriesRevenue = store.getTotalDeliveriesRevenue();
        this.xLocation = store.getLocation().getCoordinate().x;
        this.yLocation = store.getLocation().getCoordinate().y;
        this.hasDiscounts = store.hasDiscounts();
        storeItemsDto = new ArrayList<>();
        ordersDto = new ArrayList<>();
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

    public int getPpk() {
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

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public boolean getHasDiscounts() {
        return hasDiscounts;
    }
}


