package course.java.sdm.engine;

import java.awt.*;

public class Distance {

    private final double distance;

    public Distance(Location loc1, Location loc2) {
        this.distance = calculateDistanceBetweenTwoPointsUsingPythagorean(loc1.getCoordinate(), loc2.getCoordinate());
    }

    public double getDistance() {
        return distance;
    }

    private static double calculateDistanceBetweenTwoPointsUsingPythagorean(Point p1 , Point p2) {
        double x1 = p1.x;
        double y1 = p1.y;
        double x2 = p2.x;
        double y2 = p2.y;
        return Math.sqrt(((y2 - y1) * (y2 - y1)) + ((x2 - x1) * (x2 - x1)));
    }
}
