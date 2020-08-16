package course.java.sdm.engine.exceptions;

public class StoreLocationExistsException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public StoreLocationExistsException(String storeName, int x, int y)
    {
        EXCEPTION_MESSAGE = "The location (" + x + "," + y + ") already exists for the store " + storeName + ".";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
