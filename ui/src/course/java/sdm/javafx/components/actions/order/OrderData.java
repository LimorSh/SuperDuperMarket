package course.java.sdm.javafx.components.actions.order;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import course.java.sdm.javafx.components.actions.order.summery.OrderSummeryInfo;
import course.java.sdm.javafx.dto.UIOrderDto;
import javafx.beans.property.SimpleFloatProperty;

public class OrderData {

    protected final UIOrderDto uiOrderDto;
    protected SimpleFloatProperty deliveryCost;
    protected BusinessLogic businessLogic;
    protected OrderSummeryInfo orderSummeryInfo;

    public OrderData() {
        uiOrderDto = new UIOrderDto();
        this.deliveryCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        orderSummeryInfo = new OrderSummeryInfo();
    }

    public void setDeliveryCost(int storeId, int customerId) {
        float deliveryCost = businessLogic.getDeliveryCost(storeId, customerId);
        this.deliveryCost.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(deliveryCost));
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }
}
