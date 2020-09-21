package course.java.sdm.javafx.components.main;

import course.java.sdm.engine.dto.*;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.components.actions.addItem.AddItemController;
import course.java.sdm.javafx.components.actions.addStore.AddStoreController;
import course.java.sdm.javafx.components.actions.loadFile.task.TaskLogic;
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
import course.java.sdm.javafx.components.sdmData.locationMap.LocationMapController;
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
import java.util.ArrayList;
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
    @FXML private Button locationMapButton;
    @FXML private Button loadFileButton;
    @FXML private Button addOrderButton;
    @FXML private Button updateItemButton;
    @FXML private Button addStoreButton;
    @FXML private Button addItemButton;
    @FXML private Label titleVBox;

    private SimpleBooleanProperty isFileSelected;

    private LoadFileController loadFileController;
    private  Node loadFile;

    private static final String SELECTED_DATA_BUTTON_CSS_CLASS = "data-button-selected";

    public SuperDuperMarketController() {
        isFileSelected = new SimpleBooleanProperty(SuperDuperMarketConstants.INIT_BOOLEAN);
    }

    public boolean getIsFileSelected() {
        return isFileSelected.get();
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @FXML
    private void initialize() {
        // data buttons
        customersButton.disableProperty().bind(isFileSelected.not());
        itemsButton.disableProperty().bind(isFileSelected.not());
        storesButton.disableProperty().bind(isFileSelected.not());
        ordersButton.disableProperty().bind(isFileSelected.not());
        locationMapButton.disableProperty().bind(isFileSelected.not());

        // action buttons
        addOrderButton.disableProperty().bind(isFileSelected.not());
        updateItemButton.disableProperty().bind(isFileSelected.not());
        addStoreButton.disableProperty().bind(isFileSelected.not());
        addItemButton.disableProperty().bind(isFileSelected.not());
    }

    private void clearSelectedDataButton() {
        customersButton.getStyleClass().remove(SELECTED_DATA_BUTTON_CSS_CLASS);
        itemsButton.getStyleClass().remove(SELECTED_DATA_BUTTON_CSS_CLASS);
        storesButton.getStyleClass().remove(SELECTED_DATA_BUTTON_CSS_CLASS);
        ordersButton.getStyleClass().remove(SELECTED_DATA_BUTTON_CSS_CLASS);
        locationMapButton.getStyleClass().remove(SELECTED_DATA_BUTTON_CSS_CLASS);
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
            loadFileController.setSuperDuperMarketController(this);
            loadFileController.setBusinessLogic(businessLogic);

            String absolutePath = selectedFile.getAbsolutePath();

            //tasks
            loadFileController.setValuesData(absolutePath);
            TaskLogic taskLogic = new TaskLogic(loadFileController);
            loadFileController.setTaskLogic(taskLogic);
            // end tasks

            superDuperMarketBorderPane.setCenter(loadFile);
        }
         catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFileSelected(){
        isFileSelected.set(true);
        titleVBox.setDisable(false);
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
    void addStoreButtonAction(ActionEvent event) {
        clearSelectedDataButton();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ADD_STORE_FXML_RESOURCE);
            Node addStore = loader.load();
            AddStoreController addStoreController = loader.getController();

            addStoreController.setBusinessLogic(businessLogic);
            addStoreController.setTableViewData();

            superDuperMarketBorderPane.setCenter(addStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void addItemButtonAction(ActionEvent event) {
        clearSelectedDataButton();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ADD_ITEM_FXML_RESOURCE);
            Node addItem = loader.load();
            AddItemController addItemController = loader.getController();

            addItemController.setBusinessLogic(businessLogic);
            addItemController.setTableViewData();

            superDuperMarketBorderPane.setCenter(addItem);
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

    @FXML
    void locationMapButtonAction(ActionEvent event) {
        clearSelectedDataButton();
        selectedDataButton(locationMapButton);
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.LOCATION_MAP_FXML_RESOURCE);
            Node locationMap = loader.load();
            LocationMapController locationMapController = loader.getController();

            Collection<StoreDto> storesDto = businessLogic.getStoresDto();
            Collection<CustomerDto> customersDto = businessLogic.getCustomersDto();
            ArrayList<Integer> minAndMaxLocations = businessLogic.getMinAndMaxLocations();
            locationMapController.setValuesData(storesDto, customersDto, minAndMaxLocations);
            locationMapController.createLocationMap();

            superDuperMarketBorderPane.setCenter(locationMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
