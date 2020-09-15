package course.java.sdm.javafx.components.sdmData.SingleOrder;

import course.java.sdm.engine.dto.StoreOrderDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.sdmData.SingleOrder.allStores.AllStoresController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import java.io.IOException;
import java.util.Collection;

public class SingleOrderController extends OrderData {

    @FXML private BorderPane innerBorderPane;
    @FXML private Label titleLabel;
    @FXML private Label dateValueLabel;
    @FXML private Label customerDetailsValuesLabel;
    @FXML private Label orderCategoryValueLabel;
    @FXML private Label itemsCostValueLabel;
    @FXML private Label deliveryCostValueLabel;
    @FXML private Label totalCostValueLabel;


    public SingleOrderController() {
        super();
    }

    @FXML
    private void initialize() {
        titleLabel.textProperty().bind(title);
        dateValueLabel.textProperty().bind(date);
        customerDetailsValuesLabel.textProperty().bind(customerDetails);
        orderCategoryValueLabel.textProperty().bind(orderCategory);
        itemsCostValueLabel.textProperty().bind(itemsCost.asString());
        deliveryCostValueLabel.textProperty().bind(deliveryCost.asString());
        totalCostValueLabel.textProperty().bind(totalCost.asString());
    }

    public void showStores(Collection<StoreOrderDto> storesOrderDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ALL_STORES_IN_ORDER_FXML_RESOURCE);
            Node allStores = loader.load();
            AllStoresController allStoresController = loader.getController();

            innerBorderPane.setCenter(allStores);
            allStoresController.createAllStores(storesOrderDto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
