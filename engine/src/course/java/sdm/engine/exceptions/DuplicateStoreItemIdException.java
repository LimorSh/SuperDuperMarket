package course.java.sdm.engine.exceptions;

public class DuplicateStoreItemIdException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    StringBuilder sb = new StringBuilder();

    public DuplicateStoreItemIdException(String storeName, String existentItemName, int existentItemId, String addedItemName)
    {
        sb.append("Cannot add item to the store:\n")
                .append(addedItemName).append(" id ").append(existentItemId).append(" is taken - ")
                .append(storeName).append(" already sells ").append(existentItemName)
                .append(" with this id.");
        EXCEPTION_MESSAGE = sb.toString();
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
