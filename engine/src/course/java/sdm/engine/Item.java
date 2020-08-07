package course.java.sdm.engine;

public class Item {

    public enum PurchaseType {
        PER_WEIGHT,
        PER_UNIT,
    }

//    private static int numItems = 1;
    private final int id;
    private final String name;
    private final PurchaseType purchaseType;

    public Item(int id, String name, PurchaseType purchaseType) {
        this.id = id;
        this.name = name;
        this.purchaseType = purchaseType;
//        numItems++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PurchaseType getPurchaseType() {
        return purchaseType;
    }

    @Override
    public String toString() {
        return "{ID: " + id +
                ", Name: '" + name + '\'' +
                ", Purchase Type: " + purchaseType
                ;
    }
}
