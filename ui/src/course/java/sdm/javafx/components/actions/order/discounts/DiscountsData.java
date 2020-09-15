package course.java.sdm.javafx.components.actions.order.discounts;

import course.java.sdm.engine.dto.OfferDto;
import course.java.sdm.javafx.components.actions.order.summery.OrderSummeryInfo;
import course.java.sdm.javafx.dto.UIOrderDto;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class DiscountsData {

    protected OrderSummeryInfo orderSummeryInfo;
    protected UIOrderDto uiOrderDto;
    protected Map<String, Collection<OfferDto>> appliedOffersDto;    //the key is discount name

    private void setOrderSummeryInfo(OrderSummeryInfo orderSummeryInfo) {
        this.orderSummeryInfo = orderSummeryInfo;
    }
    private void setUiOrderDto(UIOrderDto uiOrderDto) {
        this.uiOrderDto = uiOrderDto;
    }

    public void setValuesData(OrderSummeryInfo orderSummeryInfo, UIOrderDto uiOrderDto) {
        setOrderSummeryInfo(orderSummeryInfo);
        setUiOrderDto(uiOrderDto);
        appliedOffersDto = new HashMap<>();
    }

    public void addAppliedOfferDto(String discountName, Collection<OfferDto> appliedOffersDto) {
        this.appliedOffersDto.put(discountName, appliedOffersDto);
    }
}
