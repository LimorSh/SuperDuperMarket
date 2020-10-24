package course.java.sdm.engine.engine;
import course.java.sdm.engine.Constants;
import course.java.sdm.engine.dto.*;
import course.java.sdm.engine.engine.accounts.AccountManager;
import course.java.sdm.engine.engine.notifications.NotificationManager;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class BusinessLogic {
    private final Map<String, SuperDuperMarket> superDuperMarkets;    // the key is zone name

    public BusinessLogic() {
        superDuperMarkets = new HashMap<>();
    }

    private SuperDuperMarket getChosenSuperDuperMarket(String zoneName) {
        return superDuperMarkets.get(zoneName);
    }

    public synchronized void addSuperDuperMarket(InputStream fileDataInputStream,
                                                 String zoneOwnerName) throws JAXBException {
        SuperDuperMarket superDuperMarket = loadSystemData(fileDataInputStream, zoneOwnerName);
        superDuperMarket.setZoneOwnerName(zoneOwnerName);
        String zoneName = superDuperMarket.getZoneName();
        if (!isZoneNameExists(zoneName)) {
            superDuperMarkets.put(zoneName, superDuperMarket);
        }
        else {
            throw new IllegalArgumentException("The zone name " + zoneName + " already exists. Zone name must be unique.");
        }
    }

    public boolean isZoneNameExists(String name) {
        for (String zoneName : superDuperMarkets.keySet()) {
            if (zoneName.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    private SuperDuperMarket loadSystemData(InputStream fileDataInputStream, String zoneOwnerName)
            throws JAXBException {
        return DataLoader.loadFromXmlFileDataInputStream(fileDataInputStream, zoneOwnerName);
    }

    public Collection<StoreDto> getStoresDto(String zoneName) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return SuperDuperMarketDto.getStoresDto(chosenSuperDuperMarket.getStores());
    }

    public Collection<StoreDto> getOwnerStoresDto(String zoneName, String ownerName) {
        Collection<StoreDto> storesDto = getStoresDto(zoneName);
        return storesDto.stream()
                .filter(storeDto-> storeDto.getOwnerName().equalsIgnoreCase(ownerName))
                .collect(Collectors.toList());
    }

    public Collection<CustomerDto> getCustomersDto(String zoneName) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Collection<CustomerDto> customersDto = new ArrayList<>();

        Collection<Customer> customers = chosenSuperDuperMarket.getCustomers();
        for (Customer customer : customers) {
            CustomerDto customerDto = getCustomerDto(zoneName, customer.getName());
            customersDto.add(customerDto);
        }

        return customersDto;
    }

    public Collection<BasicCustomerDto> getBasicCustomersDto(String zoneName) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return SuperDuperMarketDto.getBasicCustomersDto(chosenSuperDuperMarket.getCustomers());
    }

    public Collection<BasicStoreDto> getOwnerBasicStoresDto(String zoneName, String ownerName) {
        Collection<StoreDto> storesDto = getOwnerStoresDto(zoneName, ownerName);
        Collection<BasicStoreDto> basicStoresDto = new ArrayList<>();
        for (StoreDto storeDto : storesDto) {
            BasicStoreDto basicStoreDto = new BasicStoreDto(storeDto);
            basicStoresDto.add(basicStoreDto);
        }
        return basicStoresDto;
    }

    public Collection<BasicStoreDto> getBasicStoresDto(String zoneName) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return SuperDuperMarketDto.getBasicStoresDto(chosenSuperDuperMarket.getStores());
    }

    public Collection<BasicItemDto> getBasicItemsDto(String zoneName) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return SuperDuperMarketDto.getBasicItemsDto(chosenSuperDuperMarket.getItems());
    }

    public Collection<ItemDto> getItemsDto(String zoneName) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Collection<ItemDto> itemsDto = new ArrayList<>();

        Collection<Item> items = chosenSuperDuperMarket.getItems();
        for (Item item : items) {
            int itemId = item.getId();
            int numberOfStoresSellingTheItem = chosenSuperDuperMarket.getNumberOfStoresSellingTheItem(itemId);
            float averageItemPrice = chosenSuperDuperMarket.getAverageItemPrice(itemId);
            float totalAmountOfItemSells = chosenSuperDuperMarket.getTotalAmountOfItemSells(itemId);
            ItemDto itemDto = new ItemDto(item, numberOfStoresSellingTheItem, averageItemPrice, totalAmountOfItemSells);
            itemsDto.add(itemDto);
        }

        return itemsDto;
    }

    public Collection<ItemWithPriceDto> getItemsWithPriceDto(String zoneName, int storeId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Collection<ItemWithPriceDto> itemsWithPriceDto = new ArrayList<>();
        Store store = chosenSuperDuperMarket.getStore(storeId);

        Collection<Item> items = chosenSuperDuperMarket.getItems();
        for (Item item : items) {
            ItemWithPriceDto itemWithPriceDto;
            if (store.isItemInTheStore(item)) {
                itemWithPriceDto = new ItemWithPriceDto(item, true);
                itemWithPriceDto.setPrice(store.getItemPrice(item));
            }
            else {
                itemWithPriceDto = new ItemWithPriceDto(item, false);
            }
            itemsWithPriceDto.add(itemWithPriceDto);
        }

        return itemsWithPriceDto;
    }

    public Collection<OrderDto> getOrdersDto(String zoneName) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return SuperDuperMarketDto.getOrdersDto(chosenSuperDuperMarket.getOrders());
    }

    public int getNumberOfStoresSellingTheItem(String zoneName, BasicItemDto basicItemDto) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.getNumberOfStoresSellingTheItem(basicItemDto.getId());
    }

    public float getAverageItemPrice(String zoneName, BasicItemDto basicItemDto) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.getAverageItemPrice(basicItemDto.getId());
    }

    public float getTotalAmountOfItemSells(String zoneName, BasicItemDto basicItemDto) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.getTotalAmountOfItemSells(basicItemDto.getId());
    }

    public Collection<StoreItemDto> getStoreItems(StoreDto storeDto) {
        return storeDto.getStoreItemsDto();
    }

    public boolean isItemInTheStore(String zoneName, StoreDto storeDto, BasicItemDto basicItemDto) {
        int storeId = storeDto.getId();
        int itemId = basicItemDto.getId();
        return isItemInTheStore(zoneName, storeId, itemId);
    }

    public boolean isItemInTheStore(String zoneName, int storeId, int itemId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.isItemInTheStore(storeId, itemId);
    }

    public float getItemPriceInStore(String zoneName, StoreDto storeDto, BasicItemDto basicItemDto) {
        int storeId = storeDto.getId();
        int itemId = basicItemDto.getId();
        return new BusinessLogic().getItemPriceInStoreByIds(zoneName, storeId, itemId);
    }

    public float getItemPriceInStoreByIds(String zoneName, int storeId, int itemId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.getItemPriceInStore(storeId, itemId);
    }

    public StoreDto getStoreDto(String zoneName, int id) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return (new StoreDto(chosenSuperDuperMarket.getStore(id)));
    }

    public ItemWithPriceDto getItemWithPriceDto(String zoneName, int storeId, int itemId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Store store = chosenSuperDuperMarket.getStore(storeId);
        Item item = chosenSuperDuperMarket.getItem(itemId);
        ItemWithPriceDto itemWithPriceDto = new ItemWithPriceDto(item, true);
        itemWithPriceDto.setPrice(store.getItemPrice(item));
        return itemWithPriceDto;
    }

    public CustomerDto getCustomerDto(String zoneName, String name) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Customer customer = chosenSuperDuperMarket.getCustomer(name);
        int numberOfOrders = customer.getNumberOfOrders();
        float averageItemsCost = customer.getAverageItemsCost();
        float averageDeliveriesCost = customer.getAverageDeliveriesCost();
        return (new CustomerDto(customer, numberOfOrders, averageItemsCost, averageDeliveriesCost));
    }


    public String getItemPurchaseCategory(String zoneName, int id) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.getItemPurchaseCategory(id);
    }

    public String getItemName(String zoneName, int id) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.getItemName(id);
    }

    public void validateStoreIdExists(String zoneName, int id) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        if (id <= 0) {
            throw new IllegalArgumentException("The store ID " + id + " is not a positive integer number.");
        }
        if (!chosenSuperDuperMarket.isStoreExists(id)) {
            throw new IllegalArgumentException("The store ID " + id + " does not exists.");
        }
    }

    public void validateItemIdExistsInStore(String zoneName, int storeId, int storeItemId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        if (!chosenSuperDuperMarket.isItemExistsInStore(storeId, storeItemId)) {
            throw new IllegalArgumentException("The item does not exist in the store.");
        }
    }

    public void updateItemPriceInStore(String zoneName, int storeItemId, float newItemPrice, int storeId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        chosenSuperDuperMarket.updateItemPriceInStore(storeId, newItemPrice, storeItemId);
    }

    public void validateAddItemToStore(String zoneName, int storeId, int storeItemId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        new BusinessLogic().validateItemExistInTheSuper(zoneName, storeItemId);
        if (chosenSuperDuperMarket.isItemExistsInStore(storeId, storeItemId)) {
            throw new IllegalArgumentException("The item ID " + storeItemId + " already exist in the store.");
        }
    }

    public void validateItemExistInTheSuper(String zoneName, int id) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        if (id <= 0) {
            throw new IllegalArgumentException("The item ID " + id + " is not a positive integer number.");
        }
        if (!chosenSuperDuperMarket.isItemExists(id)) {
            throw new IllegalArgumentException("The item ID " + id + " does not exist in the super market.");
        }
    }

    public void addItemToStore(String zoneName, int itemId, float itemPrice, int storeId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        chosenSuperDuperMarket.addItemToStore(itemId, itemPrice, storeId);
    }

//    public void deleteItemFromStore(String zoneName, int storeItemId, int storeId) {
//        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
//        chosenSuperDuperMarket.deleteItemFromStore(storeItemId, storeId);
//    }

    public void validateItemQuantity(String zoneName, int itemId, float quantity) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity " + quantity + " is not a positive integer number. Item quantity should be greater than zero.");
        }
        String purchaseCategory = chosenSuperDuperMarket.getItemPurchaseCategory(itemId);
        if (purchaseCategory.equals(Constants.ITEM_PURCHASE_CATEGORY_PER_UNIT_STR)) {
            if ((quantity % 1) != 0) {
                throw new IllegalArgumentException("The item purchase category is '" + purchaseCategory + "', and the quantity should be in units - an integer.");
            }
        }
    }

    public double getDistanceBetweenCustomerAndStore(StoreDto storeDto, int customerLocationX, int customerLocationY) {
        return Distance.getDistanceBetweenTwoLocations(storeDto.getXLocation(), storeDto.getYLocation(),
                customerLocationX, customerLocationY);
    }

    public float getStoreDeliveryCost(String zoneName, int storeId, int locationX, int locationY) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.getStoreDeliveryCost(storeId, locationX, locationY);
    }

    public double getDistanceBetweenCustomerAndStore(String zoneName, int storeId,
                                                     int customerLocationX, int customerLocationY) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.getDistanceBetweenCustomerAndStore(storeId, customerLocationX, customerLocationY);
    }

    public int createOrder(AccountManager accountManager, String zoneName, String username, Date date,
                            int locationX, int locationY,
                            int storeId, Map<Integer, Float> itemsIdsAndQuantities,
                            Map<String, Collection<Integer>> appliedOffersStoreItemsIds) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.createOrder(accountManager, username, date,
                locationX, locationY, storeId, itemsIdsAndQuantities, appliedOffersStoreItemsIds);
    }

    public int createOrder(AccountManager accountManager, String zoneName, String username, Date date,
                            int locationX, int locationY,
                            Map<Integer, Float> itemsIdsAndQuantities,
                            Map<String, Collection<Integer>> appliedOffersStoreItemsIds) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.createOrder(accountManager, username, date,
                locationX, locationY, itemsIdsAndQuantities, appliedOffersStoreItemsIds);
    }

    public Map<StoreDto, Map<Integer, Float>> getOptimalCart(String zoneName,
                                                             Map<Integer, Float> itemsIdsAndQuantities) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Map<Store, Map<Integer, Float>> storesToItemIdsAndQuantities =
                chosenSuperDuperMarket.getOptimalCartWithItemIds(itemsIdsAndQuantities);

        Map<StoreDto, Map<Integer, Float>> storesDtoToItemIdsAndQuantities = new HashMap<>();

        storesToItemIdsAndQuantities.forEach((store,itemIdsAndQuantities) -> {
            StoreDto storeDto = new StoreDto(store);
            storesDtoToItemIdsAndQuantities.put(storeDto, itemIdsAndQuantities);
        });

        return storesDtoToItemIdsAndQuantities;
    }

    public Collection<DynamicOrderStoreDetailsDto> getDynamicOrderStoresDetailsDto(
            String zoneName, Map<Integer, Float> itemsIdsAndQuantities,
            int customerLocationX, int customerLocationY) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Map<Store, Map<Integer, Float>> storesToItemIdsAndQuantities =
                chosenSuperDuperMarket.getOptimalCartWithItemIds(itemsIdsAndQuantities);

        Collection<DynamicOrderStoreDetailsDto> dynamicOrderStoresDetailsDto = new ArrayList<>();
        storesToItemIdsAndQuantities.forEach((store,itemIdsAndQuantities) -> {
            int id = store.getId();
            String name = store.getName();
            int storeLocationX = store.getLocation().getCoordinate().x;
            int storeLocationY = store.getLocation().getCoordinate().y;
            double distance = store.getDistance(customerLocationX, customerLocationY);
            int ppk = store.getPpk();
            float deliveryCost = store.getDeliveryCost(customerLocationX, customerLocationY);
            int differentItemsType = itemIdsAndQuantities.keySet().size();
            float itemsCost = store.getItemsCost(itemIdsAndQuantities);
            DynamicOrderStoreDetailsDto dynamicOrderStoreDetailsDto =
                    new DynamicOrderStoreDetailsDto(id, name, storeLocationX, storeLocationY,
                            distance, ppk, deliveryCost, differentItemsType, itemsCost, itemIdsAndQuantities);
            dynamicOrderStoresDetailsDto.add(dynamicOrderStoreDetailsDto);
        });

        return dynamicOrderStoresDetailsDto;
    }

    public boolean isStoreHasDiscounts(String zoneName, int storeId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.isStoreHasDiscounts(storeId);
    }

    public boolean isStoresHaveDiscounts(String zoneName, Collection<Integer> storesIds) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        for (Integer storeId : storesIds) {
            if (isStoreHasDiscounts(zoneName, storeId)) {
                return true;
            }
        }
        return false;
    }


    public Map<StoreItemDto, Float> getStoreItemsDtoAndQuantities(String zoneName, int storeId,
                                                                   Map<Integer, Float> itemsIdsAndQuantities) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Map<StoreItemDto, Float> storeItemsDtoAndQuantities = new HashMap<>();

        itemsIdsAndQuantities.forEach((itemId,quantity) -> {
            StoreItem storeItem = chosenSuperDuperMarket.getStoreItem(storeId, itemId);
            StoreItemDto storeItemDto = new StoreItemDto(storeItem);
            storeItemsDtoAndQuantities.put(storeItemDto, quantity);
        });

        return storeItemsDtoAndQuantities;
    }

    public Map<StoreItemDto, Float> getStoreItemsDtoAndQuantities(String zoneName, Map<Integer, Float> itemsIdsAndQuantities) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Map<StoreItemDto, Float> storeItemsDtoAndQuantities = new HashMap<>();
        Map<Store, Map<Integer, Float>> optimalCartWithItemIds =
                chosenSuperDuperMarket.getOptimalCartWithItemIds(itemsIdsAndQuantities);

        optimalCartWithItemIds.forEach((store, itemIdsAndQuantities) -> {

            itemIdsAndQuantities.forEach((itemId,quantity) -> {
                StoreItem storeItem = chosenSuperDuperMarket.getStoreItem(store.getId(), itemId);
                StoreItemDto storeItemDto = new StoreItemDto(storeItem);
                storeItemsDtoAndQuantities.put(storeItemDto, quantity);
            });
        });

        return storeItemsDtoAndQuantities;
    }

    public boolean isDiscountInStore(String zoneName, int storeId, String discountName) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        return chosenSuperDuperMarket.isDiscountInStore(storeId, discountName);
    }

    public ArrayList<Integer> getMinAndMaxLocations() {
        ArrayList<Integer> minAndMaxLocations = new ArrayList<>(2);
        minAndMaxLocations.add(Location.getMinLocationValue());
        minAndMaxLocations.add(Location.getMaxLocationValue());
        return minAndMaxLocations;
    }

    public void createNewStore(NotificationManager notificationManager, String zoneName,
                               String ownerName, String storeName,
                               int locationX, int locationY, int ppk, Map<Integer, Float> itemIdsAndPrices) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        chosenSuperDuperMarket.addStore(notificationManager, ownerName, storeName,
                locationX, locationY, ppk, itemIdsAndPrices);
    }

    public void validateStoreId(String zoneName, int id) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        if (id <= 0) {
            throw new IllegalArgumentException("The store ID " + id + " is not a positive integer number.");
        }
        if (chosenSuperDuperMarket.isStoreExists(id)) {
            throw new IllegalArgumentException("This store ID already exists.");
        }
    }

    public void validateCoordinate(int coordinate) {
        if (!Location.isValidCoordinate(coordinate)) {
            throw new IllegalArgumentException("A coordinate should be between: "
                    + Location.getMinLocationValue() + " and " + Location.getMaxLocationValue() + ".");
        }
    }

    public void validateStorePpk(int ppk) {
        if (ppk < 0) {
            throw new IllegalArgumentException("The store PPK " + ppk + " is not a non-negative integer number.");
        }
    }

    public void validateFreeLocation(String zoneName, int x, int y) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        if (chosenSuperDuperMarket.isLocationAlreadyExists(x, y)) {
            throw new IllegalArgumentException(String.format("The location (%d, %d) already exists", x, y));
        }
    }

    public void validateItemId(String zoneName, int id) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        if (id <= 0) {
            throw new IllegalArgumentException("The item ID " + id + " is not a positive integer number.");
        }
        if (chosenSuperDuperMarket.isItemExists(id)) {
            throw new IllegalArgumentException("This item ID already exists.");
        }
    }

    public String getPurchaseCategoryPerUnitStr() {
        return Item.PurchaseCategory.PER_UNIT.getPurchaseCategoryStr();
    }

    public String getPurchaseCategoryPerWeightStr() {
        return Item.PurchaseCategory.PER_WEIGHT.getPurchaseCategoryStr();
    }

    public void createNewItem(String zoneName, String itemName, String purchasedCategory,
                              Map<Integer, Float> storeIdsAndPrices) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        chosenSuperDuperMarket.addItem(itemName, purchasedCategory, storeIdsAndPrices);
    }

    public ZoneDetailsDto getZoneDetailsDto(SuperDuperMarket superDuperMarket) {
        String zoneOwnerName = superDuperMarket.getZoneOwnerName();
        String zoneName = superDuperMarket.getZoneName();
        int totalDifferentItems = superDuperMarket.getTotalDifferentItems();
        int totalStores = superDuperMarket.getTotalStores();
        int totalOrders = superDuperMarket.getTotalOrders();
        float totalOrdersCostAverageWithoutDelivery = superDuperMarket.getTotalOrdersCostAverageWithoutDelivery();
        return new ZoneDetailsDto(zoneOwnerName, zoneName, totalDifferentItems,
                        totalStores, totalOrders, totalOrdersCostAverageWithoutDelivery
                );
    }

    public Set<ZoneDetailsDto> getZonesDetailsDto() {
        Set<ZoneDetailsDto> zonesDetailsDto = new HashSet<>();
        for (SuperDuperMarket superDuperMarket : superDuperMarkets.values()) {
            ZoneDetailsDto zoneDetailsDto = getZoneDetailsDto(superDuperMarket);
            zonesDetailsDto.add(zoneDetailsDto);
        }
        return zonesDetailsDto;
    }

    private Collection<DiscountDto> convertDiscountsToDiscountsDto(SuperDuperMarket superDuperMarket,
                                           Collection<Discount> discounts) {
        Collection<DiscountDto> discountsDto = new ArrayList<>();
        for (Discount discount : discounts) {
            int storeItemId = discount.getStoreItemId();
            String storeItemName = superDuperMarket.getItemName(storeItemId);
            DiscountDto discountDto = new DiscountDto(discount, storeItemName);
            discountsDto.add(discountDto);
        }
        return discountsDto;
    }

    public Collection<DiscountDto> getRelevantDiscounts(String zoneName, Map<Integer, Float> itemsIdsAndQuantities) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Collection<Discount> discounts =
                chosenSuperDuperMarket.getRelevantDiscounts(itemsIdsAndQuantities);
        return convertDiscountsToDiscountsDto(chosenSuperDuperMarket, discounts);
    }

    public Collection<DiscountDto> getRelevantDiscounts(String zoneName, int StoreId,
                                                        Map<Integer, Float> itemsIdsAndQuantities) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Collection<Discount> discounts =
                chosenSuperDuperMarket.getRelevantDiscounts(StoreId, itemsIdsAndQuantities);
        return convertDiscountsToDiscountsDto(chosenSuperDuperMarket, discounts);
    }

    public void addOrderFeedback(NotificationManager notificationManager, String zoneName, int orderId,
                                 Map<Integer, ArrayList<String>> storesAndRates) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        chosenSuperDuperMarket.addOrderFeedback(notificationManager, orderId, storesAndRates);
    }

    public ArrayList<PurchasedItemDto> getOrderPurchasedItemsDtoFromOrderLines
            (StoreOrder storeOrder) {
        ArrayList<PurchasedItemDto> purchasedItemsDto = new ArrayList<>();
        Map<Integer, OrderLine> orderLines = storeOrder.getOrderLines();
        for (OrderLine orderLine : orderLines.values()) {
            PurchasedItemDto purchasedItemDto = new PurchasedItemDto(
                    orderLine.getItem().getId(),
                    orderLine.getItem().getName(),
                    orderLine.getItem().getPurchaseCategory().getPurchaseCategoryStr(),
                    storeOrder.getStore().getId(),
                    storeOrder.getStore().getName(),
                    orderLine.getQuantity(),
                    orderLine.getCost(),
                    orderLine.getTotalCost(),
                    false
            );
            purchasedItemsDto.add(purchasedItemDto);
        }
        return purchasedItemsDto;
    }

    public ArrayList<PurchasedItemDto> getOrderPurchasedItemsDtoFromAppliedOffers
            (StoreOrder storeOrder) {
        ArrayList<PurchasedItemDto> purchasedItemsDto = new ArrayList<>();
        Map<String, ArrayList<Offer>> appliedOffers = storeOrder.getAppliedOffers();
        for (ArrayList<Offer> offers : appliedOffers.values()) {
            for (Offer offer : offers) {
                PurchasedItemDto purchasedItemDto = new PurchasedItemDto(
                        offer.getItem().getId(),
                        offer.getItem().getName(),
                        offer.getItem().getPurchaseCategory().getPurchaseCategoryStr(),
                        storeOrder.getStore().getId(),
                        storeOrder.getStore().getName(),
                        (float) offer.getQuantity(),
                        offer.getAdditionalPrice(),
                        offer.getTotalCost(),
                        true
                );
                purchasedItemsDto.add(purchasedItemDto);
            }
        }
        return purchasedItemsDto;
    }

    private ArrayList<PurchasedItemDto> getOrderPurchasedItemsDto(Order order) {
        Collection<StoreOrder> storesOrder = order.getStoresOrder();
        return getOrderPurchasedItemsDto(storesOrder);
    }

    private Collection<PurchasedItemDto> getStoreOrderPurchasedItemsDto
            (StoreOrder storeOrder) {
        ArrayList<PurchasedItemDto> purchasedItemsDto = new ArrayList<>();
        ArrayList<PurchasedItemDto> purchasedItemsDtoFromOrderLines =
                getOrderPurchasedItemsDtoFromOrderLines(storeOrder);

        ArrayList<PurchasedItemDto> purchasedItemsDtoFromAppliedOffers =
                getOrderPurchasedItemsDtoFromAppliedOffers(storeOrder);

        purchasedItemsDto.addAll(purchasedItemsDtoFromOrderLines);
        purchasedItemsDto.addAll(purchasedItemsDtoFromAppliedOffers);
        return purchasedItemsDto;
    }

    private ArrayList<PurchasedItemDto> getOrderPurchasedItemsDto(Collection<StoreOrder> storesOrder) {
        ArrayList<PurchasedItemDto> purchasedItemsDto = new ArrayList<>();
        for (StoreOrder storeOrder : storesOrder) {
            purchasedItemsDto.addAll(getStoreOrderPurchasedItemsDto(storeOrder));
        }
        return purchasedItemsDto;
    }

    public Collection<OrderDto> getOrderHistory(String zoneName, String username) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Collection<OrderDto> ordersDto = new ArrayList<>();
        if (chosenSuperDuperMarket.isCustomerExists(username)) {
            Collection<Order> orders = chosenSuperDuperMarket.getCustomerOrders(username);
            ArrayList<PurchasedItemDto> purchasedItemsDto;
            for (Order order : orders) {
                OrderDto orderDto = new OrderDto(order);
                purchasedItemsDto = getOrderPurchasedItemsDto(order);
                orderDto.setPurchasedItemsDto(purchasedItemsDto);
                ordersDto.add(orderDto);
            }
        }
        return ordersDto;
    }

    public Collection<StoreOrderDto> getStoreOrderHistory(String zoneName, int storeId) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Collection<StoreOrderDto> storeOrdersDto = new ArrayList<>();
        Collection<StoreOrder> storeOrders = chosenSuperDuperMarket.getStoreOrders(storeId);

        for (StoreOrder storeOrder : storeOrders) {
            StoreOrderDto storeOrderDto = new StoreOrderDto(storeOrder);
            Collection<PurchasedItemDto> purchasedItemsDto = getStoreOrderPurchasedItemsDto(storeOrder);
            ArrayList<PurchasedItemStoreOrderDto> purchasedItemsStoreOrderDto = new ArrayList<>();
            for (PurchasedItemDto purchasedItemDto : purchasedItemsDto) {
                PurchasedItemStoreOrderDto purchasedItemStoreOrderDto =
                        new PurchasedItemStoreOrderDto(purchasedItemDto);
                purchasedItemsStoreOrderDto.add(purchasedItemStoreOrderDto);
            }
            storeOrderDto.setPurchasedItemStoreOrderDto(purchasedItemsStoreOrderDto);
            storeOrdersDto.add(storeOrderDto);
        }
        return storeOrdersDto;
    }

    public Collection<StoreFeedbackDto> getFeedbacksDto(String zoneName, String storeOwnerName) {
        SuperDuperMarket chosenSuperDuperMarket = getChosenSuperDuperMarket(zoneName);
        Collection<StoreFeedbackDto> feedbacksDto = new ArrayList<>();
        Collection<StoreFeedback> feedbacks = chosenSuperDuperMarket.getFeedbacks(storeOwnerName);
        for (StoreFeedback storeFeedback : feedbacks) {
            StoreFeedbackDto storeFeedbackDto = new StoreFeedbackDto(storeFeedback);
            feedbacksDto.add(storeFeedbackDto);
        }
        return feedbacksDto;
    }




}
