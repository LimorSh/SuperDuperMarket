package course.java.sdm.javafx.components.actions.order.discounts;

import course.java.sdm.engine.dto.DiscountDto;
import course.java.sdm.engine.dto.StoreItemDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.order.discounts.singleDiscount.SingleDiscountController;
import course.java.sdm.javafx.components.main.SuperDuperMarketController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class DiscountsController extends DiscountsData {

    @FXML private FlowPane flowPane;

    private SuperDuperMarketController superDuperMarketController;

    public void setSuperDuperMarketController(SuperDuperMarketController superDuperMarketController) {
        this.superDuperMarketController = superDuperMarketController;
    }

    @FXML
    void nextButtonAction(ActionEvent event) {
        setAppliedOffersDtoToUiOrderDto();
        superDuperMarketController.showOrderSummery(orderSummeryInfo, uiOrderDto);
    }

    private void setAppliedOffersDtoToUiOrderDto() {
        uiOrderDto.setAppliedOffersDto(appliedOffersDto);
    }

    public boolean createAllDiscounts(Map<StoreItemDto, Float> storeItemsDtoAndQuantities) {
        Collection<DiscountDto> discountsDto = getDeserveDiscounts(storeItemsDtoAndQuantities);

        if (!discountsDto.isEmpty()) {
            for (DiscountDto discountDto : discountsDto) {
                createDiscount(discountDto);
            }
            return true;
        }
        else {
            setAppliedOffersDtoToUiOrderDto();
            return false;
        }
    }

    private Collection<DiscountDto> getDeserveDiscounts(Map<StoreItemDto, Float> storeItemsDtoAndQuantities) {
        Collection<DiscountDto> deserveDiscountsDto = new ArrayList<>();

        storeItemsDtoAndQuantities.forEach((storeItemDto,quantity) -> {
            Collection<DiscountDto> discountsDto = storeItemDto.getDiscountsDto();
            for (DiscountDto discountDto : discountsDto) {
                double neededQuantity = discountDto.getStoreItemQuantity();
                if (quantity >= neededQuantity) {
                    deserveDiscountsDto.add(discountDto);
                }
            }
        });

        return deserveDiscountsDto;
    }

    private void createDiscount(DiscountDto discountDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_DISCOUNT_IN_ADD_ORDER_FXML_RESOURCE);
            Node singleDiscount = loader.load();
            SingleDiscountController singleDiscountController = loader.getController();

            singleDiscountController.setDiscountsController(this);
            singleDiscountController.setDataValues(discountDto);
            singleDiscountController.setTableView(discountDto.getOffersDto());

            flowPane.getChildren().add(singleDiscount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
