package course.java.sdm.engine.systemDto;

import course.java.sdm.engine.Item;
import course.java.sdm.engine.Order;
import course.java.sdm.engine.Store;
import java.util.ArrayList;
import java.util.Collection;


public class SuperDuperMarketDto {

    public static Collection<StoreDto> getStoresDto(Collection<Store> stores) {
        Collection<StoreDto> storesDto = new ArrayList<>();
        for (Store store : stores) {
            storesDto.add(new StoreDto(store));
        }
        return storesDto;
    }

    public static Collection<ItemDto> getItemsDto(Collection<Item> items) {
        Collection<ItemDto> itemsDto = new ArrayList<>();
        for (Item item : items) {
            itemsDto.add(new ItemDto(item));
        }
        return itemsDto;
    }

    public static Collection<OrderDto> getOrdersDto(Collection<Order> orders) {
        Collection<OrderDto> ordersDto = new ArrayList<>();
        for (Order order : orders) {
            ordersDto.add(new OrderDto(order));
        }
        return ordersDto;
    }
}
