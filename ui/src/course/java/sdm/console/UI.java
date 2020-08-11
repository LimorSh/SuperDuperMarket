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

    private enum MenuOptions {
        LOAD_SYSTEM_DATA(1, "Load system data"),
        SHOW_STORES(2, "Show the super stores"),
        SHOW_ITEMS(3, "Shoe the super items"),
        NEW_ORDER(4, "Make new order"),
        ORDER_SUMMERY(5, "Shoe order summery"),
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
            int option = getOptionFromUser();
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

    private int getOptionFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
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
                showStores();
                break;
            case SHOW_STORES:
                showStores();
                break;
//            case SHOW_ITEMS:
//                break;
//            case NEW_ORDER:
//                break;
//            case ORDER_SUMMERY:
//                break;
//            case SHOW_ORDERS_HISTORY:
//                break;
            case EXIT:
                exit();
                break;
        }
    }

    private void showStores() {
        System.out.println("The stores in the super market are:");
        System.out.println(SEPARATOR_LINE);
        Collection<StoreDto> storesDto = SystemManager.getStoresDto();

        for (StoreDto storeDto : storesDto) {
            System.out.print("ID: " + storeDto.getId() + COMA_SEPARATOR);
            System.out.print("Name: " + storeDto.getName() + COMA_SEPARATOR);
            System.out.print("PPK: " + storeDto.getPpk() + COMA_SEPARATOR);
            System.out.print("Total deliveries revenue: " + storeDto.getTotalDeliveriesRevenue());
            System.out.println();
            System.out.println();

            System.out.println("The items in the store are:");
            for (StoreItemDto storeItemDto : storeDto.getStoreItemsDto()) {
                System.out.print("ID: " + storeItemDto.getId() + COMA_SEPARATOR);
                System.out.print("Name: " + storeItemDto.getName() + COMA_SEPARATOR);
                System.out.print("Purchase Category: " + storeItemDto.getPurchaseType() + COMA_SEPARATOR);
                System.out.print("Price in the store: " + storeItemDto.getPrice() + COMA_SEPARATOR); //#change later
                System.out.println("Total sold items: " + storeItemDto.getTotalNumberSold());
            }
            System.out.println(SEPARATOR_LINE);

//            System.out.println("The orders in the store are:");
//
//            Collection<OrderDto> o = storeDto.getOrdersDto();
//
//            for (OrderDto orderDto : o) {
//                System.out.print("Date: " + orderDto.getDate() + SPACE_SEPARATOR);
//                System.out.print("Items cost: " + orderDto.getItemsCost() + SPACE_SEPARATOR);
//                System.out.print("Delivery cost: " + orderDto.getDeliveryCost() + SPACE_SEPARATOR);
//            }
        }
    }
}
