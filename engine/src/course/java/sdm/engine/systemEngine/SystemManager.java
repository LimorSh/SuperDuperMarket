package course.java.sdm.engine.systemEngine;
import course.java.sdm.engine.Configurations;
import course.java.sdm.engine.exceptions.StoreLocationExistsException;
import course.java.sdm.engine.systemDto.*;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class SystemManager {

    static SuperDuperMarket superDuperMarket;

    public static void loadSystemData(String dataPath) throws JAXBException, FileNotFoundException {
        superDuperMarket = DataLoader.loadFromXmlFile(dataPath);
    }

    public static Collection<StoreDto> getStoresDto() {
        return SuperDuperMarketDto.getStoresDto(superDuperMarket.getStores());
    }

    public static Collection<ItemDto> getItemsDto() {
        return SuperDuperMarketDto.getItemsDto(superDuperMarket.getItems());
    }

    public static Collection<OrderDto> getOrdersDto() {
        return SuperDuperMarketDto.getOrdersDto(superDuperMarket.getOrders());
    }

    public static int getNumberOfStoresSellingTheItem(ItemDto itemDto) {
        return superDuperMarket.getNumberOfStoresSellingTheItem(itemDto.getId());
    }

    public static float getAverageItemPrice(ItemDto itemDto) {
        return superDuperMarket.getAverageItemPrice(itemDto.getId());
    }

    public static float getTotalAmountOfItemSells(ItemDto itemDto) {
        return superDuperMarket.getTotalAmountOfItemSells(itemDto.getId());
    }

    public static Collection<StoreItemDto> getStoreItems(StoreDto storeDto) {
        return storeDto.getStoreItemsDto();
    }

    public static boolean isItemInTheStoreDto(StoreDto storeDto, ItemDto itemDto) {
        int storeId = storeDto.getId();
        int itemId = itemDto.getId();
        return superDuperMarket.isItemInTheStore(storeId, itemId);
    }

    public static float getItemPriceInStore(StoreDto storeDto, ItemDto itemDto) {
        int storeId = storeDto.getId();
        int itemId = itemDto.getId();
        return getItemPriceInStoreByIds(storeId, itemId);
    }

    public static float getItemPriceInStoreByIds(int storeId, int itemId) {
        return superDuperMarket.getItemPriceInStore(storeId, itemId);
    }

    public static StoreDto getStoreDto(int id) {
        return (new StoreDto(superDuperMarket.getStore(id)));
    }

    public static String getItemPurchaseCategory(int id) {
        return superDuperMarket.getItemPurchaseCategory(id);
    }

    public static String getItemName(int id) {
        return superDuperMarket.getItemName(id);
    }

    public static double getDistanceBetweenCustomerAndStore(StoreDto storeDto, int customerLocationX, int customerLocationY) {
        return Distance.getDistanceBetweenTwoLocations(storeDto.getXLocation(), storeDto.getYLocation(),
                                                                    customerLocationX, customerLocationY);
    }

    public static void createOrder(Date date, int customerLocationX, int customerLocationY, StoreDto store, Map<Integer, Float> itemsIdsAndQuantities) {
        superDuperMarket.createOrder(date, customerLocationX, customerLocationY, store.getId(), itemsIdsAndQuantities);
    }

    public static void validateLocation(int x, int y) {
        Location.isValidLocation(x, y);
        if (superDuperMarket.isLocationAlreadyExistsForStore(x, y)) {
            Store store = superDuperMarket.getStoreByLocation(x, y);
            throw new StoreLocationExistsException(store.getName(), x, y);
        }
    }

    public static void validateStoreIdExists(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("The store ID " + id + " is not a positive integer number.");
        }
        if (!superDuperMarket.isStoreExists(id)) {
            throw new IllegalArgumentException("The store ID " + id + " does not exists.");
        }
    }

    public static void validateItemIdExistsInStore(int storeId, int storeItemId) {
        validateItemExistInTheSuper(storeItemId);
        if (!superDuperMarket.isItemExistsInStore(storeId, storeItemId)) {
            throw new IllegalArgumentException("The item ID " + storeItemId + " does not exist in the store.");
        }
    }

    public static void updateItemPriceInStore(int storeItemId, float newItemPrice, int storeId) {
        superDuperMarket.updateItemPriceInStore(storeId, newItemPrice, storeItemId);
    }

    public static void validateAddItemToStore(int storeId, int storeItemId) {
        validateItemExistInTheSuper(storeItemId);
        if (superDuperMarket.isItemExistsInStore(storeId, storeItemId)) {
            throw new IllegalArgumentException("The item ID " + storeItemId + " already exist in the store.");
        }
    }

    public static void validateItemExistInTheSuper(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("The item ID " + id + " is not an positive integer number.");
        }
        if (!superDuperMarket.isItemExists(id)) {
            throw new IllegalArgumentException("The item ID " + id + " does not exist in the super market.");
        }
    }

    public static void addItemToStore(int itemId, float itemPrice, int storeId) {
        superDuperMarket.addItemToStore(itemId, itemPrice, storeId);
    }

    public static void deleteItemFromStore(int storeItemId, int storeId) {
        superDuperMarket.deleteItemFromStore(storeItemId, storeId);
    }

    public static void validateItemQuantity(int itemId, float quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("The quantity " + quantity + " is not a positive integer number. Item quantity should be greater than zero.");
        }
        String purchaseCategory = superDuperMarket.getItemPurchaseCategory(itemId);
        if (purchaseCategory.equals(Configurations.ITEM_PURCHASE_CATEGORY_PER_UNIT_STR)) {
            if ((quantity % 1) != 0) {
                throw new IllegalArgumentException("The item purchase category is '" + purchaseCategory + "', and the quantity should be in units - an integer.");
            }
        }
    }



}
