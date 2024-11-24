package DP;

import java.util.Arrays;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String str1 = "bbbab";
        String str2 = "babbb";
        // System.out.println(countingLongestSubSequency(str1.length() - 1,
        // str2.length() - 1, str1, str2));
        // System.out.println(dynamicCounting(str1, str2));
        System.out.println(lcsTabulation(str1, str2));
        System.out.println(lcsSpaceOptimization(str1, str2));
    }

    // Recursion.
    // Time complexity:O(2^n*2^m).
    // space complexity:O(1).
    // Auxillary stack space:O(max(str1,str2)).
    public static int countingLongestSubSequency(int p1, int p2, String str1, String str2) {
        if (p1 < 0 || p2 < 0)
            return 0;
        else if (str1.charAt(p1) == str2.charAt(p2))
            return 1 + countingLongestSubSequency(p1 - 1, p2 - 1, str1, str2);
        else
            return Math.max(countingLongestSubSequency(p1 - 1, p2, str1, str2),
                    countingLongestSubSequency(p1, p2 - 1, str1, str2));
    }

    // Dynamic programming.
    // Time complexity:O(n*m).
    // space complexity:O(n*m).
    // Auxillary stack space:O(max(str1,str2)).
    public static int dynamicCounting(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        int res = helper(str1.length() - 1, str2.length() - 1, str1, str2, dp);
        for (int temp[] : dp)
            System.out.println(Arrays.toString(temp));
        return res;
    }

    public static int helper(int p1, int p2, String str1, String str2, int dp[][]) {
        if (p1 < 0 || p2 < 0)
            return 0;
        if (dp[p1][p2] != -1)
            return dp[p1][p2];
        else if (str1.charAt(p1) == str2.charAt(p2))
            return dp[p1][p2] = 1 + helper(p1 - 1, p2 - 1, str1, str2, dp);
        else
            return dp[p1][p2] = Math.max(helper(p1 - 1, p2, str1, str2, dp),
                    helper(p1, p2 - 1, str1, str2, dp));
    }

    // Tabulation.
    // Time complexity:O(n*m).
    // space complexity:O(n*m).
    // Auxillary stack space:O(1).
    public static int lcsTabulation(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        String str = "";
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    // this one is for printing the longest common subsequence.
                    str += str1.charAt(i - 1);
                } else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        for (int temp[] : dp)
            System.out.println(Arrays.toString(temp));
        System.out.println(str);
        return dp[str1.length()][str2.length()];
    }

    // Tabulation.
    // Time complexity:O(n*m).
    // space complexity:O(m).
    // Auxillary stack space:O(1).
    public static int lcsSpaceOptimization(String str1, String str2) {
        int prev[] = new int[str2.length() + 1];
        for (int i = 1; i <= str1.length(); i++) {
            // System.out.println(Arrays.toString(prev));
            int current[] = new int[str2.length() + 1];
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    current[j] = 1 + prev[j - 1];
                else
                    current[j] = Math.max(current[j - 1], prev[j]);
            }
            prev = current;
        }
        // System.out.println(Arrays.toString(prev));
        return prev[str2.length()];
    }
}
