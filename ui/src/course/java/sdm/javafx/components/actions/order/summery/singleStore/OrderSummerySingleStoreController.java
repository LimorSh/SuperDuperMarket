package course.java.sdm.javafx.components.actions.order.summery.singleStore;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Collection;

public class OrderSummerySingleStoreController extends OrderSummerySingleStoreData {

    @FXML private Label nameValueLabel;
    @FXML private Label idValueLabel;
    @FXML private Label ppkValueLabel;
    @FXML private Label distanceFromTheCustomerValueLabel;
    @FXML private Label deliveryCostValueLabel;

    @FXML private TableView<OrderSummerySinglePurchasedItemData> purchasedItemsTableView;
    @FXML private TableColumn<OrderSummerySinglePurchasedItemData, Integer> itemIdCol;
    @FXML private TableColumn<OrderSummerySinglePurchasedItemData, String> itemNameCol;
    @FXML private TableColumn<OrderSummerySinglePurchasedItemData, String> itemPurchaseCategoryCol;
    @FXML private TableColumn<OrderSummerySinglePurchasedItemData, Float> itemQuantityCol;
    @FXML private TableColumn<OrderSummerySinglePurchasedItemData, Float> itemPricePerUnitCol;
    @FXML private TableColumn<OrderSummerySinglePurchasedItemData, Float> itemTotalCostCol;
    @FXML private TableColumn<OrderSummerySinglePurchasedItemData, String> itemDiscountCol;

    @FXML
    private void initialize() {
        idValueLabel.textProperty().bind(id.asString());
        nameValueLabel.textProperty().bind(name);
        ppkValueLabel.textProperty().bind(ppk.asString());
        distanceFromTheCustomerValueLabel.textProperty().bind(distanceFromTheCustomer.asString());
        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());
    }

    public void setTableViewData(Collection<OrderSummerySingleStoreItemInfo> orderSummerySingleStoreItemsInfo) {
        if (!orderSummerySingleStoreItemsInfo.isEmpty()) {
            ArrayList<OrderSummerySinglePurchasedItemData> orderSummerySinglePurchasedItemsData = new ArrayList<>();
            for (OrderSummerySingleStoreItemInfo orderSummerySingleStoreItemInfo : orderSummerySingleStoreItemsInfo) {
                OrderSummerySinglePurchasedItemData orderSummerySinglePurchasedItemData =
                        new OrderSummerySinglePurchasedItemData(orderSummerySingleStoreItemInfo);
                orderSummerySinglePurchasedItemsData.add(orderSummerySinglePurchasedItemData);
            }
            final ObservableList<OrderSummerySinglePurchasedItemData> data =
                    FXCollections.observableArrayList(orderSummerySinglePurchasedItemsData);

            itemIdCol.setCellValueFactory(
                    new PropertyValueFactory<>("id")
            );
            itemNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("name")
            );
            itemPurchaseCategoryCol.setCellValueFactory(
                    new PropertyValueFactory<>("purchaseCategory")
            );
            itemQuantityCol.setCellValueFactory(
                    new PropertyValueFactory<>("quantity")
            );
            itemPricePerUnitCol.setCellValueFactory(
                    new PropertyValueFactory<>("pricePerUnit")
            );
            itemTotalCostCol.setCellValueFactory(
                    new PropertyValueFactory<>("totalCost")
            );
            itemDiscountCol.setCellValueFactory(
                    new PropertyValueFactory<>("discount")
            );

            purchasedItemsTableView.setItems(data);
        }
        else {
            // show no store items component!
        }
    }
}
