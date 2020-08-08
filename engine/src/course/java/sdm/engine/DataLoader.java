package course.java.sdm.engine;

import course.java.sdm.engine.jaxb.schema.generated.SDMStore;
import course.java.sdm.engine.jaxb.schema.generated.SDMStores;
import course.java.sdm.engine.jaxb.schema.generated.SuperDuperMarketDescriptor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataLoader {

    private final String xmlFileName;
    private static final String JAXB_XML_PACKAGE_NAME = Configurations.JAXB_XML_PACKAGE_NAME;

    public DataLoader(String xmlFileName) {
        this.xmlFileName = xmlFileName;
        readXmlFile();
    }

    private void readXmlFile() {
        try {
            InputStream inputStream = new FileInputStream(new File(xmlFileName));
            SuperDuperMarketDescriptor superDuperMarketDescriptor = deserializeFrom(inputStream);

            List<SDMStore> JAXBStores = superDuperMarketDescriptor.getSDMStores().getSDMStore();
            SuperDuperMarket superDuperMarket = new SuperDuperMarket("shira-super");

            for (SDMStore jaxbStore : JAXBStores) {
                Store copyStore = new Store(jaxbStore);
                superDuperMarket.addStore(copyStore);
                System.out.println(copyStore);
            }


//            SDMStore JAXBStore = superDuperMarketDescriptor.getSDMStores().getSDMStore().get(0);
//            Store copyStore = new Store(JAXBStore);
//            System.out.println(copyStore);
//            System.out.println("name of first country is: " + superDuperMarketDescriptor.getSDMStores().getSDMStore().get(0).getDeliveryPpk());

        } catch (JAXBException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readStores() {

    }

    private SuperDuperMarketDescriptor deserializeFrom(InputStream in) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(JAXB_XML_PACKAGE_NAME);
        Unmarshaller u = jc.createUnmarshaller();
        return (SuperDuperMarketDescriptor) u.unmarshal(in);
    }
}
