package course.java.sdm.javafx.components.sdmData.singleOrder;

import course.java.sdm.engine.dto.BasicCustomerDto;
import course.java.sdm.engine.dto.OrderDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Date;

public class OrderData {

    protected SimpleStringProperty title;
    protected SimpleStringProperty date;
    protected SimpleStringProperty customerDetails;
    protected SimpleStringProperty orderCategory;
    protected SimpleFloatProperty itemsCost;
    protected SimpleFloatProperty deliveryCost;
    protected SimpleFloatProperty totalCost;


    public OrderData() {
        title = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        date = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        customerDetails = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        orderCategory = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        itemsCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        deliveryCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        totalCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
    }

    public void setTitle(int id) {
        this.title.set(String.format("Order ID %d", id));
    }

    public void setDate(Date date) {
        this.date.set(UtilsUI.convertDateToString(date));
    }

    private void setCustomerDetails(int id, String name) {
        this.customerDetails.set(String.format("ID %d | %s", id, name));
    }

    private void setOrderCategory(String orderCategory) {
        this.orderCategory.set(orderCategory);
    }

    private void setItemsCost(float itemsCost) {
        this.itemsCost.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(itemsCost));
    }

    private void setDeliveryCost(float deliveryCost) {
        this.deliveryCost.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(deliveryCost));
    }

    private void setTotalCost(float totalCost) {
        this.totalCost.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(totalCost));
    }

    public void setOrderDataValues(OrderDto orderDto) {
        setDate(orderDto.getDate());
        BasicCustomerDto basicCustomerDto = orderDto.getBasicCustomerDto();
        setCustomerDetails(basicCustomerDto.getId(), basicCustomerDto.getName());
        setItemsCost(orderDto.getItemsCost());
        setDeliveryCost(orderDto.getDeliveryCost());
        setTotalCost(orderDto.getTotalCost());
        setOrderCategory(orderDto.getOrderCategory());
        setTitle(orderDto.getId());
    }
}
