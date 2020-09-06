package course.java.sdm.javafx.components.actions.order.storeItems;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.engine.dto.ItemWithPriceDto;
import course.java.sdm.engine.dto.StoreItemDto;
import course.java.sdm.engine.engine.StoreItem;
import course.java.sdm.javafx.components.actions.order.CustomerInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableColumn;

import java.util.ArrayList;
import java.util.Collection;

public class StoreItemsController {

    @FXML private Pane pane;
    @FXML private TableView<StoreItemData> tableView;
    @FXML private TableColumn<StoreItemData, Integer> itemIdCol;
    @FXML private TableColumn<StoreItemData, String> nameCol;
    @FXML private TableColumn<StoreItemData, String> purchaseCategoryCol;
    @FXML private TableColumn<StoreItemData, String> priceCol;

    public void setTableViewData(Collection<ItemWithPriceDto> itemsWithPriceDto) {
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
}