package course.java.sdm.javafx.components.actions.addItem;

import course.java.sdm.engine.dto.StoreDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collection;

public class AddItemController extends AddItemData{

    @FXML private GridPane gridPane;

    @FXML private Label idMsgLabel;
    @FXML private Label nameMsgLabel;
    @FXML private Label itemInfoMsgLabel;
    @FXML private Label selectStoresLabel;
    @FXML private Label enterPriceLabel;
    @FXML private Label priceMsgLabel;
    @FXML private Label confirmMsgLabel;

    @FXML private TableView<StoreData> tableView;
    @FXML private TableColumn<StoreData, Integer> storeIdCol;
    @FXML private TableColumn<StoreData, String> nameCol;
    @FXML private TableColumn<StoreData, Integer> ppkCol;
    @FXML private TableColumn<StoreData, String> locationCol;

    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField priceTextField;

    @FXML private Button setNewItemInfoButton;
    @FXML private Button addItemToStoreButton;
    @FXML private Button confirmButton;

    @FXML private RadioButton perUnitRadioButton;
    @FXML private RadioButton perWeightRadioButton;

    private final ToggleGroup purchaseCategoryRadioButtonsGroup;


    public AddItemController() {
        super();
        purchaseCategoryRadioButtonsGroup =  new ToggleGroup();
    }

    @FXML
    private void initialize() {
        setPurchaseCategoryRadioButtons();

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                storeWasChosen();
            }
        });
    }

    private void storeWasChosen() {
        enterPriceLabel.setDisable(false);
        priceTextField.setDisable(false);
        addItemToStoreButton.setDisable(false);
    }

    private void setPurchaseCategoryRadioButtons() {
        perUnitRadioButton.setToggleGroup(purchaseCategoryRadioButtonsGroup);
        perWeightRadioButton.setToggleGroup(purchaseCategoryRadioButtonsGroup);
    }

    @FXML
    void setNewItemInfoButtonAction(ActionEvent event) {
        boolean isAllInfoValid = true;

        try {
            itemId = getEnteredId();
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
            purchasedCategory = getChosenPurchaseCategory();
            itemInfoMsgLabel.setText("");
        }
        catch(Exception e) {
            isAllInfoValid = false;
            itemInfoMsgLabel.setStyle("-fx-text-fill: #ff0000;");
            itemInfoMsgLabel.setText(e.getMessage());
        }

        if (isAllInfoValid) {
            itemInfoMsgLabel.setStyle("-fx-text-fill: #0000ff;");
            itemInfoMsgLabel.setText("The item info is valid. Now please select which stores will sell the item:");
            setContinueControls();
        }
    }

    private void setContinueControls() {
        idTextField.setDisable(true);
        nameTextField.setDisable(true);
        perWeightRadioButton.setDisable(true);
        perUnitRadioButton.setDisable(true);
        setNewItemInfoButton.setDisable(true);
        selectStoresLabel.setDisable(false);
        tableView.setDisable(false);
    }

    @FXML
    void addItemToStoreButtonAction(ActionEvent event) {
        int storeId = getSelectedStoreId();
        try {
            float price = getEnteredPrice();

            storeIdsAndPrices.put(storeId, price);
            StoreData storeData = tableView.getSelectionModel().getSelectedItem();
            tableView.getItems().remove(storeData);

            priceMsgLabel.setStyle("-fx-text-fill: #0000ff;");
            priceMsgLabel.setText("The item was added to the store " + storeData.getName() + " successfully!");
            confirmButton.setDisable(false);
        }
        catch(Exception e) {
            priceMsgLabel.setStyle("-fx-text-fill: #ff0000;");
            priceMsgLabel.setText(e.getMessage());
        }
    }

    private int getSelectedStoreId() {
        StoreData storeData = tableView.getSelectionModel().getSelectedItem();
        return storeData.getId();
    }

    @FXML
    void confirmButtonAction(ActionEvent event) {
        businessLogic.createNewItem(itemId, name, purchasedCategory, storeIdsAndPrices);
        finish(ADD_ITEM_SUCCESS);
    }

    private void finish(String msg) {
        priceMsgLabel.setText("");
        itemInfoMsgLabel.setText("");
        confirmButton.setDisable(true);
        addItemToStoreButton.setDisable(true);
        confirmMsgLabel.setVisible(true);
        confirmMsgLabel.setText(msg);
    }

    private int getEnteredId() {
        try {
            int itemId = Integer.parseInt(idTextField.getText());
            businessLogic.validateItemId(itemId);
            return itemId;
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

    private String getChosenPurchaseCategory() {
        if (purchaseCategoryRadioButtonsGroup.getSelectedToggle() != null) {
            if (perUnitRadioButton.isSelected()) {
                return businessLogic.getPurchaseCategoryPerUnitStr();
            }
            if (perWeightRadioButton.isSelected()) {
                return businessLogic.getPurchaseCategoryPerWeightStr();
            }
        }
        else {
            throw new IllegalArgumentException(EMPTY_PURCHASE_CATEGORY_LABEL_TEXT);
        }
        return "";
    }

    private float getEnteredPrice() {
        try {
            return Float.parseFloat(priceTextField.getText());
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException(ITEM_PRICE_MSG_LABEL_TEXT);
        }
    }

    public void setTableViewData() {
        Collection<StoreDto> storesDto = businessLogic.getStoresDto();
        ArrayList<StoreData> storesData = new ArrayList<>();
        for (StoreDto storeDto : storesDto) {
            StoreData storeData = new StoreData(storeDto);
            storesData.add(storeData);
        }
        final ObservableList<StoreData> data = FXCollections.observableArrayList(storesData);

        storeIdCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        ppkCol.setCellValueFactory(
                new PropertyValueFactory<>("ppk")
        );
        locationCol.setCellValueFactory(
                new PropertyValueFactory<>("location")
        );

        tableView.setItems(data);
    }
}
