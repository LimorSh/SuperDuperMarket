package course.java.sdm.engine.exceptions;

import course.java.sdm.engine.Location;

public class LocationAlreadyExistsException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public LocationAlreadyExistsException(int x, int y)
    {
        EXCEPTION_MESSAGE = String.format("The location (" + x + ", " + y + ") is already exists");
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
