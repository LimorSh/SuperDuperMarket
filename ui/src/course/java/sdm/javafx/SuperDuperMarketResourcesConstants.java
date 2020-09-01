package course.java.sdm.javafx;

import java.net.URL;

public class SuperDuperMarketResourcesConstants {

    private static final String BASE_PACKAGE = "/course/java/sdm/javafx";
    public static final String MAIN_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/main/super-duper-market.fxml";

    private static final  String SINGLE_ITEM_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/singleItem/single-item.fxml";
    public static final URL SINGLE_ITEM_FXML_RESOURCE = SuperDuperMarketResourcesConstants.class.getResource(SuperDuperMarketResourcesConstants.SINGLE_ITEM_FXML_RESOURCE_IDENTIFIER);
}
