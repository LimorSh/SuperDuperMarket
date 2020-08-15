package course.java.sdm.engine;

public class Utils {

    public static boolean isStringAnEnglishWord(String str) {
        for (char c : str.toCharArray()) {
            if(Character.isLetter(c) || Character.isSpaceChar(c)) {
            }
            else {
                return false;
            }
        }
        return true;
    }
}
