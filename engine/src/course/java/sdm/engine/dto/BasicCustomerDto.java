package course.java.sdm.engine.dto;

import course.java.sdm.engine.engine.Customer;

public class BasicCustomerDto {

    private final int id;
    private final String name;

    public BasicCustomerDto(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
