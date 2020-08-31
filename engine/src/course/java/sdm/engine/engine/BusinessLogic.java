package course.java.sdm.engine.engine;
import course.java.sdm.engine.Configurations;
import course.java.sdm.engine.dto.*;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class BusinessLogic {

    private SuperDuperMarket superDuperMarket;

    public void loadSystemData(String dataPath) throws JAXBException, FileNotFoundException {
        superDuperMarket = DataLoader.loadFromXmlFile(dataPath);
    }

    public Collection<StoreDto> getStoresDto() {
        return SuperDuperMarketDto.getStoresDto(superDuperMarket.getStores());
    }

    public Collection<CustomerDto> getCustomersDto() {
        return SuperDuperMarketDto.getCustomersDto(superDuperMarket.getCustomers());
    }

    public Collection<ItemDto> getItemsDto() {
        return SuperDuperMarketDto.getItemsDto(superDuperMarket.getItems());
    }

    public Collection<OrderDto> getOrdersDto() {
        return SuperDuperMarketDto.getOrdersDto(superDuperMarket.getOrders());
    }

    public int getNumberOfStoresSellingTheItem(ItemDto itemDto) {
        return superDuperMarket.getNumberOfStoresSellingTheItem(itemDto.getId());
    }

    public float getAverageItemPrice(ItemDto itemDto) {
        return superDuperMarket.getAverageItemPrice(itemDto.getId());
    }

    public float getTotalAmountOfItemSells(ItemDto itemDto) {
        return superDuperMarket.getTotalAmountOfItemSells(itemDto.getId());
    }

    public Collection<StoreItemDto> getStoreItems(StoreDto storeDto) {
        return storeDto.getStoreItemsDto();
    }

    public boolean isItemInTheStoreDto(StoreDto storeDto, ItemDto itemDto) {
        int storeId = storeDto.getId();
        int itemId = itemDto.getId();
        return superDuperMarket.isItemInTheStore(storeId, itemId);
    }

    public float getItemPriceInStore(StoreDto storeDto, ItemDto itemDto) {
        int storeId = storeDto.getId();
        int itemId = itemDto.getId();
        return new BusinessLogic().getItemPriceInStoreByIds(storeId, itemId);
    }

    public float getItemPriceInStoreByIds(int storeId, int itemId) {
        return superDuperMarket.getItemPriceInStore(storeId, itemId);
    }

    public StoreDto getStoreDto(int id) {
        return (new StoreDto(superDuperMarket.getStore(id)));
    }

    public String getItemPurchaseCategory(int id) {
        return superDuperMarket.getItemPurchaseCategory(id);
    }

    public String getItemName(int id) {
        return superDuperMarket.getItemName(id);
    }

    public double getDistanceBetweenCustomerAndStore(StoreDto storeDto, int customerLocationX, int customerLocationY) {
        return Distance.getDistanceBetweenTwoLocations(storeDto.getXLocation(), storeDto.getYLocation(),
                                                                    customerLocationX, customerLocationY);
    }

    public void createOrder(CustomerDto customerDto, Date date, StoreDto store, Map<Integer, Float> itemsIdsAndQuantities) {
        int x = customerDto.getXLocation();
        int y = customerDto.getYLocation();
        superDuperMarket.createOrder(date, x, y, store.getId(), itemsIdsAndQuantities);

        // don't need the above - should be like this:
//        superDuperMarket.createOrder(customer, date, store.getId(), itemsIdsAndQuantities);
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
        if (!superDuperMarket.isStoreExists(id)) {
            throw new IllegalArgumentException("The store ID " + id + " does not exists.");
        }
    }

    public void validateItemIdExistsInStore(int storeId, int storeItemId) {
        new BusinessLogic().validateItemExistInTheSuper(storeItemId);
        if (!superDuperMarket.isItemExistsInStore(storeId, storeItemId)) {
            throw new IllegalArgumentException("The item ID " + storeItemId + " does not exist in the store.");
        }
    }

    public void updateItemPriceInStore(int storeItemId, float newItemPrice, int storeId) {
        superDuperMarket.updateItemPriceInStore(storeId, newItemPrice, storeItemId);
    }

    public void validateAddItemToStore(int storeId, int storeItemId) {
        new BusinessLogic().validateItemExistInTheSuper(storeItemId);
        if (superDuperMarket.isItemExistsInStore(storeId, storeItemId)) {
            throw new IllegalArgumentException("The item ID " + storeItemId + " already exist in the store.");
        }
    }

    public void validateItemExistInTheSuper(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("The item ID " + id + " is not a positive integer number.");
        }
        if (!superDuperMarket.isItemExists(id)) {
            throw new IllegalArgumentException("The item ID " + id + " does not exist in the super market.");
        }
    }

    public void addItemToStore(int itemId, float itemPrice, int storeId) {
        superDuperMarket.addItemToStore(itemId, itemPrice, storeId);
    }

    public void deleteItemFromStore(int storeItemId, int storeId) {
        superDuperMarket.deleteItemFromStore(storeItemId, storeId);
    }

    public void validateItemQuantity(int itemId, float quantity) {
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
