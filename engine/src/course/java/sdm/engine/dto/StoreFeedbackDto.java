package course.java.sdm.engine.dto;

import course.java.sdm.engine.Utils;
import course.java.sdm.engine.engine.StoreFeedback;

public class StoreFeedbackDto {

    private final int storeId;
    private final String storeName;
    private final String orderDateStr;
    private final String customerName;
    private final int rate;
    private final String feedback;

    public StoreFeedbackDto(StoreFeedback feedback) {
        this.storeId = feedback.getStoreId();
        this.storeName = feedback.getStoreName();
        this.orderDateStr = Utils.convertDateToString(feedback.getOrderDate());
        this.customerName = feedback.getCustomerName();
        this.rate = feedback.getRate();
        this.feedback = feedback.getFeedback();
    }

    public int getStoreId() {
        return storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public String getOrderDateStr() {
        return orderDateStr;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getRate() {
        return rate;
    }

    public String getFeedback() {
        return feedback;
    }

    @Override
    public String toString() {
        return "StoreFeedbackDto{" +
                "storeId=" + storeId +
                ", storeName='" + storeName + '\'' +
                ", orderDateStr='" + orderDateStr + '\'' +
                ", customerName='" + customerName + '\'' +
                ", rate=" + rate +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
