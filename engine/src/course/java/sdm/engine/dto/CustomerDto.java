package course.java.sdm.engine.dto;
import course.java.sdm.engine.engine.Customer;
import course.java.sdm.engine.engine.Order;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerDto {
    private final int id;
    private final String name;
    private final int xLocation;
    private final int yLocation;
    private final Collection<OrderDto> ordersDto;
    private final int numberOfOrders;
    private final float averageItemsCost;
    private final float averageDeliveriesCost;

    public CustomerDto(Customer customer, int numberOfOrders,
                       float averageItemsCost, float averageDeliveriesCost) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.xLocation = customer.getLocation().getCoordinate().x;
        this.yLocation = customer.getLocation().getCoordinate().y;
        ordersDto = new ArrayList<>();
        copyOrdersDto(customer);
        this.numberOfOrders = numberOfOrders;
        this.averageItemsCost = averageItemsCost;
        this.averageDeliveriesCost = averageDeliveriesCost;
    }

    private void copyOrdersDto(Customer customer) {
        Collection<Order> orders = customer.getOrders();
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

    public Collection<OrderDto> getOrdersDto() {
        return ordersDto;
    }

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public float getAverageItemsCost() {
        return averageItemsCost;
    }

    public float getAverageDeliveriesCost() {
        return averageDeliveriesCost;
    }
}
