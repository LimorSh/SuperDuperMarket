package course.java.sdm.engine.engine;
import course.java.sdm.engine.Constants;
import course.java.sdm.engine.exception.ItemDoesNotExistInTheSuperException;
import course.java.sdm.engine.exception.NotAllItemsAreBeingSoldException;
import course.java.sdm.engine.exception.DuplicateLocationException;
import course.java.sdm.engine.jaxb.schema.generated.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

public class DataLoader {

    private static final String JAXB_XML_PACKAGE_NAME = Constants.JAXB_XML_PACKAGE_NAME;
    private static final String FILE_EXTENSION = ".xml";

    public static SuperDuperMarket loadFromXmlFile(String xmlFilePath) throws JAXBException, FileNotFoundException {
        if (!xmlFilePath.toLowerCase().endsWith(FILE_EXTENSION)) {
            throw new IllegalArgumentException("The file type is not xml!");
        }
        SuperDuperMarket superDuperMarket;
        superDuperMarket = new SuperDuperMarket();
        InputStream inputStream = new FileInputStream(new File(xmlFilePath));
        SuperDuperMarketDescriptor superDuperMarketDescriptor = deserializeFrom(inputStream);
        loadItems(superDuperMarketDescriptor, superDuperMarket);
        loadStores(superDuperMarketDescriptor, superDuperMarket);
        if (!superDuperMarket.isAllItemsAreBeingSoldByAtLeastOneStore()) {
            Set<Item> missingItems = superDuperMarket.getItemsThatAreNotBeingSoldByAtLeastOneStore();
            throw new NotAllItemsAreBeingSoldException(missingItems);
        }
        loadCustomers(superDuperMarketDescriptor, superDuperMarket);

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
            try {
                superDuperMarket.addStore(store);
            }
            catch (DuplicateLocationException e) {
                throw new IllegalArgumentException("Could not add the store " + store.getName() + ":\n" + e.getMessage());
            }
            loadItemsToStore(store, sdmStore, superDuperMarket);
            loadDiscountsToStore(store, sdmStore, superDuperMarket);
        }
    }

    private static void loadItemsToStore(Store store, SDMStore sdmStore, SuperDuperMarket superDuperMarket) {
        SDMPrices sdmPrices = sdmStore.getSDMPrices();
        List<SDMSell> sdmSells = sdmPrices.getSDMSell();

        for (SDMSell sdmSell : sdmSells) {
            int itemId = sdmSell.getItemId();
            float itemPrice = sdmSell.getPrice();
            try {
                superDuperMarket.addItemToStore(itemId, itemPrice, store);
            }
            catch (ItemDoesNotExistInTheSuperException e) {
                throw new IllegalArgumentException("Could not add item to the store " + store.getName() + ":\n" + e.getMessage());
            }
        }
    }

    private static void loadDiscountsToStore(Store store, SDMStore sdmStore, SuperDuperMarket superDuperMarket) {
        SDMDiscounts sdmDiscountsContainer = sdmStore.getSDMDiscounts();
        if (sdmDiscountsContainer != null) {
            List<SDMDiscount> sdmDiscounts = sdmDiscountsContainer.getSDMDiscount();

            for (SDMDiscount sdmDiscount : sdmDiscounts) {
                Discount discount = new Discount(sdmDiscount);
                List<SDMOffer> sdmOffers = sdmDiscount.getThenYouGet().getSDMOffer();
                try {
                    for (SDMOffer sdmOffer : sdmOffers) {
                        Item item = superDuperMarket.getItem(sdmOffer.getItemId());
                        Offer offer = new Offer(sdmOffer, item);
                        discount.addOffer(offer);
                    }
                    superDuperMarket.addDiscountToStore(discount, store);
                }
                catch (ItemDoesNotExistInTheSuperException e) {
                    throw new IllegalArgumentException("Could not add discount to the store " + store.getName() + ":\n" + e.getMessage());
                }
            }
        }
    }

    private static void loadCustomers(SuperDuperMarketDescriptor superDuperMarketDescriptor, SuperDuperMarket superDuperMarket) {
        List<SDMCustomer> sdmCustomers = superDuperMarketDescriptor.getSDMCustomers().getSDMCustomer();
        for (SDMCustomer sdmCustomer : sdmCustomers) {
            Customer customer = new Customer(sdmCustomer);
            try {
                superDuperMarket.addCustomer(customer);
            }
            catch (DuplicateLocationException e) {
                throw new IllegalArgumentException("Could not add the customer " + customer.getName() + ":\n" + e.getMessage());
            }
        }
    }

    private static SuperDuperMarketDescriptor deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (SuperDuperMarketDescriptor) u.unmarshal(in);
    }
}
