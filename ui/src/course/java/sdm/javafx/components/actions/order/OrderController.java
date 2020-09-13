package course.java.sdm.javafx.components.actions.order;

import course.java.sdm.engine.dto.*;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.info.StoreInfo;
import course.java.sdm.javafx.components.actions.order.storeItems.StoreItemsController;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreInfo;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreItemInfo;
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
import java.util.concurrent.atomic.AtomicReference;

public class OrderController extends OrderData {

    @FXML private GridPane gridPane;
    @FXML private ComboBox<CustomerInfo> chooseCustomerComboBox;
    @FXML private ComboBox<StoreInfo> chooseStoreComboBox;
    @FXML private Label chooseStoreLabel;
    @FXML private Button finishButton;
    @FXML private DatePicker datePicker;
    @FXML private RadioButton staticOrderRadioButton;
    @FXML private RadioButton dynamicOrderRadioButton;
    @FXML private Label deliveryCostFieldLabel;
    @FXML private Label deliveryCostValueLabel;
    @FXML private Label selectItemsLabel;

    private final Collection<Node> storeItemsNodes;
    private final ToggleGroup orderTypeRadioButtonsGroup;

    private StoreItemsController storeItemsController;
    private SuperDuperMarketController superDuperMarketController;


    public OrderController() {
        super();
        storeItemsNodes = new ArrayList<>();
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
        chooseItems(storeId);
    }

    @FXML
    void finishButtonAction(ActionEvent event) {
        updateUiOrderDto();
        updateOrderSummeryInfo();

        if (isStaticOrder()) {
            superDuperMarketController.showOrderSummery(orderSummeryInfo, uiOrderDto);
        }
        else {
            superDuperMarketController.showDynamicOrderStoresSummery(orderSummeryInfo, uiOrderDto);
        }
    }

    public void setFinishButton(boolean value) {
        finishButton.setDisable(value);
    }

    public void createOrder() {
        Collection<CustomerDto> customersDto = businessLogic.getCustomersDto();
        Collection<StoreDto> storesDto = businessLogic.getStoresDto();
        setCustomers(customersDto);
        setStores(storesDto);
    }

    private void chooseItems(int storeId) {
        gridPane.getChildren().removeAll(storeItemsNodes);

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.STORE_ITEMS_FXML_RESOURCE);
            Node storeItems = loader.load();
            StoreItemsController storeItemsController = loader.getController();

            storeItemsController.setBusinessLogic(businessLogic);
            setStoreItemsController(storeItemsController);

            storeItemsNodes.add(storeItems);

            storeItemsController.setTableViewData(storeId);

            selectItemsLabel.setVisible(true);
            gridPane.add(storeItems, STORE_ITEMS_COLUMN_INDEX, STORE_ITEMS_ROW_INDEX);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void dynamicOrderWasChosen() {
        chooseItems(SuperDuperMarketConstants.NO_STORE_ID);
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
        if (isStaticOrder()) {
            uiOrderDto.setStoreId(getSelectedStoreId());
        }
        uiOrderDto.setDate(getPickedDate());
        uiOrderDto.setItemsIdsAndQuantities(getSelectedItemsIdsAndQuantities());
    }

    private void updateOrderSummeryInfo() {
        orderSummeryInfo.setDate(getPickedDate());
        int customerId = getSelectedCustomerId();
        orderSummeryInfo.setCustomerId(customerId);
        CustomerDto customerDto = businessLogic.getCustomerDto(customerId);
        orderSummeryInfo.setCustomerName(customerDto.getName());
        int customerXLocation = customerDto.getXLocation();
        int customerYLocation = customerDto.getYLocation();
        orderSummeryInfo.setCustomerXLocation(customerXLocation);
        orderSummeryInfo.setCustomerYLocation(customerYLocation);
        orderSummeryInfo.setIsStaticOrder(isStaticOrder());

        if (isStaticOrder()) {
            updateOrderSummeryInfoForStaticOrder(customerId);
        }
        else {
            updateOrderSummeryInfoForDynamicOrder(customerId);
        }
    }

    private void updateOrderSummeryInfoForDynamicOrder(int customerId) {
        Map<Integer, Float> selectedItemsIdsAndQuantities = getSelectedItemsIdsAndQuantities();
        Map<StoreDto, Map<Integer, Float>> optimalCart = businessLogic.getOptimalCart(selectedItemsIdsAndQuantities);

        AtomicReference<Float> itemsCost = new AtomicReference<>(0f);
        AtomicReference<Float> deliveriesCost = new AtomicReference<>(0f);

        optimalCart.forEach((storeDto, itemIdsAndQuantities) -> {
            int storeId = storeDto.getId();

            itemIdsAndQuantities.forEach((itemId, quantity) -> {
                float itemPrice = businessLogic.getItemPriceInStoreByIds(storeId, itemId);
                float itemCost = itemPrice * quantity;
                itemsCost.updateAndGet(v -> (v + itemCost));
            });

            float deliveryCost = businessLogic.getDeliveryCost(storeId, customerId);
            deliveriesCost.updateAndGet(v -> (v + deliveryCost));

            addOrderSummerySingleStoreInfo(storeDto, customerId, itemIdsAndQuantities);
        });

        float totalCost = itemsCost.get() + deliveriesCost.get();

        orderSummeryInfo.setItemsCost(itemsCost.get());
        orderSummeryInfo.setDeliveryCost(deliveriesCost.get());
        orderSummeryInfo.setTotalCost(totalCost);
    }

    private void updateOrderSummeryInfoForStaticOrder(int customerId) {
        float itemsCost = storeItemsController.getItemsCost();
        orderSummeryInfo.setItemsCost(itemsCost);
        orderSummeryInfo.setDeliveryCost(deliveryCost.floatValue());
        orderSummeryInfo.setTotalCost(itemsCost + deliveryCost.floatValue());

        int storeId = getSelectedStoreId();
        StoreDto storeDto = businessLogic.getStoreDto(storeId);
        addOrderSummerySingleStoreInfo(storeDto, customerId, getSelectedItemsIdsAndQuantities());
    }

    private void addOrderSummerySingleStoreInfo(StoreDto storeDto, int customerId,
                                                Map<Integer, Float> itemsIdsAndQuantities) {
        OrderSummerySingleStoreInfo orderSummerySingleStoreInfo = new OrderSummerySingleStoreInfo();
        int storeId = storeDto.getId();

        orderSummerySingleStoreInfo.setId(storeId);
        orderSummerySingleStoreInfo.setName(storeDto.getName());
        orderSummerySingleStoreInfo.setXLocation(storeDto.getXLocation());
        orderSummerySingleStoreInfo.setYLocation(storeDto.getYLocation());
        orderSummerySingleStoreInfo.setPpk(storeDto.getPpk());
        orderSummerySingleStoreInfo.setDistance(businessLogic.getDistanceBetweenCustomerAndStore(storeId, customerId));
        orderSummerySingleStoreInfo.setDeliveryCost(businessLogic.getDeliveryCost(storeId, customerId));
        updateOrderSummeryInfoSingleStoreItems(orderSummerySingleStoreInfo, storeId,
                itemsIdsAndQuantities);
        orderSummeryInfo.addSingleStoreInfo(orderSummerySingleStoreInfo);
    }

    private void updateOrderSummeryInfoSingleStoreItems(OrderSummerySingleStoreInfo orderSummerySingleStoreInfo,
                                                        int storeId, Map<Integer, Float> itemsIdsAndQuantities) {
        itemsIdsAndQuantities.forEach((id,quantity) -> {
            ItemWithPriceDto itemWithPriceDto = businessLogic.getItemWithPriceDto(storeId, id);
            OrderSummerySingleStoreItemInfo orderSummerySingleStoreItemInfo =
                    new OrderSummerySingleStoreItemInfo(itemWithPriceDto, quantity);
            orderSummerySingleStoreInfo.addOrderSummerySingleStoreItemsInfo(orderSummerySingleStoreItemInfo);
        });
    }

    public boolean isStaticOrder() {
        return staticOrderRadioButton.isSelected();
    }

    public boolean isDynamicOrder() {
        return dynamicOrderRadioButton.isSelected();
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
