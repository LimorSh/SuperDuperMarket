package course.java.sdm.engine.exception;

public class DuplicateStoreItemIdException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public DuplicateStoreItemIdException(String storeName, String itemName, int itemId)
    {
        EXCEPTION_MESSAGE = "The store " + storeName + " already sells " + itemName +
                " (item ID " + itemId + ").";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
