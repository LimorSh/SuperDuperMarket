package course.java.sdm.javafx.components.main.controller;

import course.java.sdm.engine.engine.BusinessLogic;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import javafx.beans.property.SimpleBooleanProperty;

public class SuperDuperMarketData {

    protected SimpleBooleanProperty isFileSelected;

    protected BusinessLogic businessLogic;

    public SuperDuperMarketData() {
        isFileSelected = new SimpleBooleanProperty(SuperDuperMarketConstants.INIT_BOOLEAN);
    }

    public boolean getIsFileSelected() {
        return isFileSelected.get();
    }

    public void setBusinessLogic(BusinessLogic businessLogic) {
        this.businessLogic = businessLogic;
    }
}
