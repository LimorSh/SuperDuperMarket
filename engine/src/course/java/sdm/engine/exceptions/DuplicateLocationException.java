package course.java.sdm.engine.exceptions;
import course.java.sdm.engine.Utils;
import course.java.sdm.engine.systemEngine.Location;

public class DuplicateLocationException extends LocationException {
    private String EXCEPTION_MESSAGE;

    public DuplicateLocationException(Object object, Object existentObject, int x, int y) {
        super(object.getClass().getSimpleName().toLowerCase(), Utils.invokeGetNameMethod(object));
        setExceptionMessage(existentObject, x, y);
    }

    public DuplicateLocationException(Object object, Object existentObject, Location location) {
        this(object, existentObject, location.getCoordinate().x, location.getCoordinate().y);
    }

    private void setExceptionMessage(Object existentObject, int x, int y) {
        String className = existentObject.getClass().getSimpleName().toLowerCase();
        String objectName = Utils.invokeGetNameMethod(existentObject);
        EXCEPTION_MESSAGE = "The location (" + x + "," + y + ") " +
                "already exists for the " +
                className + " " + objectName + ".";
    }

    @Override
    public String getMessage() {
        EXCEPTION_MESSAGE = super.getMessage() + EXCEPTION_MESSAGE;
        return EXCEPTION_MESSAGE;
    }
}

