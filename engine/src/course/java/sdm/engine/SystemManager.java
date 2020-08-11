package course.java.sdm.engine;

import course.java.sdm.engine.systemDto.ItemDto;
import course.java.sdm.engine.systemDto.StoreDto;
import course.java.sdm.engine.systemDto.StoreItemDto;
import course.java.sdm.engine.systemDto.SuperDuperMarketDto;

import java.util.Collection;
import java.util.stream.Collectors;

public class SystemManager {

    static SuperDuperMarket superDuperMarket;

    public static void loadSystemData(String dataPath) {
        superDuperMarket = DataLoader.loadFromXmlFile(dataPath);
    }

//    public static Map<Integer, Store> getStores() {
//        return superDuperMarket.getStores();
//    }

    public static Collection<StoreDto> getStoresDto() {
        return SuperDuperMarketDto.getStoresDto(superDuperMarket.getStores());
    }

    public static Collection<ItemDto> getItemsDto() {
        return SuperDuperMarketDto.getItemsDto(superDuperMarket.getItems());
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

    public static Collection<StoreDto> getActiveStoresDto() {
        return SuperDuperMarketDto.getStoresDto(superDuperMarket.getActiveStores());

//        return getStoresDto().stream().filter(StoreDto::isStoreActive).collect(Collectors.toList());
    }

    public static Collection<StoreItemDto> getStoreItems(StoreDto storeDto) {
        return storeDto.getStoreItemsDto();
    }

    public static boolean isItemInTheStoreDto(ItemDto itemDto, StoreDto storeDto) {
        int storeId = storeDto.getId();
        int itemId = itemDto.getId();
        return superDuperMarket.isItemInTheStore(storeId, itemId);
    }

    public static float getItemPriceInStore(ItemDto itemDto, StoreDto storeDto) {
        int storeId = storeDto.getId();
        int itemId = itemDto.getId();
        return superDuperMarket.getItemPriceInStore(storeId, itemId);
    }

    public static float getItemPriceInStoreByIds(int itemId, int storeId) {
        return superDuperMarket.getItemPriceInStore(storeId, itemId);
    }

    public static StoreDto getStoreDto(int storeId) {
        return (new StoreDto(superDuperMarket.getStore(storeId)));
    }

    public static String getItemPurchaseCategory(int itemId) {
        return superDuperMarket.getItemPurchaseCategory(itemId);
    }

    public static String getItemPurchaseTypePerUnitStr() {
        return Configurations.ITEM_PURCHASE_TYPE_PER_UNIT_STR;
    }

    public static String getItemPurchaseTypePerWeightStr() {
        return Configurations.ITEM_PURCHASE_TYPE_PER_WEIGHT_STR;
    }

    public static String getItemName(int itemId) {
        return superDuperMarket.getItemName(itemId);
    }

}
