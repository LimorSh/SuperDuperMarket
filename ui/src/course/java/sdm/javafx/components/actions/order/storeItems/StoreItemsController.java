package course.java.sdm.javafx.components.actions.order.storeItems;

import course.java.sdm.engine.dto.ItemWithPriceDto;
import course.java.sdm.javafx.components.actions.order.OrderController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Collection;

public class StoreItemsController extends StoreItemsData {

    @FXML private Pane pane;
    @FXML private TableView<StoreItemData> tableView;
    @FXML private TableColumn<StoreItemData, Integer> itemIdCol;
    @FXML private TableColumn<StoreItemData, String> nameCol;
    @FXML private TableColumn<StoreItemData, String> purchaseCategoryCol;
    @FXML private TableColumn<StoreItemData, String> priceCol;
    @FXML private TextField quantityTextField;
    @FXML private Button addItemButton;
    @FXML private Label quantityLabel;

    private OrderController orderController;

    @FXML
    private void initialize() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                setAddItemsControls();
            }
        });
    }

    @FXML
    void addItemButtonAction(ActionEvent event) {
        StoreItemData storeItemData = tableView.getSelectionModel().getSelectedItem();
        float quantity = Float.parseFloat(quantityTextField.getText());
        int storeItemId = storeItemData.getId();
        updateItemsAndQuantities(storeItemId, quantity);

        int storeId = orderController.getSelectedStoreId();
        float price = businessLogic.getItemPriceInStoreByIds(storeId, storeItemId);

        float cost = quantity * price;
        updateItemsCost(cost);
    }

    private void setAddItemsControls() {
        quantityLabel.setVisible(true);
        quantityTextField.setVisible(true);
        addItemButton.setVisible(true);
        addItemButton.setDisable(false);
    }

    public void setTableViewData(int storeId) {
        Collection<ItemWithPriceDto> itemsWithPriceDto = businessLogic.getItemsWithPriceDto(storeId);
        if (!itemsWithPriceDto.isEmpty()) {
            ArrayList<StoreItemData> storeItemsData = new ArrayList<>();
            for (ItemWithPriceDto itemWithPriceDto : itemsWithPriceDto) {
                StoreItemData storeItemData = new StoreItemData(itemWithPriceDto);
                storeItemsData.add(storeItemData);
            }
            final ObservableList<StoreItemData> data = FXCollections.observableArrayList(storeItemsData);

            itemIdCol.setCellValueFactory(
                    new PropertyValueFactory<StoreItemData,Integer>("id")
            );
            nameCol.setCellValueFactory(
                    new PropertyValueFactory<StoreItemData,String>("name")
            );
            purchaseCategoryCol.setCellValueFactory(
                    new PropertyValueFactory<StoreItemData,String>("purchaseCategory")
            );
            priceCol.setCellValueFactory(
                    new PropertyValueFactory<StoreItemData,String>("price")
            );

            tableView.setItems(data);
        }
        else {
            // show no store items component!
        }
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }
}