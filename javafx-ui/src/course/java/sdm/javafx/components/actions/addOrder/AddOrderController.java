package course.java.sdm.javafx.components.actions.addOrder;

import course.java.sdm.engine.dto.*;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.info.CustomerInfo;
import course.java.sdm.javafx.components.info.StoreInfo;
import course.java.sdm.javafx.components.actions.addOrder.storeItems.StoreItemsController;
import course.java.sdm.javafx.components.actions.addOrder.summery.singleStore.OrderSummerySingleStoreInfo;
import course.java.sdm.javafx.components.actions.addOrder.summery.singleStore.OrderSummerySingleStoreItemInfo;
import course.java.sdm.javafx.components.main.controller.SuperDuperMarketController;
import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AddOrderController extends AddOrderData {

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

    @FXML private HBox newItemImageHbox;
    @FXML private Button stopAnimationButton;
    @FXML private ImageView newItemImage;
    @FXML private ImageView cartImage;

    private final Collection<Node> storeItemsNodes;
    private final ToggleGroup orderTypeRadioButtonsGroup;

    private StoreItemsController storeItemsController;
    private SuperDuperMarketController superDuperMarketController;


    public AddOrderController() {
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
    void stopAnimationButtonAction(ActionEvent event) {
        storeItemsController.setActivateAnimation(false);
        newItemImage.setVisible(false);
        cartImage.setVisible(false);
        stopAnimationButton.setDisable(true);
    }

    @FXML
    void finishButtonAction(ActionEvent event) {
        updateUiOrderDto();
        updateOrderSummeryInfo();

        if (isStaticOrder()) {
            superDuperMarketController.showDiscountsInOrder(orderSummeryInfo, uiOrderDto);
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
        ArrayList<CustomerInfo> customersInfo = new ArrayList<>();

        Collection<CustomerDto> customersDtoSortedById = customersDto.stream()
                .sorted(Comparator.comparing(CustomerDto::getId))
                .collect(Collectors.toList());

        for (CustomerDto customerDto : customersDtoSortedById) {
            CustomerInfo customerInfo = new CustomerInfo(customerDto);
            customersInfo.add(customerInfo);
        }
        chooseCustomerComboBox.setItems(FXCollections.observableArrayList(customersInfo));
    }

    public void setStores(Collection<StoreDto> storesDto) {
        ArrayList<StoreInfo> storesInfo = new ArrayList<>();

        Collection<StoreDto> storesDtoSortedById = storesDto.stream()
                .sorted(Comparator.comparing(StoreDto::getId))
                .collect(Collectors.toList());

        for (StoreDto storeDto : storesDtoSortedById) {
            StoreInfo storeInfo = new StoreInfo(storeDto);
            storesInfo.add(storeInfo);
        }
        chooseStoreComboBox.setItems(FXCollections.observableArrayList(storesInfo));
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

        setOrderSummeryInfoBasicData(itemsCost.get(), deliveriesCost.get());
    }

    private void updateOrderSummeryInfoForStaticOrder(int customerId) {
        float itemsCost = storeItemsController.getItemsCost();
        setOrderSummeryInfoBasicData(itemsCost, deliveryCost.floatValue());

        int storeId = getSelectedStoreId();
        StoreDto storeDto = businessLogic.getStoreDto(storeId);
        addOrderSummerySingleStoreInfo(storeDto, customerId, getSelectedItemsIdsAndQuantities());
    }

    private void setOrderSummeryInfoBasicData(float itemsCost, float deliveryCost) {
        orderSummeryInfo.setItemsCost(itemsCost);
        orderSummeryInfo.setDeliveryCost(deliveryCost);
        orderSummeryInfo.setTotalCost(itemsCost + deliveryCost);
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
        storeItemsController.setAddOrderController(this);
        this.storeItemsController = storeItemsController;
    }

    public void setSuperDuperMarketController(SuperDuperMarketController superDuperMarketController) {
        this.superDuperMarketController = superDuperMarketController;
    }

    public void startAddItemToCartAnimation() {
        newItemImage.setVisible(true);
        cartImage.setVisible(true);
        stopAnimationButton.setVisible(true);
        stopAnimationButton.setDisable(false);

        Path path = new Path();
        path.getElements().addAll(new MoveTo(50, 50), new VLineTo(180));
        path.setStroke(Color.TRANSPARENT);
        newItemImageHbox.getChildren().add(path);

        PathTransition pt = new PathTransition(Duration.millis(1700), path, newItemImage);
        pt.play();
    }
}
