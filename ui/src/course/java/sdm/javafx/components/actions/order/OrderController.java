package course.java.sdm.javafx.components.actions.order;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.engine.dto.ItemWithPriceDto;
import course.java.sdm.engine.dto.StoreDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.order.staticOrder.StoreInfo;
import course.java.sdm.javafx.components.actions.order.storeItems.StoreItemsController;
import course.java.sdm.javafx.components.actions.order.storeItems.StoreItemsData;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class OrderController extends OrderData {

    @FXML private GridPane gridPane;
    @FXML private ComboBox<CustomerInfo> chooseCustomerComboBox;
    @FXML private ComboBox<StoreInfo> chooseStoreComboBox;
    @FXML private Label chooseStoreLabel;
    @FXML private Button confirmOrderButton;
    @FXML private DatePicker datePicker;
    @FXML private RadioButton staticOrderRadioButton;
    @FXML private RadioButton dynamicOrderRadioButton;
    @FXML private Label deliveryCostFieldLabel;
    @FXML private Label deliveryCostValueLabel;
    @FXML private Label selectItemsLabel;

    private final Collection<Node> oneStoreItemsNodes;
//    Collection<Node> bestCartItemsNodes;
    private final ToggleGroup orderTypeRadioButtonsGroup;

    private StoreItemsController storeItemsController;


    public OrderController() {
        super();
        oneStoreItemsNodes = new ArrayList<>();
//        bestCartItemsNodes = new ArrayList<>();
        orderTypeRadioButtonsGroup =  new ToggleGroup();
    }

    @FXML
    private void initialize() {
        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());

        setOrderTypeRadioButtons();

        orderTypeRadioButtonsGroup.selectedToggleProperty().addListener((ov, old_toggle, new_toggle) -> {
            if (orderTypeRadioButtonsGroup.getSelectedToggle() != null) {
                if (staticOrderRadioButton.isSelected()) {
                    setOneStoreControls(true);
                }
                if (dynamicOrderRadioButton.isSelected()) {
                    setOneStoreControls(false);
                    setDeliveryCostLabels(false);
                    dynamicOrderWasChosen();
                }
            }
        });
    }

    @FXML
    void chooseStoreComboBoxAction(ActionEvent event) {
        setDeliveryCostLabels(true);
        int storeId = getStoreIdSelected();
        int customerId = getCustomerIdSelected();
        setDeliveryCost(storeId, customerId);
        staticOrderWasChosen(storeId, customerId);
    }

    @FXML
    void confirmOrderButtonAction(ActionEvent event) {
        finishOrdering();
    }

    public void createOrder() {
        Collection<CustomerDto> customersDto = businessLogic.getCustomersDto();
        Collection<StoreDto> storesDto = businessLogic.getStoresDto();
        setCustomers(customersDto);
        setStores(storesDto);
    }

    private void staticOrderWasChosen(int storeId, int customerId) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.STORE_ITEMS_FXML_RESOURCE);
            Node storeItems = loader.load();
            StoreItemsController storeItemsController = loader.getController();

            setStoreItemsController(storeItemsController);

            oneStoreItemsNodes.add(storeItems);

//            StoreDto storeDto = businessLogic.getStoreDto(storeId);
            Collection<ItemWithPriceDto> itemsWithPriceDto = businessLogic.getItemsWithPriceDto(storeId);
            storeItemsController.setTableViewData(itemsWithPriceDto);

            selectItemsLabel.setVisible(true);
            gridPane.add(storeItems, 1, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dynamicOrderWasChosen() {
        gridPane.getChildren().removeAll(oneStoreItemsNodes);
    }

    private void setOneStoreControls(boolean value) {
        chooseStoreLabel.setVisible(value);
        chooseStoreComboBox.setVisible(value);
        chooseStoreComboBox.setDisable(!value);
    }

    private void setDeliveryCostLabels(boolean value) {
        deliveryCostFieldLabel.setVisible(value);
        deliveryCostValueLabel.setVisible(value);
    }

    private void setOrderTypeRadioButtons() {
        staticOrderRadioButton.setToggleGroup(orderTypeRadioButtonsGroup);
        dynamicOrderRadioButton.setToggleGroup(orderTypeRadioButtonsGroup);
    }

    public void setCustomers(Collection<CustomerDto> customersDto) {
        if (!customersDto.isEmpty()) {
            ArrayList<CustomerInfo> customersInfo = new ArrayList<>();
            for (CustomerDto customerDto : customersDto) {
                CustomerInfo customerInfo = new CustomerInfo(customerDto);
                customersInfo.add(customerInfo);
            }
            chooseCustomerComboBox.setItems(FXCollections.observableArrayList(customersInfo));
        }
        else {
            // show no customers component!
        }
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

    public void finishOrdering() {
        uiOrderDto.setCustomerId(getCustomerIdSelected());
        uiOrderDto.setStoreId(getStoreIdSelected());
        uiOrderDto.setDate(datePicker.getValue());
        uiOrderDto.setItemsIdsAndQuantities(storeItemsController.getItemsIdsAndQuantities());
    }

    private int getStoreIdSelected() {
        return chooseStoreComboBox.getValue().getId();
    }

    private int getCustomerIdSelected() {
        return chooseCustomerComboBox.getValue().getId();
    }

    public void setStoreItemsController(StoreItemsController storeItemsController) {
        this.storeItemsController = storeItemsController;
    }
}
