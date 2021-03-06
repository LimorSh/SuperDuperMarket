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

    public static SuperDuperMarket loadFromXmlFileDataInputStream(InputStream inputStream, String zoneOwnerName)
            throws JAXBException {
        SuperDuperMarket superDuperMarket;
        superDuperMarket = new SuperDuperMarket();
        SuperDuperMarketDescriptor superDuperMarketDescriptor = deserializeFrom(inputStream);
        loadItems(superDuperMarketDescriptor, superDuperMarket);
        loadStores(superDuperMarketDescriptor, superDuperMarket, zoneOwnerName);
        if (!superDuperMarket.isAllItemsAreBeingSoldByAtLeastOneStore()) {
            Set<Item> missingItems = superDuperMarket.getItemsThatAreNotBeingSoldByAtLeastOneStore();
            throw new NotAllItemsAreBeingSoldException(missingItems);
        }
        loadZoneName(superDuperMarketDescriptor, superDuperMarket);

        return superDuperMarket;
    }

    private static void loadItems(SuperDuperMarketDescriptor superDuperMarketDescriptor, SuperDuperMarket superDuperMarket) {
        List<SDMItem> sdmItems = superDuperMarketDescriptor.getSDMItems().getSDMItem();
        for (SDMItem sdmItem : sdmItems) {
            Item item = new Item(sdmItem);
            superDuperMarket.addItem(item);
        }
    }

    private static void loadStores(SuperDuperMarketDescriptor superDuperMarketDescriptor,
                                   SuperDuperMarket superDuperMarket, String zoneOwnerName) {
        List<SDMStore> sdmStores = superDuperMarketDescriptor.getSDMStores().getSDMStore();
        for (SDMStore sdmStore : sdmStores) {
            Store store = new Store(sdmStore, zoneOwnerName);
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
                Discount discount = new Discount(sdmDiscount, store.getId());
                List<SDMOffer> sdmOffers = sdmDiscount.getThenYouGet().getSDMOffer();
                try {
                    for (SDMOffer sdmOffer : sdmOffers) {
                        int offerId = sdmOffer.getItemId();
                        String discountName = discount.getName();
                        superDuperMarket.validateItemIsInSuperAndStore(offerId, store, discountName);
                        Item item = superDuperMarket.getItem(offerId);
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

    private static void loadZoneName(SuperDuperMarketDescriptor superDuperMarketDescriptor, SuperDuperMarket superDuperMarket) {
        String zoneName = superDuperMarketDescriptor.getSDMZone().getName();
        superDuperMarket.setZoneName(zoneName);
    }

    private static SuperDuperMarketDescriptor deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (SuperDuperMarketDescriptor) u.unmarshal(in);
    }
}
