package course.java.sdm.javafx.components.singleCustomer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SingleCustomerController extends CustomerData{

    @FXML private Label idLabel;
    @FXML private Label nameLabel;
    @FXML private Label locationLabel;
    @FXML private Label totalOrdersLabel;
    @FXML private Label averageOrdersCostLabel;
    @FXML private Label averageDeliveriesCostLabel;

    @FXML
    private void initialize() {
        idLabel.textProperty().bind(id.asString());
        nameLabel.textProperty().bind(name);
        locationLabel.textProperty().bind(location);
//        totalOrdersLabel.textProperty().bind(totalOrders.asString());
//        averageOrdersCostLabel.textProperty().bind(averageOrdersCost.asString());
//        averageDeliveriesCostLabel.textProperty().bind(averageDeliveriesCost.asString());
    }

}