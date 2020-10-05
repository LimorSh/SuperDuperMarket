package course.java.sdm.javafx.components.actions.loadFile;

import course.java.sdm.javafx.components.main.controller.SuperDuperMarketController;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class LoadFileController extends LoadFileData{

    @FXML private Label selectedFileNameLabel;

    @FXML private Label progressPercentLabel;
    @FXML private ProgressBar taskProgressBar;
    @FXML private Label taskMessageLabel;
    @FXML private Label msgLabel;
    @FXML private Label FinalMsgLabel;
    @FXML private Button collectMetadataButton;

    private SuperDuperMarketController superDuperMarketController;


    @FXML
    private void initialize() {
        selectedFileNameLabel.textProperty().bind(selectedFileName);
    }

    @FXML
    public void collectMetadataAction() {
        taskLogic.collectMetadata();
    }

    public void bindTaskToUIComponents(Task<Boolean> aTask) {
        // task message
        taskMessageLabel.textProperty().bind(aTask.messageProperty());

        // task progress bar
        taskProgressBar.progressProperty().bind(aTask.progressProperty());

        // task percent label
        progressPercentLabel.textProperty().bind(
                Bindings.concat(
                        Bindings.format(
                                "%.0f",
                                Bindings.multiply(
                                        aTask.progressProperty(),
                                        100)),
                        " %"));

        // task finish
        aTask.valueProperty().addListener((observable, oldValue, newValue) -> {
            onTaskFinished();
        });
    }

    public void onTaskFinished() {
        try {
            businessLogic.loadSystemData(selectedFileName.getValue());
            superDuperMarketController.setFileSelected();
            showMsg(true, true, "");
        }
        catch (Exception e) {
            boolean firstTime = !superDuperMarketController.getIsFileSelected();
            showMsg(false, firstTime, e.getMessage());
        }
        finally {
            collectMetadataButton.setDisable(true);
        }
    }

    public void setSuperDuperMarketController(SuperDuperMarketController superDuperMarketController) {
        this.superDuperMarketController = superDuperMarketController;
    }

    public void showMsg(boolean loadedSuccessfully, boolean firstTime, String msg) {
        if (loadedSuccessfully) {
            msgLabel.setText("The file was loaded successfully!");
        }
        else {
            msgLabel.setStyle("-fx-text-fill: #ff0000;");
            msgLabel.setText(FAILED_MSG + msg);
            if (!firstTime) {
                FinalMsgLabel.setText(FINAL_MSG);
            }
        }
    }
}

