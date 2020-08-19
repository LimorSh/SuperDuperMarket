package course.java.sdm.engine.exceptions;

public class ItemDoesNotExistInTheSuperException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public ItemDoesNotExistInTheSuperException(int id)
    {
        EXCEPTION_MESSAGE = "item ID " + id + " does not exist in the super market.";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
