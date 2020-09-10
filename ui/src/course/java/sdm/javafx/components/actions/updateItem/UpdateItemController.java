package course.java.sdm.javafx.components.actions.updateItem;

import course.java.sdm.engine.dto.ItemWithPriceDto;
import course.java.sdm.engine.dto.StoreDto;
import course.java.sdm.javafx.components.actions.info.StoreInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collection;

public class UpdateItemController extends UpdateItemData {

    @FXML private GridPane gridPane;
    @FXML private ComboBox<StoreInfo> chooseStoreComboBox;
    @FXML private RadioButton deleteItemRadioButton;
    @FXML private RadioButton addItemRadioButton;
    @FXML private RadioButton updateItemPriceRadioButton;
    @FXML private Button confirmButton;
    @FXML private Label msgLabel;
    @FXML private TextField priceTextField;
    @FXML private Label enterPriceLabel;

    @FXML private Label selectItemLabel;
    @FXML private TableView<StoreItemData> tableView;
    @FXML private TableColumn<StoreItemData, Integer> itemIdCol;
    @FXML private TableColumn<StoreItemData, String> nameCol;
    @FXML private TableColumn<StoreItemData, String> purchaseCategoryCol;
    @FXML private TableColumn<StoreItemData, String> priceCol;

    private final ToggleGroup actionRadioButtonsGroup;

    private static final String DELETE_ITEM = "Delete Item";
    private static final String ADD_ITEM = "Add Item";
    private static final String UPDATE_PRICE = "Update Price";
    private static final String DELETE_ITEM_SUCCESS = "The item was deleted from the store successfully!";
    private static final String ADD_ITEM_SUCCESS = "The item was added to the store successfully!";
    private static final String UPDATE_ITEM_SUCCESS = "The item price was updated successfully!";


    public UpdateItemController() {
        super();
        actionRadioButtonsGroup =  new ToggleGroup();
    }

    @FXML
    private void initialize() {
//        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());

        setActionRadioButtons();

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                itemWasChosen();
            }
        });

        actionRadioButtonsGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (actionRadioButtonsGroup.getSelectedToggle() != null) {
                selectItemLabel.setVisible(true);
                tableView.setDisable(false);

                if (deleteItemRadioButton.isSelected()) {
                    setDeleteItemControls();
                }
                if (addItemRadioButton.isSelected()) {
                    setAddItemControls();
                }
                if (updateItemPriceRadioButton.isSelected()) {
                    setUpdateItemPriceControls();
                }
            }
        });
    }

    private void itemWasChosen() {
        if (actionRadioButtonsGroup.getSelectedToggle().isSelected()) {
            if (!deleteItemRadioButton.isSelected()) {
                showPriceControls(true);
                setPriceControls(false);
            }
            confirmButton.setDisable(false);
        }
    }

    @FXML
    void chooseStoreComboBoxAction(ActionEvent event) {
        setActionRadioButtonsDisable(false);
        showTableView();
    }

    @FXML
    void confirmButtonAction(ActionEvent event) {
        if (actionRadioButtonsGroup.getSelectedToggle().isSelected()) {
            int itemId = getSelectedItemId();
            int storeId = getSelectedStoreId();

            try {
                if (deleteItemRadioButton.isSelected()) {
                    businessLogic.deleteItemFromStore(itemId, storeId);
                    finish(false, DELETE_ITEM_SUCCESS);
                }
                if (addItemRadioButton.isSelected()) {
                    businessLogic.addItemToStore(itemId, getEnteredPrice(), storeId);
                    finish(false, ADD_ITEM_SUCCESS);
                }
                if (updateItemPriceRadioButton.isSelected()) {
                    businessLogic.updateItemPriceInStore(itemId, getEnteredPrice(), storeId);
                    finish(false, UPDATE_ITEM_SUCCESS);
                }
            }
            catch (Exception e) {
                finish(true, e.getMessage());
            }
        }
    }

    private void finish(boolean error, String msg) {
        chooseStoreComboBox.setDisable(true);
        setActionRadioButtonsDisable(true);
        tableView.setDisable(true);
        confirmButton.setDisable(true);
        setPriceControls(true);

        if (error) {
            msgLabel.setStyle("-fx-text-fill: #ff0000;");
        }
        msgLabel.setVisible(true);
        msgLabel.setText(msg);
    }

    private void setActionRadioButtonsDisable(boolean value) {
        deleteItemRadioButton.setDisable(value);
        addItemRadioButton.setDisable(value);
        updateItemPriceRadioButton.setDisable(value);
    }

    private float getEnteredPrice() {
        try {
            return Float.parseFloat(priceTextField.getText());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("The price you entered is not valid.");
        }
    }

    private void setDeleteItemControls() {
        showConfirmButton(DELETE_ITEM);
        showPriceControls(false);
    }

    private void setAddItemControls() {
        showConfirmButton(ADD_ITEM);
        showPriceControls(true);
    }

    private void setUpdateItemPriceControls() {
        showConfirmButton(UPDATE_PRICE);
        showPriceControls(true);
    }

    private void showConfirmButton(String buttonText) {
        confirmButton.setText(buttonText);
        confirmButton.setVisible(true);
    }

    private void showPriceControls(boolean value) {
        enterPriceLabel.setVisible(value);
        priceTextField.setVisible(value);
    }

    private void setPriceControls(boolean value) {
        enterPriceLabel.setDisable(value);
        priceTextField.setDisable(value);
    }

    public int getSelectedStoreId() {
        return chooseStoreComboBox.getValue().getId();
    }

    private int getSelectedItemId() {
        StoreItemData storeItemData = tableView.getSelectionModel().getSelectedItem();
        return storeItemData.getId();
    }

    private void showTableView() {
        setTableViewData(getSelectedStoreId());
        tableView.setVisible(true);
    }

    private void setActionRadioButtons() {
        deleteItemRadioButton.setToggleGroup(actionRadioButtonsGroup);
        addItemRadioButton.setToggleGroup(actionRadioButtonsGroup);
        updateItemPriceRadioButton.setToggleGroup(actionRadioButtonsGroup);
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
                    new PropertyValueFactory<>("id")
            );
            nameCol.setCellValueFactory(
                    new PropertyValueFactory<>("name")
            );
            purchaseCategoryCol.setCellValueFactory(
                    new PropertyValueFactory<>("purchaseCategory")
            );
            priceCol.setCellValueFactory(
                    new PropertyValueFactory<>("price")
            );

            tableView.setItems(data);
        }
        else {
            // show no store items component!
        }
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
