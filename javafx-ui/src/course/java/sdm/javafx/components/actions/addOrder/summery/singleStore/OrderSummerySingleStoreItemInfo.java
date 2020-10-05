package course.java.sdm.javafx.components.actions.addOrder.summery.singleStore;

import course.java.sdm.engine.dto.ItemWithPriceDto;
import course.java.sdm.engine.dto.OfferDto;

public class OrderSummerySingleStoreItemInfo {

    protected int id;
    protected String name;
    protected String purchaseCategory;
    protected float quantity;
    protected float pricePerUnit;
    protected float totalCost;
    protected boolean isInDiscount;

    public OrderSummerySingleStoreItemInfo(ItemWithPriceDto itemWithPriceDto, float quantity) {
        this.id = itemWithPriceDto.getId();
        this.name = itemWithPriceDto.getName();
        this.purchaseCategory = itemWithPriceDto.getPurchaseCategory();
        this.quantity = quantity;
        float price = itemWithPriceDto.getPrice();
        this.pricePerUnit = price;
        this.totalCost = quantity * price;
        this.isInDiscount = false;
    }

    public OrderSummerySingleStoreItemInfo(OfferDto offerDto) {
        this.id = offerDto.getStoreItemId();
        this.name = offerDto.getStoreItemName();
        this.purchaseCategory = offerDto.getStoreItemPurchaseCategory();
        this.quantity = (float) (offerDto.getQuantity());
        float price = offerDto.getAdditionalPrice();
        this.pricePerUnit = price;
        this.totalCost = quantity * price;
        this.isInDiscount = true;
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

    public float getPricePerUnit() {
        return pricePerUnit;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public boolean getIsInDiscount() {
        return isInDiscount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPurchaseCategory(String purchaseCategory) {
        this.purchaseCategory = purchaseCategory;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public void setPricePerUnit(float pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }
}
