package course.java.sdm.javafx.components.main;

import course.java.sdm.engine.engine.BusinessLogic;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;

public class SuperDuperMarketController {

    private BusinessLogic businessLogic;
    private Stage primaryStage;

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
    void addOrderButtonAction(ActionEvent event) {

    }

    @FXML
    void customersButtonAction(ActionEvent event) {

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
    void ordersButtonAction(ActionEvent event) {

    }

    @FXML
    void productsButtonAction(ActionEvent event) {

    }

    @FXML
    void storesButtonAction(ActionEvent event) {

    }

    @FXML
    void updateItemButtonAction(ActionEvent event) {

    }

}
