package course.java.sdm.javafx;

import java.net.URL;

public class SuperDuperMarketConstants {

    public static final int INIT_INT = -1;
    public static final float INIT_FLOAT = -1f;
    public static final String INIT_STRING = "";

    private static final String BASE_PACKAGE = "/course/java/sdm/javafx";

    public static final String MAIN_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/main/super-duper-market.fxml";

    private static final String SINGLE_ITEM_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/singleItem/single-item.fxml";
    public static final URL SINGLE_ITEM_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.SINGLE_ITEM_FXML_RESOURCE_IDENTIFIER);
    private static final String ITEMS_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/items/items.fxml";
    public static final URL ITEMS_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ITEMS_FXML_RESOURCE_IDENTIFIER);

    private static final String SINGLE_CUSTOMER_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/singleCustomer/single-customer.fxml";
    public static final URL SINGLE_CUSTOMER_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.SINGLE_CUSTOMER_FXML_RESOURCE_IDENTIFIER);
    private static final String CUSTOMERS_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/customers/customers.fxml";
    public static final URL CUSTOMERS_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.CUSTOMERS_FXML_RESOURCE_IDENTIFIER);

    private static final String ORDER_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/order.fxml";
    public static final URL ORDER_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ORDER_FXML_RESOURCE_IDENTIFIER);

    private static final String STATIC_ORDER_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/staticOrder/static-order.fxml";
    public static final URL STATIC_ORDER_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.STATIC_ORDER_FXML_RESOURCE_IDENTIFIER);

    private static final String STORE_ITEMS_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/storeItems/store-items.fxml";
    public static final URL STORE_ITEMS_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.STORE_ITEMS_FXML_RESOURCE_IDENTIFIER);
}
