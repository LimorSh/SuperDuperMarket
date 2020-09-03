package course.java.sdm.engine.exception;

public class LocationException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public LocationException(String className, String objectName)
    {
        EXCEPTION_MESSAGE = "The location of " + className.toLowerCase() + " " + objectName + " is not valid:\n";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
