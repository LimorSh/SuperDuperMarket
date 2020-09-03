package course.java.sdm.javafx.components.sdmData.singleCustomer;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
//    protected SimpleIntegerProperty xLocation;
//    protected SimpleIntegerProperty yLocation;
    protected SimpleStringProperty location;


    public CustomerData() {
        this.id = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.name = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.location = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
//        this.xLocation = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
//        this.yLocation = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
    }

    private void setId(int id) {
        this.id.set(id);
    }

    private void setName(String name) {
        this.name.set(name);
    }

//    public void setXLocation(int xLocation) {
//        this.xLocation.set(xLocation);
//    }
//
//    public void setYLocation(int yLocation) {
//        this.yLocation.set(yLocation);
//    }

    public void setLocation(int x, int y) {
        this.location.set(String.format("(%d,%d)", x, y));
    }

    public void setItemDataValues(CustomerDto customerDto) {
        setId(customerDto.getId());
        setName(customerDto.getName());
        setLocation(customerDto.getXLocation(), customerDto.getYLocation());
//        setXLocation(customerDto.getXLocation());
//        setYLocation(customerDto.getYLocation());
    }
}
