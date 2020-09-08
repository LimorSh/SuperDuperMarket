package course.java.sdm.javafx.components.actions.order;

import course.java.sdm.engine.dto.*;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.order.staticOrder.StoreInfo;
import course.java.sdm.javafx.components.actions.order.storeItems.StoreItemsController;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.SingleStoreInfo;
import course.java.sdm.javafx.components.main.SuperDuperMarketController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class OrderController extends OrderData {

    @FXML private GridPane gridPane;
    @FXML private ComboBox<CustomerInfo> chooseCustomerComboBox;
    @FXML private ComboBox<StoreInfo> chooseStoreComboBox;
    @FXML private Label chooseStoreLabel;
    @FXML private Button nextButton;
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
    private SuperDuperMarketController superDuperMarketController;

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
        int storeId = getSelectedStoreId();
        int customerId = getSelectedCustomerId();
        setDeliveryCost(storeId, customerId);
        staticOrderWasChosen(storeId, customerId);
    }

    @FXML
    void nextButtonAction(ActionEvent event) {
        updateUiOrderDto();
        updateOrderSummeryInfo();
        superDuperMarketController.showOrderSummery(orderSummeryInfo);
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

            storeItemsController.setBusinessLogic(businessLogic);
            setStoreItemsController(storeItemsController);

            oneStoreItemsNodes.add(storeItems);

//            StoreDto storeDto = businessLogic.getStoreDto(storeId);
//            Collection<ItemWithPriceDto> itemsWithPriceDto = businessLogic.getItemsWithPriceDto(storeId);
            storeItemsController.setTableViewData(storeId);

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

    public void updateUiOrderDto() {
        uiOrderDto.setCustomerId(getSelectedCustomerId());
        uiOrderDto.setStoreId(getSelectedStoreId());
        uiOrderDto.setDate(getPickedDate());
        uiOrderDto.setItemsIdsAndQuantities(getSelectedItemsIdsAndQuantities());
    }

    private void updateOrderSummeryInfo() {
        int customerId = getSelectedCustomerId();
        orderSummeryInfo.setCustomerId(customerId);
        CustomerDto customerDto = businessLogic.getCustomerDto(customerId);
        orderSummeryInfo.setCustomerName(customerDto.getName());
        int customerXLocation = customerDto.getXLocation();
        int customerYLocation = customerDto.getYLocation();
        orderSummeryInfo.setCustomerXLocation(customerXLocation);
        orderSummeryInfo.setCustomerYLocation(customerYLocation);

        float itemsCost = storeItemsController.getItemsCost();
        orderSummeryInfo.setItemsCost(itemsCost);
        orderSummeryInfo.setDeliveryCost(deliveryCost.floatValue());
        orderSummeryInfo.setTotalCost(itemsCost + deliveryCost.floatValue());

        SingleStoreInfo singleStoreInfo = new SingleStoreInfo();
        int storeId = getSelectedStoreId();
        singleStoreInfo.setId(storeId);
        StoreDto storeDto = businessLogic.getStoreDto(storeId);
        singleStoreInfo.setName(storeDto.getName());
        singleStoreInfo.setPpk(storeDto.getPpk());
        singleStoreInfo.setDistance(businessLogic.getDistanceBetweenCustomerAndStore(storeId, customerId));
        singleStoreInfo.setDeliveryCost(businessLogic.getDeliveryCost(storeId, customerId));

        orderSummeryInfo.addSingleStoreInfo(singleStoreInfo);

    }

    private LocalDate getPickedDate() {
        return datePicker.getValue();
    }

    private Map<Integer, Float> getSelectedItemsIdsAndQuantities() {
        return storeItemsController.getItemsIdsAndQuantities();
    }

    public int getSelectedStoreId() {
        return chooseStoreComboBox.getValue().getId();
    }

    public int getSelectedCustomerId() {
        return chooseCustomerComboBox.getValue().getId();
    }

    public void setStoreItemsController(StoreItemsController storeItemsController) {
        storeItemsController.setOrderController(this);
        this.storeItemsController = storeItemsController;
    }

    public void setSuperDuperMarketController(SuperDuperMarketController superDuperMarketController) {
        this.superDuperMarketController = superDuperMarketController;
    }
}
