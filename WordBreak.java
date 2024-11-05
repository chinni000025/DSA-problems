package RecPrac;

import java.util.Arrays;
import java.util.HashSet;

public class WordBreak {
    public static void main(String[] args) {
        String word = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";

        String dict[] = { "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa", "aaaaaaaa", "aaaaaaaaa",
                "aaaaaaaaaa" };
        // System.out.println(searchInDict(word, dict));
        System.out.println(searchInDict2(word, dict));
    }

    // String word =
    // "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
    // String dict[] = { "a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa", "aaaaaaa",
    // "aaaaaaaa", "aaaaaaaaa",
    // "aaaaaaaaaa" };
    // For the above test case it would gives the time limit exceed error , so
    // inorder to minimize the TLE we are using dynamic programming.
    // Time complexity:O(2^n).
    // space complexity:O(dict.length).

    public static boolean searchInDict(String word, String dict[]) {
        return helper(word, new HashSet<>(Arrays.asList(dict)), 0, 0);
    }

    public static boolean helper(String word, HashSet<String> dict, int start, int end) {
        if (end == word.length()) {
            return dict.contains(word.substring(start, end));
        }
        if (dict.contains(word.substring(start, end + 1))) {
            if (helper(word, dict, end + 1, end + 1)) {
                return true;
            }
        }
        return helper(word, dict, start, end + 1);
    }

    // Using Dynamic programming.
    // Time complexity:O(n).
    // spac complexity:O(n*n)+O(n).
    public static boolean searchInDict2(String word, String dict[]) {
        int dp[][] = new int[word.length() + 1][word.length() + 1];
        for (int a[] : dp) {
            Arrays.fill(a, -1);
        }
        return helper(word, new HashSet<>(Arrays.asList(dict)), 0, 0, dp);
    }

    public static boolean helper(String word, HashSet<String> dict, int start, int end, int dp[][]) {
        if (end == word.length()) {
            dp[start][end] = dict.contains(word.substring(start, end)) ? 1 : 0;
            return dp[start][end] == 1;
        }
        if (dp[start][end] != -1) {
            return dp[start][end] == 1;
        }
        if (dict.contains(word.substring(start, end + 1))) {
            if (helper(word, dict, end + 1, end + 1, dp)) {
                dp[start][end] = 1;
                return true;
            }
        }
        dp[start][end] = helper(word, dict, start, end + 1, dp) ? 1 : 0;
        return dp[start][end] == 1;
    }
}
