package course.java.sdm.engine.exceptions;

public class DuplicateStoreLocationException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public DuplicateStoreLocationException(String existentStoreName, int x, int y)
    {
        EXCEPTION_MESSAGE = existentStoreName + " is already located in " + "(" + x + "," + y + ").";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
