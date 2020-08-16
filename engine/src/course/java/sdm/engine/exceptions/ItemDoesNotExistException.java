package course.java.sdm.engine.exceptions;

public class ItemDoesNotExistException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public ItemDoesNotExistException(String storeName, int id)
    {
        EXCEPTION_MESSAGE = "Could not add to the store " + storeName + " item ID " + id + ":\n"
                + "item ID does not exist.";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
