package course.java.sdm.javafx.components.items;

import course.java.sdm.engine.dto.ItemDto;
import course.java.sdm.javafx.SuperDuperMarketResourcesConstants;
import course.java.sdm.javafx.components.singleItem.SingleItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Collection;

public class ItemsController {

    @FXML private ScrollPane itemsScrollPane;
    @FXML private FlowPane itemsFlowPane;
//    @FXML private VBox itemsVBox;

    public void createAllItems(Collection<ItemDto> itemsDto) {
        if (!itemsDto.isEmpty()) {
            for (ItemDto itemDto : itemsDto) {
                createItem(itemDto);
            }
        }
        else {
            // show no items component!
        }
    }


    private void createItem(ItemDto itemDto) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SuperDuperMarketResourcesConstants.SINGLE_ITEM_FXML_RESOURCE);
            Node singleItem = loader.load();

            SingleItemController singleItemController = loader.getController();
            singleItemController.setItemDataValues(itemDto);

            itemsFlowPane.getChildren().add(singleItem);
//            itemsVBox.getChildren().add(singleItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
