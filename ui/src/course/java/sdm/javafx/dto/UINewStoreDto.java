package course.java.sdm.javafx.dto;

import java.util.HashMap;
import java.util.Map;

public class UINewStoreDto {

    private int id;
    private String name;
    private int locationX;
    private int locationY;
    private int ppk;
    Map<Integer, Float> itemIdsAndPrices = new HashMap<>();

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

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public int getPpk() {
        return ppk;
    }

    public void setPpk(int ppk) {
        this.ppk = ppk;
    }

    public Map<Integer, Float> getItemIdsAndPrices() {
        return itemIdsAndPrices;
    }

    public void updateItemIdsAndPrices(int id, float price) {
        itemIdsAndPrices.put(id, price);
    }
}
