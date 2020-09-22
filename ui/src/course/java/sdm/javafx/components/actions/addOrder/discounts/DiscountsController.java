package course.java.sdm.javafx.components.actions.addOrder.discounts;

import course.java.sdm.engine.dto.DiscountDto;
import course.java.sdm.engine.dto.StoreItemDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.addOrder.discounts.singleDiscount.SingleDiscountController;
import course.java.sdm.javafx.components.main.controller.SuperDuperMarketController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.*;

public class DiscountsController extends DiscountsData {

    @FXML private FlowPane flowPane;

    private Map<Node, SingleDiscountController> singleDiscountControllers;

    private SuperDuperMarketController superDuperMarketController;

    public void setSuperDuperMarketController(SuperDuperMarketController superDuperMarketController) {
        this.superDuperMarketController = superDuperMarketController;
    }

    @FXML
    void nextButtonAction(ActionEvent event) {
        setAppliedOffersDtoToUiOrderDtoAndUpdateOrderSummeryInfo();
        superDuperMarketController.showOrderSummery(orderSummeryInfo, uiOrderDto);
    }

    public boolean createAllDiscounts(Map<StoreItemDto, Float> storeItemsDtoAndQuantities) {
        singleDiscountControllers = new HashMap<>();
        Map<DiscountDto, Float> discountsDtoAndQuantities = getValidDiscountsToShow(storeItemsDtoAndQuantities);

        if (!discountsDtoAndQuantities.isEmpty()) {
            discountsDtoAndQuantities.forEach(this::createDiscount);
            return true;
        }
        else {
            setAppliedOffersDtoToUiOrderDtoAndUpdateOrderSummeryInfo();
            return false;
        }
    }

    private Map<DiscountDto, Float> getValidDiscountsToShow(Map<StoreItemDto, Float> storeItemsDtoAndQuantities) {
        Map<DiscountDto, Float> validDiscountsDto = new HashMap<>();

        storeItemsDtoAndQuantities.forEach((storeItemDto,purchasedQuantity) -> {
            Collection<DiscountDto> discountsDto = storeItemDto.getDiscountsDto();
            for (DiscountDto discountDto : discountsDto) {
                double neededQuantityToApplyDiscount = discountDto.getStoreItemQuantity();
                if (purchasedQuantity >= neededQuantityToApplyDiscount) {
                    validDiscountsDto.put(discountDto, purchasedQuantity);
                }
            }
        });

        return validDiscountsDto;
    }

    private void createDiscount(DiscountDto discountDto, float purchasedQuantity) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_DISCOUNT_IN_ADD_ORDER_FXML_RESOURCE);
            Node singleDiscount = loader.load();
            SingleDiscountController singleDiscountController = loader.getController();

            singleDiscountController.setDiscountsController(this);
            singleDiscountController.setDataValues(discountDto, purchasedQuantity);
            singleDiscountController.setTableView(discountDto.getOffersDto());

            singleDiscountControllers.put(singleDiscount, singleDiscountController);
            flowPane.getChildren().add(singleDiscount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkDiscountsExpiration(int itemIdTriggered, double itemQuantity) {
        singleDiscountControllers.forEach((node,singleDiscountController) -> {
            if (singleDiscountController.getItemIdTriggered() == itemIdTriggered) {
                singleDiscountController.updateRemainderQuantityToApply(itemQuantity);
                if (singleDiscountController.getRemainderQuantityToApply() < singleDiscountController.getItemQuantity()) {
                    flowPane.getChildren().remove(node);
                }
            }
        });
    }
}
