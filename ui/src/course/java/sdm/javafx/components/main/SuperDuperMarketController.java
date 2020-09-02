package course.java.sdm.javafx.components.main;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.engine.dto.ItemDto;
import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.engine.engine.Customer;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.customers.CustomersController;
import course.java.sdm.javafx.components.items.ItemsController;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class SuperDuperMarketController {

    private BusinessLogic businessLogic;
    private Stage primaryStage;

    @FXML private BorderPane superDuperMarketBorderPane;
//    @FXML private FlowPane superDuperMarketFlowPane;
    @FXML private Button customersButton;
    @FXML private Button storesButton;
    @FXML private Button productsButton;
    @FXML private Button ordersButton;
    @FXML private Button loadFileButton;
    @FXML private Button updateItemButton;
    @FXML private Button addOrderButton;

    private SimpleBooleanProperty isFileSelected;

    public SuperDuperMarketController() {
        isFileSelected = new SimpleBooleanProperty(false);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    @FXML
    private void initialize() {
        customersButton.disableProperty().bind(isFileSelected.not());
        storesButton.disableProperty().bind(isFileSelected.not());
        productsButton.disableProperty().bind(isFileSelected.not());
        ordersButton.disableProperty().bind(isFileSelected.not());
        updateItemButton.disableProperty().bind(isFileSelected.not());
        addOrderButton.disableProperty().bind(isFileSelected.not());
    }

    @FXML
    void loadFileButtonAction() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select system data file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("xml files", "*.xml"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile == null) {
            return;
        }

        String absolutePath = selectedFile.getAbsolutePath();
        try {
            businessLogic.loadSystemData(absolutePath);
            isFileSelected.set(true);
        }
        catch (Exception e) {
            // activate file error component!!!
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void updateItemButtonAction(ActionEvent event) {

    }

    @FXML
    void addOrderButtonAction(ActionEvent event) {

    }

    @FXML
    void customersButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.CUSTOMERS_FXML_RESOURCE);
            Node customers = loader.load();
            CustomersController customersController = loader.getController();

            Collection<CustomerDto> customersDto = businessLogic.getCustomersDto();
            customersController.createAllCustomers(customersDto);

            superDuperMarketBorderPane.setCenter(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void storesButtonAction(ActionEvent event) {

    }

    @FXML
    void productsButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.ITEMS_FXML_RESOURCE);
            Node items = loader.load();
            ItemsController itemsController = loader.getController();

            Collection<ItemDto> itemsDto = businessLogic.getItemsDto();
            itemsController.createAllItems(itemsDto);

            superDuperMarketBorderPane.setCenter(items);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ordersButtonAction(ActionEvent event) {

    }
}