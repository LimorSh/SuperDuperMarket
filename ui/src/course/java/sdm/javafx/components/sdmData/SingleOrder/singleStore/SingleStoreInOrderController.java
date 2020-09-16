package course.java.sdm.javafx.components.sdmData.SingleOrder.singleStore;

import course.java.sdm.engine.dto.OfferDto;
import course.java.sdm.engine.dto.OrderLineDto;
import course.java.sdm.engine.dto.StoreOrderDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.ArrayList;
import java.util.Collection;

public class SingleStoreInOrderController extends StoreInOrderData {

    @FXML private Label nameValueLabel;
    @FXML private Label idValueLabel;
    @FXML private Label ppkValueLabel;
    @FXML private Label distanceFromTheCustomerValueLabel;
    @FXML private Label deliveryCostValueLabel;

    @FXML private TableView<SinglePurchasedItemData> purchasedItemsTableView;
    @FXML private TableColumn<SinglePurchasedItemData, Integer> itemIdCol;
    @FXML private TableColumn<SinglePurchasedItemData, String> itemNameCol;
    @FXML private TableColumn<SinglePurchasedItemData, String> itemPurchaseCategoryCol;
    @FXML private TableColumn<SinglePurchasedItemData, Float> itemQuantityCol;
    @FXML private TableColumn<SinglePurchasedItemData, Float> itemPricePerUnitCol;
    @FXML private TableColumn<SinglePurchasedItemData, Float> itemTotalCostCol;
    @FXML private TableColumn<SinglePurchasedItemData, String> itemDiscountCol;

    @FXML
    private void initialize() {
        idValueLabel.textProperty().bind(id.asString());
        nameValueLabel.textProperty().bind(name);
        ppkValueLabel.textProperty().bind(ppk.asString());
        distanceFromTheCustomerValueLabel.textProperty().bind(distanceFromTheCustomer.asString());
        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());
    }

    public void setTableViewData(StoreOrderDto storeOrderDto) {
        Collection<OrderLineDto> orderLinesDto = storeOrderDto.getOrderLinesDto();
        Collection<OfferDto> appliedOffersDto = storeOrderDto.getAppliedOffersDto();
        if (!orderLinesDto.isEmpty()) {
            ArrayList<SinglePurchasedItemData> singlePurchasedItemsData = new ArrayList<>();
            for (OrderLineDto orderLineDto : orderLinesDto) {
                SinglePurchasedItemData singlePurchasedItemData =
                        new SinglePurchasedItemData(orderLineDto);
                singlePurchasedItemsData.add(singlePurchasedItemData);
            }
            if (!appliedOffersDto.isEmpty()) {
                for (OfferDto offerDto : appliedOffersDto) {
                    SinglePurchasedItemData singlePurchasedItemData =
                            new SinglePurchasedItemData(offerDto);
                    singlePurchasedItemsData.add(singlePurchasedItemData);
                }
            }
            final ObservableList<SinglePurchasedItemData> data =
                    FXCollections.observableArrayList(singlePurchasedItemsData);

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
