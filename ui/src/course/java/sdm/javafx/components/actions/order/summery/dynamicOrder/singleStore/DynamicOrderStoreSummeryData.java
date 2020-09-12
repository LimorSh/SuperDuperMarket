package course.java.sdm.javafx.components.actions.order.summery.dynamicOrder.singleStore;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import course.java.sdm.javafx.components.actions.order.summery.singleStore.OrderSummerySingleStoreInfo;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DynamicOrderStoreSummeryData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleStringProperty location;
    protected SimpleDoubleProperty distance;
    protected SimpleIntegerProperty ppk;
    protected SimpleFloatProperty deliveryCost;
    protected SimpleIntegerProperty numberOfDifferentPurchasedItems;
    protected SimpleFloatProperty totalItemsCost;


    public DynamicOrderStoreSummeryData() {
        this.id = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.name = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.location = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.distance = new SimpleDoubleProperty(SuperDuperMarketConstants.INIT_DOUBLE);
        this.ppk = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.deliveryCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
        this.numberOfDifferentPurchasedItems = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.totalItemsCost = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
    }

    private void setId(int id) {
        this.id.set(id);
    }

    private void setName(String name) {
        this.name.set(name);
    }

    public void setLocation(int x, int y) {
        this.location.set(String.format("(%d,%d)", x, y));
    }


    public void setDistance(double distance) {
        this.distance.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(distance));
    }

    public void setPpk(int ppk) {
        this.ppk.set(ppk);
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(deliveryCost));
    }

    public void setNumberOfDifferentPurchasedItems(int numberOfDifferentPurchasedItems) {
        this.numberOfDifferentPurchasedItems.set(numberOfDifferentPurchasedItems);
    }

    public void setTotalItemsCost(float totalItemsCost) {
        this.totalItemsCost.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(totalItemsCost));
    }

    public void setItemDataValues(OrderSummerySingleStoreInfo singleStoreInfo) {
        setId(singleStoreInfo.getId());
        setName(singleStoreInfo.getName());
        setLocation(singleStoreInfo.getXLocation(), singleStoreInfo.getYLocation());
        setDistance(singleStoreInfo.getDistance());
        setPpk(singleStoreInfo.getPpk());
        setDeliveryCost(singleStoreInfo.getDeliveryCost());
        setNumberOfDifferentPurchasedItems(singleStoreInfo.getNumberOfDifferentPurchasedItems());
        setTotalItemsCost(singleStoreInfo.getTotalItemsCost());
    }
}
