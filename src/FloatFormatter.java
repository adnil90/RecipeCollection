import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FloatFormatter {
    public static String replaceSeparator(String string) {
        Pattern pattern = Pattern.compile("(?<=\\d),(?=\\d)");
        Matcher matcher = pattern.matcher(string);
        return matcher.replaceAll(".");
    }
}
