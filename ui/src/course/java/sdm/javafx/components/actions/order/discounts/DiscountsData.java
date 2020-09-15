package course.java.sdm.javafx.components.actions.order.discounts;

import course.java.sdm.javafx.components.actions.order.summery.OrderSummeryInfo;
import course.java.sdm.javafx.dto.UIOrderDto;

public class DiscountsData {

    protected OrderSummeryInfo orderSummeryInfo;
    protected UIOrderDto uiOrderDto;

    private void setOrderSummeryInfo(OrderSummeryInfo orderSummeryInfo) {
        this.orderSummeryInfo = orderSummeryInfo;
    }
    private void setUiOrderDto(UIOrderDto uiOrderDto) {
        this.uiOrderDto = uiOrderDto;
    }

    public void setValuesData(OrderSummeryInfo orderSummeryInfo, UIOrderDto uiOrderDto) {
        setOrderSummeryInfo(orderSummeryInfo);
        setUiOrderDto(uiOrderDto);
    }
}
