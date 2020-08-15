package course.java.sdm.engine.exceptions;

import course.java.sdm.engine.Location;

public class LocationOutOfRangeException extends RuntimeException{
    private final String EXCEPTION_MESSAGE;

    public LocationOutOfRangeException(int x, int y)
    {
        EXCEPTION_MESSAGE = String.format("The location (" + x + ", " + y + ") is not valid:\n" +
                "coordinates are not in range, should be between" +
                " %d and %d", Location.getMinLocationValue(), Location.getMaxLocationValue());
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
