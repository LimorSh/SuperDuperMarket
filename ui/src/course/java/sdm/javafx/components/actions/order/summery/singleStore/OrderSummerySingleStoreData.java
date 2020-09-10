package course.java.sdm.javafx.components.actions.order.summery.singleStore;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class OrderSummerySingleStoreData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleIntegerProperty ppk;
    protected SimpleDoubleProperty distanceFromTheCustomer;
    protected SimpleFloatProperty deliveryCost;

    public OrderSummerySingleStoreData() {
        this.id = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.name = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.ppk = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.distanceFromTheCustomer = new SimpleDoubleProperty(SuperDuperMarketConstants.INIT_DOUBLE);
        this.deliveryCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
    }

    private void setId(int id) {
        this.id.set(id);
    }

    private void setName(String name) {
        this.name.set(name);
    }

    public void setPpk(int ppk) {
        this.ppk.set(ppk);
    }

    public void setDistanceFromTheCustomer(double distanceFromTheCustomer) {
        this.distanceFromTheCustomer.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(distanceFromTheCustomer));
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(deliveryCost));
    }

    public void setDataValues(OrderSummerySingleStoreInfo orderSummerySingleStoreInfo) {
        setId(orderSummerySingleStoreInfo.getId());
        setName(orderSummerySingleStoreInfo.getName());
        setPpk(orderSummerySingleStoreInfo.getPpk());
        setDistanceFromTheCustomer(orderSummerySingleStoreInfo.getDistance());
        setDeliveryCost(orderSummerySingleStoreInfo.getDeliveryCost());
    }
}
