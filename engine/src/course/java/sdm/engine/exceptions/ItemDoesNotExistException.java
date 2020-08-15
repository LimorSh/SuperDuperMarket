package course.java.sdm.engine.exceptions;

public class ItemDoesNotExistException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public ItemDoesNotExistException(int id)
    {
        EXCEPTION_MESSAGE = "The id " + id + " does not exist";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
