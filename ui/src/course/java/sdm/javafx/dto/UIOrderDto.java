package course.java.sdm.javafx.dto;

import course.java.sdm.engine.dto.OfferDto;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreInfo;

import java.time.LocalDate;
import java.util.*;

public class UIOrderDto {

    private int customerId;
    private Date date;
    private int storeId;
    private Map<Integer, Float> itemsIdsAndQuantities;
    private Map<String, Collection<OfferDto>> appliedOffersDto = new HashMap<>(); //the key is discount name

    public int getCustomerId() {
        return customerId;
    }

    public Date getDate() {
        return date;
    }

    public int getStoreId() {
        return storeId;
    }

    public Map<Integer, Float> getItemsIdsAndQuantities() {
        return itemsIdsAndQuantities;
    }

    public Map<String, Collection<OfferDto>> getAppliedOffersDto() {
        return appliedOffersDto;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setDate(LocalDate localDate) {
        this.date = java.sql.Date.valueOf(localDate);
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public void setItemsIdsAndQuantities(Map<Integer, Float> itemsIdsAndQuantities) {
        this.itemsIdsAndQuantities = itemsIdsAndQuantities;
    }

    public void setAppliedOffersDto(Map<String, Collection<OfferDto>> appliedOffersDto) {
        this.appliedOffersDto = appliedOffersDto;
    }
}
