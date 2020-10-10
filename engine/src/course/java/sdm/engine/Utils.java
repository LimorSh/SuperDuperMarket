package course.java.sdm.engine;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Date;

public class Utils {

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
    public static void sleepForAWhile(long sleepTime) {
        if (sleepTime != 0) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ignored) {

            }
        }
    }

    public static float roundNumberWithTwoDigitsAfterPoint(float number) {
        try {
            return Float.parseFloat(String.format("%.2f", number));
//                DecimalFormat df = new DecimalFormat("0.00");
//                String str = df.format(number);
//                return (df.parse(str)).floatValue();
        } catch (Exception ignore) {
        }
        return -1f;
    }

    public static double roundNumberWithTwoDigitsAfterPoint(double number) {
        try {
            return Double.parseDouble(String.format("%.2f", number));
        } catch (Exception ignore) {
        }
        return -1f;
    }

    public static String convertDateToString(Date date) {
        LocalDate localDate = new java.sql.Date(date.getTime()).toLocalDate();
        return convertLocalDateToString(localDate);
    }

    public static String convertLocalDateToString(LocalDate localDate) {
        return (String.format("%d/%d/%d", localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear()));
    }
}
