package course.java.sdm.console;

import course.java.sdm.engine.*;

import java.text.ParseException;
import java.util.Set;

public class SuperDuperMarket {

    //    public enum MenuOptions {
//        SHOW_STORES("show stores"),
//        SHOW_ITEMS,
//        ;
//
//        MenuOptions(String s) {
//        }
//    }

    public void run() throws ParseException {
        Store s1 = new Store(1,"Meni",20, new Location(1, 1) );
        Store s2 = new Store(2,"Joy",100, new Location (15, 3) );
        Store s3 = new Store(3,"Bambi",77, new Location (9, 3) );

        Item item1 = new Item(12, "bamba", Item.PurchaseType.PER_UNIT);
        Item item2 = new Item(56, "peanut butter", Item.PurchaseType.PER_UNIT);
        Item item3 = new Item(89, "tomato", Item.PurchaseType.PER_WEIGHT);


//        Set<Store> stores = new HashSet<>();
//        stores.add(s1);
//        stores.add(s2);
//        stores.add(s3);

        course.java.sdm.engine.SuperDuperMarket sup = new course.java.sdm.engine.SuperDuperMarket("Rami_Levi");
        sup.addStore(s1);
        sup.addStore(s2);
        sup.addStore(s3);
        sup.addItem(item1);
        sup.addItem(item2);
        sup.addItem(item3);

        s1.addItem(item1, 6.54f);
        s2.addItem(item2, 3.2f);
        s3.addItem(item3, 7f);

        Customer customer = new Customer(1, "shira");

        // not working and it's good - not supposed - throws exception as it should:
//        Order order1 = new Order(1, new Date(), customer, new Location(1, 2), s1);
//        Order order1 = new Order(1, "01/03/2009", customer, new Location(1, 2), s1);

        // working good as it should:   need to check why is the first one working...
//        Order order1 = new Order(1, "01/03-11:53:34", customer, new Location(1, 2), s1);
        Order order1 = new Order(1, "01/03-11:53", customer, new Location(1, 2), s1);


        order1.addItem(item1, 12f);
        s1.addOrder(order1);


        System.out.println("Show stores:");
        Set<Store> stores = sup.getStores();
        stores.forEach(System.out::println);
    }
}
