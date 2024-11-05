package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationOfPhoneNumber {
    public static void main(String[] args) {
        String digits[] = { "--", "--", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        String digitInput = "2";
        System.out.println(combinationsSum(digits, digitInput));
    }

    // Time complexity:O(2^n).
    // Space complexity:O(n).
    public static List<String> combinationsSum(String digits[], String digitInput) {
        List<String> ds = new ArrayList<>();
        if (digitInput.length() == 0) {
            return ds;
        }
        helper(digits, digitInput, 0, "", ds);
        return ds;
    }

    public static void helper(String digits[], String digitInput, int index, String res, List<String> ds) {
        if (index == digitInput.length()) {
            ds.add(res);
            return;
        }
        int len = digits[digitInput.charAt(index) - '0'].length();
        for (int i = 0; i < len; i++) {
            helper(digits, digitInput, index + 1, res + digits[digitInput.charAt(index) - '0'].charAt(i), ds);
        }

    }

}
