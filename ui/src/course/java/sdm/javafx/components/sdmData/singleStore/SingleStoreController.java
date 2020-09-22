package course.java.sdm.javafx.components.sdmData.singleStore;

import course.java.sdm.engine.dto.*;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.sdmData.singleStore.singleDiscount.singleDiscountController;
import course.java.sdm.javafx.components.sdmData.singleStore.singleStoreOrder.SingleStoreOrderData;
import course.java.sdm.javafx.components.sdmData.singleStore.singleStoreItem.SingleStoreItemData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class SingleStoreController extends StoreData {

    @FXML private Label nameValueLabel;
    @FXML private Label idValueLabel;
    @FXML private Label ppkValueLabel;
    @FXML private Label totalDeliveryRevenueValueLabel;

    @FXML private Label noDiscountsLabel;
    @FXML private HBox discountsHbox;

    @FXML private TableView<SingleStoreItemData> itemsTableView;
    @FXML private TableColumn<SingleStoreItemData, Integer> itemIdCol;
    @FXML private TableColumn<SingleStoreItemData, String> itemNameCol;
    @FXML private TableColumn<SingleStoreItemData, String> itemPurchaseCategoryCol;
    @FXML private TableColumn<SingleStoreItemData, Float> itemPricePerUnitCol;
    @FXML private TableColumn<SingleStoreItemData, Float> itemTotalSoldCol;

    @FXML private TableView<SingleStoreOrderData> ordersTableView;
    @FXML private TableColumn<SingleStoreOrderData, String> orderDateCol;
    @FXML private TableColumn<SingleStoreOrderData, Integer> orderTotalItemsCol;
    @FXML private TableColumn<SingleStoreOrderData, Float> orderItemsCostCol;
    @FXML private TableColumn<SingleStoreOrderData, Float> orderDeliveryCostCol;
    @FXML private TableColumn<SingleStoreOrderData, Float> orderTotalCostCol;

    @FXML private FlowPane discountsFlowPane;

    @FXML
    private void initialize() {
        idValueLabel.textProperty().bind(id.asString());
        nameValueLabel.textProperty().bind(name);
        ppkValueLabel.textProperty().bind(ppk.asString());
        totalDeliveryRevenueValueLabel.textProperty().bind(totalDeliveryRevenue.asString());
    }

    public void setItemsTableView(Collection<StoreItemDto> storeItemsDto) {
        ArrayList<SingleStoreItemData> singleStoreItemsData = new ArrayList<>();
        for (StoreItemDto storeItemDto : storeItemsDto) {
            SingleStoreItemData singleStoreItemData = new SingleStoreItemData(storeItemDto);
            singleStoreItemsData.add(singleStoreItemData);
        }
        final ObservableList<SingleStoreItemData> data = FXCollections.observableArrayList(singleStoreItemsData);

        itemIdCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );
        itemNameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        itemPurchaseCategoryCol.setCellValueFactory(
                new PropertyValueFactory<>("purchaseCategory")
        );
        itemPricePerUnitCol.setCellValueFactory(
                new PropertyValueFactory<>("pricePerUnit")
        );
        itemTotalSoldCol.setCellValueFactory(
                new PropertyValueFactory<>("totalSold")
        );

        itemsTableView.setItems(data);
    }

    public void setOrdersTableView(Collection<OrderDto> ordersDto, int storeId) {
        if (!ordersDto.isEmpty()) {
            ArrayList<SingleStoreOrderData> singleStoreOrdersData = new ArrayList<>();
            for (OrderDto orderDto : ordersDto) {
                StoreOrderDto storeOrderDto = orderDto.getStoreOrderDto(storeId);
                SingleStoreOrderData singleStoreOrderData = new SingleStoreOrderData(storeOrderDto);
                singleStoreOrdersData.add(singleStoreOrderData);
            }
            final ObservableList<SingleStoreOrderData> data = FXCollections.observableArrayList(singleStoreOrdersData);

            orderDateCol.setCellValueFactory(
                    new PropertyValueFactory<>("date")
            );
            orderTotalItemsCol.setCellValueFactory(
                    new PropertyValueFactory<>("totalItems")
            );
            orderItemsCostCol.setCellValueFactory(
                    new PropertyValueFactory<>("itemsCost")
            );
            orderDeliveryCostCol.setCellValueFactory(
                    new PropertyValueFactory<>("deliveryCost")
            );
            orderTotalCostCol.setCellValueFactory(
                    new PropertyValueFactory<>("totalCost")
            );

            ordersTableView.setItems(data);
        }
        else {
            ordersTableView.setPlaceholder(new Label(NO_ORDERS_LABEL_TEXT));
        }
    }

    public void setDiscountsFlowPane(StoreDto storeDto) {
        if (storeDto.getHasDiscounts()) {
            discountsHbox.getChildren().removeAll(noDiscountsLabel);
            Collection<StoreItemDto> storeItemsDto = storeDto.getStoreItemsDto();
            createAllDiscounts(storeItemsDto);
        }
    }

    public void createAllDiscounts(Collection<StoreItemDto> storeItemsDto) {
        for (StoreItemDto storeItemDto : storeItemsDto) {
            createStoreItemDiscounts(storeItemDto.getDiscountsDto());
        }
    }

    private void createStoreItemDiscounts(Collection<DiscountDto> discountsDto) {
        for (DiscountDto discountDto : discountsDto) {
            createDiscount(discountDto);
        }
    }

    private void createDiscount(DiscountDto discountDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_DISCOUNT_FXML_RESOURCE);
            Node singleDiscount = loader.load();

            singleDiscountController singleDiscountController = loader.getController();
            singleDiscountController.setDataValues(discountDto);
            singleDiscountController.setTableView(discountDto.getOffersDto());

            discountsFlowPane.getChildren().add(singleDiscount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
