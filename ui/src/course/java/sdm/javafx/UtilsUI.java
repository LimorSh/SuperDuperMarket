package course.java.sdm.javafx;

public class UtilsUI {

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


}
