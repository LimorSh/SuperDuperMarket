package course.java.sdm.javafx.components.sdmData.orders;

import course.java.sdm.engine.dto.OrderDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.sdmData.SingleOrder.SingleOrderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;

public class OrdersController {

    @FXML private FlowPane flowPane;

    public void createAllOrders(Collection<OrderDto> ordersDto) {
        if (!ordersDto.isEmpty()) {
            for (OrderDto orderDto : ordersDto) {
                createOrder(orderDto);
            }
        }
        else {
            // show no orders component!
        }
    }

    private void createOrder(OrderDto orderDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_ORDER_FXML_RESOURCE);
            Node singleOrder= loader.load();

            SingleOrderController singleOrderController = loader.getController();
            singleOrderController.setOrderDataValues(orderDto);

            flowPane.getChildren().add(singleOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
