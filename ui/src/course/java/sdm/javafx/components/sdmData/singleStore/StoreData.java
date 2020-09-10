package course.java.sdm.javafx.components.sdmData.singleStore;

import course.java.sdm.engine.dto.StoreDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.UtilsUI;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class StoreData {

    protected SimpleIntegerProperty id;
    protected SimpleStringProperty name;
    protected SimpleIntegerProperty ppk;
    protected SimpleFloatProperty totalDeliveryRevenue;

    public StoreData() {
        this.id = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.name = new SimpleStringProperty(SuperDuperMarketConstants.INIT_STRING);
        this.ppk = new SimpleIntegerProperty(SuperDuperMarketConstants.INIT_INT);
        this.totalDeliveryRevenue = new SimpleFloatProperty(SuperDuperMarketConstants.INIT_FLOAT);
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

    public void setTotalDeliveryRevenue(float deliveryCost) {
        this.totalDeliveryRevenue.set(UtilsUI.roundNumberWithTwoDigitsAfterPoint(deliveryCost));
    }

    public void setStoreDataValues(StoreDto storeDto) {
        setId(storeDto.getId());
        setName(storeDto.getName());
        setPpk(storeDto.getPpk());
        setTotalDeliveryRevenue(storeDto.getTotalDeliveriesRevenue());
    }
}
