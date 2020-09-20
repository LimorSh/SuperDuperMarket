package course.java.sdm.javafx.components.sdmData.orders;

import course.java.sdm.engine.dto.OrderDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.sdmData.singleOrder.SingleOrderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import javafx.scene.control.Label;
import java.io.IOException;
import java.util.Collection;

public class OrdersController {

    @FXML private FlowPane flowPane;
    @FXML private Label noOrdersLabel;

    public void createAllOrders(Collection<OrderDto> ordersDto) {
        if (!ordersDto.isEmpty()) {
            flowPane.getChildren().removeAll(noOrdersLabel);
            for (OrderDto orderDto : ordersDto) {
                createOrder(orderDto);
            }
        }
        else {
            noOrdersLabel.setVisible(true);
        }
    }

    private void createOrder(OrderDto orderDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_ORDER_FXML_RESOURCE);
            Node singleOrder= loader.load();
            SingleOrderController singleOrderController = loader.getController();

            singleOrderController.setOrderDataValues(orderDto);
            singleOrderController.showStores(orderDto.getStoresOrderDto());

            flowPane.getChildren().add(singleOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
