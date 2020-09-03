package course.java.sdm.engine;

import java.lang.reflect.Method;

public class Utils {

    public static boolean isStringAnEnglishWord(String str) {
        for (char c : str.toCharArray()) {
            if(!(Character.isLetter(c) || Character.isSpaceChar(c))) {
                return false;
            }
        }
        return true;
    }

    public static String invokeGetNameMethod(Object object) {
        try {
            Method func = object.getClass().getDeclaredMethod("getName", (Class<?>[]) null);
            return (String) func.invoke(object);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static int invokeGetIdMethod(Object object) {
        try {
            Method func = object.getClass().getDeclaredMethod("getId", (Class<?>[]) null);
            return (int) func.invoke(object);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
