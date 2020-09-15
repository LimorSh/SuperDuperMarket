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
        Collection<DiscountDto> discountsDto = getValidDiscountsToShow(storeItemsDtoAndQuantities);

        if (!discountsDto.isEmpty()) {
            for (DiscountDto discountDto : discountsDto) {
                createDiscount(discountDto);
            }
            return true;
        }
        else {
            setAppliedOffersDtoToUiOrderDtoAndUpdateOrderSummeryInfo();
            return false;
        }
    }

    private Collection<DiscountDto> getValidDiscountsToShow(Map<StoreItemDto, Float> storeItemsDtoAndQuantities) {
        Collection<DiscountDto> validDiscountsDto = new ArrayList<>();

        storeItemsDtoAndQuantities.forEach((storeItemDto,purchasedQuantity) -> {
            Collection<DiscountDto> discountsDto = storeItemDto.getDiscountsDto();
            for (DiscountDto discountDto : discountsDto) {
                double neededQuantityToApplyDiscount = discountDto.getStoreItemQuantity();
                if (purchasedQuantity >= neededQuantityToApplyDiscount) {
                    validDiscountsDto.add(discountDto);
                }
            }
        });

        return validDiscountsDto;
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

            singleDiscountControllers.put(singleDiscount, singleDiscountController);
            flowPane.getChildren().add(singleDiscount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkDiscountsExpiration(int itemIdTriggered, double itemQuantity) {
        singleDiscountControllers.forEach((node,singleDiscountController) -> {
            if (singleDiscountController.getItemIdTriggered() == itemIdTriggered) {
                if (singleDiscountController.getRemainderQuantityToApply() < itemQuantity) {
                    flowPane.getChildren().removeAll(node);
                }
                singleDiscountController.updateItemQuantityTriggered(itemQuantity);
            }
        });
    }
}
