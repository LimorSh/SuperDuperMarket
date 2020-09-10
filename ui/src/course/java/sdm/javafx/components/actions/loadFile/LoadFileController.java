package course.java.sdm.javafx.components.actions.loadFile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoadFileController {

    @FXML private Label msgLabel;
    @FXML private Label FinalMsgLabel;

    private static final String FAILED_MSG = "Could not load the file:\nThe file you tried to load is not valid for the following reason:\n";
    private static final String FINAL_MSG = "\n\nThe system contains the last valid data.";

    @FXML
    private void initialize() {
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

