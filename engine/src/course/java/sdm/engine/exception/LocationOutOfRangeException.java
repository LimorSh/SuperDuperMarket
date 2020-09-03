package course.java.sdm.engine.exception;
import course.java.sdm.engine.engine.Location;

public class LocationOutOfRangeException extends LocationException{
    private String EXCEPTION_MESSAGE;

//    public LocationOutOfRangeException(int x, int y)
//    {
//        EXCEPTION_MESSAGE = "The location (" + x + "," + y + ") is not valid: " +
//                "coordinates are not in range, each value should be between " + Location.getMinLocationValue() +
//                " and " + Location.getMaxLocationValue() + ".";
//    }

    public LocationOutOfRangeException(String className, String objectName, int x, int y)
    {
        super(className, objectName);
        EXCEPTION_MESSAGE = "The location (" + x + "," + y + ") is not valid: " +
                "coordinates are not in range, each value should be between " + Location.getMinLocationValue() +
                " and " + Location.getMaxLocationValue() + ".";
    }

    @Override
    public String getMessage() {
        EXCEPTION_MESSAGE = super.getMessage() + EXCEPTION_MESSAGE;
        return EXCEPTION_MESSAGE;
    }
}
