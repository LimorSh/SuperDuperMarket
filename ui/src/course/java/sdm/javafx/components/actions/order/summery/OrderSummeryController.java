package course.java.sdm.javafx.components.actions.order.summery;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreInfo;
import course.java.sdm.javafx.components.actions.order.summery.stores.OrderSummeryStoresController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Collection;

public class OrderSummeryController extends OrderSummeryData {

    @FXML private BorderPane innerBorderPane;
    @FXML private Label customerDetailsValuesLabel;
    @FXML private Label itemsCostValueLabel;
    @FXML private Label deliveryCostValueLabel;
    @FXML private Label totalCostValueLabel;

    public OrderSummeryController() {
        super();
    }

    @FXML
    private void initialize() {
        customerDetailsValuesLabel.textProperty().bind(customerDetails);
        itemsCostValueLabel.textProperty().bind(itemsCost.asString());
        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());
        totalCostValueLabel.textProperty().bind(totalCost.asString());
    }

    public void showStores(Collection<OrderSummerySingleStoreInfo> orderSummerySingleStoresInfo) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ORDER_SUMMERY_STORES_FXML_RESOURCE);
            Node stores = loader.load();
            OrderSummeryStoresController orderSummeryStoresController = loader.getController();

            innerBorderPane.setCenter(stores);
            orderSummeryStoresController.createAllStores(orderSummerySingleStoresInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
