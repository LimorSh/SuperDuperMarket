package course.java.sdm.engine;

import course.java.sdm.engine.exceptions.LocationOutOfRangeException;

import java.awt.*;
import java.util.Objects;

public class Location {

    private final Point coordinate;
    private static final int MIN_LOCATION_VALUE = 1;
    private static final int MAX_LOCATION_VALUE = 50;


    public Location(Point point) {
        this.coordinate = point;
    }

    public Location(int x, int y) {
        this.coordinate = new Point(x, y);
    }

    public Location(course.java.sdm.engine.jaxb.schema.generated.Location sdmLocation) {
        this(sdmLocation.getX(),sdmLocation.getY());
    }

    public static void isValidLocation(int x, int y) {
        if ((x < MIN_LOCATION_VALUE || x > MAX_LOCATION_VALUE) || (y < MIN_LOCATION_VALUE || y > MAX_LOCATION_VALUE))
            throw new LocationOutOfRangeException(x, y);
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public static int getMinLocationValue() {
        return MIN_LOCATION_VALUE;
    }

    public static int getMaxLocationValue() {
        return MAX_LOCATION_VALUE;
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
