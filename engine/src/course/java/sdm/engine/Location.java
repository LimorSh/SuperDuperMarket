package course.java.sdm.engine;

import java.awt.*;

public class Location {

    private final Point coordinate;

    public Location(Point point) {
        this.coordinate = point;
    }

    public Location(int x, int y) {
        this.coordinate = new Point(x, y);
    }

    public Point getCoordinate() {
        return coordinate;
    }
}
