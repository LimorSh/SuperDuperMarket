package course.java.sdm.engine.engine;
import course.java.sdm.engine.Constants;
import course.java.sdm.engine.dto.OfferDto;
import course.java.sdm.engine.exception.DuplicateElementIdException;
import course.java.sdm.engine.exception.ItemDoesNotExistInTheStoreException;
import course.java.sdm.engine.exception.ItemDoesNotExistInTheSuperException;
import course.java.sdm.engine.exception.DuplicateLocationException;

import java.time.LocalDateTime;
import java.util.*;

public class SuperDuperMarket {

    private final Map<Integer, Customer> customers;
    private final Map<Integer, Store> stores;
    private final Map<Integer, Item> items;
    private final Map<Integer, Order> orders;
    private final Set<Item> itemsSold;
    private Object[][] locationGrid;

    public SuperDuperMarket() {
        customers = new HashMap<>();
        stores = new HashMap<>();
        items = new HashMap<>();
        orders = new HashMap<>();
        itemsSold = new HashSet<>();
        initializeLocationGrid();
    }

    private void initializeLocationGrid() {
        int maxLocation = Location.getMaxLocationValue();
        locationGrid = new Object[maxLocation][maxLocation];
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

    public void addItemIdToItemsSoldIds(Item item) {
        itemsSold.add(item);
    }

    public void addCustomer(Customer customer) {
        int id = customer.getId();
        if (!isCustomerExists(id)) {
            Location location = customer.getLocation();
            validateLocation(customer, location);
            addObjectToLocationGrid(customer, location);
            customers.put(id, customer);
        }
        else {
            Customer existentCustomer = getCustomer(id);
            throw new DuplicateElementIdException(customer, existentCustomer);
        }
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

    public void addStore(int id, String name, int locationX, int locationY, int ppk, Map<Integer, Float> itemIdsAndPrices) {
        Location location = new Location(locationX, locationY);
        Store store = new Store(id, name, ppk, location);
        addStore(store);

        itemIdsAndPrices.forEach((itemId, price) -> {
            Item item = getItem(itemId);
            store.addItem(item, price);
        });
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

    public Customer getCustomer(int id) {
        return customers.get(id);
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

    public void addItem(int itemId, String itemName, String purchasedCategory,
                                Map<Integer, Float> storeIdsAndPrices) {
        addItem(itemId, itemName, purchasedCategory);
        storeIdsAndPrices.forEach((storeId, itemPrice) -> {
            addItemToStore(itemId, itemPrice, storeId);
        });
    }

    public void addDiscountToStore(Discount discount, Store store) {
        validateDiscount(discount, store);
        store.addDiscount(discount);
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

    public boolean isCustomerExists(int id) {
        return customers.containsKey(id);
    }

    public void deleteItemFromStore(int storeItemId, int storeId) {
        if (getNumberOfStoresSellingTheItem(storeItemId) == 1) {
            throw new IllegalArgumentException("The item is currently being sold by this store only." +
                    "\nItem must be sell in at least one store in the super market.");
        }
        else {
            Store store = stores.get(storeId);
            store.deleteItem(storeItemId);
        }
    }

    public float getDeliveryCost(int storeId, int customerId) {
        Store store = getStore(storeId);
        Customer customer = getCustomer(customerId);
        return store.getDeliveryCost(customer.getLocation());
    }

    public double getDistanceBetweenCustomerAndStore(int storeId, int customerId) {
        Store store = stores.get(storeId);
        Customer customer = customers.get(customerId);
        Location customerLocation = customer.getLocation();
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

    public void createOrder(int customerId, Date date, Map<Integer, Float> itemsIdsAndQuantities,
                            Map<String, ArrayList<Offer>> appliedOffers) {
        Map<Store, Map<Item, Float>> storesToItemsAndQuantities = getOptimalCartWithItems(itemsIdsAndQuantities);

        Collection<DynamicOrderStoreData> dynamicOrderStoresData = getDynamicOrderStoresData(
                storesToItemsAndQuantities, appliedOffers);

        Customer customer = getCustomer(customerId);
        Order order = new Order(customer, date, Constants.ORDER_CATEGORY_DYNAMIC_STR);
        addOrder(order);

        order.addStoresOrder(dynamicOrderStoresData);

        order.finish(storesToItemsAndQuantities.keySet());
    }

    public void createOrder(int customerId, Date date, int storeId, Map<Integer, Float> itemsIdsAndQuantities,
                            Map<String, ArrayList<Offer>> appliedOffers) {
        Customer customer = getCustomer(customerId);
        Order order = new Order(customer, date, Constants.ORDER_CATEGORY_STATIC_STR);
        addOrder(order);

        Store store = getStore(storeId);

        Map<Item, Float> itemsAndQuantities = new HashMap<>();
        itemsIdsAndQuantities.forEach((itemId,itemQuantity) -> {
            Item item = getItem(itemId);
            itemsAndQuantities.put(item, itemQuantity);
        });
        order.addStoreOrder(store, itemsAndQuantities, appliedOffers);

        order.finish(store);
    }

    public boolean isStoreHasDiscounts(int id) {
        Store store = getStore(id);
        return store.hasDiscounts();
    }


    public boolean isDiscountInStore(int storeId, String discountName) {
        Store store = getStore(storeId);
        return store.isDiscountExist(discountName);
    }
}
