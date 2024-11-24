package DP;

import java.util.Arrays;

public class EditDistance {
    public static void main(String[] args) {
        String str1 = "horse";
        String str2 = "ros";
        System.out.println(makingEqual(str1.length() - 1, str2.length() - 1, str1, str2));
        System.out.println(makingEqualDynamic(str1, str2));
        System.out.println(makingEqualTabulation(str1, str2));
        System.out.println(spaceOptimization(str1, str2));
    }

    // Recursion.
    // Time complexity:O(3^n *3^m).
    // space complexity:O(1).
    // Auxillary stack space:O((n)).
    public static int makingEqual(int p1, int p2, String str1, String str2) {
        if (p1 < 0 || p2 < 0) {
            if (p1 < 0)
                return p2 + 1;
            return p1 + 1;
        }
        if (str1.charAt(p1) == str2.charAt(p2)) {
            return makingEqual(p1 - 1, p2 - 1, str1, str2);
        }
        int delete = 1 + makingEqual(p1 - 1, p2, str1, str2);
        int insert = 1 + makingEqual(p1, p2 - 1, str1, str2);
        int replace = 1 + makingEqual(p1 - 1, p2 - 1, str1, str2);
        return Math.min(delete, Math.min(insert, replace));
    }

    // dynamic programming.
    // Time complexity:O(n*m).
    // Space complexity:O(n*m).
    // Auxillary stack space:O(n).
    public static int makingEqualDynamic(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        return helper(str1.length() - 1, str2.length() - 1, str1, str2, dp);
    }

    public static int helper(int p1, int p2, String str1, String str2, int dp[][]) {
        if (p1 < 0 || p2 < 0) {
            if (p1 < 0)
                return p2 + 1;
            return p1 + 1;
        }
        if (dp[p1][p2] != -1)
            return dp[p1][p2];
        if (str1.charAt(p1) == str2.charAt(p2)) {
            return dp[p1][p2] = helper(p1 - 1, p2 - 1, str1, str2, dp);
        }
        int delete = 1 + helper(p1 - 1, p2, str1, str2, dp);
        int insert = 1 + helper(p1, p2 - 1, str1, str2, dp);
        int replace = 1 + helper(p1 - 1, p2 - 1, str1, str2, dp);
        return dp[p1][p2] = Math.min(delete, Math.min(insert, replace));
    }

    // Tabulation.
    // Time complexity:O(n*m).
    // Space complexity:O(n*m).
    // Auxillary stack space:O(1).
    public static int makingEqualTabulation(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }
        for (int p1 = 1; p1 < dp.length; p1++) {
            for (int p2 = 1; p2 < dp[0].length; p2++) {
                if (str1.charAt(p1 - 1) == str2.charAt(p2 - 1)) {
                    dp[p1][p2] = dp[p1 - 1][p2 - 1];
                } else {
                    int delete = 1 + dp[p1 - 1][p2];
                    int insert = 1 + dp[p1][p2 - 1];
                    int replace = 1 + dp[p1 - 1][p2 - 1];
                    dp[p1][p2] = Math.min(delete, Math.min(insert, replace));
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }

    // space optimization.
    // Time complexity:O(n*m).
    // Space complexity:O(m).
    // Auxillary stack space:O(1).
    public static int spaceOptimization(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        int prev[] = new int[str2.length() + 1];
        for (int j = 0; j < prev.length; j++) {
            prev[j] = j;
        }
        for (int p1 = 1; p1 < dp.length; p1++) {
            int current[] = new int[str2.length() + 1];
            current[0] = p1;
            for (int p2 = 1; p2 < dp[0].length; p2++) {
                if (str1.charAt(p1 - 1) == str2.charAt(p2 - 1)) {
                    current[p2] = prev[p2 - 1];
                } else {
                    int delete = 1 + prev[p2];
                    int insert = 1 + current[p2 - 1];
                    int replace = 1 + prev[p2 - 1];
                    current[p2] = Math.min(delete, Math.min(insert, replace));
                }
            }
            prev = current;
        }
        return prev[str2.length()];
    }

}
