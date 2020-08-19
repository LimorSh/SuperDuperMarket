package course.java.sdm.engine.exceptions;

public class DuplicateStoreItemIdException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public DuplicateStoreItemIdException(String storeName, String itemName, int itemId)
    {
        EXCEPTION_MESSAGE = storeName + " already sells " + itemName +
                " (ID " + itemId + ").";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
