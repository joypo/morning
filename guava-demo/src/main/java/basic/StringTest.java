package basic;

import com.google.common.base.Strings;

/**
 * @author sunx
 * @date 2020-03-25
 */
public class StringTest {
    private static final String str = "Java,Golang,Python,Scala";
    private static final String str1 = "Java Golang Python Scala";

    public static void main(String[] args) {
        String input="20";

        System.out.println(Strings.commonPrefix("xyz", "abcxyz"));
    }
}
