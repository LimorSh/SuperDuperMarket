package course.java.sdm.javafx.components.main;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.main.controller.SuperDuperMarketController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;

import static course.java.sdm.javafx.SuperDuperMarketConstants.BASE_PACKAGE;

public class JavaFxUI extends Application{
    private static final String PRIMARY_STAGE_NAME = "Super Duper Market";
    private static final int SCENE_WIDTH = 1052;
    private static final int SCENE_HEIGHT = 602;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();

        // load main fxml
        URL mainFXML = getClass().getResource(SuperDuperMarketConstants.MAIN_FXML_RESOURCE_IDENTIFIER);
        loader.setLocation(mainFXML);
        Parent root = loader.load();

        // wire up controller
        SuperDuperMarketController superDuperMarketController = loader.getController();
        BusinessLogic businessLogic = new BusinessLogic();
        superDuperMarketController.setPrimaryStage(primaryStage);
        superDuperMarketController.setBusinessLogic(businessLogic);

        // set stage
        primaryStage.setTitle(PRIMARY_STAGE_NAME);
        primaryStage.getIcons().add(new Image(SuperDuperMarketConstants.CART_ICON_IDENTIFIER));
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



