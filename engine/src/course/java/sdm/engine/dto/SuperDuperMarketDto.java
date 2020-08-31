package course.java.sdm.engine.dto;
import course.java.sdm.engine.engine.Customer;
import course.java.sdm.engine.engine.Item;
import course.java.sdm.engine.engine.Order;
import course.java.sdm.engine.engine.Store;
import java.util.ArrayList;
import java.util.Collection;

public class SuperDuperMarketDto {

    public static Collection<CustomerDto> getCustomersDto(Collection<Customer> customers) {
        Collection<CustomerDto> customersDto = new ArrayList<>();
        for (Customer customer : customers) {
            customersDto.add(new CustomerDto(customer));
        }
        return customersDto;
    }

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
