package course.java.sdm.console;
import course.java.sdm.engine.systemDto.*;
import course.java.sdm.engine.SystemManager;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;

public class UI {

    private static final String SEPARATOR_LINE = "\n----------------------------------------------------------" +
            "---------------------------------------------------------";
    private static final String COMA_SEPARATOR = ", ";
    private static final String SPACE_SEPARATOR = " ";
    private static final String WELCOME_MESSAGE_STR = "Welcome to Super Duper Market!";
    private static final String EXIT_MESSAGE_STR = "Thank you for buying in super duper market :)\nGoodbye!";
    private static final String PLEASE_CHOOSE_ACTION_STR = "Please choose an option from the menu:";
    private static final String USER_FINISHED_CHOOSE_ITEMS_KEY = "q";
    private static final String ORDER_DATE_FORMAT = "dd/MM-hh:mm";
    private static final int MAXIMUM_FRACTION_DIGITS = 2;
    private static final DecimalFormat DECIMAL_FORMAT;
    private static final DateFormat DATE_FORMAT;

    static {
        DECIMAL_FORMAT = new DecimalFormat();
        DECIMAL_FORMAT.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
        DATE_FORMAT = new SimpleDateFormat(ORDER_DATE_FORMAT);
    }

    private enum APPROVAL{
        YES("y");

        private final String key;

        APPROVAL(String yes_key) {
            this.key = yes_key;
        }
    }

    private enum CANCEL{
        NO("n");

        private final String key;

        CANCEL(String yes_key) {
            this.key = yes_key;
        }
    }

    private enum MenuOptions {
        LOAD_SYSTEM_DATA(1, "Load system data"),
        SHOW_STORES(2, "Show the super stores"),
        SHOW_ITEMS(3, "Show the super items"),
        CREATE_ORDER(4, "Create new order"),
        SHOW_ORDERS_HISTORY(5, "Show order history"),
        EXIT(6, "Exit")
        ;

        private final int optionNumber;
        private final String optionTitle;

        MenuOptions(int optionNumber, String optionTitle) {
            this.optionNumber = optionNumber;
            this.optionTitle = optionTitle;
        }

        public static MenuOptions getMenuOptions(int optionNumber) {
            for (MenuOptions menuOptions : MenuOptions.values()) {
                if (menuOptions.optionNumber == optionNumber)
                    return menuOptions;
            }
            String errorMsg = optionNumber + " is not an option in the menu.";
            throw new IllegalArgumentException(errorMsg);
        }
    }


    private int getIntInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String getStringInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private float getFloatInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextFloat();
    }

    private String getTokenInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private void exit() {
        System.out.println(EXIT_MESSAGE_STR);
        System.exit(1);
    }

    public void run() {
        printWelcome();
        loadSystemDataFirstTime();
        loopProgram();
    }

    private void loopProgram() {
        showMenu();

        try {
            int option = getIntInputFromUser();
            MenuOptions menuOptions = MenuOptions.getMenuOptions(option);
            handleUserAction(menuOptions);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        catch (InputMismatchException e1) {
            System.out.println("The input you entered is not a number.");
        }
        finally {
            loopProgram();
        }
    }

    private void printWelcome() {
        System.out.println(WELCOME_MESSAGE_STR);
    }

    private void showMenu() {
        System.out.println();
        System.out.println(PLEASE_CHOOSE_ACTION_STR);
        for (MenuOptions menuOptions : MenuOptions.values()) {
            System.out.println(menuOptions.optionNumber + ". " + menuOptions.optionTitle);
        }
        System.out.println();
    }

    private void handleUserAction(MenuOptions menuOptions) {
        switch (menuOptions) {
            case LOAD_SYSTEM_DATA:
                loadSystemData();
                break;
            case SHOW_STORES:
                showAllStores();
                break;
            case SHOW_ITEMS:
                showAllItems();
                break;
            case CREATE_ORDER:
                createOrder();
                break;
            case SHOW_ORDERS_HISTORY:
                showOrdersHistory();
                break;
            case EXIT:
                exit();
                break;
        }
    }

    private void printSeparatorLine() {
        System.out.println(SEPARATOR_LINE);
    }

    private void showStore(StoreDto store) {
        showStoreBasicDetails(store);
        System.out.print(COMA_SEPARATOR);
        System.out.print("Total deliveries revenue: " + DECIMAL_FORMAT.format(store.getTotalDeliveriesRevenue()));

        Collection<StoreItemDto> storeItems = store.getStoreItemsDto();

        if (!storeItems.isEmpty()) {
            System.out.println();
            System.out.println();
            System.out.println("The items in the store are:");
            for (StoreItemDto storeItem : storeItems) {
                showItemBasicDetails(storeItem);
                System.out.print(COMA_SEPARATOR);
                System.out.print("Price: " + storeItem.getPrice() + COMA_SEPARATOR);
                System.out.print("Total sold in the store: " + DECIMAL_FORMAT.format(storeItem.getTotalSold()));
                System.out.println();
            }
        }
        else {
            System.out.println("There are no items in the store.");
        }

        Collection<OrderDto> orders = store.getOrdersDto();
        if (!orders.isEmpty()) {
            System.out.println();
            System.out.println("The orders in the store are:");
            for (OrderDto orderDto : orders) {
                System.out.print("Date: " + covertDateToDateStr(orderDto.getDate()) + COMA_SEPARATOR);
                System.out.print("Total items: " + orderDto.getTotalItems() + COMA_SEPARATOR);
                System.out.print("Items cost: " + DECIMAL_FORMAT.format(orderDto.getItemsCost()) + COMA_SEPARATOR);
                System.out.print("Delivery cost: " + DECIMAL_FORMAT.format(orderDto.getDeliveryCost()) + COMA_SEPARATOR);
                System.out.print("Total cost: " + DECIMAL_FORMAT.format(orderDto.getTotalCost()));
                System.out.println();
            }
        }
        else {
            System.out.println("There are no orders in the store.");
        }

        printSeparatorLine();
    }

    private void showAllStores() {
        System.out.println(SEPARATOR_LINE);
        Collection<StoreDto> stores = SystemManager.getStoresDto();
        System.out.println("The stores in the super market are:");
        for (StoreDto store : stores) {
            showStore(store);
        }
    }

    private void showItemBasicDetails(ItemDto item) {
        System.out.print("ID: " + item.getId() + COMA_SEPARATOR);
        System.out.print("Name: " + item.getName() + COMA_SEPARATOR);
        System.out.print("Purchase Category: " + item.getPurchaseCategory());
    }

    private void showStoreBasicDetails(StoreDto store) {
        System.out.print("ID: " + store.getId() + COMA_SEPARATOR);
        System.out.print("Name: " + store.getName() + COMA_SEPARATOR);
        System.out.print("PPK: " + store.getPpk());
    }

    private void showItem(ItemDto item) {
        showItemBasicDetails(item);
        System.out.println();
        int numberOfStoresSellingTheItem = SystemManager.getNumberOfStoresSellingTheItem(item);
        float averageItemPrice = SystemManager.getAverageItemPrice(item);
        float totalAmountOfItemSells = SystemManager.getTotalAmountOfItemSells(item);
        System.out.print("       ");
        System.out.print("The number of stores selling the item: " + numberOfStoresSellingTheItem + COMA_SEPARATOR);
        System.out.print("The average price of the item: " + DECIMAL_FORMAT.format(averageItemPrice) + COMA_SEPARATOR);
        System.out.print("The total amount of item sells: " + DECIMAL_FORMAT.format(totalAmountOfItemSells));
        System.out.println();
    }

    private void showAllItems() {
        System.out.println(SEPARATOR_LINE);

        Collection<ItemDto> items = SystemManager.getItemsDto();

        if (!items.isEmpty()) {
            System.out.println("The items in the super market are:");
            for (ItemDto item : items) {
                showItem(item);
            }
        }
        else {
            System.out.println("There are no items in the super market.");
        }
    }

    private void showItemsPerStore(StoreDto store) {
        Collection<ItemDto> items = SystemManager.getItemsDto();
        System.out.println("The items in the super market are:");
        for (ItemDto itemDto : items) {
            showItemBasicDetails(itemDto);
            System.out.print(COMA_SEPARATOR);
            if(SystemManager.isItemInTheStoreDto(store, itemDto)) {
                float price = SystemManager.getItemPriceInStore(store, itemDto);
                System.out.println("Price: " + DECIMAL_FORMAT.format(price));
            }
            else
                System.out.println("Item is not available.");
        }
    }

    private boolean continueOrder(String userInput) {
        return !(userInput.equalsIgnoreCase(USER_FINISHED_CHOOSE_ITEMS_KEY));
    }

    private Map<Integer, Float> getItemsIdsAndQuantitiesFromUser() {
        Map<Integer, Float> itemsIdsAndQuantities = new HashMap<>();

        System.out.print("Please start buying by enter item ID, or press 'q' to exit: ");
        String userIdOrQ = getStringInputFromUser();
        boolean toContinue = continueOrder(userIdOrQ);

        while(toContinue) {
            try {
                int itemId =  Integer.parseInt(userIdOrQ);
                System.out.print("Please enter item quantity: ");
                float quantity = getFloatInputFromUser();
                if (SystemManager.getItemPurchaseCategory(itemId).equals(SystemManager.getItemPurchaseCategoryPerUnitStr())) {
                    if ((quantity % 1) != 0) {
                        //throw exception
                        //move this check to another function that returns build-in exception
                    }
                }

                float totalQuantity = quantity;
                if (itemsIdsAndQuantities.containsKey(itemId)) {
                    totalQuantity += itemsIdsAndQuantities.get(itemId);
                }
                itemsIdsAndQuantities.put(itemId, totalQuantity);

                System.out.print("Please continue buying and enter item ID, or press 'q' to finish: ");
                userIdOrQ = getStringInputFromUser();
                toContinue = continueOrder(userIdOrQ);
            }
            catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }

        return itemsIdsAndQuantities;
    }

    private void showOrderSummery(Map<Integer, Float> itemsIdsAndQuantities, StoreDto store) {
        System.out.println("Order Summery:");
        int itemId;
        float itemQuantity;
        String itemName;
        String itemPurchaseCategory;
        float itemPrice;
        float itemTotalCost;

        System.out.println("Purchased Items:");
        for (Map.Entry<Integer, Float> entry : itemsIdsAndQuantities.entrySet()) {
            itemId = entry.getKey();
            itemQuantity = entry.getValue();
            System.out.print("ID: " + itemId + COMA_SEPARATOR);
            itemName = SystemManager.getItemName(itemId);
            System.out.print("Name: " + itemName + COMA_SEPARATOR);
            itemPurchaseCategory = SystemManager.getItemPurchaseCategory(itemId);
            System.out.print("Purchase category: " + itemPurchaseCategory + COMA_SEPARATOR);
            itemPrice = SystemManager.getItemPriceInStoreByIds(store.getId(), itemId);
            System.out.print("Item price: " + DECIMAL_FORMAT.format(itemPrice) + COMA_SEPARATOR);
            System.out.print("Quantity: " + DECIMAL_FORMAT.format(itemQuantity) + COMA_SEPARATOR);
            itemTotalCost = itemQuantity * itemPrice;
            System.out.print("Total item cost: " + DECIMAL_FORMAT.format(itemTotalCost));
            System.out.println();
        }
    }

    private Date covertDateStrToDate(String dateStr) throws ParseException {
        return DATE_FORMAT.parse(dateStr);
    }

    private String covertDateToDateStr(Date date) {
        return DATE_FORMAT.format(date);
    }

    private boolean orderConfirmed() {
        System.out.println("Please press " + APPROVAL.YES.key + " to confirm your order or " + CANCEL.NO.key + " to cancel");
        String userConfirmation = getStringInputFromUser();

        if (userConfirmation.equalsIgnoreCase(APPROVAL.YES.key)) {
            return true;
        }
        else if (userConfirmation.equalsIgnoreCase(CANCEL.NO.key)) {
            return false;
        }

        String errorMsg = userConfirmation + "is not " + APPROVAL.YES.key + " or " + CANCEL.NO.key + ".";
        throw new IllegalArgumentException(errorMsg);
    }

    private void createOrder() {
        showAllStores();
        try {
            System.out.print("Please enter store ID: ");
            int storeId = getIntInputFromUser();
            System.out.print("Please enter order's date: ");
            String dateStr = getStringInputFromUser();

            // enter this to loop somehow
            Date date = null;
            try {
                date = covertDateStrToDate(dateStr);
            }
            catch (ParseException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("Please enter your location:");
            System.out.print("X: ");
            int customerLocationX = getIntInputFromUser();
            System.out.print("Y: ");
            int customerLocationY = getIntInputFromUser();
            StoreDto store = SystemManager.getStoreDto(storeId);
            showItemsPerStore(store);
            System.out.println();

            Map<Integer, Float> itemsIdsAndQuantities = getItemsIdsAndQuantitiesFromUser();
            if (!itemsIdsAndQuantities.isEmpty()) {
                System.out.println();
                showOrderSummery(itemsIdsAndQuantities, store);

                int storePpk = store.getPpk();
                double distanceBetweenCustomerAndStore = SystemManager.getDistanceBetweenCustomerAndStore(store, customerLocationX, customerLocationY);
                float deliveryCost = storePpk * (float) distanceBetweenCustomerAndStore;
                System.out.println("The delivery cost is: " + DECIMAL_FORMAT.format(deliveryCost));
                System.out.println("The store ppk is: " + storePpk);
                System.out.println("Your distance from the store is: " + DECIMAL_FORMAT.format(distanceBetweenCustomerAndStore));
                System.out.println();

                // enter this to loop somehow
                try {
                    boolean orderConfirmed = orderConfirmed();
                    if (orderConfirmed) {
                        SystemManager.createOrder(date, customerLocationX, customerLocationY, store, itemsIdsAndQuantities);
                        System.out.println("Your order was confirmed and added successfully!");
                    }
                    else {
                        System.out.println("Your order was canceled.");
                    }
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }

//                System.out.println("Please press " + APPROVAL.YES.key + " to confirm your order or " + CANCEL.NO.key + " to cancel");
//                String userConfirmation = getStringInputFromUser();
//                if (userConfirmation.equalsIgnoreCase(APPROVAL.YES.key)) {
//                    // move this check to another function that returns build-in exception.
//                    SystemManager.createOrder(date, customerLocationX, customerLocationY, store, itemsIdsAndQuantities);
//                }
//                else {
//                    System.out.println("Your order was not confirmed.");
//                }
            }
        }
        catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showOrdersHistory() {
        System.out.println("The orders in the super market are:");
        Collection<OrderDto> ordersDto = SystemManager.getOrdersDto();

        if (!ordersDto.isEmpty()) {
            System.out.println();
            for (OrderDto orderDto : ordersDto) {
                int orderId = orderDto.getId();
                System.out.print("ID: " + orderId + COMA_SEPARATOR);
                System.out.print("Date: " + covertDateToDateStr(orderDto.getDate()) + COMA_SEPARATOR);
                System.out.print("Store ID: " + orderDto.getStoreId() + COMA_SEPARATOR);
                System.out.print("Store name: " + orderDto.getStoreName() + COMA_SEPARATOR);
                System.out.print("Total types of items: " + orderDto.getTotalItemsTypes() + COMA_SEPARATOR);
                System.out.print("Total amount of items: " + orderDto.getTotalItems() + COMA_SEPARATOR);
                System.out.println();
                System.out.print("       ");
                System.out.print("Items cost: " + DECIMAL_FORMAT.format(orderDto.getItemsCost()) + COMA_SEPARATOR);
                System.out.print("Delivery cost: " + DECIMAL_FORMAT.format(orderDto.getDeliveryCost()) + COMA_SEPARATOR);
                System.out.print("Total cost: " + DECIMAL_FORMAT.format(orderDto.getTotalCost()));
                System.out.println();
            }
        }
        else {
            System.out.println("There are no orders in the super market.");
        }
    }

    private void loadSystemDataFirstTime() {
        loadSystemData();
    }

    private void loadSystemData() {
        System.out.println("Please enter the xml file path you would like to load: ");
        String filePath = getStringInputFromUser();
        SystemManager.loadSystemData(filePath);
    }






}