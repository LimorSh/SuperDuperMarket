package course.java.sdm.engine;

import java.awt.*;

public class Location {

    private final Point coordinate;

    // it's nice to have - but we need to validate the coordinates that they are int
//    public Location(Point point) {
//        this.coordinate = point;
//    }

    public Location(int x, int y) {
        this.coordinate = new Point(x, y);
    }

    public Point getCoordinate() {
        return coordinate;
    }
}
