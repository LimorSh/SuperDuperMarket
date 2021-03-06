package course.java.sdm.engine.engine;
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

    public static boolean isValidLocation(int x, int y) {
        boolean isValidLocationX = isValidCoordinate(x);
        boolean isValidLocationY = isValidCoordinate(y);
        return (isValidLocationX && isValidLocationY);
    }

    public static boolean isValidCoordinate(int coordinate) {
        return (coordinate >= MIN_LOCATION_VALUE && coordinate <= MAX_LOCATION_VALUE);
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

    public static String getLocationStr(int x, int y) {
        return String.format("(%d,%d)", x, y);
    }

    public static String getLocationStr(Location location) {
        int x = location.coordinate.x;
        int y = location.coordinate.y;
        return getLocationStr(x, y);
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
        return coordinate != null ? coordinate.hashCode() : 0;
    }
}
