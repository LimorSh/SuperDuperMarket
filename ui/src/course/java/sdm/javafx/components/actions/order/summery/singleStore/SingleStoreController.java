package course.java.sdm.javafx.components.actions.order.summery.singleStore;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public class SingleStoreController extends SingleStoreData {

    @FXML private Label nameValueLabel;
    @FXML private Label idValueLabel;
    @FXML private Label ppkValueLabel;
    @FXML private Label distanceFromTheCustomerValueLabel;
    @FXML private Label deliveryCostValueLabel;
    @FXML private TableView<?> purchasedItemsTableView;

    @FXML
    private void initialize() {
        idValueLabel.textProperty().bind(id.asString());
        nameValueLabel.textProperty().bind(name);
        ppkValueLabel.textProperty().bind(ppk.asString());
        distanceFromTheCustomerValueLabel.textProperty().bind(distanceFromTheCustomer.asString());
        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());
    }
}
