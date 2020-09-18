package course.java.sdm.javafx.components.main;

import course.java.sdm.engine.dto.*;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.loadFile.LoadFileController;
import course.java.sdm.javafx.components.actions.order.OrderController;
import course.java.sdm.javafx.components.actions.order.discounts.DiscountsController;
import course.java.sdm.javafx.components.actions.order.summery.OrderSummeryController;
import course.java.sdm.javafx.components.actions.order.summery.OrderSummeryInfo;
import course.java.sdm.javafx.components.actions.order.summery.dynamicOrder.DynamicOrderStoresSummeryController;
import course.java.sdm.javafx.components.actions.updateItem.UpdateItemController;
import course.java.sdm.javafx.components.sdmData.customers.CustomersController;
import course.java.sdm.javafx.components.sdmData.items.ItemsController;
import course.java.sdm.javafx.components.sdmData.orders.OrdersController;
import course.java.sdm.javafx.components.sdmData.stores.StoresController;
import course.java.sdm.javafx.dto.UIOrderDto;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public class SuperDuperMarketController {

    private BusinessLogic businessLogic;
    private Stage primaryStage;

    @FXML private BorderPane superDuperMarketBorderPane;
    @FXML private Button customersButton;
    @FXML private Button storesButton;
    @FXML private Button itemsButton;
    @FXML private Button ordersButton;
    @FXML private Button loadFileButton;
    @FXML private Button updateItemButton;
    @FXML private Button addOrderButton;
    @FXML private Label titleVBox;

    private SimpleBooleanProperty isFileSelected;
    private LoadFileController loadFileController;
    private  Node loadFile;

    private static final String SELECTED_DATA_BUTTON_CSS_CLASS = "data-button-selected";


    public SuperDuperMarketController() {
        isFileSelected = new SimpleBooleanProperty(false);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @FXML
    private void initialize() {
        customersButton.disableProperty().bind(isFileSelected.not());
        storesButton.disableProperty().bind(isFileSelected.not());
        itemsButton.disableProperty().bind(isFileSelected.not());
        ordersButton.disableProperty().bind(isFileSelected.not());
        updateItemButton.disableProperty().bind(isFileSelected.not());
        addOrderButton.disableProperty().bind(isFileSelected.not());
    }

    private void clearSelectedDataButton() {
        customersButton.getStyleClass().remove(SELECTED_DATA_BUTTON_CSS_CLASS);
        itemsButton.getStyleClass().remove(SELECTED_DATA_BUTTON_CSS_CLASS);
        storesButton.getStyleClass().remove(SELECTED_DATA_BUTTON_CSS_CLASS);
        ordersButton.getStyleClass().remove(SELECTED_DATA_BUTTON_CSS_CLASS);
    }

    private void selectedDataButton (Button dataButton) {
        dataButton.getStyleClass().add(SELECTED_DATA_BUTTON_CSS_CLASS);
    }

    @FXML
    void loadFileButtonAction() {
        clearSelectedDataButton();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select system data file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) {
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.LOAD_FILE_FXML_RESOURCE);
            Node loadFile = loader.load();
            LoadFileController loadFileController = loader.getController();
            this.loadFileController = loadFileController;
            this.loadFile = loadFile;

            String absolutePath = selectedFile.getAbsolutePath();
            businessLogic.loadSystemData(absolutePath);
            isFileSelected.set(true);
            titleVBox.setDisable(false);
            loadFileController.showMsg(true, true, "");
            superDuperMarketBorderPane.setCenter(loadFile);
        }
        catch (Exception e) {
            if (isFileSelected.getValue()) {
                loadFileController.showMsg(false, false, e.getMessage());
            }
            else {
                loadFileController.showMsg(false, true, e.getMessage());
            }
            superDuperMarketBorderPane.setCenter(loadFile);
        }
    }

    @FXML
    void updateItemButtonAction(ActionEvent event) {
        clearSelectedDataButton();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.UPDATE_ITEM_FXML_RESOURCE);
            Node updateItem = loader.load();
            UpdateItemController updateItemController = loader.getController();

            updateItemController.setBusinessLogic(businessLogic);
            updateItemController.updateItem();

            superDuperMarketBorderPane.setCenter(updateItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void addOrderButtonAction(ActionEvent event) {
        clearSelectedDataButton();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ORDER_FXML_RESOURCE);
            Node order = loader.load();
            OrderController orderController = loader.getController();

            orderController.setSuperDuperMarketController(this);
            orderController.setBusinessLogic(businessLogic);
            orderController.createOrder();

            superDuperMarketBorderPane.setCenter(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showOrderSummery(OrderSummeryInfo orderSummeryInfo, UIOrderDto uiOrderDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ORDER_SUMMERY_FXML_RESOURCE);
            Node orderSummery = loader.load();
            OrderSummeryController orderSummeryController = loader.getController();

            orderSummeryController.setBusinessLogic(businessLogic);
            orderSummeryController.setUiOrderDto(uiOrderDto);
            orderSummeryController.setDataValues(orderSummeryInfo);
            orderSummeryController.showStores(orderSummeryInfo.getSingleStoresInfo());
            superDuperMarketBorderPane.setCenter(orderSummery);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDynamicOrderStoresSummery(OrderSummeryInfo orderSummeryInfo, UIOrderDto uiOrderDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.DYNAMIC_ORDER_STORES_SUMMERY_FXML_RESOURCE);
            Node dynamicOrderStoresSummery = loader.load();
            DynamicOrderStoresSummeryController dynamicOrderStoresSummeryController = loader.getController();

            dynamicOrderStoresSummeryController.setSuperDuperMarketController(this);
            dynamicOrderStoresSummeryController.setValuesData(orderSummeryInfo, uiOrderDto);
            dynamicOrderStoresSummeryController.createAllStores();
            superDuperMarketBorderPane.setCenter(dynamicOrderStoresSummery);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean noDiscounts(OrderSummeryInfo orderSummeryInfo, UIOrderDto uiOrderDto) {
        boolean noDiscounts = false;
        if (orderSummeryInfo.getIsStaticOrder()) {
            if (!businessLogic.isStoreHasDiscounts(uiOrderDto.getStoreId())) {
                noDiscounts = true;
            }
        }
        else {
            Collection<Integer> storesIds = orderSummeryInfo.getStoresIds();
            if (!businessLogic.isStoresHaveDiscounts(storesIds)) {
                noDiscounts = true;
            }
        }
        return noDiscounts;
    }

    private Map<StoreItemDto, Float> getStoreItemsDtoAndQuantities(OrderSummeryInfo orderSummeryInfo,
                                                                   UIOrderDto uiOrderDto) {
        Map<StoreItemDto, Float> storeItemsDtoAndQuantities;
        if (orderSummeryInfo.getIsStaticOrder()) {
            int storeId = uiOrderDto.getStoreId();
            storeItemsDtoAndQuantities =
                    businessLogic.getStoreItemsDtoAndQuantities(storeId, uiOrderDto.getItemsIdsAndQuantities());
        }
        else {
            storeItemsDtoAndQuantities =
                    businessLogic.getStoreItemsDtoAndQuantities(uiOrderDto.getItemsIdsAndQuantities());
        }
        return storeItemsDtoAndQuantities;
    }

    public void showDiscountsInOrder(OrderSummeryInfo orderSummeryInfo, UIOrderDto uiOrderDto) {
        if (noDiscounts(orderSummeryInfo, uiOrderDto)) {
            showOrderSummery(orderSummeryInfo, uiOrderDto);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ALL_DISCOUNTS_IN_ADD_ORDER_FXML_RESOURCE);
            Node discounts = loader.load();
            DiscountsController discountsController = loader.getController();

            discountsController.setSuperDuperMarketController(this);
            discountsController.setBusinessLogic(businessLogic);
            discountsController.setValuesData(orderSummeryInfo, uiOrderDto);

            Map<StoreItemDto, Float> storeItemsDtoAndQuantities =
                    getStoreItemsDtoAndQuantities(orderSummeryInfo, uiOrderDto);

            boolean success = discountsController.createAllDiscounts(storeItemsDtoAndQuantities);
            if (success) {
                superDuperMarketBorderPane.setCenter(discounts);
            }
            else {
                showOrderSummery(orderSummeryInfo, uiOrderDto);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void customersButtonAction(ActionEvent event) {
        clearSelectedDataButton();
        selectedDataButton(customersButton);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.CUSTOMERS_FXML_RESOURCE);
            Node customers = loader.load();
            CustomersController customersController = loader.getController();

            Collection<CustomerDto> customersDto = businessLogic.getCustomersDto();
            customersController.createAllCustomers(customersDto);

            superDuperMarketBorderPane.setCenter(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void itemsButtonAction(ActionEvent event) {
        clearSelectedDataButton();
        selectedDataButton(itemsButton);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ITEMS_FXML_RESOURCE);
            Node items = loader.load();
            ItemsController itemsController = loader.getController();

            Collection<ItemDto> itemsDto = businessLogic.getItemsDto();
            itemsController.createAllItems(itemsDto);

            superDuperMarketBorderPane.setCenter(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void storesButtonAction(ActionEvent event) {
        clearSelectedDataButton();
        selectedDataButton(storesButton);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.STORES_FXML_RESOURCE);
            Node stores = loader.load();
            StoresController storesController = loader.getController();

            Collection<StoreDto> storesDto = businessLogic.getStoresDto();
            storesController.createAllStores(storesDto);

            superDuperMarketBorderPane.setCenter(stores);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ordersButtonAction(ActionEvent event) {
        clearSelectedDataButton();
        selectedDataButton(ordersButton);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ORDERS_FXML_RESOURCE);
            Node orders = loader.load();
            OrdersController ordersController = loader.getController();

            Collection<OrderDto> ordersDto = businessLogic.getOrdersDto();
            ordersController.createAllOrders(ordersDto);

            superDuperMarketBorderPane.setCenter(orders);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
