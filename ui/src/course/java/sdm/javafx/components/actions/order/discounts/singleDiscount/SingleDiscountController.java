package course.java.sdm.javafx.components.actions.order.discounts.singleDiscount;

import course.java.sdm.engine.Constants;
import course.java.sdm.engine.dto.OfferDto;
import course.java.sdm.javafx.components.actions.order.discounts.DiscountsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Collection;

public class SingleDiscountController extends DiscountData {

    @FXML private Label nameLabel;
    @FXML private Label itemDetailsLabel;
    @FXML private Label itemQuantityLabel;
    @FXML private Label categoryLabel;

    @FXML private Button applyDiscountButton;

    @FXML private TableView<DiscountOfferData> tableView;
    @FXML private TableColumn<DiscountOfferData, Integer> idCol;
    @FXML private TableColumn<DiscountOfferData, String> nameCol;
    @FXML private TableColumn<DiscountOfferData, String> purchaseCategoryCol;
    @FXML private TableColumn<DiscountOfferData, Double> quantityCol;
    @FXML private TableColumn<DiscountOfferData, Integer> additionalPriceCol;

    private DiscountsController discountsController;

    public void setDiscountsController(DiscountsController discountsController) {
        this.discountsController = discountsController;
    }

    @FXML
    private void initialize() {
        nameLabel.textProperty().bind(name);
        itemDetailsLabel.textProperty().bind(itemDetails);
        itemQuantityLabel.textProperty().bind(itemQuantity.asString());
        categoryLabel.textProperty().bind(category);

        applyDiscountButton.disableProperty().bindBidirectional(isOneOfDiscountCategory);

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setApplyButtonEnableForOneOfDiscountCategory();
            }
        });
    }

    private void setApplyButtonEnableForOneOfDiscountCategory() {
        if (isOneOfDiscountCategory.getValue()) {
            setApplyDiscountButtonEnable();
        }
    }

    private void setApplyDiscountButtonEnable() {
        applyDiscountButton.setDisable(false);
    }

    @FXML
    void applyDiscountButtonAction(ActionEvent event) {
        if (categoryStr.equalsIgnoreCase(Constants.DISCOUNT_CATEGORY_ONE_OF)) {
            DiscountOfferData discountOfferData = tableView.getSelectionModel().getSelectedItem();
            OfferDto offerDto = getOfferDtoByStoreItemId(discountOfferData.getStoreItemId());
            discountsController.addAppliedOfferDto(getName(), offerDto);
        }
        else {
            discountsController.addAppliedOffersDto(getName(), getOffersDto());
        }
    }

    public void setTableView(Collection<OfferDto> offersDto) {
        if (!offersDto.isEmpty()) {
            ArrayList<DiscountOfferData> discountOffersData = new ArrayList<>();
            for (OfferDto offerDto : offersDto) {
                DiscountOfferData discountOfferData = new DiscountOfferData(offerDto);
                discountOffersData.add(discountOfferData);
            }
            final ObservableList<DiscountOfferData> data = FXCollections.observableArrayList(discountOffersData);

            idCol.setCellValueFactory(
                    new PropertyValueFactory<>("storeItemId")
            );
            nameCol.setCellValueFactory(
                    new PropertyValueFactory<>("storeItemName")
            );
            purchaseCategoryCol.setCellValueFactory(
                    new PropertyValueFactory<>("storeItemPurchaseCategory")
            );
            quantityCol.setCellValueFactory(
                    new PropertyValueFactory<>("quantity")
            );
            additionalPriceCol.setCellValueFactory(
                    new PropertyValueFactory<>("additionalPrice")
            );

            tableView.setItems(data);
        }
        else {
            // show no store orders component!
        }
    }
}

