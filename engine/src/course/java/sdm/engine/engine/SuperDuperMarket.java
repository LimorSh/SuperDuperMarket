package course.java.sdm.engine.engine;
import course.java.sdm.engine.Constants;
import course.java.sdm.engine.engine.accounts.AccountManager;
import course.java.sdm.engine.exception.DuplicateElementIdException;
import course.java.sdm.engine.exception.ItemDoesNotExistInTheStoreException;
import course.java.sdm.engine.exception.ItemDoesNotExistInTheSuperException;
import course.java.sdm.engine.exception.DuplicateLocationException;

import java.util.*;

public class SuperDuperMarket {

    private String zoneName;
    private String zoneOwnerName;
    private final Map<String, Customer> customers;
    private final Map<Integer, Store> stores;
    private final Map<Integer, Item> items;
    private final Map<Integer, Order> orders;
    private final Map<String, Discount> discounts;
    private final Set<Item> itemsSold;
    private Object[][] locationGrid;

    public SuperDuperMarket() {
        customers = new HashMap<>();
        stores = new HashMap<>();
        items = new HashMap<>();
        orders = new HashMap<>();
        discounts = new HashMap<>();
        itemsSold = new HashSet<>();
        initializeLocationGrid();
    }

    private void initializeLocationGrid() {
        int maxLocation = Location.getMaxLocationValue();
        locationGrid = new Object[maxLocation][maxLocation];
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName.trim();
    }

    public String getZoneOwnerName() {
        return zoneOwnerName;
    }

    public void setZoneOwnerName(String zoneOwnerName) {
        this.zoneOwnerName = zoneOwnerName.trim();
    }

    public Collection<Customer> getCustomers() {
        return customers.values();
    }

    public Collection<Store> getStores() {
        return stores.values();
    }

    public Collection<Item> getItems() {
        return items.values();
    }

    public Collection<Order> getOrders() {
        return orders.values();
    }

    public int getTotalDifferentItems() {
        return items.keySet().size();
    }

    public int getTotalStores() {
        return stores.keySet().size();
    }

    public int getTotalOrders() {
        return orders.keySet().size();
    }

    public float getTotalOrdersCostAverageWithoutDelivery() {
        float avg = 0f;
        int totalOrders = getTotalOrders();
        if (totalOrders != 0) {
            float sum = 0f;
            for (Order order : orders.values()) {
                sum += order.getItemsCost();
            }
            avg = (sum / totalOrders);
        }
        return avg;
    }

    public boolean isItemInTheStore(int storeId, int itemId) {
        Store store = stores.get(storeId);
        return store.isItemInTheStore(itemId);
    }

    public float getItemPriceInStore(int storeId, int itemId) {
        Store store = stores.get(storeId);
        return store.getItemPrice(itemId);
    }

    public String getItemName(int itemId) {
        return items.get(itemId).getName();
    }

    public String getItemPurchaseCategory(int itemId) {
        return items.get(itemId).getPurchaseCategory().getPurchaseCategoryStr();
    }

    public Collection<StoreFeedback> getFeedbacks(String storeOwnerName) {
        Collection<StoreFeedback> feedbacks = new ArrayList<>();
        for (Order order : orders.values()) {
            for (StoreOrder storeOrder : order.getStoresOrder()) {
                if (storeOrder.getStore().getOwnerName().equalsIgnoreCase(storeOwnerName)) {
                    StoreFeedback feedback = storeOrder.getStoreFeedback();
                    if (feedback != null) {
                        feedbacks.add(feedback);
                    }
                }
            }
        }
        return feedbacks;
    }

    public void addItemIdToItemsSoldIds(Item item) {
        itemsSold.add(item);
    }

    public Customer createCustomer(String name) {
        Customer customer = new Customer(name);
        customers.put(name, customer);
        return customer;
    }

    public void addStore(Store store) {
        int id = store.getId();
        if (!isStoreExists(id)) {
            Location location = store.getLocation();
            validateLocation(store, location);
            addObjectToLocationGrid(store, location);
            stores.put(id, store);
        }
        else {
            Store existentStore = getStore(id);
            throw new DuplicateElementIdException(store, existentStore);
        }
    }

    private int getNextFreeStoreId() {
        return Collections.max(stores.keySet()) + 1;
    }

    public Store addStore(String ownerName, String storeName,
                         int locationX, int locationY, int ppk, Map<Integer, Float> itemIdsAndPrices) {
        Location location = new Location(locationX, locationY);
        int id = getNextFreeStoreId();
        Store store = new Store(id, storeName, ownerName, ppk, location);
        addStore(store);

        itemIdsAndPrices.forEach((itemId, price) -> {
            Item item = getItem(itemId);
            store.addItem(item, price);
        });
        return store;
    }

    private void validateLocation(Object object, Location location) {
        if (isLocationAlreadyExists(location)) {
            Object existentObject = getObjectByLocation(location);
            throw new DuplicateLocationException(object, existentObject, location);
        }
    }

    private void addObjectToLocationGrid(Object object, int x, int y) {
        locationGrid[x - 1][y - 1] = object;
    }

    private void addObjectToLocationGrid(Object object, Location location) {
        int x = location.getCoordinate().x;
        int y = location.getCoordinate().y;
        addObjectToLocationGrid(object, x, y);
    }

    public boolean isLocationAlreadyExists(int x, int y) {
        Object object = locationGrid[x - 1][y - 1];
        return object != null;
    }

    public boolean isLocationAlreadyExists(Location location) {
        int x = location.getCoordinate().x;
        int y = location.getCoordinate().y;
        return isLocationAlreadyExists(x, y);
    }

    public Object getObjectByLocation(int x, int y) {
        return locationGrid[x - 1][y - 1];
    }

    public Object getObjectByLocation(Location location) {
        int x = location.getCoordinate().x;
        int y = location.getCoordinate().y;
        return getObjectByLocation(x, y);
    }

    public void addItem(Item item) {
        int id = item.getId();
        if (!isItemExists(id)) {
            items.put(id, item);
        }
        else {
            Item existentItem = getItem(id);
            throw new DuplicateElementIdException(item, existentItem);
        }
    }

    public void addItem(int itemId, String itemName, String purchasedCategory) {
        Item.PurchaseCategory purchaseCategory = Item.convertStringToPurchaseCategory(purchasedCategory);
        Item item = new Item(itemId, itemName, purchaseCategory);
        addItem(item);
    }

    public void addOrder(Order order) {
        int id = order.getId();
        if (!orders.containsKey(id)) {
            orders.put(id, order);
        }
    }

    public boolean isItemExists(int id) {
        return items.containsKey(id);
    }

    public boolean isItemExistsInStore(int storeId, int storeItemId) {
        Store store = getStore(storeId);
        return store.isItemInTheStore(storeItemId);
    }

    public Customer getCustomer(String name) {
        return customers.get(name);
    }

    public StoreItem getStoreItem(int storeId, int itemId) {
        Store store = getStore(storeId);
        return store.getStoreItem(itemId);
    }

    public Item getItem(int id) {
        return items.get(id);
    }

    public Store getStore(int id) {
        return stores.get(id);
    }

    public Order getOrder(int id) {
        return orders.get(id);
    }

    public boolean isAllItemsAreBeingSoldByAtLeastOneStore() {
        return itemsSold.containsAll(items.values());
    }

    public void addItemToStore(int itemId, float itemPrice, Store store) {
        if (isItemExists(itemId)) {
            Item item = getItem(itemId);
            store.addItem(item, itemPrice);
            addItemIdToItemsSoldIds(item);
        }
        else {
            throw new ItemDoesNotExistInTheSuperException(itemId);
        }
    }

    public void addItemToStore(int itemId, float itemPrice, int storeId) {
        Store store = stores.get(storeId);
        addItemToStore(itemId, itemPrice, store);
    }

    private int getNextFreeItemId() {
        return Collections.max(items.keySet()) + 1;
    }

    // add new item from seller (not file)
    public void addItem(String itemName, String purchasedCategory,
                                Map<Integer, Float> storeIdsAndPrices) {
        int itemId =  getNextFreeItemId();
        addItem(itemId, itemName, purchasedCategory);
        storeIdsAndPrices.forEach((storeId, itemPrice) -> {
            addItemToStore(itemId, itemPrice, storeId);
        });
    }

    public void addDiscountToStore(Discount discount, Store store) {
        validateDiscount(discount, store);
        store.addDiscount(discount);
        discounts.put(discount.getName(), discount);
    }

    public void validateItemIsInSuperAndStore(int itemId, Store store, String discountName) {
        if (!isItemExists(itemId)) {
            String msg = String.format("The discount '%s' is not valid: " +
                    "item ID %d does not exist in the super market.", discountName, itemId);
            throw new IllegalArgumentException(msg);
        }
        if (!isItemInTheStore(store.getId(), itemId)) {
            String itemName = getItemName(itemId);
            String msg = String.format("The discount '%s' is not valid: " +
                            "The item %s (ID %d) does not exist in the store %s.",
                    discountName,itemName, itemId, store.getName());
            throw new IllegalArgumentException(msg);
        }
    }

    private void validateDiscount(Discount discount, Store store) {
        int discountItemId = discount.getStoreItemId();
        String discountName = discount.getName();
        validateItemIsInSuperAndStore(discountItemId, store, discountName);

        for (Offer offer : discount.getOffers()) {
            int offerItemId = offer.getItem().getId();
            validateItemIsInSuperAndStore(offerItemId, store, discountName);
        }
    }

    public void updateItemPriceInStore(int storeId, float newItemPrice, int storeItemId) {
        Store store = stores.get(storeId);
        if (!store.isItemInTheStore(storeItemId)) {
            Item item = items.get(storeItemId);
            throw new ItemDoesNotExistInTheStoreException(store.getName(), item.getName(), storeItemId);
        }
        store.updateItemPrice(storeItemId, newItemPrice);
    }

    public Set<Item> getItemsThatAreNotBeingSoldByAtLeastOneStore() {
        Set<Item> itemsCopy = new HashSet<>(items.values());
        itemsCopy.removeAll(itemsSold);
        return itemsCopy;
    }
    public int getNumberOfStoresSellingTheItem(int id) {
        return ((int) stores.values().stream().filter(store -> store.isItemInTheStore(items.get(id))).count());
    }

    public float getAverageItemPrice(int id) {
        float sum = 0f;
        Item item = items.get(id);
        for (Store store : stores.values()) {
            if (store.isItemInTheStore(item)) {
                sum += store.getItemPrice(item);
            }
        }
        return (sum / getNumberOfStoresSellingTheItem(id));
    }

    public float getTotalAmountOfItemSells(int id) {
        float amount = 0f;

        for (Order order : orders.values()) {
            if (order.isItemInTheOrder(id))
                amount += order.getItemQuantity(id);
        }
        return amount;
    }

    public boolean isStoreExists(int id) {
        return stores.containsKey(id);
    }

    public boolean isCustomerExists(String name) {
        return customers.containsKey(name);
    }

//    public void deleteItemFromStore(int storeItemId, int storeId) {
//        if (isItemExistsInStore(storeId, storeItemId)) {
//            if (getNumberOfStoresSellingTheItem(storeItemId) == 1) {
//                throw new IllegalArgumentException("The item is currently being sold by this store only." +
//                        "\nItem must be sell in at least one store in the super market.");
//            }
//            else {
//                Store store = stores.get(storeId);
//                store.deleteItem(storeItemId);
//                // need to delete store item discounts from discounts map here in the super
//            }
//        }
//        else {
//            Item item = getItem(storeItemId);
//            Store store = getStore(storeId);
//            throw new ItemDoesNotExistInTheStoreException(store.getName(), item.getName(), storeItemId);
//        }
//    }

    public float getStoreDeliveryCost(int storeId, int locationX, int locationY) {
        Store store = getStore(storeId);
        Location location = new Location(locationX, locationY);
        return store.getDeliveryCost(location);
    }

    public double getDistanceBetweenCustomerAndStore(int storeId, int customerLocationX, int customerLocationY) {
        Store store = stores.get(storeId);
        Location customerLocation = new Location(customerLocationX, customerLocationY);
        return store.getDistance(customerLocation);
    }

    public Map<Store, Map<Integer, Float>> getOptimalCartWithItemIds(Map<Integer, Float> itemsIdsAndQuantities) {
        Map<Store, Map<Integer, Float>> storesToItemIdsAndQuantities = new HashMap<>();

        itemsIdsAndQuantities.forEach((itemId,itemQuantity) -> {
            Store minStore = null;
            float minPrice = Float.MAX_VALUE;

            for (Store store : stores.values()) {
                if (store.isItemInTheStore(itemId)) {
                    float storeItemPrice = store.getItemPrice(itemId);
                    if (storeItemPrice < minPrice) {
                        minStore = store;
                        minPrice = storeItemPrice;
                    }
                }
            }

            if (storesToItemIdsAndQuantities.containsKey(minStore)) {
                storesToItemIdsAndQuantities.get(minStore).put(itemId, itemQuantity);
            }
            else {
                Map<Integer, Float> itemsAndQuantities = new HashMap<>();
                itemsAndQuantities.put(itemId, itemQuantity);
                storesToItemIdsAndQuantities.put(minStore, itemsAndQuantities);
            }
        });

        return storesToItemIdsAndQuantities;
    }

    public Map<Store, Map<Item, Float>> getOptimalCartWithItems(Map<Integer, Float> itemsIdsAndQuantities) {
        Map<Store, Map<Integer, Float>> storesToItemIdsAndQuantities = getOptimalCartWithItemIds(itemsIdsAndQuantities);
        Map<Store, Map<Item, Float>> storesToItemsAndQuantities = new HashMap<>();

        storesToItemIdsAndQuantities.forEach((store,itemIdsAndQuantities) -> {
            Map<Item, Float> itemsAndQuantities = new HashMap<>();

            itemIdsAndQuantities.forEach((itemId,itemQuantity) -> {
                Item item = getItem(itemId);
                itemsAndQuantities.put(item, itemQuantity);
            });
            storesToItemsAndQuantities.put(store, itemsAndQuantities);
        });

        return storesToItemsAndQuantities;
    }

    private Collection<DynamicOrderStoreData> getDynamicOrderStoresData (
            Map<Store, Map<Item, Float>> storesToItemsAndQuantities,
            Map<String, ArrayList<Offer>> appliedOffers) {

        Collection<DynamicOrderStoreData> dynamicOrderStoresData = new ArrayList<>();

        storesToItemsAndQuantities.forEach((store,itemsAndQuantities) -> {
            DynamicOrderStoreData dynamicOrderStoreData = new DynamicOrderStoreData(store, itemsAndQuantities);
            dynamicOrderStoresData.add(dynamicOrderStoreData);
            Map<String, ArrayList<Offer>> appliedOffersInStore = new HashMap<>();

            appliedOffers.forEach((discountName, offers) -> {
                if (store.isDiscountExist(discountName)) {
                    appliedOffersInStore.put(discountName, offers);
                }
            });

            dynamicOrderStoreData.setAppliedOffers(appliedOffersInStore);
        });

        return dynamicOrderStoresData;
    }

    public Customer getCustomerForNewOrder(String customerName) {
        Customer customer;
        if (!isCustomerExists(customerName)) {
            customer = createCustomer(customerName);
        }
        else {
            customer = getCustomer(customerName);
        }
        return customer;
    }

    private void transferPaymentToStoresOwners(AccountManager accountManager,
                                               Order order, String customerName) {
        for (StoreOrder storeOrder : order.getStoresOrder()) {
            float totalCost = storeOrder.getTotalCost();
            String storeOwner = storeOrder.getStore().getOwnerName();
            accountManager.transferCredit(order.getDate(), storeOwner, customerName, totalCost);
        }
    }

    private Offer getOfferByStoreItemId(String discountName, Integer storeItemId) {
        Discount discount = discounts.get(discountName);
        return discount.getOffer(storeItemId);
    }

    private Map<String, ArrayList<Offer>> getAppliedOffers(
            Map<String, Collection<Integer>> appliedOffersStoreItemsIds) {
        Map<String, ArrayList<Offer>> appliedOffers = new HashMap<>();

        appliedOffersStoreItemsIds.forEach((discountName, offersStoreItemsIds) -> {
            ArrayList<Offer> offers = new ArrayList<>();
            for (Integer storeItemId : offersStoreItemsIds) {
                Offer offer = getOfferByStoreItemId(discountName, storeItemId);
                offers.add(offer);
            }
            appliedOffers.put(discountName, offers);
        });

        return appliedOffers;
    }

    public int createOrder(AccountManager accountManager, String customerName, Date date,
                            int locationX, int locationY,
                            Map<Integer, Float> itemsIdsAndQuantities,
                            Map<String, Collection<Integer>> appliedOffersStoreItemsIds) {
        Map<Store, Map<Item, Float>> storesToItemsAndQuantities = getOptimalCartWithItems(itemsIdsAndQuantities);
        Collection<DynamicOrderStoreData> dynamicOrderStoresData = getDynamicOrderStoresData(
                storesToItemsAndQuantities, getAppliedOffers(appliedOffersStoreItemsIds));
        Customer customer = getCustomerForNewOrder(customerName);
        Location location = new Location(locationX, locationY);
        Order order = new Order(date, customer.getName(), location, Constants.ORDER_CATEGORY_DYNAMIC_STR);
        addOrder(order);
        order.addStoresOrder(dynamicOrderStoresData);
        Collection<Store> stores = storesToItemsAndQuantities.keySet();
        for (Store store : stores) {
            store.updateTotalDeliveriesRevenue(location);
        }
        customer.addOrder(order);
        transferPaymentToStoresOwners(accountManager, order, customerName);
        return order.getId();
    }

    public int createOrder(AccountManager accountManager, String customerName, Date date,
                            int locationX, int locationY,
                            int storeId, Map<Integer, Float> itemsIdsAndQuantities,
                            Map<String, Collection<Integer>> appliedOffersStoreItemsIds) {
        Customer customer = getCustomerForNewOrder(customerName);
        Location location = new Location(locationX, locationY);
        Order order = new Order(date, customer.getName(), location, Constants.ORDER_CATEGORY_STATIC_STR);
        addOrder(order);
        Store store = getStore(storeId);
        Map<Item, Float> itemsAndQuantities = new HashMap<>();
        itemsIdsAndQuantities.forEach((itemId,itemQuantity) -> {
            Item item = getItem(itemId);
            itemsAndQuantities.put(item, itemQuantity);
        });
        order.addStoreOrder(store, itemsAndQuantities, getAppliedOffers(appliedOffersStoreItemsIds));
        store.updateTotalDeliveriesRevenue(location);
        customer.addOrder(order);
        transferPaymentToStoresOwners(accountManager, order, customerName);
        return order.getId();
    }

    public boolean isStoreHasDiscounts(int id) {
        Store store = getStore(id);
        return store.hasDiscounts();
    }


    public boolean isDiscountInStore(int storeId, String discountName) {
        Store store = getStore(storeId);
        return store.isDiscountExist(discountName);
    }

    public Collection<Discount> getRelevantDiscounts(Map<Integer, Float> itemsIdsAndQuantities) {
        ArrayList<Discount> discounts = new ArrayList<>();
        Map<Store, Map<Integer, Float>> optimalCart = getOptimalCartWithItemIds(itemsIdsAndQuantities);
        optimalCart.forEach((store,optimalItemsIdsAndQuantities) -> {

            optimalItemsIdsAndQuantities.forEach((itemId,itemQuantity) -> {
                StoreItem storeItem = store.getStoreItem(itemId);
                discounts.addAll(storeItem.getRelevantDiscounts(itemQuantity));
            });
        });
        return  discounts;
    }

    public Collection<Discount> getRelevantDiscounts(int storeId, Map<Integer, Float> itemsIdsAndQuantities) {
        ArrayList<Discount> discounts = new ArrayList<>();
        Store store = getStore(storeId);
        itemsIdsAndQuantities.forEach((itemId,itemQuantity) -> {
            StoreItem storeItem = store.getStoreItem(itemId);
            discounts.addAll(storeItem.getRelevantDiscounts(itemQuantity));
        });
        return discounts;
    }

    public void addOrderFeedback(int orderId, Map<Integer, ArrayList<String>> storesAndRates) {
        Order order = getOrder(orderId);
        order.addFeedback(storesAndRates);
    }

    public Collection<Order> getCustomerOrders(String name) {
        Customer customer = getCustomer(name);
        return customer.getOrders();
    }

    public Collection<StoreOrder> getStoreOrders(int storeId) {
        Store store = getStore(storeId);
        return store.getStoreOrders();
    }

    public int getTotalItems() {
        return items.size();
    }
}
