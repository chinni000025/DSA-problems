package DP;

import java.util.Arrays;

public class WildCardMatching {
    public static void main(String[] args) {
        String s = "aa";
        String p = "a*";
        System.out.println(matching(s.length() - 1, p.length() - 1, s, p));
        System.out.println(tabulation(s, p));
    }

    // Recursion. Approach.
    // Time complexity:O(2^s*2^p).
    // Space complexity:O(1).
    // Auxillary stack space:O(s).
    public static boolean matching(int i, int j, String s, String p) {
        if (i < 0 || j < 0) {
            if (i < 0 && j < 0) {
                return true;
            }
            if (i < 0 && j > 0) {
                while (j >= 0) {
                    if (p.charAt(j) != '*') {
                        return false;
                    }
                    j--;
                }
                return true;
            }
            if (i < 0 && p.charAt(j) == '*') {
                return true;
            }
            return false;
        }
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            return matching(i - 1, j - 1, s, p);
        } else if (p.charAt(j) == '*') {
            return matching(i - 1, j, s, p) || matching(i, j - 1, s, p);
        } else {
            return false;
        }
    }

    // Dynamic programming.
    // Time complexity:O(s*p).
    // Space complexity:O(s*p).
    // Auxillary stack space:O(s).
    public static boolean dynamicMathching(String s, String p) {
        int dp[][] = new int[s.length() + 1][p.length() + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        return helper(s.length() - 1, p.length() - 1, s, p, dp);
    }

    public static boolean helper(int i, int j, String s, String p, int dp[][]) {
        if (i < 0 || j < 0) {
            if (i < 0 && j < 0) {
                return true;
            }
            if (i < 0 && j > 0) {
                while (j >= 0) {
                    if (p.charAt(j) != '*') {
                        return false;
                    }
                    j--;
                }
                return true;
            }
            if (i < 0 && p.charAt(j) == '*') {
                return true;
            }
            return false;
        }
        if (dp[i][j] != -1)
            return dp[i][j] == 1 ? true : false;
        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
            boolean res = helper(i - 1, j - 1, s, p, dp);
            dp[i][j] = res ? 1 : 0;
            return res;
        } else if (p.charAt(j) == '*') {
            boolean res = helper(i - 1, j, s, p, dp) || helper(i, j - 1, s, p, dp);
            dp[i][j] = res ? 1 : 0;
            return res;
        } else {
            return false;
        }
    }

    // Tabulation.
    // Time complexity:O(s*2).
    // Space complexity:O(s*p).
    // Auxillary stack space:O(1).
    public static boolean tabulation(String s, String p) {
        boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < dp[0].length; i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i - 1];
            }
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    boolean res = dp[i - 1][j - 1];
                    dp[i][j] = res;
                } else if (p.charAt(j - 1) == '*') {
                    boolean res = dp[i - 1][j] || dp[i][j - 1];
                    dp[i][j] = res;
                }
            }
        }
        return dp[s.length()][p.length()];
    }

}
