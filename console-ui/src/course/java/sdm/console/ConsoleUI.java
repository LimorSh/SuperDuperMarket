package course.java.sdm.console;
import course.java.sdm.engine.dto.*;
import course.java.sdm.engine.engine.BusinessLogic;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;

public class ConsoleUI {

    private static final String SEPARATOR_LINE = "\n----------------------------------------------------------" +
            "-----------------------------------------------------------------";
    private static final String COMA_SEPARATOR = ", ";
    private static final String WELCOME_MESSAGE_STR = "Welcome to Super Duper Market!";
    private static final String EXIT_MESSAGE_STR = "Thank you for buying in super duper market :)\nGoodbye!";
    private static final String PLEASE_CHOOSE_ACTION_STR = "Please choose an option from the menu:";
    private static final String USER_FINISHED_CHOOSE_ITEMS_KEY = "q";
    private static final String ORDER_DATE_FORMAT = "dd/MM-hh:mm";
    private static final int MAXIMUM_FRACTION_DIGITS = 2;
    private static final DecimalFormat DECIMAL_FORMAT;
    private static final DateFormat DATE_FORMAT;

    private final BusinessLogic businessLogic = new BusinessLogic();

    static {
        DECIMAL_FORMAT = new DecimalFormat();
        DECIMAL_FORMAT.setMaximumFractionDigits(MAXIMUM_FRACTION_DIGITS);
        DATE_FORMAT = new SimpleDateFormat(ORDER_DATE_FORMAT);
        DATE_FORMAT.setLenient(false);
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
        LOAD_SYSTEM_DATA(1, "Load system data file"),
        SHOW_STORES(2, "Show the super stores"),
        SHOW_ITEMS(3, "Show the super items"),
        CREATE_ORDER(4, "Create new order"),
        SHOW_ORDERS_HISTORY(5, "Show orders history"),
        UPDATE_STORE_ITEMS(6, "Update store items"),
        EXIT(7, "Exit"),
        SHOW_CUSTOMERS(8, "Show the super customers"),
        SHOW_DISCOUNTS(9, "Show the super discounts"),
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

    private enum SubMenuUpdateItemsStoreOptions {
        DELETE_ITEM(1, "Delete item"),
        ADD_ITEM(2, "Add item"),
        UPDATE_ITEM_PRICE(3, "Update item price"),
        BACK_TO_MAIN_MENU(4, "Back to main menu")
        ;

        private final int optionNumber;
        private final String optionTitle;

        SubMenuUpdateItemsStoreOptions(int optionNumber, String optionTitle) {
            this.optionNumber = optionNumber;
            this.optionTitle = optionTitle;
        }

        public static SubMenuUpdateItemsStoreOptions getSubMenuUpdateItemsStoreOptions(int optionNumber) {
            for (SubMenuUpdateItemsStoreOptions subMenuUpdateItemsStoreOptions : SubMenuUpdateItemsStoreOptions.values()) {
                if (subMenuUpdateItemsStoreOptions.optionNumber == optionNumber)
                    return subMenuUpdateItemsStoreOptions;
            }
            String errorMsg = optionNumber + " is not an option in the menu.";
            throw new IllegalArgumentException(errorMsg);
        }
    }

    private String getFormatNumberWithTwoDigitsAfterPoint(float number) {
        return String.format("%.2f", number);
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
                System.out.println("The input you entered is not a decimal number!");
                System.out.print("Please enter a decimal number and try again: ");
            }
        }
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
            System.out.print("Your option: ");
            int option = getIntInputFromUser();
            MenuOptions menuOptions = MenuOptions.getMenuOptions(option);
            handleUserAction(menuOptions);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
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
                    System.out.println("The file was loaded successfully!");
                }
                catch (Exception e) {
                    System.out.println("The file you tried to load is not valid for the following reason:");
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
            case UPDATE_STORE_ITEMS:
                updateStoreItems();
                break;
            case EXIT:
                exit();
                break;
            case SHOW_CUSTOMERS:
                showAllCustomers();
                break;
            case SHOW_DISCOUNTS:
                showAllDiscounts();
                break;
        }
    }

    // new functions:


    private void showAllCustomers() {
        Collection<CustomerDto> customersDto = businessLogic.getCustomersDto();
        System.out.println("\nSuper market customers:");
        System.out.println("-------------------");
        for (CustomerDto customerDto : customersDto) {
            showCustomerBasicDetails(customerDto);
        }
    }

    private void showCustomerBasicDetails(CustomerDto customerDto) {
        System.out.print("ID: " + customerDto.getId() + COMA_SEPARATOR);
        System.out.print("Name: " + customerDto.getName() + COMA_SEPARATOR);
        System.out.print("Location: (" + customerDto.getXLocation() + "," + customerDto.getYLocation() + ")");
        System.out.println();
    }

    private void showAllDiscounts() {
        Collection<StoreDto> storesDto = businessLogic.getStoresDto();
        System.out.println("\nSuper market discounts:");
        System.out.println("----------------------");
        for (StoreDto storeDto : storesDto) {
            showDiscount(storeDto);
        }
    }

    private void showDiscount(StoreDto storeDto) {
        Collection<StoreItemDto> storeItemsDto = storeDto.getStoreItemsDto();

        if (!storeItemsDto.isEmpty()) {
            for (StoreItemDto storeItemDto : storeItemsDto) {
                Collection<DiscountDto> discountsDto = storeItemDto.getDiscountsDto();
                if (!discountsDto.isEmpty()) {
                    for (DiscountDto discountDto : discountsDto) {
                        System.out.print("Discount name: " + discountDto.getName() + COMA_SEPARATOR);
                        System.out.print("Item ID: " + discountDto.getStoreItemId() + COMA_SEPARATOR);
                        System.out.print("Item Quantity: " + discountDto.getStoreItemQuantity() + COMA_SEPARATOR);
                        System.out.println("Category: " + discountDto.getCategory());
                        System.out.println();

                        System.out.println("Discount offers:");
                        Collection<OfferDto> offersDto = discountDto.getOffersDto();
                        for (OfferDto offerDto : offersDto) {
                            System.out.print("Item ID: " + offerDto.getStoreItemId() + COMA_SEPARATOR);
                            System.out.print("Item Quantity: " + offerDto.getQuantity() + COMA_SEPARATOR);
                            System.out.print("Additional Price: " + offerDto.getAdditionalPrice());
                            System.out.println();
                        }
                        printSeparatorLine();
                    }
                }
            }
        }
        else {
            System.out.println("There are no items in the store.");
        }

        System.out.println();
    }


    private void printSeparatorLine() {
        System.out.println(SEPARATOR_LINE);
    }

    private void showStore(StoreDto storeDto) {
        showStoreBasicDetails(storeDto);
        System.out.print(COMA_SEPARATOR);
        System.out.print("Total deliveries revenue: " + DECIMAL_FORMAT.format(storeDto.getTotalDeliveriesRevenue()));

        Collection<StoreItemDto> storeItemsDto = storeDto.getStoreItemsDto();

        if (!storeItemsDto.isEmpty()) {
            System.out.println();
            System.out.println();
            System.out.println("Items:");
            for (StoreItemDto storeItemDto : storeItemsDto) {
                showItemBasicDetails(storeItemDto);
                System.out.print(COMA_SEPARATOR);
                if (storeItemDto.getPurchaseCategory().equalsIgnoreCase("quantity")) {
                    System.out.print("Price per unit: ");
                }
                else{
                    System.out.print("Price per kilogram: ");
                }
                System.out.print(getFormatNumberWithTwoDigitsAfterPoint(storeItemDto.getPrice()) + COMA_SEPARATOR);
                System.out.print("Total sells in the store: " + DECIMAL_FORMAT.format(storeItemDto.getTotalSold()));
                System.out.println();
            }
        }
        else {
            System.out.println("There are no items in the store.");
        }

        System.out.println();

        Collection<OrderDto> ordersDto = storeDto.getOrdersDto();
        if (!ordersDto.isEmpty()) {
            System.out.println("Store orders:");
            for (OrderDto orderDto : ordersDto) {
                System.out.print("Date: " + covertDateToDateStr(orderDto.getDate()) + COMA_SEPARATOR);
                System.out.print("Total items: " + orderDto.getTotalItems() + COMA_SEPARATOR);
                System.out.print("Items cost: " + DECIMAL_FORMAT.format(orderDto.getItemsCost()) + COMA_SEPARATOR);
                System.out.print("Delivery cost: " + DECIMAL_FORMAT.format(orderDto.getDeliveryCost()) + COMA_SEPARATOR);
                System.out.print("Total cost: " + DECIMAL_FORMAT.format(orderDto.getTotalCost()));
                System.out.println();
            }
        }
        else {
            System.out.print("There are no orders in the store.");
        }

        printSeparatorLine();
    }

    private void showAllStores() {
        Collection<StoreDto> storesDto = businessLogic.getStoresDto();
        System.out.println("\nSuper market stores:");
        System.out.println("-------------------");
        for (StoreDto storeDto : storesDto) {
            showStore(storeDto);
        }
    }

    private void showItemBasicDetails(BasicItemDto basicItemDto) {
        System.out.print("ID: " + basicItemDto.getId() + COMA_SEPARATOR);
        System.out.print("Name: " + basicItemDto.getName() + COMA_SEPARATOR);
        System.out.print("Purchase category: " + basicItemDto.getPurchaseCategory());
    }

    private void showStoreBasicDetails(StoreDto storeDto) {
        System.out.print("ID: " + storeDto.getId() + COMA_SEPARATOR);
        System.out.print("Name: " + storeDto.getName() + COMA_SEPARATOR);
        System.out.print("PPK: " + storeDto.getPpk());
    }

    private void showItem(BasicItemDto basicItemDto) {
        showItemBasicDetails(basicItemDto);
        System.out.println();
        int numberOfStoresSellingTheItem = businessLogic.getNumberOfStoresSellingTheItem(basicItemDto);
        float averageItemPrice = businessLogic.getAverageItemPrice(basicItemDto);
        float totalAmountOfItemSells = businessLogic.getTotalAmountOfItemSells(basicItemDto);
        System.out.print("       ");
        System.out.print("Number of stores selling the item: " + numberOfStoresSellingTheItem + COMA_SEPARATOR);
        System.out.print("Average price: " + getFormatNumberWithTwoDigitsAfterPoint(averageItemPrice) + COMA_SEPARATOR);
        System.out.print("Total sells: " + DECIMAL_FORMAT.format(totalAmountOfItemSells));
        printSeparatorLine();
    }

    private void showAllItems() {
        Collection<BasicItemDto> itemsDto = businessLogic.getBasicItemsDto();

        if (!itemsDto.isEmpty()) {
            System.out.println("\nSuper market items:");
            System.out.println("------------------");
            for (BasicItemDto basicItemDto : itemsDto) {
                showItem(basicItemDto);
            }
        }
        else {
            System.out.println("There are no items in the super market.");
        }
    }

    private void showItemsPerStore(StoreDto storeDto) {
        Collection<BasicItemDto> itemsDto = businessLogic.getBasicItemsDto();
        System.out.println("The items in the super market are:");
        for (BasicItemDto basicItemDto : itemsDto) {
            showItemBasicDetails(basicItemDto);
            System.out.print(COMA_SEPARATOR);
            if(businessLogic.isItemInTheStoreDto(storeDto, basicItemDto)) {
                float price = businessLogic.getItemPriceInStore(storeDto, basicItemDto);
                System.out.println("Price: " + getFormatNumberWithTwoDigitsAfterPoint(price));
            }
            else
                System.out.println("Item is not available.");
        }
    }

    private boolean continueOrder(String userInput) {
        return !(userInput.equalsIgnoreCase(USER_FINISHED_CHOOSE_ITEMS_KEY));
    }

    private int getValidStoreItemIdFromUser(int storeId, int itemId) {
        boolean isValidInput = false;
        int intInput = itemId;
        while (!isValidInput) {
            try {
                businessLogic.validateItemIdExistsInStore(storeId, intInput);
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

    private float getValidItemQuantityFromUser(int itemId) {
        float quantity;
        while (true) {
            quantity = getFloatInputFromUser();
            try {
                businessLogic.validateItemQuantity(itemId, quantity);
                return quantity;
            }
            catch (Exception e) {
                System.out.println("Invalid item quantity: " + e.getMessage());
                System.out.print("Please try again: ");
            }
        }
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

    private Map<Integer, Float> getItemsIdsAndQuantitiesFromUser(StoreDto storeDto) {
        Map<Integer, Float> itemsIdsAndQuantities = new HashMap<>();

        System.out.print("Please start buying by enter item ID, or press 'q' to exit: ");
        String userIdOrQ = getIdOrQFromUser();
        boolean toContinue = continueOrder(userIdOrQ);

        while(toContinue) {
            int intInput =  Integer.parseInt(userIdOrQ);
            int itemId = getValidStoreItemIdFromUser(storeDto.getId(), intInput);

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

    private void showOrderSummery(Map<Integer, Float> itemsIdsAndQuantities, StoreDto storeDto) {
        System.out.println("Order Summery:");
        System.out.println("-------------");
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
            System.out.print("\tID: " + itemId + COMA_SEPARATOR);
            itemName = businessLogic.getItemName(itemId);
            System.out.print("Name: " + itemName + COMA_SEPARATOR);
            itemPurchaseCategory = businessLogic.getItemPurchaseCategory(itemId);
            System.out.print("Purchase category: " + itemPurchaseCategory + COMA_SEPARATOR);
            itemPrice = businessLogic.getItemPriceInStoreByIds(storeDto.getId(), itemId);
            System.out.print("Item price: " + getFormatNumberWithTwoDigitsAfterPoint(itemPrice) + COMA_SEPARATOR);
            System.out.print("Quantity: " + DECIMAL_FORMAT.format(itemQuantity) + COMA_SEPARATOR);
            itemTotalCost = itemQuantity * itemPrice;
            System.out.print("Total item cost: " + DECIMAL_FORMAT.format(itemTotalCost));
            System.out.println();
        }
    }

    private Date covertDateStrToDate(String dateStr) throws ParseException {
        if (dateStr.length() < ORDER_DATE_FORMAT.length()) {
            throw new IllegalArgumentException("You entered: " + dateStr + ".");
        }
        return DATE_FORMAT.parse(dateStr);
    }

    private String covertDateToDateStr(Date date) {
        return DATE_FORMAT.format(date);
    }

    private Date getDateFromUser(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String dateStr = getStringInputFromUser();
                return covertDateStrToDate(dateStr);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("The order date should be in the following format: " + ORDER_DATE_FORMAT + ".");
                System.out.println("Please notice the day, month, hour and minutes you enter are valid.");
            }
        }
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

//    private Point getLocationFromUser(String msg) {
//        System.out.println(msg);
//        int x = 0;
//        int y = 0;
//        boolean isValidInput = false;
//        while (!isValidInput) {
//            try {
//                System.out.print("X: ");
//                x = getIntInputFromUser();
//                System.out.print("Y: ");
//                y = getIntInputFromUser();
//                SystemManager.validateLocation(x, y);
//                isValidInput = true;
//            }
//            catch (InputMismatchException e) {
//                System.out.println(e.getMessage());
//                System.out.println("The coordinate should be an integer number!");
//                System.out.println(msg);
//            }
//            catch (DuplicateLocationException e) {
//                System.out.println(e.getMessage());
//                System.out.println("The location's order cannot be the same as one of the stores.");
//                System.out.println(msg);
//            }
//            catch (Exception e) {
//                System.out.println(e.getMessage());
//                System.out.println(msg);
//            }
//        }
//        return new Point(x, y);
//    }

    private int getValidStoreIdFromUser(String msg) {
        System.out.print(msg);
        int storeId = 0;
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                storeId = getIntInputFromUser();
                businessLogic.validateStoreIdExists(storeId);
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

    private StoreDto getStoreFromUser() {
        String msg = "Please enter store ID: ";
        int storeId = getValidStoreIdFromUser(msg);
        return businessLogic.getStoreDto(storeId);
    }

    private void createOrder() {
        System.out.println("\nShop now:");
        System.out.println("--------");
        showAllStoresForCreateOrder();
        StoreDto storeDto = getStoreFromUser();

        String msg = "Please enter order's date: ";
        Date date = getDateFromUser(msg);

//        msg = "Please enter your location (X,Y):";
//        Point userLocation = getLocationFromUser(msg);
//        int userLocationX = userLocation.x;
//        int userLocationY = userLocation.y;

        System.out.println();
        showItemsPerStore(storeDto);
        System.out.println();

//        orderContinuation(storeDto, date, userLocationX, userLocationY);
    }

    private void orderContinuation(StoreDto storeDto, Date date, int userLocationX, int userLocationY) {
        Map<Integer, Float> itemsIdsAndQuantities = getItemsIdsAndQuantitiesFromUser(storeDto);
        if (!itemsIdsAndQuantities.isEmpty()) {
            System.out.println();
            showOrderSummery(itemsIdsAndQuantities, storeDto);

            int storePpk = storeDto.getPpk();
            double distanceBetweenCustomerAndStore = businessLogic.getDistanceBetweenCustomerAndStore(storeDto, userLocationX, userLocationY);
            float deliveryCost = storePpk * (float) distanceBetweenCustomerAndStore;
            System.out.println("Delivery cost: " + DECIMAL_FORMAT.format(deliveryCost));
            System.out.println("Store PPK (Price Per Kilometer): " + storePpk);
            System.out.println("Your distance from the store (in kilometers): " + DECIMAL_FORMAT.format(distanceBetweenCustomerAndStore));
            System.out.println();

            boolean orderConfirmed = orderConfirmed();
            if (orderConfirmed) {
//                SystemManager.createOrder(date, userLocationX, userLocationY, storeDto, itemsIdsAndQuantities);
                System.out.println("Your order was confirmed and added successfully!");
            }
            else {
                System.out.println("Your order was canceled.");
            }
        }
    }

    private void showOrdersHistory() {
        System.out.println("\nOrders history:");
        System.out.println("--------------");
        Collection<OrderDto> ordersDto = businessLogic.getOrdersDto();

        if (!ordersDto.isEmpty()) {
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
                printSeparatorLine();
            }
        }
        else {
            System.out.println("There are no orders in the super market.");
        }
    }

    private int getUserSubMenuUpdateItemsStoreOption() {
        while (true)  {
            try {
                System.out.print("Your option: ");
                int option = getIntInputFromUser();
                SubMenuUpdateItemsStoreOptions.getSubMenuUpdateItemsStoreOptions(option);
                return option;
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                showSubMenuUpdateItemsStoreOptions();
            }
        }
    }

    private void updateStoreItems() {
        System.out.println("\n" + MenuOptions.UPDATE_STORE_ITEMS.optionTitle + ":");
        System.out.println("------------------");
        showAllStores();
        showSubMenuUpdateItemsStoreOptions();

        try {
            int option = getUserSubMenuUpdateItemsStoreOption();
            SubMenuUpdateItemsStoreOptions subMenuUpdateItemsStoreOptions = SubMenuUpdateItemsStoreOptions.getSubMenuUpdateItemsStoreOptions(option);
            String title = subMenuUpdateItemsStoreOptions.optionTitle;
            System.out.println("\n" + title + ":");
            int len = title.length();
            for (int i = 0; i < len; i++) {
                System.out.print("-");
            }
            System.out.println();
            handleUserAction(subMenuUpdateItemsStoreOptions);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void showSubMenuUpdateItemsStoreOptions() {
        System.out.println();
        System.out.println(PLEASE_CHOOSE_ACTION_STR);
        for (SubMenuUpdateItemsStoreOptions subMenuUpdateItemsStoreOptions : SubMenuUpdateItemsStoreOptions.values()) {
            System.out.println(subMenuUpdateItemsStoreOptions.optionNumber + ". " + subMenuUpdateItemsStoreOptions.optionTitle);
        }
        System.out.println();
    }

    private void handleUserAction(SubMenuUpdateItemsStoreOptions subMenuUpdateItemsStoreOptions) {
        if (!subMenuUpdateItemsStoreOptions.equals(SubMenuUpdateItemsStoreOptions.BACK_TO_MAIN_MENU)) {
            StoreDto storeDto = getStoreFromUser();
            switch (subMenuUpdateItemsStoreOptions) {
                case DELETE_ITEM:
                    deleteItem(storeDto);
                    break;
                case ADD_ITEM:
                    addItem(storeDto);
                    break;
                case UPDATE_ITEM_PRICE:
                    updateItemPrice(storeDto);
                    break;
            }
        }
    }

    private void deleteItem(StoreDto storeDto) {
        System.out.print("Please enter item ID: ");
        int intInput =  getIntInputFromUser();
        int itemId = getValidStoreItemIdFromUser(storeDto.getId(), intInput);

        try {
            businessLogic.deleteItemFromStore(itemId, storeDto.getId());
            System.out.println("\nThe item was deleted from the store successfully!");
        }
        catch (Exception e) {
            System.out.println("\nCould not delete the item from the store: " + e.getMessage());
        }
    }

    private int getValidItemIdFromUser(int storeId, int itemId) {
        boolean isValidInput = false;
        int intInput = itemId;
        while (!isValidInput) {
            try {
                businessLogic.validateAddItemToStore(storeId, intInput);
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

    private void addItem(StoreDto storeDto) {
        showAllItems();

        System.out.print("Please enter item ID: ");
        int intInput =  getIntInputFromUser();
        int itemId = getValidItemIdFromUser(storeDto.getId(), intInput);

        System.out.print("Please enter item price: ");
        float itemPrice = getValidItemPrice();

        try {
            businessLogic.addItemToStore(itemId, itemPrice, storeDto.getId());
            System.out.println("\nThe item was added to the store successfully!");
        }
        catch (Exception e) {
            System.out.println("\nCould not add the item to the store: " + e.getMessage());
        }
    }

    private float getValidItemPrice() {
        float itemPrice = 0f;
        boolean isValidInput = false;
        while (!isValidInput) {
            itemPrice = getFloatInputFromUser();
            if (itemPrice <= 0) {
                System.out.println("The item price should be greater than zero.");
                System.out.print("Please try again: ");
            }
            else {
                isValidInput = true;
            }
        }
        return itemPrice;
    }

    private void updateItemPrice(StoreDto storeDto) {
        System.out.print("Please enter item ID: ");
        int intInput =  getIntInputFromUser();
        int itemId = getValidStoreItemIdFromUser(storeDto.getId(), intInput);

        System.out.print("Please enter the new item price: ");
        float newItemPrice = getValidItemPrice();

        try {
            businessLogic.updateItemPriceInStore(itemId, newItemPrice, storeDto.getId());
            System.out.println("\nThe item price was updated successfully!");
        }
        catch (Exception e) {
            System.out.println("\nCould not change the item price: " + e.getMessage());
        }
    }

    private void loadSystemDataFirstTime() {
        boolean isValidInput = false;
        while (!isValidInput) {
            try {
                loadSystemData();
                System.out.println("The file was loaded successfully!");
                isValidInput = true;
            }
            catch (Exception e) {
                System.out.println("\nThe file you tried to load is not valid for the following reason:");
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    private void loadSystemData() throws JAXBException, FileNotFoundException {
        System.out.println("\n" + MenuOptions.LOAD_SYSTEM_DATA.optionTitle + ":");
        System.out.println("---------------------");
        System.out.println("Please enter a xml file path you would like to load:");
        String filePath = getStringInputFromUser();
        businessLogic.loadSystemData(filePath);
    }

    private void showAllStoresForCreateOrder() {
        Collection<StoreDto> storesDto = businessLogic.getStoresDto();
        System.out.println("The stores in the super market are:");
        for (StoreDto storeDto : storesDto) {
            showStoreBasicDetails(storeDto);
            System.out.println();
        }
    }






}