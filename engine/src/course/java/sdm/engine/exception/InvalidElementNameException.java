package course.java.sdm.engine.exception;

public class InvalidElementNameException extends RuntimeException{
    private final String EXCEPTION_MESSAGE;

    public InvalidElementNameException(String className, String name)
    {
        EXCEPTION_MESSAGE = "The " + className.toLowerCase() + " name " + name +
                " is not valid: should contain English letters or spaces only.";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
