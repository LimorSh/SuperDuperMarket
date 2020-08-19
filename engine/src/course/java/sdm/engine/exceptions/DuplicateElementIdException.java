package course.java.sdm.engine.exceptions;

public class DuplicateElementIdException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    public DuplicateElementIdException(String element, String elementName, String existentElementName, int elementId)
    {
        EXCEPTION_MESSAGE = "Duplicate " + element.toLowerCase() + " ID: " +
                "ID " + elementId + " already exists for " +
                existentElementName + ", " + elementName + " cannot has it.";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
