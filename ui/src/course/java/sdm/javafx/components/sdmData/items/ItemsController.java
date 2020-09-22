package course.java.sdm.javafx.components.sdmData.items;

import course.java.sdm.engine.dto.CustomerDto;
import course.java.sdm.engine.dto.ItemDto;
import course.java.sdm.javafx.SuperDuperMarketConstants;
import course.java.sdm.javafx.components.sdmData.singleItem.SingleItemController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.FlowPane;
import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

public class ItemsController {

    @FXML private FlowPane flowPane;

    public void createAllItems(Collection<ItemDto> itemsDto) {

        Collection<ItemDto> itemsDtoSortedById = itemsDto.stream()
                .sorted(Comparator.comparing(ItemDto::getId))
                .collect(Collectors.toList());

        for (ItemDto itemDto : itemsDtoSortedById) {
            createItem(itemDto);
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
