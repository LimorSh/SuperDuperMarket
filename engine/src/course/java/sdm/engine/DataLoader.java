package course.java.sdm.engine;

import course.java.sdm.engine.jaxb.schema.generated.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class DataLoader {

    private static final String JAXB_XML_PACKAGE_NAME = Configurations.JAXB_XML_PACKAGE_NAME;

    public static SuperDuperMarket loadFromXmlFile(String xmlFilePath) {
        SuperDuperMarket superDuperMarket = null;
        try {
            superDuperMarket = new SuperDuperMarket();
            InputStream inputStream = new FileInputStream(new File(xmlFilePath));
            SuperDuperMarketDescriptor superDuperMarketDescriptor = deserializeFrom(inputStream);
            loadItems(superDuperMarketDescriptor, superDuperMarket);
            loadStores(superDuperMarketDescriptor, superDuperMarket);
            return superDuperMarket;
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return superDuperMarket;
    }

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
            float itemPrice = sdmSell.getPrice();
            Item item = superDuperMarket.getItem(itemId);
            store.addItem(item, itemPrice);
        }
    }

    private static SuperDuperMarketDescriptor deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (SuperDuperMarketDescriptor) u.unmarshal(in);
    }
}
