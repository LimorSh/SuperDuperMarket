package course.java.sdm.engine.exceptions;
import course.java.sdm.engine.Utils;

public class DuplicateLocationException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;
    public DuplicateLocationException(Object object, int x, int y) {
        String className = object.getClass().getSimpleName();
        String objectName = Utils.invokeGetNameMethod(object);
        EXCEPTION_MESSAGE = "The location (" + x + "," + y + ") already exists for the " + className.toLowerCase() + " " + objectName + ".";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}

