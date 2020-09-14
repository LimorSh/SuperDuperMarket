package course.java.sdm.javafx.components.actions.order.discounts;

import course.java.sdm.engine.dto.DiscountDto;
import course.java.sdm.engine.dto.StoreItemDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.order.discounts.singleDiscount.SingleDiscountController;
import course.java.sdm.javafx.components.main.SuperDuperMarketController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;

public class DiscountsController {

    @FXML private FlowPane flowPane;

    private SuperDuperMarketController superDuperMarketController;

    public void createAllDiscounts(Collection<StoreItemDto> storeItemsDto) {
        for (StoreItemDto storeItemDto : storeItemsDto) {
            Collection<DiscountDto> discountsDto = storeItemDto.getDiscountsDto();
            if (!discountsDto.isEmpty()) {
                for (DiscountDto discountDto : discountsDto) {
                    createDiscount(discountDto);
                }
            }
        }
    }

    private void createDiscount(DiscountDto discountDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_DISCOUNT_IN_ADD_ORDER_FXML_RESOURCE);
            Node singleDiscount = loader.load();

            SingleDiscountController singleDiscountController = loader.getController();
            singleDiscountController.setDataValues(discountDto);
            singleDiscountController.setTableView(discountDto.getOffersDto());

            flowPane.getChildren().add(singleDiscount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSuperDuperMarketController(SuperDuperMarketController superDuperMarketController) {
        this.superDuperMarketController = superDuperMarketController;
    }
}
