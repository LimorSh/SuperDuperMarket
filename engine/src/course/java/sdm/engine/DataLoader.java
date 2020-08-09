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

    private final String xmlFileName;
    private static final String JAXB_XML_PACKAGE_NAME = Configurations.JAXB_XML_PACKAGE_NAME;

    public DataLoader(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public void loadFromXmlFile(SuperDuperMarket superDuperMarket) {
        try {
            InputStream inputStream = new FileInputStream(new File(xmlFileName));
            SuperDuperMarketDescriptor superDuperMarketDescriptor = deserializeFrom(inputStream);
            loadItems(superDuperMarketDescriptor, superDuperMarket);
            loadStores(superDuperMarketDescriptor, superDuperMarket);
        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadItems(SuperDuperMarketDescriptor superDuperMarketDescriptor, SuperDuperMarket superDuperMarket) {
        List<SDMItem> sdmItems = superDuperMarketDescriptor.getSDMItems().getSDMItem();
        for (SDMItem sdmItem : sdmItems) {
            Item item = new Item(sdmItem);
            System.out.println(item);
            superDuperMarket.addItem(item);
        }
    }

    private void loadStores(SuperDuperMarketDescriptor superDuperMarketDescriptor, SuperDuperMarket superDuperMarket) {
        List<SDMStore> sdmStores = superDuperMarketDescriptor.getSDMStores().getSDMStore();
        for (SDMStore sdmStore : sdmStores) {
            Store store = new Store(sdmStore);
            superDuperMarket.addStore(store);
            loadItemsToStore(store, sdmStore, superDuperMarket);
            System.out.println(store);
        }
    }

    private void loadItemsToStore(Store store, SDMStore sdmStore, SuperDuperMarket superDuperMarket) {
        SDMPrices sdmPrices = sdmStore.getSDMPrices();
        List<SDMSell> sdmSells = sdmPrices.getSDMSell();

        for (SDMSell sdmSell : sdmSells) {
            int itemId = sdmSell.getItemId();
            float itemPrice = sdmSell.getPrice();
            Item item = superDuperMarket.getItem(itemId);
            store.addItem(item, itemPrice);
        }
    }

    private SuperDuperMarketDescriptor deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (SuperDuperMarketDescriptor) u.unmarshal(in);
    }
}
