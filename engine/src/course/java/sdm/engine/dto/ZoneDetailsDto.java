package course.java.sdm.engine.dto;

import course.java.sdm.engine.Utils;

public class ZoneDetailsDto {

    private final String ownerName;
    private final String zoneName;
    private final int totalDifferentItems;
    private final int totalStores;
    private final int totalOrders;
    private final float totalOrdersCostAverageWithoutDelivery;

    public ZoneDetailsDto(String ownerName, String zoneName, int totalDifferentItems,
                          int totalStores, int totalOrders, float totalOrdersCostAverageWithoutDelivery) {
        this.ownerName = ownerName;
        this.zoneName = zoneName;
        this.totalDifferentItems = totalDifferentItems;
        this.totalStores = totalStores;
        this.totalOrders = totalOrders;
        this.totalOrdersCostAverageWithoutDelivery = Utils.roundNumberWithTwoDigitsAfterPoint(totalOrdersCostAverageWithoutDelivery);
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public int getTotalDifferentItems() {
        return totalDifferentItems;
    }

    public int getTotalStores() {
        return totalStores;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public float getTotalOrdersCostAverageWithoutDelivery() {
        return totalOrdersCostAverageWithoutDelivery;
    }
}
