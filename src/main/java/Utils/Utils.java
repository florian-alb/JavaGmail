package Utils;

import mailBox.Email;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {
    public static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    /**
     * <p>Check if the email format is correct using a regex</p>
     *
     * @param email the email we want to validate
     */
    public static boolean checkEmail(String email) {
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.matches();
    }

    /**
     * <p>Filter a list and return a new list with the filtered elements only</p>
     *
     * @param predicate    the condition that need to be true
     * @param listToFilter the list to filter
     * @return a list of Email filtered with a predicate
     */
    public static List<Email> filter(Predicate<Email> predicate, List<Email> listToFilter) {
        return listToFilter.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

}
