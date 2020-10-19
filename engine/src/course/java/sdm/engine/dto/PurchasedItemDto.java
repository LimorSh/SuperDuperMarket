package course.java.sdm.engine.dto;

import course.java.sdm.engine.Constants;
import course.java.sdm.engine.Utils;

public class PurchasedItemDto {

    private final int id;
    private final String name;
    private final String purchaseCategory;
    private final int storeId;
    private final String storeName;
    private final float quantity;
    private final float cost;   //the price of one item/kg in the order
    private final float totalCost;
    private final String discount;

    public PurchasedItemDto(int id, String name, String purchaseCategory,
                            int storeId, String storeName, float quantity,
                            float cost, float totalCost, boolean discount) {
        this.id = id;
        this.name = name;
        this.purchaseCategory = purchaseCategory;
        this.storeId = storeId;
        this.storeName = storeName;
        this.quantity = Utils.roundNumberWithTwoDigitsAfterPoint(quantity);
        this.cost = Utils.roundNumberWithTwoDigitsAfterPoint(cost);
        this.totalCost = Utils.roundNumberWithTwoDigitsAfterPoint(totalCost);
        if (discount) {
            this.discount = Constants.PURCHASE_ITEM_FROM_DISCOUNT_STR;
        }
        else {
            this.discount = Constants.PURCHASE_ITEM_NOT_FROM_DISCOUNT_STR;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPurchaseCategory() {
        return purchaseCategory;
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public float getQuantity() {
        return quantity;
    }

    public float getCost() {
        return cost;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public String getDiscount() {
        return discount;
    }
}
