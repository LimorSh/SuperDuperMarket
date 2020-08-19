package course.java.sdm.engine.systemEngine;
import java.awt.*;

public class Distance {

    public static double getDistanceBetweenTwoLocations(Location loc1, Location loc2) {
        return calculateDistanceBetweenTwoPointsUsingPythagorean(loc1.getCoordinate(), loc2.getCoordinate());
    }

    public static double getDistanceBetweenTwoLocations(int x1, int y1, int x2, int y2) {
        return calculateDistanceBetweenTwoPointsUsingPythagorean(x1, y1, x2, y2);
    }

    private static double calculateDistanceBetweenTwoPointsUsingPythagorean(Point p1, Point p2) {
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;
        return calculateDistanceBetweenTwoPointsUsingPythagorean(x1, y1, x2, y2);
    }

    private static double calculateDistanceBetweenTwoPointsUsingPythagorean(int x1, int y1, int x2, int y2) {
        int deltaY = y2 - y1;
        int deltaX = x2 - x1;
        return Math.sqrt((deltaY * deltaY) + (deltaX * deltaX));
    }
}