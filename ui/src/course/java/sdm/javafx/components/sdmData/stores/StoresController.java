package course.java.sdm.javafx.components.sdmData.stores;

import course.java.sdm.engine.dto.ItemDto;
import course.java.sdm.engine.dto.StoreDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.info.StoreInfo;
import course.java.sdm.javafx.components.sdmData.singleStore.SingleStoreController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;


public class StoresController extends StoresData {

    @FXML private FlowPane flowPane;
    @FXML private ScrollPane scrollPane;
    @FXML private ComboBox<StoreInfo> comboBox;

    @FXML
    void comboBoxAction(ActionEvent event) {
        int storeId = getSelectedStoreId();
        StoreDto storeDto = getStoreDto(storeId);
        createStore(storeDto);
    }

    public void showStores(Collection<StoreDto> storesDto) {
        setStoresDto(storesDto);
        setStores();
    }

    private void setStores() {
        ArrayList<StoreInfo> storesInfo = new ArrayList<>();

        Collection<StoreDto> storesDtoSortedById = storesDto.stream()
                .sorted(Comparator.comparing(StoreDto::getId))
                .collect(Collectors.toList());

        for (StoreDto storeDto : storesDtoSortedById) {
            StoreInfo storeInfo = new StoreInfo(storeDto);
            storesInfo.add(storeInfo);
        }
        comboBox.setItems(FXCollections.observableArrayList(storesInfo));
    }

    private int getSelectedStoreId() {
        return comboBox.getValue().getId();
    }

    private void createStore(StoreDto storeDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketConstants.SINGLE_STORE_FXML_RESOURCE);
            Node singleStore = loader.load();

            SingleStoreController singleStoreController = loader.getController();
            singleStoreController.setStoreDataValues(storeDto);
            singleStoreController.setItemsTableView(storeDto.getStoreItemsDto());
            singleStoreController.setOrdersTableView(storeDto.getOrdersDto(), storeDto.getId());
            singleStoreController.setDiscountsFlowPane(storeDto);

            flowPane.getChildren().add(singleStore);
//            scrollPane.setContent(singleStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
