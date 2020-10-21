package course.java.sdm.javafx.components.sdmData.singleStore.singleDiscount;

import course.java.sdm.engine.dto.OfferDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Collection;

public class singleDiscountController extends DiscountData {

    @FXML private Label nameLabel;
    @FXML private Label itemDetailsLabel;
    @FXML private Label itemQuantityLabel;
    @FXML private Label categoryLabel;

    @FXML private TableView<DiscountOfferData> tableView;
    @FXML private TableColumn<DiscountOfferData, Integer> idCol;
    @FXML private TableColumn<DiscountOfferData, String> nameCol;
    @FXML private TableColumn<DiscountOfferData, String> purchaseCategoryCol;
    @FXML private TableColumn<DiscountOfferData, Double> quantityCol;
    @FXML private TableColumn<DiscountOfferData, Integer> additionalPriceCol;

    @FXML
    private void initialize() {
        nameLabel.textProperty().bind(name);
        itemDetailsLabel.textProperty().bind(itemDetails);
        itemQuantityLabel.textProperty().bind(itemQuantity.asString());
        categoryLabel.textProperty().bind(category);
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
