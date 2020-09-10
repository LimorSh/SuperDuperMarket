package course.java.sdm.javafx.components.sdmData.customers;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.sdmData.singleCustomer.SingleCustomerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;

public class CustomersController {

    @FXML private FlowPane flowPane;

    public void createAllCustomers(Collection<CustomerDto> customersDto) {
        if (!customersDto.isEmpty()) {
            for (CustomerDto customerDto : customersDto) {
                createCustomer(customerDto);
            }
        }
        else {
            // show no customer component!
        }
    }

    private void createCustomer(CustomerDto customerDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_CUSTOMER_FXML_RESOURCE);
            Node singleCustomer = loader.load();

            SingleCustomerController singleCustomerController = loader.getController();
            singleCustomerController.setCustomerDataValues(customerDto);

            flowPane.getChildren().add(singleCustomer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
