package course.java.sdm.javafx.components.actions.order.summery;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class OrderSummeryController extends OrderSummeryData {

    @FXML private Label customerDetailsValuesLabel;

    public OrderSummeryController() {
        super();
    }

    @FXML
    private void initialize() {
        customerDetailsValuesLabel.textProperty().bind(customerDetails);
    }
}
