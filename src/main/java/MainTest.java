/**
 * 15.07.2017
 *
 * @author Robert Bagramov.
 */
public class MainTest {
    public static void main(String[] args) {
        String str = "This is a sentence.  This is a question, right?  Yes!  It is.";
        String delims = "[ .,?!]+";

        String newSearchComment = "";

        String[] tokens = str.split(delims);
        for (String temp : tokens) {
            newSearchComment += temp;
        }

        System.out.println(newSearchComment);

    }
}
