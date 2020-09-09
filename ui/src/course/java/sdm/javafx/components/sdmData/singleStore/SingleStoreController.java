package course.java.sdm.javafx.components.sdmData.singleStore;

import course.java.sdm.engine.dto.ItemWithPriceDto;
import course.java.sdm.engine.dto.StoreItemDto;
import course.java.sdm.javafx.components.actions.order.storeItems.StoreItemData;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySinglePurchasedItemData;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreItemInfo;
import course.java.sdm.javafx.components.sdmData.singleStore.SingleStoreItem.SingleStoreItemData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.Collection;

public class SingleStoreController extends StoreData {

    @FXML private Label nameValueLabel;
    @FXML private Label idValueLabel;
    @FXML private Label ppkValueLabel;
    @FXML private Label totalDeliveryRevenueValueLabel;

    @FXML private TableView<SingleStoreItemData> itemsTableView;
    @FXML private TableColumn<SingleStoreItemData, Integer> itemIdCol;
    @FXML private TableColumn<SingleStoreItemData, String> itemNameCol;
    @FXML private TableColumn<SingleStoreItemData, String> itemPurchaseCategoryCol;
    @FXML private TableColumn<SingleStoreItemData, Float> itemPricePerUnitCol;
    @FXML private TableColumn<SingleStoreItemData, Float> itemTotalSoldCol;

    @FXML private TableView<?> ordersTableView;
    @FXML private TableColumn<?, ?> orderDateCol;
    @FXML private TableColumn<?, ?> orderTotalItemsCol;
    @FXML private TableColumn<?, ?> orderTotalItemsCostCol;
    @FXML private TableColumn<?, ?> orderDeliveryCostCol;
    @FXML private TableColumn<?, ?> orderTotalCostCol;

    @FXML private FlowPane discountsFlowPane;

    @FXML
    private void initialize() {
        idValueLabel.textProperty().bind(id.asString());
        nameValueLabel.textProperty().bind(name);
        ppkValueLabel.textProperty().bind(ppk.asString());
        totalDeliveryRevenueValueLabel.textProperty().bind(totalDeliveryRevenue.asString());
    }

    public void setItemsTableView(Collection<StoreItemDto> storeItemsDto) {
        if (!storeItemsDto.isEmpty()) {
            ArrayList<SingleStoreItemData> singleStoreItemsData = new ArrayList<>();
            for (StoreItemDto storeItemDto : storeItemsDto) {
                SingleStoreItemData singleStoreItemData = new SingleStoreItemData(storeItemDto);
                singleStoreItemsData.add(singleStoreItemData);
            }
            final ObservableList<SingleStoreItemData> data = FXCollections.observableArrayList(singleStoreItemsData);

            itemIdCol.setCellValueFactory(
                    new PropertyValueFactory<>("id")
            );
            itemNameCol.setCellValueFactory(
                    new PropertyValueFactory<>("name")
            );
            itemPurchaseCategoryCol.setCellValueFactory(
                    new PropertyValueFactory<>("purchaseCategory")
            );
            itemPricePerUnitCol.setCellValueFactory(
                    new PropertyValueFactory<>("pricePerUnit")
            );
            itemTotalSoldCol.setCellValueFactory(
                    new PropertyValueFactory<>("totalSold")
            );

            itemsTableView.setItems(data);
        }
        else {
            // show no store items component!
        }
    }

}
