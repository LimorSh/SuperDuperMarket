package course.java.sdm.engine;

import java.awt.*;

public class Distance {

    public static double getDistanceBetweenTwoLocations(Location loc1, Location loc2) {
        return calculateDistanceBetweenTwoPointsUsingPythagorean(loc1.getCoordinate(), loc2.getCoordinate());
    }

    private static double calculateDistanceBetweenTwoPointsUsingPythagorean(Point p1, Point p2) {
        double x1 = p1.x;
        double y1 = p1.y;
        double x2 = p2.x;
        double y2 = p2.y;
        double deltaY = y2 - y1;
        double deltaX = x2 - x1;
        return Math.sqrt((deltaY * deltaY) + (deltaX * deltaX));
    }
}
