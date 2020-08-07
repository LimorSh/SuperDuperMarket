package course.java.sdm.console;

import course.java.sdm.engine.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import static course.java.sdm.engine.Item.PurchaseType.PER_UNIT;

public class Main {

//    public enum MenuOptions {
//        SHOW_STORES("show stores"),
//        SHOW_ITEMS,
//        ;
//
//        MenuOptions(String s) {
//        }
//    }

    public static void main(String[] args) {
        Store s1 = new Store(1,"Meni",20, new Location (1, 1) );
        Store s2 = new Store(2,"Joy",100, new Location (15, 3) );
        Store s3 = new Store(3,"Bambi",77, new Location (9, 3) );

        Item item1 = new Item(12, "bamba", Item.PurchaseType.PER_UNIT);
        Item item2 = new Item(56, "peanut butter", Item.PurchaseType.PER_UNIT);
        Item item3 = new Item(89, "tomato", Item.PurchaseType.PER_WEIGHT);


//        Set<Store> stores = new HashSet<>();
//        stores.add(s1);
//        stores.add(s2);
//        stores.add(s3);

        SuperDuperMarket sup = new SuperDuperMarket("Rami_Levi");
        sup.addStore(s1);
        sup.addStore(s2);
        sup.addStore(s3);
        sup.addItem(item1);
        sup.addItem(item2);
        sup.addItem(item3);

        s1.addItem(item1, 6.54f);
        s2.addItem(item2, 3.2f);
        s3.addItem(item3, 7f);

        System.out.println("Show stores:");

        Set<Store> stores = sup.getStores();

        stores.forEach(System.out::println);

    }
}
