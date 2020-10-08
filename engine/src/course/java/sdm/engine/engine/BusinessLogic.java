package course.java.sdm.engine.engine;
import course.java.sdm.engine.Constants;
import course.java.sdm.engine.dto.*;

import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.*;

public class BusinessLogic {
    private final Map<String, SuperDuperMarket> superDuperMarkets;    // the key is zone name
    private SuperDuperMarket chosenSuperDuperMarket;

    public BusinessLogic() {
        superDuperMarkets = new HashMap<>();
    }

    public synchronized void addSuperDuperMarket(InputStream fileDataInputStream,
                                                 String zoneOwnerName) throws JAXBException {
        SuperDuperMarket superDuperMarket = loadSystemData(fileDataInputStream);
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

    private SuperDuperMarket loadSystemData(InputStream fileDataInputStream) throws JAXBException {
        //        superDuperMarket.initNumOrders();
        return DataLoader.loadFromXmlFileDataInputStream(fileDataInputStream);
    }

    public Collection<StoreDto> getStoresDto() {
        return SuperDuperMarketDto.getStoresDto(chosenSuperDuperMarket.getStores());
    }

    public Collection<CustomerDto> getCustomersDto() {
        Collection<CustomerDto> customersDto = new ArrayList<>();

        Collection<Customer> customers = chosenSuperDuperMarket.getCustomers();
        for (Customer customer : customers) {
            CustomerDto customerDto = getCustomerDto(customer.getId());
            customersDto.add(customerDto);
        }

        return customersDto;
    }

    public Collection<BasicCustomerDto> getBasicCustomersDto() {
        return SuperDuperMarketDto.getBasicCustomersDto(chosenSuperDuperMarket.getCustomers());
    }

    public Collection<BasicItemDto> getBasicItemsDto() {
        return SuperDuperMarketDto.getBasicItemsDto(chosenSuperDuperMarket.getItems());
    }

    public Collection<ItemDto> getItemsDto() {
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

    public Collection<ItemWithPriceDto> getItemsWithPriceDto(int storeId) {
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

    public Collection<OrderDto> getOrdersDto() {
        return SuperDuperMarketDto.getOrdersDto(chosenSuperDuperMarket.getOrders());
    }

    public int getNumberOfStoresSellingTheItem(BasicItemDto basicItemDto) {
        return chosenSuperDuperMarket.getNumberOfStoresSellingTheItem(basicItemDto.getId());
    }

    public float getAverageItemPrice(BasicItemDto basicItemDto) {
        return chosenSuperDuperMarket.getAverageItemPrice(basicItemDto.getId());
    }

    public float getTotalAmountOfItemSells(BasicItemDto basicItemDto) {
        return chosenSuperDuperMarket.getTotalAmountOfItemSells(basicItemDto.getId());
    }

    public Collection<StoreItemDto> getStoreItems(StoreDto storeDto) {
        return storeDto.getStoreItemsDto();
    }

    public boolean isItemInTheStore(StoreDto storeDto, BasicItemDto basicItemDto) {
        int storeId = storeDto.getId();
        int itemId = basicItemDto.getId();
        return isItemInTheStore(storeId, itemId);
    }

    public boolean isItemInTheStore(int storeId, int itemId) {
        return chosenSuperDuperMarket.isItemInTheStore(storeId, itemId);
    }

    public float getItemPriceInStore(StoreDto storeDto, BasicItemDto basicItemDto) {
        int storeId = storeDto.getId();
        int itemId = basicItemDto.getId();
        return new BusinessLogic().getItemPriceInStoreByIds(storeId, itemId);
    }

    public float getItemPriceInStoreByIds(int storeId, int itemId) {
        return chosenSuperDuperMarket.getItemPriceInStore(storeId, itemId);
    }

    public StoreDto getStoreDto(int id) {
        return (new StoreDto(chosenSuperDuperMarket.getStore(id)));
    }

    public ItemWithPriceDto getItemWithPriceDto(int storeId, int itemId) {
        Store store = chosenSuperDuperMarket.getStore(storeId);
        Item item = chosenSuperDuperMarket.getItem(itemId);
        ItemWithPriceDto itemWithPriceDto = new ItemWithPriceDto(item, true);
        itemWithPriceDto.setPrice(store.getItemPrice(item));
        return itemWithPriceDto;
    }

    public CustomerDto getCustomerDto(int id) {
        Customer customer = chosenSuperDuperMarket.getCustomer(id);
        int numberOfOrders = customer.getNumberOfOrders();
        float averageItemsCost = customer.getAverageItemsCost();
        float averageDeliveriesCost = customer.getAverageDeliveriesCost();
        return (new CustomerDto(customer, numberOfOrders, averageItemsCost, averageDeliveriesCost));
    }


    public String getItemPurchaseCategory(int id) {
        return chosenSuperDuperMarket.getItemPurchaseCategory(id);
    }

    public String getItemName(int id) {
        return chosenSuperDuperMarket.getItemName(id);
    }

//    public static void createOrder(Date date, int customerLocationX, int customerLocationY, StoreDto store, Map<Integer, Float> itemsIdsAndQuantities) {
//        superDuperMarket.createOrder(date, customerLocationX, customerLocationY, store.getId(), itemsIdsAndQuantities);
//    }

//    public static void validateLocation(int x, int y) {
//        Location.isValidLocation(x, y);
//        if (superDuperMarket.isLocationAlreadyExists(x, y)) {
//            Object object = superDuperMarket.getObjectByLocation(x, y);
//            throw new DuplicateLocationException(object, x, y);
//        }
//    }

    public void validateStoreIdExists(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("The store ID " + id + " is not a positive integer number.");
        }
        if (!chosenSuperDuperMarket.isStoreExists(id)) {
            throw new IllegalArgumentException("The store ID " + id + " does not exists.");
        }
    }

    public void validateItemIdExistsInStore(int storeId, int storeItemId) {
        if (!chosenSuperDuperMarket.isItemExistsInStore(storeId, storeItemId)) {
            throw new IllegalArgumentException("The item does not exist in the store.");
        }
    }

    public void updateItemPriceInStore(int storeItemId, float newItemPrice, int storeId) {
        chosenSuperDuperMarket.updateItemPriceInStore(storeId, newItemPrice, storeItemId);
    }

    public void validateAddItemToStore(int storeId, int storeItemId) {
        new BusinessLogic().validateItemExistInTheSuper(storeItemId);
        if (chosenSuperDuperMarket.isItemExistsInStore(storeId, storeItemId)) {
            throw new IllegalArgumentException("The item ID " + storeItemId + " already exist in the store.");
        }
    }

    public void validateItemExistInTheSuper(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("The item ID " + id + " is not a positive integer number.");
        }
        if (!chosenSuperDuperMarket.isItemExists(id)) {
            throw new IllegalArgumentException("The item ID " + id + " does not exist in the super market.");
        }
    }

    public void addItemToStore(int itemId, float itemPrice, int storeId) {
        chosenSuperDuperMarket.addItemToStore(itemId, itemPrice, storeId);
    }

    public void deleteItemFromStore(int storeItemId, int storeId) {
        chosenSuperDuperMarket.deleteItemFromStore(storeItemId, storeId);
    }

    public void validateItemQuantity(int itemId, float quantity) {
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

    public float getDeliveryCost(int storeId, int customerId) {
        return chosenSuperDuperMarket.getDeliveryCost(storeId, customerId);
    }

    public double getDistanceBetweenCustomerAndStore(int storeId, int customerId) {
        return chosenSuperDuperMarket.getDistanceBetweenCustomerAndStore(storeId, customerId);
    }

    public void createOrder(int customerId, Date date, int storeId, Map<Integer, Float> itemsIdsAndQuantities,
                            Map<String, Collection<OfferDto>> appliedOffersDto) {
        chosenSuperDuperMarket.createOrder(customerId, date, storeId, itemsIdsAndQuantities,
                convertAppliedOffersDtoToOffers(appliedOffersDto));
    }

    public void createOrder(int customerId, Date date, Map<Integer, Float> itemsIdsAndQuantities,
                            Map<String, Collection<OfferDto>> appliedOffersDto) {
        chosenSuperDuperMarket.createOrder(customerId, date, itemsIdsAndQuantities,
                convertAppliedOffersDtoToOffers(appliedOffersDto));
    }

    private Map<String, ArrayList<Offer>> convertAppliedOffersDtoToOffers(Map<String, Collection<OfferDto>> appliedOffersDto) {
        Map<String, ArrayList<Offer>> appliedOffers = new HashMap<>();

        appliedOffersDto.forEach((discountName, offersDto) -> {
            ArrayList<Offer> offers = new ArrayList<>();
            for (OfferDto offerDto : offersDto) {
                int storeItemId = offerDto.getStoreItemId();
                Item item = chosenSuperDuperMarket.getItem(storeItemId);
                Offer offer = new Offer(item, offerDto.getQuantity(), offerDto.getAdditionalPrice());

                offers.add(offer);
            }
            appliedOffers.put(discountName, offers);
        });

        return appliedOffers;
    }

    public Map<StoreDto, Map<Integer, Float>> getOptimalCart(Map<Integer, Float> itemsIdsAndQuantities) {
        Map<Store, Map<Integer, Float>> storesToItemIdsAndQuantities =
                chosenSuperDuperMarket.getOptimalCartWithItemIds(itemsIdsAndQuantities);

        Map<StoreDto, Map<Integer, Float>> storesDtoToItemIdsAndQuantities = new HashMap<>();

        storesToItemIdsAndQuantities.forEach((store,itemIdsAndQuantities) -> {
            StoreDto storeDto = new StoreDto(store);
            storesDtoToItemIdsAndQuantities.put(storeDto, itemIdsAndQuantities);
        });

        return storesDtoToItemIdsAndQuantities;
    }

    public boolean isStoreHasDiscounts(int storeId) {
        return chosenSuperDuperMarket.isStoreHasDiscounts(storeId);
    }

    public boolean isStoresHaveDiscounts(Collection<Integer> storesIds) {
        for (Integer storeId : storesIds) {
            if (isStoreHasDiscounts(storeId)) {
                return true;
            }
        }
        return false;
    }


    public Map<StoreItemDto, Float> getStoreItemsDtoAndQuantities(int storeId,
                                                                   Map<Integer, Float> itemsIdsAndQuantities) {
        Map<StoreItemDto, Float> storeItemsDtoAndQuantities = new HashMap<>();

        itemsIdsAndQuantities.forEach((itemId,quantity) -> {
            StoreItem storeItem = chosenSuperDuperMarket.getStoreItem(storeId, itemId);
            StoreItemDto storeItemDto = new StoreItemDto(storeItem);
            storeItemsDtoAndQuantities.put(storeItemDto, quantity);
        });

        return storeItemsDtoAndQuantities;
    }

    public Map<StoreItemDto, Float> getStoreItemsDtoAndQuantities(Map<Integer, Float> itemsIdsAndQuantities) {
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

    public boolean isDiscountInStore(int storeId, String discountName) {
        return chosenSuperDuperMarket.isDiscountInStore(storeId, discountName);
    }

    public ArrayList<Integer> getMinAndMaxLocations() {
        ArrayList<Integer> minAndMaxLocations = new ArrayList<>(2);
        minAndMaxLocations.add(Location.getMinLocationValue());
        minAndMaxLocations.add(Location.getMaxLocationValue());
        return minAndMaxLocations;
    }

    public void createNewStore(int id, String name, int locationX, int locationY, int ppk, Map<Integer, Float> itemIdsAndPrices) {
        chosenSuperDuperMarket.addStore(id, name, locationX, locationY, ppk, itemIdsAndPrices);
    }

    public void validateStoreId(int id) {
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

    public void validateFreeLocation(int x, int y) {
        if (chosenSuperDuperMarket.isLocationAlreadyExists(x, y)) {
            throw new IllegalArgumentException(String.format("The location (%d, %d) already exists", x, y));
        }
    }

    public void validateItemId(int id) {
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

    public void createNewItem(int itemId, String itemName, String purchasedCategory,
                              Map<Integer, Float> storeIdsAndPrices) {
        chosenSuperDuperMarket.addItem(itemId, itemName, purchasedCategory, storeIdsAndPrices);
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
}
