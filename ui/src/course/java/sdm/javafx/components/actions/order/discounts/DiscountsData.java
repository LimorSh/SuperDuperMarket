package course.java.sdm.javafx.components.actions.order.discounts;

import course.java.sdm.engine.dto.OfferDto;
import course.java.sdm.javafx.components.actions.order.summery.OrderSummeryInfo;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreInfo;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreItemInfo;
import course.java.sdm.javafx.dto.UIOrderDto;

import java.util.ArrayList;
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

    public void addAppliedOffersDto(String discountName, Collection<OfferDto> appliedOffersDto) {
        this.appliedOffersDto.put(discountName, appliedOffersDto);
    }

    public void addAppliedOfferDto(String discountName, OfferDto appliedOfferDto) {
        Collection<OfferDto> offersDto = new ArrayList<>();
        offersDto.add(appliedOfferDto);
        this.appliedOffersDto.put(discountName, offersDto);
    }

    protected void setAppliedOffersDtoToUiOrderDto() {
        uiOrderDto.setAppliedOffersDto(appliedOffersDto);

        Collection<OrderSummerySingleStoreInfo> orderSummerySingleStoresInfo =
                orderSummeryInfo.getSingleStoresInfo();

        OrderSummerySingleStoreInfo orderSummerySingleStoreInfo =
                orderSummerySingleStoresInfo.stream().findFirst().orElse(null);

        appliedOffersDto.forEach((discountName, offersDto) -> {

            for (OfferDto offerDto : offersDto) {

                OrderSummerySingleStoreItemInfo orderSummerySingleStoreItemInfo =
                        new OrderSummerySingleStoreItemInfo(offerDto);

                assert orderSummerySingleStoreInfo != null;
                orderSummerySingleStoreInfo.addOrderSummerySingleStoreItemsInfo(orderSummerySingleStoreItemInfo);
            }
        });
    }
}
