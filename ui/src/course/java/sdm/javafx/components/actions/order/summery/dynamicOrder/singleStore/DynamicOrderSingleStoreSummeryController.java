package course.java.sdm.javafx.components.actions.order.summery.dynamicOrder.singleStore;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DynamicOrderSingleStoreSummeryController extends DynamicOrderStoreSummeryData{

    @FXML private Label nameLabel;
    @FXML private Label idValueLabel;
    @FXML private Label locationValueLabel;
    @FXML private Label ppkValueLabel;
    @FXML private Label distanceValueLabel;
    @FXML private Label deliveryCostValueLabel;
    @FXML private Label numberOfPurchasedItemsValueLabel;
    @FXML private Label totalItemsCostValueLabel;

    @FXML
    private void initialize() {
        idValueLabel.textProperty().bind(id.asString());
        nameLabel.textProperty().bind(name);
        locationValueLabel.textProperty().bind(location);
        ppkValueLabel.textProperty().bind(ppk.asString());
        distanceValueLabel.textProperty().bind(distance.asString());
        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());
        numberOfPurchasedItemsValueLabel.textProperty().bind(numberOfDifferentPurchasedItems.asString());
        totalItemsCostValueLabel.textProperty().bind(totalItemsCost.asString());
    }
}
