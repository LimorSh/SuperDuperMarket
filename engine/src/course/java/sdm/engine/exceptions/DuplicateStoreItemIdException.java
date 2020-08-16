package course.java.sdm.engine.exceptions;

public class DuplicateStoreItemIdException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public DuplicateStoreItemIdException(String storeName, String existentItemName, int existentItemId, String addedItemName)
    {
        EXCEPTION_MESSAGE = "Could not add item to the store:\n" +
                addedItemName + " ID " + existentItemId + " is taken - " +
                storeName + " already sells " + existentItemName +
                " with this ID.";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
