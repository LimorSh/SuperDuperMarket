package course.java.sdm.engine.exceptions;

public class ItemDoesNotExistInTheStoreException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public ItemDoesNotExistInTheStoreException(String storeName, String itemName, int itemId)
    {
        EXCEPTION_MESSAGE = "The item " + itemName + " (ID " + itemId + ") does not exist in the store " + storeName + ".";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
