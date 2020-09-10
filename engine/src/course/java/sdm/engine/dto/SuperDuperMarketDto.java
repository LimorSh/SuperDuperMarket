package course.java.sdm.engine.dto;
import course.java.sdm.engine.engine.Customer;
import course.java.sdm.engine.engine.Item;
import course.java.sdm.engine.engine.Order;
import course.java.sdm.engine.engine.Store;
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

    public static Collection<BasicItemDto> getBasicItemsDto(Collection<Item> items) {
        Collection<BasicItemDto> basicItemsDto = new ArrayList<>();
        for (Item item : items) {
            basicItemsDto.add(new BasicItemDto(item));
        }
        return basicItemsDto;
    }

    public static Collection<BasicCustomerDto> getBasicCustomersDto(Collection<Customer> customers) {
        Collection<BasicCustomerDto> basicCustomersDto = new ArrayList<>();
        for (Customer customer : customers) {
            basicCustomersDto.add(new BasicCustomerDto(customer));
        }
        return basicCustomersDto;
    }

    public static Collection<OrderDto> getOrdersDto(Collection<Order> orders) {
        Collection<OrderDto> ordersDto = new ArrayList<>();
        for (Order order : orders) {
            ordersDto.add(new OrderDto(order));
        }
        return ordersDto;
    }
}
