package course.java.sdm.javafx.components.actions.order.summery;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreInfo;
import course.java.sdm.javafx.components.actions.order.summery.stores.OrderSummeryStoresController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.util.Collection;

public class OrderSummeryController extends OrderSummeryData {

    @FXML private BorderPane innerBorderPane;
    @FXML private Label dateValueLabel;
    @FXML private Label customerDetailsValuesLabel;
    @FXML private Label itemsCostValueLabel;
    @FXML private Label deliveryCostValueLabel;
    @FXML private Label totalCostValueLabel;
    @FXML private Button cancelButton;
    @FXML private Button confirmButton;
    @FXML private Label finalActionLabel;

    private static final String CONFIRM_MSG = "Your order was added successfully!";
    private static final String CANCEL_MSG = "Your order was canceled";

    public OrderSummeryController() {
        super();
    }

    @FXML
    private void initialize() {
        dateValueLabel.textProperty().bind(date);
        customerDetailsValuesLabel.textProperty().bind(customerDetails);
        // make bind between isStaticOrder property and checkbox or something like that - with V mark.
        itemsCostValueLabel.textProperty().bind(itemsCost.asString());
        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());
        totalCostValueLabel.textProperty().bind(totalCost.asString());
    }

    @FXML void cancelButtonAction(ActionEvent event) {
        setFinalControls(CANCEL_MSG);
    }

    @FXML void confirmButtonAction(ActionEvent event) {
        if (isStaticOrder.getValue()) {
            businessLogic.createOrder(uiOrderDto.getCustomerId(), uiOrderDto.getDate(),
                    uiOrderDto.getStoreId(), uiOrderDto.getItemsIdsAndQuantities());
        }
        else {
            businessLogic.createOrder(uiOrderDto.getCustomerId(),
                    uiOrderDto.getDate(), uiOrderDto.getItemsIdsAndQuantities());
        }

        setFinalControls(CONFIRM_MSG);
    }

    private void setFinalControls(String msg) {
        cancelButton.setDisable(true);
        confirmButton.setDisable(true);
        finalActionLabel.setVisible(true);
        finalActionLabel.setText(msg);
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
