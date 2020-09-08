package course.java.sdm.javafx.components.actions.order.summery;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import course.java.sdm.javafx.dto.UIOrderDto;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderSummeryData {

    protected SimpleStringProperty customerDetails;
    protected SimpleFloatProperty itemsCost;
    protected SimpleFloatProperty deliveryCost;
    protected SimpleFloatProperty totalCost;
    protected BusinessLogic businessLogic;
    protected UIOrderDto uiOrderDto;

    public OrderSummeryData() {
        customerDetails = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        itemsCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        deliveryCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        totalCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
    }

    private void setCustomerDetails(int id, String name, int xLocation, int yLocation) {
        this.customerDetails.set(String.format("ID %d | %s | Location: (%d,%d)", id,
                name, xLocation, yLocation));
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

    public void setDataValues(OrderSummeryInfo orderSummeryInfo) {
        setCustomerDetails(orderSummeryInfo.getCustomerId(),
                orderSummeryInfo.getCustomerName(), orderSummeryInfo.getCustomerXLocation(),
                orderSummeryInfo.getCustomerYLocation());
        setItemsCost(orderSummeryInfo.getItemsCost());
        setDeliveryCost(orderSummeryInfo.getDeliveryCost());
        setTotalCost(orderSummeryInfo.getTotalCost());
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public void setUiOrderDto(UIOrderDto uiOrderDto) {
        this.uiOrderDto = uiOrderDto;
    }
}
