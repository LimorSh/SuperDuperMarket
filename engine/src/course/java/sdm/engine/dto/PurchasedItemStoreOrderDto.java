package course.java.sdm.engine.dto;

import course.java.sdm.engine.Constants;
import course.java.sdm.engine.Utils;

public class PurchasedItemStoreOrderDto {

    private final int id;
    private final String name;
    private final String purchaseCategory;
    private final float quantity;
    private final float cost;   //the price of one item/kg in the order
    private final float totalCost;
    private final String discount;

    public PurchasedItemStoreOrderDto(int id, String name, String purchaseCategory,
                                      float quantity, float cost, float totalCost,
                                      boolean discount) {
        this.id = id;
        this.name = name;
        this.purchaseCategory = purchaseCategory;
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

    public PurchasedItemStoreOrderDto(PurchasedItemDto purchasedItemDto) {
        this.id = purchasedItemDto.getId();
        this.name = purchasedItemDto.getName();
        this.purchaseCategory = purchasedItemDto.getPurchaseCategory();
        this.quantity = purchasedItemDto.getQuantity();
        this.cost =purchasedItemDto.getCost();
        this.totalCost = purchasedItemDto.getTotalCost();
        this.discount = purchasedItemDto.getDiscount();
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
