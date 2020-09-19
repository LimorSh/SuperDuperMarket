package course.java.sdm.intermediate.task;

import course.java.sdm.engine.Utils;
import javafx.concurrent.Task;

public class CollectMetadataTask extends Task<Boolean> {

    private static final int SLEEP_TIME = 2000;

    @Override
    protected Boolean call() {
        updateMessage("Fetching file...");
        updateProgress(0,1);
        Utils.sleepForAWhile(SLEEP_TIME);
        updateMessage("Trying loading system data...");
        updateProgress(0.5,1);
        Utils.sleepForAWhile(SLEEP_TIME);
        updateProgress(1,1);
        updateMessage("Done.");
        return Boolean.TRUE;
    }
}
