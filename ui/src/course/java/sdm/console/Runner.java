package course.java.sdm.console;

import course.java.sdm.engine.*;
import course.java.sdm.engine.jaxb.schema.generated.SystemManager;

import java.text.ParseException;
import java.util.Set;

public class Runner {

    private final static String DATA_PATH = "C:\\Users\\limorsh\\Desktop\\Java\\SuperDuperMarket\\engine\\src\\course\\java\\sdm\\engine\\resources\\ex1-small.xml";

    //    public enum MenuOptions {
//        SHOW_STORES("show stores"),
//        SHOW_ITEMS,
//        ;
//
//        MenuOptions(String s) {
//        }
//    }

    public void run() throws ParseException {
        SystemManager systemManager = new SystemManager();
        systemManager.loadSystemData(DATA_PATH);































//
//        Store s1 = new Store(1,"Meni",20, new Location(3, 4) );
//        Store s2 = new Store(2,"Joy",100, new Location (15, 3) );
//        Store s3 = new Store(3,"Bambi",77, new Location (9, 3) );
//
//        Item item1 = new Item(12, "bamba", Item.PurchaseType.PER_UNIT);
//        Item item2 = new Item(56, "peanut butter", Item.PurchaseType.PER_UNIT);
//        Item item3 = new Item(89, "tomato", Item.PurchaseType.PER_WEIGHT);
//
//
////        Set<Store> stores = new HashSet<>();
////        stores.add(s1);
////        stores.add(s2);
////        stores.add(s3);
//
//        course.java.sdm.engine.SuperDuperMarket sup = new course.java.sdm.engine.SuperDuperMarket("Rami_Levi");
//        sup.addStore(s1);
//        sup.addStore(s2);
//        sup.addStore(s3);
//        sup.addItem(item1);
//        sup.addItem(item2);
//        sup.addItem(item3);
//
//        s1.addItem(item1, 6.54f);
//        s1.addItem(item2, 7.2f);
//        s1.addItem(item3, 2.5f);
//
//        s2.addItem(item1, 4.5f);
//        s2.addItem(item2, 3.2f);
//        s2.addItem(item3, 2.21f);
//
//        s3.addItem(item3, 7f);
//
//        Customer customer = new Customer(1, "shira");
//
//        // not working and it's good - not supposed - throws exception as it should:
////        Order order1 = new Order(1, new Date(), customer, new Location(1, 2), s1);
////        Order order1 = new Order(1, "01/03/2009", customer, new Location(1, 2), s1);
//
        // working good as it should:   need to check why is the first one working...
//        Order order1 = new Order(1, "01/03-11:53:34", customer, new Location(1, 2), s1);
//
//
//
//
//        Order order1 = new Order(1, "01/03-11:53", customer, new Location(1, 2), s1);
//        order1.addItem(superDuperMarket.getItem(1), 12f);
//        order1.addItem(superDuperMarket.getItem(2), 2f);
//        order1.addItem(superDuperMarket.getItem(3), 1.2f);
//        order1.finish();
//
//        Order order2 = new Order(2, "01/06-12:11", customer, new Location(1, 2), s2);
//        order2.addItem(item1, 10f);
//        order2.addItem(item2, 4f);
//        order2.addItem(item3, 2.5f);
//        order2.finish();
//
//        sup.addOrder(order1);
//        sup.addOrder(order2);
//
//
//        System.out.println("Show stores:");
//        Set<Store> stores = sup.getStores();
//        stores.forEach(System.out::println);
//
//
//        System.out.println("Show items:");
//        Set<Item> items = sup.getItems();
//        items.forEach(item -> {
//            System.out.println(item);
//            System.out.print("Number of stores selling the item: ");
//            System.out.println(sup.numberOfStoresSellingTheItem(item));
//            System.out.print("Average price of the item in stores: ");
//            System.out.println(sup.averageItemPrice(item));
//            System.out.print("Total amount item was sold: ");
//            System.out.println(sup.totalNumberItemSold(item));
//        });
    }
}
