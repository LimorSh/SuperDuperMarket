package course.java.sdm.engine.exceptions;

import java.lang.reflect.Method;

public class DuplicateLocationException extends RuntimeException {
    private final String EXCEPTION_MESSAGE;
    public DuplicateLocationException(Object object, int x, int y) {
        String className = object.getClass().getSimpleName();
        String objectName = invokeMethodThatReturnsString(object);
        EXCEPTION_MESSAGE = "The location (" + x + "," + y + ") already exists for the " + className.toLowerCase() + " " + objectName + ".";
    }

    @Override
    public String getMessage() {
        return EXCEPTION_MESSAGE;
    }

    private String invokeMethodThatReturnsString(Object object) {
        try {
            Method func = object.getClass().getDeclaredMethod("getName", (Class<?>[]) null);
            return (String) func.invoke(object);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

