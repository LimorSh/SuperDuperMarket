package course.java.sdm.javafx.components.sdmData.customers;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.actions.addOrder.summery.singleStore.OrderSummerySingleStoreInfo;
import course.java.sdm.javafx.components.sdmData.singleCustomer.SingleCustomerController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class CustomersController {

    @FXML private FlowPane flowPane;

    public void createAllCustomers(Collection<CustomerDto> customersDto) {

        Collection<CustomerDto> customersDtoSortedById = customersDto.stream()
                .sorted(Comparator.comparing(CustomerDto::getId))
                .collect(Collectors.toList());

        for (CustomerDto customerDto : customersDtoSortedById) {
            createCustomer(customerDto);
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
