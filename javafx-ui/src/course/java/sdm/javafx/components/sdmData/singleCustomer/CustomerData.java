package course.java.sdm.javafx.components.sdmData.singleCustomer;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleStringProperty location;
    protected SimpleIntegerProperty numberOfOrders;
    protected SimpleFloatProperty averageItemsCost;
    protected SimpleFloatProperty averageDeliveriesCost;

    public CustomerData() {
        this.id = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.name = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.location = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.numberOfOrders = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.averageItemsCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        this.averageDeliveriesCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
    }

    private void setId(int id) {
        this.id.set(id);
    }

    private void setName(String name) {
        this.name.set(name);
    }

    public void setLocation(int x, int y) {
        this.location.set(String.format("(%d,%d)", x, y));
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders.set(numberOfOrders);
    }

    public void setAverageItemsCost(float averageItemsCost) {
        this.averageItemsCost.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(averageItemsCost));
    }

    public void setAverageDeliveriesCost(float averageDeliveriesCost) {
        this.averageDeliveriesCost.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(averageDeliveriesCost));
    }

    public void setCustomerDataValues(CustomerDto customerDto) {
        setId(customerDto.getId());
        setName(customerDto.getName());
        setLocation(customerDto.getXLocation(), customerDto.getYLocation());
        setNumberOfOrders(customerDto.getNumberOfOrders());
        setAverageItemsCost(customerDto.getAverageItemsCost());
        setAverageDeliveriesCost(customerDto.getAverageDeliveriesCost());
    }
}
