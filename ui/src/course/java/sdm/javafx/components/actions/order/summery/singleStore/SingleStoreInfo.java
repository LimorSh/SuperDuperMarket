package course.java.sdm.javafx.components.actions.order.summery.singleStore;

public class SingleStoreInfo {

    private int id;
    private String name;
    private int ppk;
    private double distance;
    private float deliveryCost;

    public SingleStoreInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPpk() {
        return ppk;
    }

    public void setPpk(int ppk) {
        this.ppk = ppk;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(float deliveryCost) {
        this.deliveryCost = deliveryCost;
    }
}
