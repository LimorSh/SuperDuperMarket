package course.java.sdm.javafx.components.actions.order;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.javafx.dto.UIOrderDto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


import java.util.ArrayList;
import java.util.Collection;

public class OrderController {

//    @FXML private ComboBox<String> chooseCustomerComboBox;
//    @FXML private ComboBox<BasicCustomerDto> chooseCustomerComboBox;
    @FXML private ComboBox<CustomerInfo> chooseCustomerComboBox;
    @FXML private Button confirmOrderButton;
    @FXML private DatePicker datePicker;
    @FXML private RadioButton staticOrderRadioButton;
    @FXML private RadioButton dynamicOrderRadioButton;

    private final ToggleGroup orderTypeRadioButtonsGroup;
    private final UIOrderDto uiOrderDto;

    public OrderController() {
        uiOrderDto = new UIOrderDto();
        orderTypeRadioButtonsGroup =  new ToggleGroup();
    }

    @FXML
    private void initialize() {
        setOrderTypeRadioButtons();
    }

    private void setOrderTypeRadioButtons() {
        staticOrderRadioButton.setToggleGroup(orderTypeRadioButtonsGroup);
        staticOrderRadioButton.setSelected(true);
        dynamicOrderRadioButton.setToggleGroup(orderTypeRadioButtonsGroup);
    }

    @FXML
    void confirmOrderButtonAction(ActionEvent event) {
        finishOrdering();
    }

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

    public void finishOrdering() {
        uiOrderDto.setCustomerId(chooseCustomerComboBox.getValue().getId());
        uiOrderDto.setDate(datePicker.getValue());
    }



}
