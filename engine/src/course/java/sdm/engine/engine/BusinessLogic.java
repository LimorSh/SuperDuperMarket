package course.java.sdm.engine.engine;
import course.java.sdm.engine.Constants;
import course.java.sdm.engine.dto.*;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.*;

public class BusinessLogic {

    private SuperDuperMarket superDuperMarket;

    public void loadSystemData(String dataPath) throws JAXBException, FileNotFoundException {
        superDuperMarket = DataLoader.loadFromXmlFile(dataPath);
    }

    public Collection<StoreDto> getStoresDto() {
        return SuperDuperMarketDto.getStoresDto(superDuperMarket.getStores());
    }

    public Collection<CustomerDto> getCustomersDto() {
        Collection<CustomerDto> customersDto = new ArrayList<>();

        Collection<Customer> customers = superDuperMarket.getCustomers();
        for (Customer customer : customers) {
            CustomerDto customerDto = getCustomerDto(customer.getId());
            customersDto.add(customerDto);
        }

        return customersDto;
    }

    public Collection<BasicCustomerDto> getBasicCustomersDto() {
        return SuperDuperMarketDto.getBasicCustomersDto(superDuperMarket.getCustomers());
    }

    public Collection<BasicItemDto> getBasicItemsDto() {
        return SuperDuperMarketDto.getBasicItemsDto(superDuperMarket.getItems());
    }

    public Collection<ItemDto> getItemsDto() {
        Collection<ItemDto> itemsDto = new ArrayList<>();

        Collection<Item> items = superDuperMarket.getItems();
        for (Item item : items) {
            int itemId = item.getId();
            int numberOfStoresSellingTheItem = superDuperMarket.getNumberOfStoresSellingTheItem(itemId);
            float averageItemPrice = superDuperMarket.getAverageItemPrice(itemId);
            float totalAmountOfItemSells = superDuperMarket.getTotalAmountOfItemSells(itemId);
            ItemDto itemDto = new ItemDto(item, numberOfStoresSellingTheItem, averageItemPrice, totalAmountOfItemSells);
            itemsDto.add(itemDto);
        }

        return itemsDto;
    }

    public Collection<ItemWithPriceDto> getItemsWithPriceDto(int storeId) {
        Collection<ItemWithPriceDto> itemsWithPriceDto = new ArrayList<>();
        Store store = superDuperMarket.getStore(storeId);

        Collection<Item> items = superDuperMarket.getItems();
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
        return SuperDuperMarketDto.getOrdersDto(superDuperMarket.getOrders());
    }

    public int getNumberOfStoresSellingTheItem(BasicItemDto basicItemDto) {
        return superDuperMarket.getNumberOfStoresSellingTheItem(basicItemDto.getId());
    }

    public float getAverageItemPrice(BasicItemDto basicItemDto) {
        return superDuperMarket.getAverageItemPrice(basicItemDto.getId());
    }

    public float getTotalAmountOfItemSells(BasicItemDto basicItemDto) {
        return superDuperMarket.getTotalAmountOfItemSells(basicItemDto.getId());
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
        return superDuperMarket.isItemInTheStore(storeId, itemId);
    }

    public float getItemPriceInStore(StoreDto storeDto, BasicItemDto basicItemDto) {
        int storeId = storeDto.getId();
        int itemId = basicItemDto.getId();
        return new BusinessLogic().getItemPriceInStoreByIds(storeId, itemId);
    }

    public float getItemPriceInStoreByIds(int storeId, int itemId) {
        return superDuperMarket.getItemPriceInStore(storeId, itemId);
    }

    public StoreDto getStoreDto(int id) {
        return (new StoreDto(superDuperMarket.getStore(id)));
    }

    public ItemWithPriceDto getItemWithPriceDto(int storeId, int itemId) {
        Store store = superDuperMarket.getStore(storeId);
        Item item = superDuperMarket.getItem(itemId);
        ItemWithPriceDto itemWithPriceDto = new ItemWithPriceDto(item, true);
        itemWithPriceDto.setPrice(store.getItemPrice(item));
        return itemWithPriceDto;
    }

    public CustomerDto getCustomerDto(int id) {
        Customer customer = superDuperMarket.getCustomer(id);
        int numberOfOrders = customer.getNumberOfOrders();
        float averageItemsCost = customer.getAverageItemsCost();
        float averageDeliveriesCost = customer.getAverageDeliveriesCost();
        return (new CustomerDto(customer, numberOfOrders, averageItemsCost, averageDeliveriesCost));
    }


    public String getItemPurchaseCategory(int id) {
        return superDuperMarket.getItemPurchaseCategory(id);
    }

    public String getItemName(int id) {
        return superDuperMarket.getItemName(id);
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
        return superDuperMarket.getDeliveryCost(storeId, customerId);
    }

    public double getDistanceBetweenCustomerAndStore(int storeId, int customerId) {
        return superDuperMarket.getDistanceBetweenCustomerAndStore(storeId, customerId);
    }

    public void createOrder(int customerId, Date date, int storeId, Map<Integer, Float> itemsIdsAndQuantities) {
        superDuperMarket.createOrder(customerId, date, storeId, itemsIdsAndQuantities);
    }

    public void createOrder(int customerId, Date date, Map<Integer, Float> itemsIdsAndQuantities) {
        superDuperMarket.createOrder(customerId, date, itemsIdsAndQuantities);
    }

    public Map<StoreDto, Map<Integer, Float>> getOptimalCart(Map<Integer, Float> itemsIdsAndQuantities) {
        Map<Store, Map<Integer, Float>> storesToItemIdsAndQuantities =
                superDuperMarket.getOptimalCartWithItemIds(itemsIdsAndQuantities);

        Map<StoreDto, Map<Integer, Float>> storesDtoToItemIdsAndQuantities = new HashMap<>();

        storesToItemIdsAndQuantities.forEach((store,itemIdsAndQuantities) -> {
            StoreDto storeDto = new StoreDto(store);
            storesDtoToItemIdsAndQuantities.put(storeDto, itemIdsAndQuantities);
        });

        return storesDtoToItemIdsAndQuantities;
    }





}
