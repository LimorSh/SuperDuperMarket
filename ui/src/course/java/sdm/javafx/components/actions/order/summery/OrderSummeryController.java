package course.java.sdm.javafx.components.actions.order.summery;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderSummeryController extends OrderSummeryData {

    @FXML private Label customerDetailsValuesLabel;
    @FXML private Label orderCostValueLabel;
    @FXML private Label deliveryCostValueLabel;
    @FXML private Label totalCostValueLabel;

    public OrderSummeryController() {
        super();
    }

    @FXML
    private void initialize() {
        customerDetailsValuesLabel.textProperty().bind(customerDetails);
        orderCostValueLabel.textProperty().bind(itemsCost.asString());
        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());
        totalCostValueLabel.textProperty().bind(totalCost.asString());
    }
}
