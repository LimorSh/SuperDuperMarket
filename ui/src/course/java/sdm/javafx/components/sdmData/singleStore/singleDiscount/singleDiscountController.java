package course.java.sdm.javafx.components.sdmData.singleStore.singleDiscount;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class singleDiscountController extends DiscountData {

    @FXML private Label nameLabel;
    @FXML private Label itemDetailsLabel;
    @FXML private Label itemQuantityLabel;
    @FXML private Label categoryLabel;
    @FXML private TableView<?> tableView;
    @FXML private TableColumn<?, ?> idCol;
    @FXML private TableColumn<?, ?> nameCol;
    @FXML private TableColumn<?, ?> purchaseCategoryCol;
    @FXML private TableColumn<?, ?> quantityCol;
    @FXML private TableColumn<?, ?> additionalPriceCol;

    @FXML
    private void initialize() {
        nameLabel.textProperty().bind(name);
        itemDetailsLabel.textProperty().bind(itemDetails);
        itemQuantityLabel.textProperty().bind(itemQuantity.asString());
        categoryLabel.textProperty().bind(category);
    }



}
