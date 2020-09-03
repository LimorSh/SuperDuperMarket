package course.java.sdm.javafx.components.actions.order;

import course.java.sdm.engine.dto.BasicCustomerDto;
import course.java.sdm.engine.dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Collection;

public class OrderController {

//    @FXML private ComboBox<String> chooseCustomerComboBox;
//    @FXML private ComboBox<BasicCustomerDto> chooseCustomerComboBox;
    @FXML private ComboBox<CustomerInfo> chooseCustomerComboBox;


//    public void setCustomers(Collection<BasicCustomerDto> basicCustomersDto) {
//        if (!basicCustomersDto.isEmpty()) {
////            ArrayList<BasicCustomerDto> basicCustomerDtos = new ArrayList<>();
//            for (BasicCustomerDto basicCustomerDto : basicCustomersDto) {
////                CustomerOrderData c = new C...(customerDto.getId(), customerDto.getName())
////                String str = String.format("ID %d: %s", customerDto.getId(), customerDto.getName());
////                strings.add(str);
////                strings.add(c);
//            }
//            chooseCustomerComboBox.setItems(FXCollections.observableArrayList(basicCustomersDto));
//        }
//        else {
//            // show no items component!
//        }
//    }

    public void setCustomers(Collection<CustomerDto> customersDto) {
        if (!customersDto.isEmpty()) {
            ArrayList<CustomerInfo> customersInfo = new ArrayList<>();
            for (CustomerDto customerDto : customersDto) {
                CustomerInfo customerInfo = new CustomerInfo(customerDto);
                customersInfo.add(customerInfo);
            }
            chooseCustomerComboBox.setItems(FXCollections.observableArrayList(customersInfo));
        }
        else {
            // show no customers component!
        }
    }
}
