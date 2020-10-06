package course.java.sdm.javafx.components.actions.addStore;

import course.java.sdm.engine.dto.BasicItemDto;
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
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collection;

public class AddStoreController extends  AddStoreData {

    @FXML private GridPane gridPane;

    @FXML private Label idMsgLabel;
    @FXML private Label nameMsgLabel;
    @FXML private Label locationXMsgLabel;
    @FXML private Label locationYMsgLabel;
    @FXML private Label ppkMsgLabel;
    @FXML private Label selectItemsLabel;
    @FXML private Label enterPriceLabel;
    @FXML private Label priceMsgLabel;
    @FXML private Label confirmMsgLabel;
    @FXML private Label storeInfoMsgLabel;

    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField locationXTextField;
    @FXML private TextField locationYTextField;
    @FXML private TextField ppkTextField;
    @FXML private TextField priceTextField;

    @FXML private Button setNewStoreInfoButton;
    @FXML private Button addItemButton;
    @FXML private Button confirmButton;

    @FXML private TableView<ItemData> tableView;
    @FXML private TableColumn<ItemData, Integer> itemIdCol;
    @FXML private TableColumn<ItemData, String> nameCol;
    @FXML private TableColumn<ItemData, String> purchaseCategoryCol;

    @FXML
    private void initialize() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                itemWasChosen();
            }
        });
    }

    private void itemWasChosen() {
        enterPriceLabel.setDisable(false);
        priceTextField.setDisable(false);
        addItemButton.setDisable(false);
    }

    @FXML
    void setNewStoreInfoButtonAction(ActionEvent event) {
        storeInfoMsgLabel.setText("");
        boolean isAllInfoValid = true;

        try {
            storeId = getEnteredId();
            idMsgLabel.setText("");
        }
        catch(Exception e) {
            isAllInfoValid = false;
            idMsgLabel.setText(e.getMessage());
        }
        try {
            name = getEnteredName();
            nameMsgLabel.setText("");
        }
        catch(Exception e) {
            isAllInfoValid = false;
            nameMsgLabel.setText(e.getMessage());
        }
        try {
            locationX = getEnteredLocationCoordinate(locationXTextField);
            locationXMsgLabel.setText("");
        }
        catch(Exception e) {
            isAllInfoValid = false;
            locationXMsgLabel.setText(e.getMessage());
        }

        try {
            locationY = getEnteredLocationCoordinate(locationYTextField);
            locationYMsgLabel.setText("");
        }
        catch(Exception e) {
            isAllInfoValid = false;
            locationYMsgLabel.setText(e.getMessage());
        }

        try {
            ppk = getEnteredPPK();
            ppkMsgLabel.setText("");
        }
        catch(Exception e) {
            isAllInfoValid = false;
            ppkMsgLabel.setText(e.getMessage());
        }

        try {
            businessLogic.validateFreeLocation(locationX, locationY);
            storeInfoMsgLabel.setText("");
        }
        catch(Exception e) {
            isAllInfoValid = false;
            storeInfoMsgLabel.setStyle("-fx-text-fill: #ff0000;");
            storeInfoMsgLabel.setText(e.getMessage());
        }

        if (isAllInfoValid) {
            storeInfoMsgLabel.setStyle("-fx-text-fill: #0000ff;");
            storeInfoMsgLabel.setText("The store info is valid. Now please select the items:");
            setContinueControls();
        }
    }

    private void setContinueControls() {
        idTextField.setDisable(true);
        nameTextField.setDisable(true);
        locationXTextField.setDisable(true);
        locationYTextField.setDisable(true);
        ppkTextField.setDisable(true);
        setNewStoreInfoButton.setDisable(true);
        selectItemsLabel.setDisable(false);
        tableView.setDisable(false);
    }

    @FXML
    void addItemButtonAction(ActionEvent event) {
        int itemId = getSelectedItemId();
        try {
            float price = getEnteredPrice();

            itemIdsAndPrices.put(itemId, price);
            ItemData itemData = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(itemData);

            priceMsgLabel.setStyle("-fx-text-fill: #0000ff;");
            priceMsgLabel.setText("The item " + itemData.getName() + " was added successfully!");
            confirmButton.setDisable(false);
        }
        catch(Exception e) {
            priceMsgLabel.setStyle("-fx-text-fill: #ff0000;");
            priceMsgLabel.setText(e.getMessage());
        }
    }

    @FXML
    void confirmButtonAction(ActionEvent event) {
        businessLogic.createNewStore(storeId,name, locationX, locationY, ppk, itemIdsAndPrices);
        finish(ADD_STORE_SUCCESS);
    }

    private void finish(String msg) {
        priceMsgLabel.setText("");
        storeInfoMsgLabel.setText("");
        confirmButton.setDisable(true);
        addItemButton.setDisable(true);
        confirmMsgLabel.setVisible(true);
        confirmMsgLabel.setText(msg);
    }

    private int getEnteredId() {
        try {
            int storeId = Integer.parseInt(idTextField.getText());
            businessLogic.validateStoreId(storeId);
            return storeId;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(ID_MSG_LABEL_TEXT);
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private String getEnteredName() {
        String name = nameTextField.getText();
        if (name.equals("") || name.chars().allMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException(NAME_MSG_LABEL_TEXT);
        }
        return name;
    }

    private int getEnteredLocationCoordinate(TextField textField) {
        try {
            int coordinate = Integer.parseInt(textField.getText());
            businessLogic.validateCoordinate(coordinate);
            return coordinate;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(LOCATION_COORDINATE_MSG_LABEL_TEXT);
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private int getEnteredPPK() {
        try {
            int ppk = Integer.parseInt(ppkTextField.getText());
            businessLogic.validateStorePpk(ppk);
            return ppk;
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(PPK_MSG_LABEL_TEXT);
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private float getEnteredPrice() {
        try {
            return Float.parseFloat(priceTextField.getText());
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(ITEM_PRICE_MSG_LABEL_TEXT);
        }
    }

    private int getSelectedItemId() {
        ItemData itemData = tableView.getSelectionModel().getSelectedItem();
        return itemData.getId();
    }

    public void setTableViewData() {
        Collection<BasicItemDto> basicItemsDto = businessLogic.getBasicItemsDto();
        ArrayList<ItemData> itemsData = new ArrayList<>();
        for (BasicItemDto basicItemDto : basicItemsDto) {
            ItemData itemData = new ItemData(basicItemDto);
            itemsData.add(itemData);
        }
        final ObservableList<ItemData> data = FXCollections.observableArrayList(itemsData);

        itemIdCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        purchaseCategoryCol.setCellValueFactory(
                new PropertyValueFactory<>("purchaseCategory")
        );

        tableView.setItems(data);
    }
}