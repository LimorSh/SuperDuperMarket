package course.java.sdm.javafx.components.actions.updateItem;

import course.java.sdm.engine.dto.StoreDto;
import course.java.sdm.javafx.components.actions.info.StoreInfo;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collection;

public class UpdateItemController extends UpdateItemData {

    @FXML private GridPane gridPane;
    @FXML private ComboBox<StoreInfo> chooseStoreComboBox;
    @FXML private RadioButton deleteItemRadioButton;
    @FXML private RadioButton addItemRadioButton;
    @FXML private RadioButton updateItemPriceRadioButton;
    @FXML private Label chooseStoreLabel;
    @FXML private Button confirmButton;

    @FXML private Label selectItemsLabel;
    @FXML private TableView<?> tableView;
    @FXML private TableColumn<?, ?> itemIdCol;
    @FXML private TableColumn<?, ?> nameCol;
    @FXML private TableColumn<?, ?> purchaseCategoryCol;
    @FXML private TableColumn<?, ?> priceCol;

    private final ToggleGroup actionRadioButtonsGroup;

    public UpdateItemController() {
        super();
        actionRadioButtonsGroup =  new ToggleGroup();
    }

    @FXML
    private void initialize() {
//        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());
//
        setActionRadioButtons();
//
//        orderTypeRadioButtonsGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
//            if (orderTypeRadioButtonsGroup.getSelectedToggle() != null) {
//                if (staticOrderRadioButton.isSelected()) {
//                    setOneStoreControls(true);
//                }
//                if (dynamicOrderRadioButton.isSelected()) {
//                    setOneStoreControls(false);
//                    setDeliveryCostLabels(false);
//                    dynamicOrderWasChosen();
//                }
//            }
//        });
    }


    @FXML
    void chooseStoreComboBoxAction(ActionEvent event) {
        storeWasChosen(getSelectedStoreId());
    }

    @FXML
    void confirmButtonAction(ActionEvent event) {

    }

    public int getSelectedStoreId() {
        return chooseStoreComboBox.getValue().getId();
    }

    private void storeWasChosen(int storeId) {
//            StoreDto storeDto = businessLogic.getStoreDto(storeId);
//            Collection<ItemWithPriceDto> itemsWithPriceDto = businessLogic.getItemsWithPriceDto(storeId);
        setTableViewData(storeId);

        selectItemsLabel.setVisible(true);
        gridPane.add(tableView, 1, 4);
    }

    private void setActionRadioButtons() {
        deleteItemRadioButton.setToggleGroup(actionRadioButtonsGroup);
        addItemRadioButton.setToggleGroup(actionRadioButtonsGroup);
        updateItemPriceRadioButton.setToggleGroup(actionRadioButtonsGroup);
    }

    public void setTableViewData(int storeId) {
//        Collection<ItemWithPriceDto> itemsWithPriceDto = businessLogic.getItemsWithPriceDto(storeId);
//        if (!itemsWithPriceDto.isEmpty()) {
//            ArrayList<StoreItemData> storeItemsData = new ArrayList<>();
//            for (ItemWithPriceDto itemWithPriceDto : itemsWithPriceDto) {
//                StoreItemData storeItemData = new StoreItemData(itemWithPriceDto);
//                storeItemsData.add(storeItemData);
//            }
//            final ObservableList<StoreItemData> data = FXCollections.observableArrayList(storeItemsData);
//
//            itemIdCol.setCellValueFactory(
//                    new PropertyValueFactory<>("id")
//            );
//            nameCol.setCellValueFactory(
//                    new PropertyValueFactory<>("name")
//            );
//            purchaseCategoryCol.setCellValueFactory(
//                    new PropertyValueFactory<>("purchaseCategory")
//            );
//            priceCol.setCellValueFactory(
//                    new PropertyValueFactory<>("price")
//            );
//
//            tableView.setItems(data);
//        }
//        else {
//            // show no store items component!
//        }
    }

    public void updateItem() {
        Collection<StoreDto> storesDto = businessLogic.getStoresDto();
        setStores(storesDto);
    }

    public void setStores(Collection<StoreDto> storesDto) {
        if (!storesDto.isEmpty()) {
            ArrayList<StoreInfo> storesInfo = new ArrayList<>();
            for (StoreDto storeDto : storesDto) {
                StoreInfo storeInfo = new StoreInfo(storeDto);
                storesInfo.add(storeInfo);
            }
            chooseStoreComboBox.setItems(FXCollections.observableArrayList(storesInfo));
        }
        else {
            // show no customers component!
        }
    }
}
