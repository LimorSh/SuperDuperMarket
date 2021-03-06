package course.java.sdm.engine.exception;
import course.java.sdm.engine.Utils;

public class DuplicateElementIdException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public DuplicateElementIdException(Object object, Object existentObject)
    {
        String className = object.getClass().getSimpleName().toLowerCase();
        String existentObjectName = Utils.invokeGetNameMethod(existentObject);
        String objectName = Utils.invokeGetNameMethod(object);
        int objectId = Utils.invokeGetIdMethod(object);
        EXCEPTION_MESSAGE = "Duplicate " + className + " ID: " +
                "ID " + objectId + " already exists for " +
                existentObjectName + ", " + objectName + " cannot has it.";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
