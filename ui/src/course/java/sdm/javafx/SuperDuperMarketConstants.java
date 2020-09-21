package course.java.sdm.javafx;

import java.net.URL;

public class SuperDuperMarketConstants {

    public static final int INIT_INT = 0;
    public static final float INIT_FLOAT = 0f;
    public static final double INIT_DOUBLE = 0d;
    public static final String INIT_STRING = "";
    public static final boolean INIT_BOOLEAN = false;
    public static final int NO_STORE_ID = -1;

    public static final String STATIC_ORDER_CATEGORY = "one store";
    public static final String DYNAMIC_ORDER_CATEGORY = "best cart";

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

    private static final String SINGLE_ORDER_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/singleOrder/single-order.fxml";
    public static final URL SINGLE_ORDER_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.SINGLE_ORDER_FXML_RESOURCE_IDENTIFIER);
    private static final String ORDERS_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/orders/orders.fxml";
    public static final URL ORDERS_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ORDERS_FXML_RESOURCE_IDENTIFIER);

    private static final String SINGLE_STORE_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/singleStore/single-store.fxml";
    public static final URL SINGLE_STORE_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.SINGLE_STORE_FXML_RESOURCE_IDENTIFIER);
    private static final String STORES_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/stores/stores.fxml";
    public static final URL STORES_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.STORES_FXML_RESOURCE_IDENTIFIER);

    private static final String SINGLE_DISCOUNT_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/singleStore/singleDiscount/single-discount.fxml";
    public static final URL SINGLE_DISCOUNT_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.SINGLE_DISCOUNT_FXML_RESOURCE_IDENTIFIER);

    private static final String SINGLE_STORE_IN_ORDER_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/singleOrder/singleStore/single-store-in-order.fxml";
    public static final URL SINGLE_STORE_IN_ORDER_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.SINGLE_STORE_IN_ORDER_FXML_RESOURCE_IDENTIFIER);
    private static final String ALL_STORES_IN_ORDER_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/singleOrder/allStores/all-stores.fxml";
    public static final URL ALL_STORES_IN_ORDER_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ALL_STORES_IN_ORDER_FXML_RESOURCE_IDENTIFIER);

    private static final String LOCATION_MAP_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/sdmData/locationMap/location-map.fxml";
    public static final URL LOCATION_MAP_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.LOCATION_MAP_FXML_RESOURCE_IDENTIFIER);

    private static final String SINGLE_DISCOUNT_IN_ADD_ORDER_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/discounts/singleDiscount/single-discount.fxml";
    public static final URL SINGLE_DISCOUNT_IN_ADD_ORDER_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.SINGLE_DISCOUNT_IN_ADD_ORDER_FXML_RESOURCE_IDENTIFIER);
    private static final String ALL_DISCOUNTS_IN_ADD_ORDER_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/discounts/discounts.fxml";
    public static final URL ALL_DISCOUNTS_IN_ADD_ORDER_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ALL_DISCOUNTS_IN_ADD_ORDER_FXML_RESOURCE_IDENTIFIER);

    private static final String ORDER_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/order.fxml";
    public static final URL ORDER_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ORDER_FXML_RESOURCE_IDENTIFIER);

    private static final String STORE_ITEMS_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/storeItems/store-items.fxml";
    public static final URL STORE_ITEMS_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.STORE_ITEMS_FXML_RESOURCE_IDENTIFIER);

    private static final String ORDER_SUMMERY_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/summery/order-summery.fxml";
    public static final URL ORDER_SUMMERY_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ORDER_SUMMERY_FXML_RESOURCE_IDENTIFIER);

    private static final String ORDER_SUMMERY_STORES_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/summery/stores/order-summery-stores.fxml";
    public static final URL ORDER_SUMMERY_STORES_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ORDER_SUMMERY_STORES_FXML_RESOURCE_IDENTIFIER);

    private static final String ORDER_SUMMERY_SINGLE_STORE_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/summery/singleStore/order-summery-single-store.fxml";
    public static final URL ORDER_SUMMERY_SINGLE_STORE_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ORDER_SUMMERY_SINGLE_STORE_FXML_RESOURCE_IDENTIFIER);

    private static final String UPDATE_ITEM_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/updateItem/update-item.fxml";
    public static final URL UPDATE_ITEM_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.UPDATE_ITEM_FXML_RESOURCE_IDENTIFIER);

    private static final String ADD_STORE_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/addStore/add-store.fxml";
    public static final URL ADD_STORE_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ADD_STORE_FXML_RESOURCE_IDENTIFIER);

    private static final String ADD_ITEM_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/addItem/add-item.fxml";
    public static final URL ADD_ITEM_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.ADD_ITEM_FXML_RESOURCE_IDENTIFIER);

    private static final String LOAD_FILE_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/loadFile/load-file.fxml";
    public static final URL LOAD_FILE_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.LOAD_FILE_FXML_RESOURCE_IDENTIFIER);

    private static final String DYNAMIC_ORDER_SINGLE_STORE_SUMMERY_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/summery/dynamicOrder/singleStore/dynamic-order-single-store-summery.fxml";
    public static final URL DYNAMIC_ORDER_SINGLE_STORE_SUMMERY_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.DYNAMIC_ORDER_SINGLE_STORE_SUMMERY_FXML_RESOURCE_IDENTIFIER);
    private static final String DYNAMIC_ORDER_STORES_SUMMERY_FXML_RESOURCE_IDENTIFIER = BASE_PACKAGE + "/components/actions/order/summery/dynamicOrder/dynamic-order-stores-summery.fxml";
    public static final URL DYNAMIC_ORDER_STORES_SUMMERY_FXML_RESOURCE = SuperDuperMarketConstants.class.getResource(SuperDuperMarketConstants.DYNAMIC_ORDER_STORES_SUMMERY_FXML_RESOURCE_IDENTIFIER);

    public static void printURLs() {
        // for checking the URLs
        System.out.println("1." + SINGLE_ITEM_FXML_RESOURCE.toExternalForm());
        System.out.println("2." + ITEMS_FXML_RESOURCE.toExternalForm());
        System.out.println("3." + CUSTOMERS_FXML_RESOURCE.toExternalForm());
        System.out.println("4." + SINGLE_ORDER_FXML_RESOURCE.toExternalForm());
        System.out.println("5." + ORDERS_FXML_RESOURCE.toExternalForm());
        System.out.println("6." + SINGLE_STORE_FXML_RESOURCE.toExternalForm());
        System.out.println("7." + STORES_FXML_RESOURCE.toExternalForm());
        System.out.println("8." + SINGLE_DISCOUNT_FXML_RESOURCE.toExternalForm());
        System.out.println("9." + SINGLE_STORE_IN_ORDER_FXML_RESOURCE.toExternalForm());
        System.out.println("10." + ALL_STORES_IN_ORDER_FXML_RESOURCE.toExternalForm());
        System.out.println("11." + LOCATION_MAP_FXML_RESOURCE.toExternalForm());
        System.out.println("12." + SINGLE_DISCOUNT_IN_ADD_ORDER_FXML_RESOURCE.toExternalForm());
        System.out.println("13." + ALL_DISCOUNTS_IN_ADD_ORDER_FXML_RESOURCE.toExternalForm());
        System.out.println("14." + ORDER_FXML_RESOURCE.toExternalForm());
        System.out.println("15." + STORE_ITEMS_FXML_RESOURCE.toExternalForm());
        System.out.println("16." + ORDER_SUMMERY_FXML_RESOURCE.toExternalForm());
        System.out.println("17." + ORDER_SUMMERY_STORES_FXML_RESOURCE.toExternalForm());
        System.out.println("18." + ORDER_SUMMERY_SINGLE_STORE_FXML_RESOURCE.toExternalForm());
        System.out.println("19." + UPDATE_ITEM_FXML_RESOURCE.toExternalForm());
        System.out.println("20." + LOAD_FILE_FXML_RESOURCE.toExternalForm());
        System.out.println("21." + DYNAMIC_ORDER_SINGLE_STORE_SUMMERY_FXML_RESOURCE.toExternalForm());
        System.out.println("22." + DYNAMIC_ORDER_STORES_SUMMERY_FXML_RESOURCE.toExternalForm());
    }
}
