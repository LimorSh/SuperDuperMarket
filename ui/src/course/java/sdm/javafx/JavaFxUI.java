package course.java.sdm.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFxUI extends Application{
    private static final String PRIMARY_STAGE_NAME = "Super Duper Market";
    private static final String FXML_FILE_NAME = "controller/super-duper-market.fxml";
    private static final int SCENE_WIDTH = 600;
    private static final int SCENE_HEIGHT = 400;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(PRIMARY_STAGE_NAME);

        Parent load = FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
        Scene scene = new Scene(load, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void run() {
        launch(JavaFxUI.class);
    }



}



