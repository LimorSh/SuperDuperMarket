package course.java.sdm.engine.exceptions;

public class DuplicateStoreLocationException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;

    StringBuilder sb = new StringBuilder();

    public DuplicateStoreLocationException(String storeName, String existentStoreName, int x, int y)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot add the store ").append(storeName).append(":\n")
                .append(existentStoreName).append(" is already located in ")
                .append("(").append(x).append(",").append(y).append(").");
        EXCEPTION_MESSAGE = sb.toString();
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }
}
