package course.java.sdm.javafx;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.components.main.SuperDuperMarketController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

//import org.fxmisc.cssfx.CSSFX;

import java.net.URL;

public class JavaFxUI extends Application{
    private static final String PRIMARY_STAGE_NAME = "Super Duper Market";
    private static final String FXML_FILE_NAME = "/course/java/sdm/javafx/components/main/super-duper-market.fxml";
    private static final int SCENE_WIDTH = 1050;
    private static final int SCENE_HEIGHT = 600;


    public static void main(String[] args) {
        launch(args);
    }

//    public void run() {
//        launch(JavaFxUI.class);
//    }


    @Override
    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle(PRIMARY_STAGE_NAME);
//
//        Parent load = FXMLLoader.load(getClass().getResource(FXML_FILE_NAME));
//        Scene scene = new Scene(load, SCENE_WIDTH, SCENE_HEIGHT);
//        primaryStage.setScene(scene);
//        primaryStage.show();

//        CSSFX.start();

        FXMLLoader loader = new FXMLLoader();

        // load main fxml
        URL mainFXML = getClass().getResource(FXML_FILE_NAME);
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



