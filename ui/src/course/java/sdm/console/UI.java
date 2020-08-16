package course.java.sdm.console;
import course.java.sdm.engine.exceptions.StoreLocationExistsException;
import course.java.sdm.engine.systemDto.*;
import course.java.sdm.engine.SystemManager;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.FileNotFoundException;
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
        LOAD_SYSTEM_DATA(1, "Load xml file - system data"),
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
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextInt();
            }
            catch (Exception e) {
                System.out.println("The input you entered is not an integer number!");
                System.out.print("Please enter an integer number and try again: ");
            }
        }
    }

    private String getStringInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private float getFloatInputFromUser() {
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                return scanner.nextFloat();
            }
            catch (Exception e) {
                System.out.println("The input you entered is not a float number!");
                System.out.print("Please enter a float number and try again: ");
            }
        }
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
                try {
                    loadSystemData();
                }
                catch (Exception e) {
                    System.out.println("The xml file you tried to load is not valid for the following reason:");
                    System.out.println(e.getMessage());
                    System.out.println("The system contains the last valid data.");
                }
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

    private int getValidItemIdFromUser(int storeId, int itemId) {
        boolean isValidInput = false;
        int intInput = itemId;
        while (!isValidInput) {
            try {
                SystemManager.validateItemIdExistsInStore(storeId, intInput);
                isValidInput = true;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.print("Please enter valid item ID: ");
                intInput = getIntInputFromUser();
            }
        }
        return intInput;
    }

    private void validateInputIdOrQ(String input) {
        if (input.equalsIgnoreCase(USER_FINISHED_CHOOSE_ITEMS_KEY)) {
            return;
        }
        Integer.parseInt(input);
    }

    private String getIdOrQFromUser() {
        String userIdOrQ = null;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                userIdOrQ = getStringInputFromUser();
                validateInputIdOrQ(userIdOrQ);
                isValidInput = true;
            }
            catch (Exception e) {
                System.out.println("The input you entered was: " + userIdOrQ + ". It should be an integer number or 'q' only.");
                System.out.print("Please try again: ");
            }
        }
        return userIdOrQ;
    }

    private float getValidItemQuantityFromUser(int itemId) {
        boolean isValidInput = false;
        float quantity = itemId;
        while (!isValidInput) {
            quantity = getFloatInputFromUser();
            if (quantity <= 0) {
                System.out.println("Item quantity should be greater than zero.");
                System.out.print("Please try again: ");
            }
            else {
                String purchaseCategory = SystemManager.getItemPurchaseCategory(itemId);
                if (purchaseCategory.equals(SystemManager.getItemPurchaseCategoryPerUnitStr())) {
                    if ((quantity % 1) != 0) {
                        System.out.println("The purchase category of the item you chose is " + purchaseCategory + ".");
                        System.out.print("Please enter item quantity in units: ");
                    }
                    else {
                        isValidInput = true;
                    }
                }
                else {
                    isValidInput = true;
                }
            }
        }
        return quantity;
    }

    private Map<Integer, Float> getItemsIdsAndQuantitiesFromUser(StoreDto store) {
        Map<Integer, Float> itemsIdsAndQuantities = new HashMap<>();

        System.out.print("Please start buying by enter item ID, or press 'q' to exit: ");
        String userIdOrQ = getIdOrQFromUser();
        boolean toContinue = continueOrder(userIdOrQ);

        while(toContinue) {
            int intInput =  Integer.parseInt(userIdOrQ);
            int itemId = getValidItemIdFromUser(store.getId(), intInput);

            System.out.print("Please enter item quantity: ");
            float quantity = getValidItemQuantityFromUser(itemId);
            if (itemsIdsAndQuantities.containsKey(itemId)) {
                quantity += itemsIdsAndQuantities.get(itemId);
            }
            itemsIdsAndQuantities.put(itemId, quantity);

            System.out.print("Please continue buying and enter item ID, or press 'q' to finish: ");
            userIdOrQ = getIdOrQFromUser();
            toContinue = continueOrder(userIdOrQ);
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
        System.out.print("Please press '" + APPROVAL.YES.key + "' to confirm your order or '" + CANCEL.NO.key + "' to cancel: ");
        String userInput;
        while (true) {
            userInput = getStringInputFromUser();
            if (userInput.equalsIgnoreCase(APPROVAL.YES.key)) {
                return true;
            }
            else if (userInput.equalsIgnoreCase(CANCEL.NO.key)) {
                return false;
            }
            else{
                System.out.println(userInput + " is not '" + APPROVAL.YES.key + "' or '" + CANCEL.NO.key + "'.");
                System.out.print("Please try again: ");
            }
        }
    }

    private Date getDateFromUser(String msg) {
        System.out.print(msg);
        Date date = null;
        try {
            String dateStr = getStringInputFromUser();
            date = covertDateStrToDate(dateStr);
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
            System.out.println("The order date should be in the following format: " + ORDER_DATE_FORMAT + ".");
            getDateFromUser(msg);
        }
        return date;
    }

    private Point getLocationFromUser(String msg) {
        System.out.println(msg);
        int x = 0;
        int y = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                System.out.print("X: ");
                x = getIntInputFromUser();
                System.out.print("Y: ");
                y = getIntInputFromUser();
                SystemManager.validateLocation(x, y);
                isValidInput = true;
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("The coordinate should be an integer number!");
                System.out.println(msg);
            }
            catch (StoreLocationExistsException e) {
                System.out.println(e.getMessage());
                System.out.println("The location's order cannot be the same as one of the stores.");
                System.out.println(msg);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(msg);
            }
        }
        return new Point(x, y);
    }

    private int getStoreIdFromUser(String msg) {
        System.out.print(msg);
        int storeId = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                storeId = getIntInputFromUser();
                SystemManager.validateStoreIdExists(storeId);
                isValidInput = true;
            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                System.out.println("The store id should be an integer number!");
                System.out.print(msg);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.print(msg);
            }
        }
        return storeId;
    }

    private void createOrder() {
        showAllStores();
//        try {
        String msg = "Please enter store ID: ";
        int storeId = getStoreIdFromUser(msg);
        StoreDto store = SystemManager.getStoreDto(storeId);

        msg = "Please enter order's date: ";
        Date date = getDateFromUser(msg);

        msg = "Please enter your location:";
        Point userLocation = getLocationFromUser(msg);
        int userLocationX = userLocation.x;
        int userLocationY = userLocation.y;

        System.out.println();
        showItemsPerStore(store);
        System.out.println();

        Map<Integer, Float> itemsIdsAndQuantities = getItemsIdsAndQuantitiesFromUser(store);
        if (!itemsIdsAndQuantities.isEmpty()) {
            System.out.println();
            showOrderSummery(itemsIdsAndQuantities, store);

            int storePpk = store.getPpk();
            double distanceBetweenCustomerAndStore = SystemManager.getDistanceBetweenCustomerAndStore(store, userLocationX, userLocationY);
            float deliveryCost = storePpk * (float) distanceBetweenCustomerAndStore;
            System.out.println("The delivery cost is: " + DECIMAL_FORMAT.format(deliveryCost));
            System.out.println("The store ppk is: " + storePpk);
            System.out.println("Your distance from the store is: " + DECIMAL_FORMAT.format(distanceBetweenCustomerAndStore));
            System.out.println();

            boolean orderConfirmed = orderConfirmed();
            if (orderConfirmed) {
                SystemManager.createOrder(date, userLocationX, userLocationY, store, itemsIdsAndQuantities);
                System.out.println("Your order was confirmed and added successfully!");
            }
            else {
                System.out.println("Your order was canceled.");
            }
        }
    }

//        catch (InputMismatchException e) {
//            System.out.println(e.getMessage());
//        }


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
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                loadSystemData();
                isValidInput = true;
            }
            catch (Exception e) {
                System.out.println("The xml file you tried to load is not valid for the following reason:");
                System.out.println(e.getMessage());
            }
        }
    }

    private void loadSystemData() throws JAXBException, FileNotFoundException {
        System.out.println("Please enter the xml file path you would like to load: ");
        String filePath = getStringInputFromUser();
        SystemManager.loadSystemData(filePath);
    }






}