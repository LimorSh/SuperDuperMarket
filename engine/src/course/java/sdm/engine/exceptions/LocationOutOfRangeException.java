package course.java.sdm.engine.exceptions;

import course.java.sdm.engine.Location;

public class LocationOutOfRangeException extends RuntimeException{
    private final String EXCEPTION_MESSAGE;

    public LocationOutOfRangeException(int x, int y)
    {
        EXCEPTION_MESSAGE = "The location (" + x + "," + y + ") is not valid: " +
                "coordinates are not in range, each value should be between " + Location.getMinLocationValue() +
                " and " + Location.getMaxLocationValue() + ".";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
