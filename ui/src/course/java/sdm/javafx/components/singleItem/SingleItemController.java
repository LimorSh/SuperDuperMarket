package course.java.sdm.javafx.components.singleItem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SingleItemController extends ItemData {

    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    @FXML private Label purchaseCategoryLabel;
    @FXML private Label numberOfStoresSellingTheItemLabel;
    @FXML private Label averagePriceLabel;
    @FXML private Label totalSellsLabel;

    private static final int INIT_INT = -1;
    private static final String INIT_STRING = "";

    public SingleItemController() {
        super(INIT_INT, INIT_STRING, INIT_STRING, INIT_INT, INIT_INT, INIT_INT);
    }

    @FXML
    private void initialize() {
        idLabel.textProperty().bind(id.asString());
        nameLabel.textProperty().bind(name);
        purchaseCategoryLabel.textProperty().bind(purchaseCategory);
        numberOfStoresSellingTheItemLabel.textProperty().bind(numberOfStoresSellingTheItem.asString());
        averagePriceLabel.textProperty().bind(averagePrice.asString());
        totalSellsLabel.textProperty().bind(totalSells.asString());
    }





}