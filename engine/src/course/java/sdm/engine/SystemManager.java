package course.java.sdm.engine;

import course.java.sdm.engine.systemDto.*;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class SystemManager {

    static SuperDuperMarket superDuperMarket;

    public static void loadSystemData(String dataPath) {
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

    public static String getItemPurchaseCategoryPerUnitStr() {
        return Configurations.ITEM_PURCHASE_CATEGORY_PER_UNIT_STR;
    }

    public static String getItemPurchaseCategoryPerWeightStr() {
        return Configurations.ITEM_PURCHASE_CATEGORY_PER_WEIGHT_STR;
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
    }

    public static void validateStoreIdExists(int id) {
        if (!superDuperMarket.isStoreExists(id))
            throw new IllegalArgumentException("The store id " + id + " does not exists.");
    }

    public static void validateItemIdExists(int id) {
        if (superDuperMarket.isItemExists(id))
            throw new IllegalArgumentException("The item id " + id + " is already exists.");
    }


}
