package course.java.sdm.javafx.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SuperDuperMarketController {

//    private BusinessLogic businessLogic;
    private Stage primaryStage;

    @FXML private Button customersButton;
    @FXML private Button storesButton;
    @FXML private Button productsButton;
    @FXML private Button ordersButton;
    @FXML private Button loadFileButton;
    @FXML private Button updateItemButton;
    @FXML private Button addOrderButton;

    private SimpleStringProperty selectedFileProperty;
    private SimpleBooleanProperty isFileSelected;

    public SuperDuperMarketController() {
        selectedFileProperty = new SimpleStringProperty();
        isFileSelected = new SimpleBooleanProperty(false);
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
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
        selectedFileProperty.set(absolutePath);
        isFileSelected.set(true);
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
