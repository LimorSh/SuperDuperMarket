package course.java.sdm.intermediate.task;

import course.java.sdm.javafx.components.actions.loadFile.LoadFileController;
import javafx.concurrent.Task;

public class TaskLogic {
    private final LoadFileController loadFileController;
    private Task<Boolean> currentRunningTask;

    public TaskLogic(LoadFileController loadFileController) {
        this.loadFileController = loadFileController;
    }

    public void collectMetadata() {
        currentRunningTask = new CollectMetadataTask();
        loadFileController.bindTaskToUIComponents(currentRunningTask);
        new Thread(currentRunningTask).start();
    }
}
