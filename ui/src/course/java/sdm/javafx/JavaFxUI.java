package course.java.sdm.javafx;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.components.main.SuperDuperMarketController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

public class JavaFxUI extends Application{
    private static final String PRIMARY_STAGE_NAME = "Super Duper Market";
    private static final int SCENE_WIDTH = 1050;
    private static final int SCENE_HEIGHT = 600;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();

        // load main fxml
        URL mainFXML = getClass().getResource(SuperDuperMarketConstants.MAIN_FXML_RESOURCE_IDENTIFIER);
        loader.setLocation(mainFXML);
        BorderPane root = loader.load();

        // wire up controller
        SuperDuperMarketController superDuperMarketController = loader.getController();
        BusinessLogic businessLogic = new BusinessLogic();
        superDuperMarketController.setPrimaryStage(primaryStage);
        superDuperMarketController.setBusinessLogic(businessLogic);

        // set stage
        primaryStage.setTitle(PRIMARY_STAGE_NAME);
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


