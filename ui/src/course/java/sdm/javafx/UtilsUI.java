package course.java.sdm.javafx;

import java.text.DecimalFormat;

public class UtilsUI {

        public static float roundNumberWithTwoDigitsAfterPoint(float number) {
            try {
                return Float.parseFloat(String.format("%.2f", number));
//                DecimalFormat df = new DecimalFormat("0.00");
//                String str = df.format(number);
//                return (df.parse(str)).floatValue();
            } catch (Exception ignore) {
//                System.out.println(e.getMessage());
            }
            return -1f;
        }



}
