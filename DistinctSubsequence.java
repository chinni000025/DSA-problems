package DP;

import java.util.Arrays;

public class DistinctSubsequence {
    public static void main(String[] args) {
        String str1 = "babgbag";
        String str2 = "bag";
        System.out.println(countingNumberOfDistinctWays(str1.length() - 1, str2.length() - 1, str1, str2));
        System.out.println(countingDynamic(str1, str2));
        System.out.println(countingTabulation(str1, str2));
    }

    // Recursion
    // Time comlexity:O(2^n*2^m)
    // space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int countingNumberOfDistinctWays(int p1, int p2, String str1, String str2) {
        if (p1 < 0 || p2 < 0) {
            if (p2 < 0) {
                return 1;
            }
            return 0;
        }
        if (str1.charAt(p1) == str2.charAt(p2)) {
            return countingNumberOfDistinctWays(p1 - 1, p2 - 1, str1, str2)
                    + countingNumberOfDistinctWays(p1 - 1, p2, str1, str2);
        } else {
            return countingNumberOfDistinctWays(p1 - 1, p2, str1, str2);
        }
    }

    // Dynamic programming.
    // Time comlexity:O(n*m)
    // space complexity:O(n*m).
    // Auxillary stack space:O(n).
    public static int countingDynamic(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        return helper(str1.length() - 1, str2.length() - 1, str1, str2, dp);
    }

    public static int helper(int p1, int p2, String str1, String str2, int dp[][]) {
        if (p1 < 0 || p2 < 0) {
            if (p2 < 0) {
                return 1;
            }
            return 0;
        }
        if (dp[p1][p2] != -1)
            return dp[p1][p2];
        if (str1.charAt(p1) == str2.charAt(p2)) {
            return dp[p1][p2] = helper(p1 - 1, p2 - 1, str1, str2, dp)
                    + helper(p1 - 1, p2, str1, str2, dp);
        } else {
            return dp[p1][p2] = helper(p1 - 1, p2, str1, str2, dp);
        }
    }

    // Tabulation.
    public static int countingTabulation(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 1;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }
}
