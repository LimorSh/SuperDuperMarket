package course.java.sdm.engine.dto;

import course.java.sdm.engine.Utils;

import java.util.Map;

public class DynamicOrderStoreDetailsDto {
    private final int id;
    private final String name;
    private final int xLocation;
    private final int yLocation;
    private final double distance;
    private final int ppk;
    private final float deliveryCost;
    private final int differentItemsType;
    private final float itemsCost;
    private final Map<Integer, Float> itemIdsAndQuantities;

    public DynamicOrderStoreDetailsDto(int id, String name, int xLocation, int yLocation, double distance,
                                       int ppk, float deliveryCost, int differentItemsType, float itemsCost,
                                       Map<Integer, Float> itemIdsAndQuantities) {
        this.id = id;
        this.name = name;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.distance = Utils.roundNumberWithTwoDigitsAfterPoint(distance);
        this.ppk = ppk;
        this.deliveryCost = Utils.roundNumberWithTwoDigitsAfterPoint(deliveryCost);
        this.differentItemsType = differentItemsType;
        this.itemsCost = Utils.roundNumberWithTwoDigitsAfterPoint(itemsCost);
        this.itemIdsAndQuantities = itemIdsAndQuantities;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public double getDistance() {
        return distance;
    }

    public int getPpk() {
        return ppk;
    }

    public float getDeliveryCost() {
        return deliveryCost;
    }

    public int getDifferentItemsType() {
        return differentItemsType;
    }

    public float getItemsCost() {
        return itemsCost;
    }

    public Map<Integer, Float> getItemIdsAndQuantities() {
        return itemIdsAndQuantities;
    }
}
