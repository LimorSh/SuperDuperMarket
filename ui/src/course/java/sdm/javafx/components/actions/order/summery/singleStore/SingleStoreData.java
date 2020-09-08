package course.java.sdm.javafx.components.actions.order.summery.singleStore;

import course.java.sdm.javafx.SuperDuperMarketConstants;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SingleStoreData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleIntegerProperty ppk;
    protected SimpleDoubleProperty distanceFromTheCustomer;
    protected SimpleFloatProperty deliveryCost;

    public SingleStoreData() {
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
        this.distanceFromTheCustomer.set(distanceFromTheCustomer);
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost.set(deliveryCost);
    }

    public void setDataValues(SingleStoreInfo singleStoreInfo) {
        setId(singleStoreInfo.getId());
        setName(singleStoreInfo.getName());
        setPpk(singleStoreInfo.getPpk());
        setDistanceFromTheCustomer(singleStoreInfo.getDistance());
        setDeliveryCost(singleStoreInfo.getDeliveryCost());
    }
}
