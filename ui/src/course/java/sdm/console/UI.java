package course.java.sdm.console;
import course.java.sdm.engine.systemDto.*;
import course.java.sdm.engine.SystemManager;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;

public class UI {

    private static final String SEPARATOR_LINE = "\n----------------------------------------------------------" +
                                                    "---------------------------------------------------------";
    private static final String COMA_SEPARATOR = ", ";
    private static final String SPACE_SEPARATOR = " ";
    private static final String WELCOME_MESSAGE_STR = "Welcome to Super Duper Market!";
    private static final String EXIT_MESSAGE_STR = "Thank you for buying in super duper market :)\nGoodbye!";
    private static final String PLEASE_CHOOSE_ACTION_STR = "Please choose an option from the menu:";

    private final static String DATA_PATH = "C:\\Users\\limorsh\\Desktop\\Java\\SuperDuperMarket\\engine\\src\\course\\java\\sdm\\engine\\resources\\ex1-small.xml";
//    private final static String DATA_PATH_SHIRA = "C:\\Users\\victo\\Documents\\Study\\Java\\EX\\EX1\\ex1-small.xml";


    private enum MenuOptions {
        LOAD_SYSTEM_DATA(1, "Load system data"),
        SHOW_STORES(2, "Show the super stores"),
        SHOW_ITEMS(3, "Show the super items"),
        CREATE_ORDER(4, "Create new order"),
        ORDER_SUMMERY(5, "Show order summery"),
        SHOW_ORDERS_HISTORY(6, "Show order history"),
        EXIT(7, "Exit")
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

    private void exit() {
        System.out.println(EXIT_MESSAGE_STR);
        System.exit(1);
    }

    public void run() {
        printWelcome();
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

    private int getIntInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private String getStringInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
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
                SystemManager.loadSystemData(DATA_PATH);
                showAllStores();
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
//            case ORDER_SUMMERY:
//                break;
//            case SHOW_ORDERS_HISTORY:
//                break;
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
        System.out.print("Total deliveries revenue: " + store.getTotalDeliveriesRevenue());

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
            System.out.println();
            System.out.println("The orders in the store are:");
            for (OrderDto orderDto : orders) {
                System.out.print("Date: " + orderDto.getDate() + COMA_SEPARATOR);
                System.out.print("Total items: " + orderDto.getTotalItems() + COMA_SEPARATOR);
                System.out.print("Items cost: " + orderDto.getItemsCost() + COMA_SEPARATOR);
                System.out.print("Delivery cost: " + orderDto.getDeliveryCost() + COMA_SEPARATOR);
                System.out.print("Total cost: " + orderDto.getTotalCost());
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
        System.out.print("Purchase Category: " + item.getPurchaseType());
    }

    private void showStoreBasicDetails(StoreDto store) {
        System.out.print("ID: " + store.getId() + COMA_SEPARATOR);
        System.out.print("Name: " + store.getName() + COMA_SEPARATOR);
        System.out.print("PPK: " + store.getPpk());
    }

    private void showItem(ItemDto item) {
        showItemBasicDetails(item);
        int numberOfStoresSellingTheItem = SystemManager.getNumberOfStoresSellingTheItem(item);
        float averageItemPrice = SystemManager.getAverageItemPrice(item);
        float totalAmountOfItemSells = SystemManager.getTotalAmountOfItemSells(item);
        System.out.print("The number of stores selling the item: " + numberOfStoresSellingTheItem + COMA_SEPARATOR);
        System.out.print("The average price of the item: " + averageItemPrice + COMA_SEPARATOR);
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
        Collection<StoreItemDto> storeItems = SystemManager.getStoreItems(store);
        Collection<ItemDto> items = SystemManager.getItemsDto();

        if (!items.isEmpty()) {
            System.out.println("The items in the super market are:");
            for (ItemDto itemDto : items) {
                showItemBasicDetails(itemDto);
                System.out.print(COMA_SEPARATOR);
                if(SystemManager.isItemInTheStoreDto(itemDto, store)) {
                    float price = SystemManager.getItemPriceInStore(itemDto, store);
                    System.out.println("Price: " + price);
                }
                else
                    System.out.println("Item is not available.");
            }
        }
        else {
            System.out.println("There are no items in the super market.");
        }
    }

    private void createOrder() {
        if (showActiveStores()) {
            try {
                System.out.print("Please enter store ID: ");
                int storeId = getIntInputFromUser();
                System.out.print("Please enter order's date: ");
                String dateStr = getStringInputFromUser();
                System.out.println("Please enter your location:");
                System.out.print("X: ");
                int x = getIntInputFromUser();
                System.out.println();
                System.out.print("Y: ");
                int y = getIntInputFromUser();
                StoreDto store = SystemManager.getStoreDto(storeId);
                showItemsPerStore(store);

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





}
