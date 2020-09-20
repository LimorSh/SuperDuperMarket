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

    @FXML private TextField idTextField;
    @FXML private TextField nameTextField;
    @FXML private TextField locationXTextField;
    @FXML private TextField locationYTextField;
    @FXML private TextField ppkTextField;

    @FXML private TextField priceTextField;
    @FXML private Button addItemButton;
    @FXML private Label priceMsgLabel;

    @FXML private TableView<ItemData> tableView;
    @FXML private TableColumn<ItemData, Integer> itemIdCol;
    @FXML private TableColumn<ItemData, String> nameCol;
    @FXML private TableColumn<ItemData, String> purchaseCategoryCol;

    @FXML private Button confirmButton;
    @FXML private Label confirmMsgLabel;


    @FXML
    private void initialize() {
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                itemWasChosen();
            }
        });
    }

    private void itemWasChosen() {
        addItemButton.setDisable(false);
        priceTextField.setDisable(false);
    }

    @FXML
    void addItemButtonAction(ActionEvent event) {
        int itemId = getSelectedItemId();
        float price = getEnteredPrice();

        itemIdsAndPrices.put(itemId, price);
        // after validation
        confirmButton.setDisable(false);
    }

    @FXML
    void confirmButtonAction(ActionEvent event) {
        int storeId = getEnteredId();
        String name = nameTextField.getText();
        int locationX = getEnteredLocationX();
        int locationY = getEnteredLocationY();
        int ppk = getEnteredPPK();

        businessLogic.createNewStore(storeId,name, locationX, locationY, ppk, itemIdsAndPrices);
        finish(true, ADD_STORE_SUCCESS);
    }

    private void finish(boolean error, String msg) {
        confirmButton.setDisable(true);
        addItemButton.setDisable(true);

        if (error) {
            confirmMsgLabel.setStyle("-fx-text-fill: #ff0000;");
        }
        confirmMsgLabel.setVisible(true);
        confirmMsgLabel.setText(msg);
    }

    private int getEnteredId() {
        try {
            return Integer.parseInt(idTextField.getText());
        }
//        catch (Exception e) {
//            throw new IllegalArgumentException(ID_MSG_LABEL_TEXT);
//        }
        catch (Exception ignore) {
            return 0;
        }
    }

    private int getEnteredLocationX() {
        try {
            return Integer.parseInt(locationXTextField.getText());
        }
//        catch (Exception e) {
//            throw new IllegalArgumentException(LOCATION_COORDINATE_MSG_LABEL_TEXT);
//        }
        catch (Exception ignore) {
            return 0;
        }
    }

    private int getEnteredLocationY() {
        try {
            return Integer.parseInt(locationYTextField.getText());
        }
//        catch (Exception e) {
//            throw new IllegalArgumentException(LOCATION_COORDINATE_MSG_LABEL_TEXT);
//        }
        catch (Exception ignore) {
            return 0;
        }
    }

    private int getEnteredPPK() {
        try {
            return Integer.parseInt(ppkTextField.getText());
        }
//        catch (Exception e) {
//            throw new IllegalArgumentException(PPK_MSG_LABEL_TEXT);
//        }
        catch (Exception ignore) {
            return 0;
        }
    }

    private float getEnteredPrice() {
        try {
            return Float.parseFloat(priceTextField.getText());
        }
//        catch (Exception e) {
//            throw new IllegalArgumentException(ITEM_PRICE_MSG_LABEL_TEXT);
//        }
        catch (Exception ignore) {
            return 0;
        }
    }


    private int getSelectedItemId() {
        ItemData ItemData = tableView.getSelectionModel().getSelectedItem();
        return ItemData.getId();
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
