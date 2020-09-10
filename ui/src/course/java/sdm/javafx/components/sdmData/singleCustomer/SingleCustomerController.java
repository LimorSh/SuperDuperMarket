package course.java.sdm.javafx.components.sdmData.singleCustomer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SingleCustomerController extends CustomerData{

    @FXML private Label idValueLabel;
    @FXML private Label nameValueLabel;
    @FXML private Label locationValueLabel;
    @FXML private Label numberOfOrdersValueLabel;
    @FXML private Label averageItemsCostValueLabel;
    @FXML private Label averageDeliveriesCostValueLabel;

    @FXML
    private void initialize() {
        idValueLabel.textProperty().bind(id.asString());
        nameValueLabel.textProperty().bind(name);
        locationValueLabel.textProperty().bind(location);
        numberOfOrdersValueLabel.textProperty().bind(numberOfOrders.asString());
        averageItemsCostValueLabel.textProperty().bind(averageItemsCost.asString());
        averageDeliveriesCostValueLabel.textProperty().bind(averageDeliveriesCost.asString());
    }
}