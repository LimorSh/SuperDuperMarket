package course.java.sdm.javafx.dto;

import java.time.LocalDate;
import java.util.Date;

public class UIOrderDto {

    private int customerId;
    private Date date;
    private int storeId;

    public int getCustomerId() {
        return customerId;
    }

    public Date getDate() {
        return date;
    }

    public int getStoreId() {
        return storeId;
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
}
