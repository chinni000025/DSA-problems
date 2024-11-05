package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        String str = "aabb";
        System.out.println(driver(str));
    }

    public static List<List<String>> driver(String str) {
        List<List<String>> res = new ArrayList<>();
        palindromePartitioning(0, str, new ArrayList<>(), res);
        return res;
    }

    // Time complexity:O(string length^2).
    // Space complexity:O(string length)
    public static void palindromePartitioning(int index, String str, List<String> temp, List<List<String>> res) {
        if (index == str.length()) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < str.length(); i++) {
            if (isPalindrome(str.substring(index, i + 1))) {
                temp.add(str.substring(index, i + 1));
                palindromePartitioning(i + 1, str, temp, res);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public static boolean isPalindrome(String str) {
        int st = 0, end = str.length() - 1;
        while (st <= end) {
            if (str.charAt(st) != str.charAt(end)) {
                return false;
            }
            st++;
            end--;
        }
        return true;
    }
}
