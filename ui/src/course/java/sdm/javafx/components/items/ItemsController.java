package course.java.sdm.javafx.components.items;

import course.java.sdm.engine.dto.ItemDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.singleItem.SingleItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;

public class ItemsController {

    @FXML private FlowPane flowPane;

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
            loader.setLocation(SuperDuperMarketConstants.SINGLE_ITEM_FXML_RESOURCE);
            Node singleItem = loader.load();

            SingleItemController singleItemController = loader.getController();
            singleItemController.setItemDataValues(itemDto);

            flowPane.getChildren().add(singleItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
