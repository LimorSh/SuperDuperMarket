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
            System.out.println("orders in system\n");
            flowPane.getChildren().removeAll(noOrdersLabel);
            System.out.println("remove label\n");
            for (OrderDto orderDto : ordersDto) {
                System.out.println("before create order: \n");
                createOrder(orderDto);
                System.out.println("after create order: \n");
            }
        }
        else {
            noOrdersLabel.setVisible(true);
        }
    }

    private void createOrder(OrderDto orderDto) {
        try {
            System.out.println("in create order, before FXML loader:\n");
            FXMLLoader loader = new FXMLLoader();
            System.out.println("about to load resource from: "
                    + SuperDuperMarketConstants.SINGLE_ORDER_FXML_RESOURCE.toExternalForm());
            loader.setLocation(SuperDuperMarketConstants.SINGLE_ORDER_FXML_RESOURCE);

            Node singleOrder= loader.load();
            System.out.println("successfully loaded data from: "
                    + SuperDuperMarketConstants.SINGLE_ORDER_FXML_RESOURCE);

            SingleOrderController singleOrderController = loader.getController();
            System.out.println("Extracted controller");

            singleOrderController.setOrderDataValues(orderDto);
            System.out.println("before showStores:");
            singleOrderController.showStores(orderDto.getStoresOrderDto());
            System.out.println("after showStores:");

            System.out.println("data updated in controller: " + "Orders" + "Controller");
            flowPane.getChildren().add(singleOrder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
