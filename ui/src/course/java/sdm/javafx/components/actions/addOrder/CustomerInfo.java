package course.java.sdm.javafx.components.actions.addOrder;

import course.java.sdm.engine.dto.CustomerDto;

public class CustomerInfo {

    private final int id;
    private final String name;

    public CustomerInfo(CustomerDto customerDto) {
        this.id = customerDto.getId();
        this.name = customerDto.getName();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("ID %d: %s", id, name);
    }
}
