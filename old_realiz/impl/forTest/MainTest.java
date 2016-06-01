package old_realiz.impl.forTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Student on 31-May-16.
 */
public class MainTest {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(".*[фФ]ильм.*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher("Фильмы");
        System.out.println(matcher.matches()) ;
    }
}
