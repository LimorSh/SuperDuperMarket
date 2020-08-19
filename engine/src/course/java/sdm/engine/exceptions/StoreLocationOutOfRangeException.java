package course.java.sdm.engine.exceptions;

public class StoreLocationOutOfRangeException extends LocationOutOfRangeException {
    private String EXCEPTION_MESSAGE;

    public StoreLocationOutOfRangeException(String name, int x, int y)
    {
        super(x, y);
        EXCEPTION_MESSAGE = "The store " + name + " is not valid:\n";
    }

    @Override
    public String getMessage() {
        EXCEPTION_MESSAGE = EXCEPTION_MESSAGE + super.getMessage();
        return EXCEPTION_MESSAGE;
    }
}
