package course.java.sdm.engine;

import java.awt.*;
import java.util.Objects;

public class Location {

    private final Point coordinate;
//    private final int MIN_LOCATION_VALUE = 1;
//    private int MAX_LOCATION_VALUE = 50;


    // it's nice to have - but we need to validate the coordinates that they are int
//    public Location(Point point) {
//        this.coordinate = point;
//    }

    public Location(int x, int y) {
        this.coordinate = new Point(x, y);
    }

    public Location(course.java.sdm.engine.jaxb.schema.generated.Location sdmLocation) {
        int x = sdmLocation.getX();
        int y = sdmLocation.getY();
        this.coordinate = new Point(x, y);
    }

    public Point getCoordinate() {
        return coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        return Objects.equals(coordinate, location.coordinate);
    }

    @Override
    public int hashCode() {
        return coordinate.hashCode();
    }
}
