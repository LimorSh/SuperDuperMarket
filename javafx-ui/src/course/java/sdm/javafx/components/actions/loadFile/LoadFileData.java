package course.java.sdm.javafx.components.actions.loadFile;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.components.actions.loadFile.task.TaskLogic;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import javafx.beans.property.SimpleStringProperty;

public class LoadFileData {

    protected SimpleStringProperty selectedFileName;

    protected static final String FAILED_MSG = "Could not load the file:\nThe file you tried to load is not valid for the following reason:\n";
    protected static final String FINAL_MSG = "The system contains the last valid data.";

    protected TaskLogic taskLogic;
    protected BusinessLogic businessLogic;

    LoadFileData() {
        selectedFileName = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
    }

    public void setTaskLogic(TaskLogic taskLogic) {
        this.taskLogic = taskLogic;
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }

    public void setValuesData(String selectedFileName) {
        this.selectedFileName.set(selectedFileName);
    }
}
