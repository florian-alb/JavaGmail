package Utils;

import java.util.List;
import java.util.regex.Matcher;

import java.util.regex.Pattern;

public class Utils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    // checkEmail : check if the email format is correct using a regex
    public static boolean checkEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    public static boolean checkEmail(List<String> emails) {
        for (String email : emails){
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (!matcher.matches()){
                return false;
            }
        }
        return true;
    }
}
