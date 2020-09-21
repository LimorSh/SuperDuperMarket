package course.java.sdm.javafx.components.actions.addOrder.summery;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import course.java.sdm.javafx.dto.UIOrderDto;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;

public class OrderSummeryData {

    protected SimpleStringProperty date;
    protected SimpleStringProperty customerDetails;
    protected SimpleStringProperty orderCategory;
    protected SimpleFloatProperty itemsCost;
    protected SimpleFloatProperty deliveryCost;
    protected SimpleFloatProperty totalCost;

    protected BusinessLogic businessLogic;
    protected UIOrderDto uiOrderDto;
    protected boolean isStaticOrder;

    protected static final String CONFIRM_MSG = "Your order was added successfully!";
    protected static final String CANCEL_MSG = "Your order was canceled";


    public OrderSummeryData() {
        date = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        customerDetails = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        orderCategory = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        itemsCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        deliveryCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        totalCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
    }

    public void setDate(LocalDate localDate) {
        this.date.set(UtilsUI.convertLocalDateToString(localDate));
    }

    private void setCustomerDetails(int id, String name, int xLocation, int yLocation) {
        this.customerDetails.set(String.format("ID %d | %s | Location: (%d,%d)", id,
                name, xLocation, yLocation));
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

    private void setIsStaticOrder(boolean value) {
        this.isStaticOrder = value;
    }

    public void setDataValues(OrderSummeryInfo orderSummeryInfo) {
        setDate(orderSummeryInfo.getDate());
        setCustomerDetails(orderSummeryInfo.getCustomerId(),
                orderSummeryInfo.getCustomerName(), orderSummeryInfo.getCustomerXLocation(),
                orderSummeryInfo.getCustomerYLocation());
        setItemsCost(orderSummeryInfo.getItemsCost());
        setDeliveryCost(orderSummeryInfo.getDeliveryCost());
        setTotalCost(orderSummeryInfo.getTotalCost());

        setIsStaticOrder(orderSummeryInfo.getIsStaticOrder());
        if (isStaticOrder) {
            setOrderCategory(SuperDuperMarketConstants.STATIC_ORDER_CATEGORY);
        }
        else {
            setOrderCategory(SuperDuperMarketConstants.DYNAMIC_ORDER_CATEGORY);
        }
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public void setUiOrderDto(UIOrderDto uiOrderDto) {
        this.uiOrderDto = uiOrderDto;
    }
}
