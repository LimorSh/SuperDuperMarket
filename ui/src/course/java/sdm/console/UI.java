package course.java.sdm.console;
import course.java.sdm.engine.Order;
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
    public final static String ORDER_DATE_FORMAT = "dd/MM-hh:mm";



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
            throw new IllegalArgumentException(optionNumber + " is not an option in the menu.");
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
        try {
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
        catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printSeparatorLine() {
        System.out.println(SEPARATOR_LINE);
    }

    private void showStore(StoreDto store) {

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        showStoreBasicDetails(store);
        System.out.print(COMA_SEPARATOR);
        System.out.print("Total deliveries revenue: " + df.format(store.getTotalDeliveriesRevenue()));

        Collection<StoreItemDto> storeItems = store.getStoreItemsDto();

        if (!storeItems.isEmpty()) {
            System.out.println();
            System.out.println();
            System.out.println("The items in the store are:");
            for (StoreItemDto storeItem : storeItems) {
                showItemBasicDetails(storeItem);
                System.out.print(COMA_SEPARATOR);
                System.out.print("Price: " + storeItem.getPrice() + COMA_SEPARATOR); //#change later
                System.out.print("Total sold in the store: " + storeItem.getTotalSold());
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
                System.out.print("Items cost: " + df.format(orderDto.getItemsCost()) + COMA_SEPARATOR);
                System.out.print("Delivery cost: " + df.format(orderDto.getDeliveryCost()) + COMA_SEPARATOR);
                System.out.print("Total cost: " + df.format(orderDto.getTotalCost()));
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

        if (!stores.isEmpty()) {
            System.out.println("The stores in the super market are:");
            for (StoreDto store : stores) {
                showStore(store);
            }
        }
        else {
            System.out.println("There are no stores in the super market.");
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
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        showItemBasicDetails(item);
        System.out.println();
        int numberOfStoresSellingTheItem = SystemManager.getNumberOfStoresSellingTheItem(item);
        float averageItemPrice = SystemManager.getAverageItemPrice(item);
        float totalAmountOfItemSells = SystemManager.getTotalAmountOfItemSells(item);
        System.out.print("       ");
        System.out.print("The number of stores selling the item: " + numberOfStoresSellingTheItem + COMA_SEPARATOR);
        System.out.print("The average price of the item: " + df.format(averageItemPrice) + COMA_SEPARATOR);
        System.out.print("The total amount of item sells: " + totalAmountOfItemSells);
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

    private boolean showActiveStores() {
        Collection<StoreDto> activeStores = SystemManager.getActiveStoresDto();

        if (!activeStores.isEmpty()) {
            System.out.println("The current active stores are:");
            for (StoreDto store : activeStores) {
                showStoreBasicDetails(store);
                System.out.println();
            }
            return true;
        }
        else {
            System.out.println("There are no active stores in the super market.");
            return false;
        }
    }

    private void showItemsPerStore(StoreDto store) {
        Collection<ItemDto> items = SystemManager.getItemsDto();

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        if (!items.isEmpty()) {
            System.out.println("The items in the super market are:");
            for (ItemDto itemDto : items) {
                showItemBasicDetails(itemDto);
                System.out.print(COMA_SEPARATOR);
                if(SystemManager.isItemInTheStoreDto(store, itemDto)) {
                    float price = SystemManager.getItemPriceInStore(store, itemDto);
                    System.out.println("Price: " + df.format(price));
                }
                else
                    System.out.println("Item is not available.");
            }
        }
        else {
            System.out.println("There are no items in the super market.");
        }
    }

    private Map<Integer, Float> getItemsIdsAndQuantitiesFromUser() {
        Map<Integer, Float> itemsIdsAndQuantities = new HashMap<>();
        boolean toContinue = true;

        while(toContinue) {
            System.out.print("Please enter item id: ");
            int itemId = getIntInputFromUser();
            System.out.print("Please enter item quantity: ");
            float quantity = getFloatInputFromUser();
            if (SystemManager.getItemPurchaseCategory(itemId).equals(SystemManager.getItemPurchaseCategoryPerUnitStr())) {
                if ((quantity % 1) != 0) {
                    //throw exception
                }
            }

            float totalQuantity = quantity;
            if (itemsIdsAndQuantities.containsKey(itemId)) {
                totalQuantity += itemsIdsAndQuantities.get(itemId);
            }
            itemsIdsAndQuantities.put(itemId, totalQuantity);

            System.out.print("Please continue buying items or press 'q' to finish: "); //#need to change
            String userChoice = getTokenInputFromUser();
            if (userChoice.equalsIgnoreCase(USER_FINISHED_CHOOSE_ITEMS_KEY))
                toContinue = false;
        }

        return itemsIdsAndQuantities;
    }

    private void showOrderSummery(Map<Integer, Float> itemsIdsAndQuantities, StoreDto store) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

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
            System.out.print("Item price: " + df.format(itemPrice) + COMA_SEPARATOR);
            System.out.print("Quantity: " + df.format(itemQuantity) + COMA_SEPARATOR);
            itemTotalCost = itemQuantity * itemPrice;
            System.out.print("Total item cost: " + df.format(itemTotalCost));
            System.out.println();
        }
    }

    private Date covertDateStrToDate(String dateStr) throws ParseException {
        return new SimpleDateFormat(ORDER_DATE_FORMAT).parse(dateStr);
    }

    private String covertDateToDateStr(Date date) {
        DateFormat df = new SimpleDateFormat(ORDER_DATE_FORMAT);
        return df.format(date);
    }

    private void createOrder() throws ParseException {
        if (showActiveStores()) {
            try {
                System.out.print("Please enter store ID: ");
                int storeId = getIntInputFromUser();
                System.out.print("Please enter order's date: ");
                String dateStr = getStringInputFromUser();

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
                System.out.println();
                System.out.print("Y: ");
                int customerLocationY = getIntInputFromUser();
                StoreDto store = SystemManager.getStoreDto(storeId);
                showItemsPerStore(store);
                Map<Integer, Float> itemsIdsAndQuantities = getItemsIdsAndQuantitiesFromUser();
                System.out.println();
                showOrderSummery(itemsIdsAndQuantities, store);

                DecimalFormat df = new DecimalFormat();
                df.setMaximumFractionDigits(2);
                float storePpk = store.getPpk();
                double distanceBetweenCustomerAndStore = SystemManager.getDistanceBetweenCustomerAndStore(store, customerLocationX, customerLocationY);
                float deliveryCost = storePpk * (float) distanceBetweenCustomerAndStore;
                System.out.println("The delivery cost is: " + df.format(deliveryCost));
                System.out.println("The store ppk is: " + storePpk);
                System.out.println("Your distance from the store is: " + df.format(distanceBetweenCustomerAndStore));
                System.out.println();
                System.out.println("Please press 'y' to confirm your order or 'n' to cancel");
                String userConfirmation = getStringInputFromUser();
                if (userConfirmation.equalsIgnoreCase("y")) {
                    SystemManager.createOrder(date, customerLocationX, customerLocationY, store, itemsIdsAndQuantities);
                }
                else {
                    System.out.println("Your order was not confirmed.");
                }



            }
            catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println();
            //throw an exception for loop program.
        }
    }

    private void showOrdersHistory() {
        System.out.println("The orders in the super market are:");
        Collection<OrderDto> ordersDto = SystemManager.getOrdersDto();
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

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
                System.out.print("Items cost: " + df.format(orderDto.getItemsCost()) + COMA_SEPARATOR);
                System.out.print("Delivery cost: " + df.format(orderDto.getDeliveryCost()) + COMA_SEPARATOR);
                System.out.print("Total cost: " + df.format(orderDto.getTotalCost()));
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
