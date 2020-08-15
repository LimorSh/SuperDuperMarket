package course.java.sdm.engine;

import course.java.sdm.engine.exceptions.ItemDoesNotExistException;
import course.java.sdm.engine.exceptions.NotAllItemsAreBeingSold;
import course.java.sdm.engine.jaxb.schema.generated.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DataLoader {

    private static final String JAXB_XML_PACKAGE_NAME = Configurations.JAXB_XML_PACKAGE_NAME;
    private static final String FILE_EXTENSION = "xml";

    public static SuperDuperMarket loadFromXmlFile(String xmlFilePath) throws JAXBException, FileNotFoundException {
//        if (!getExtensionByStringHandling(xmlFilePath).equals(FILE_EXTENSION)) {
//        if (!FILE_EXTENSION.equals(getExtensionByStringHandling(xmlFilePath))) {

//            throw new Illegal...
//        }
        SuperDuperMarket superDuperMarket = null;
        superDuperMarket = new SuperDuperMarket();
        InputStream inputStream = new FileInputStream(new File(xmlFilePath));
        SuperDuperMarketDescriptor superDuperMarketDescriptor = deserializeFrom(inputStream);
        loadItems(superDuperMarketDescriptor, superDuperMarket);
        loadStores(superDuperMarketDescriptor, superDuperMarket);
        if (!superDuperMarket.isAllItemsAreBeingSoldByAtLeastOneStore()) {
            Set<Item> missingItems = superDuperMarket.getItemsThatAreNotBeingSoldByAtLeastOneStore();
            throw new NotAllItemsAreBeingSold(missingItems);
        }
        return superDuperMarket;
    }

//    private static Optional<String> getExtensionByStringHandling(String filename) {
//        return Optional.ofNullable(filename)
//                .filter(f -> f.contains("."))
//                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
//    }

    private static void loadItems(SuperDuperMarketDescriptor superDuperMarketDescriptor, SuperDuperMarket superDuperMarket) {
        List<SDMItem> sdmItems = superDuperMarketDescriptor.getSDMItems().getSDMItem();
        for (SDMItem sdmItem : sdmItems) {
            Item item = new Item(sdmItem);
            superDuperMarket.addItem(item);
        }
    }

    private static void loadStores(SuperDuperMarketDescriptor superDuperMarketDescriptor, SuperDuperMarket superDuperMarket) {
        List<SDMStore> sdmStores = superDuperMarketDescriptor.getSDMStores().getSDMStore();
        for (SDMStore sdmStore : sdmStores) {
            Store store = new Store(sdmStore);
            superDuperMarket.addStore(store);
            loadItemsToStore(store, sdmStore, superDuperMarket);
        }
    }

    private static void loadItemsToStore(Store store, SDMStore sdmStore, SuperDuperMarket superDuperMarket) {
        SDMPrices sdmPrices = sdmStore.getSDMPrices();
        List<SDMSell> sdmSells = sdmPrices.getSDMSell();

        for (SDMSell sdmSell : sdmSells) {
            int itemId = sdmSell.getItemId();
            if (superDuperMarket.isItemExists(itemId)) {
                float itemPrice = sdmSell.getPrice();
                superDuperMarket.addItemToStore(itemId, itemPrice, store);
            }
            throw new ItemDoesNotExistException(itemId);
        }
    }

    private static SuperDuperMarketDescriptor deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (SuperDuperMarketDescriptor) u.unmarshal(in);
    }
}
