package course.java.sdm.javafx.components.actions.order;

import course.java.sdm.engine.dto.CustomerDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.ArrayList;
import java.util.Collection;

public class OrderController {

    @FXML private ComboBox<String> chooseCustomerComboBox;


    public void setCustomers(Collection<CustomerDto> customersDto) {
        if (!customersDto.isEmpty()) {
            ArrayList<String> strings = new ArrayList<>();
            for (CustomerDto customerDto : customersDto) {
                String str = String.format("ID %d: %s", customerDto.getId(), customerDto.getName());
                strings.add(str);
            }
            chooseCustomerComboBox.setItems(FXCollections.observableArrayList(strings));
        }
        else {
            // show no items component!
        }
    }
}
