package DP;

import java.util.Arrays;

public class PalindromePartitioningFrontPartition {
    public static void main(String[] args) {
        String str = "aabcdegddd";
        System.out.println(dynamicPartitioning(0, str) - 1);
        System.out.println(frontPartitioning(0, str) - 1);
        System.out.println(frontPartitioningTabulation(str));
    }

    // Time complexity:O(2^n).
    // Space complexity;O(1).
    // Auxillary stack space:O(n).
    public static int frontPartitioning(int ind, String str) {
        if (ind == str.length()) {
            return 0;
        }
        int mini = (int) 1e9;
        for (int j = ind; j < str.length(); j++) {
            if (isValid(ind, j, str)) {
                int cuts = 1 + frontPartitioning(j + 1, str);
                mini = Math.min(mini, cuts);
            }
        }
        return mini;
    }

    public static boolean isValid(int i, int j, String str) {
        while (i < j) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    // Time complexity:O(n).
    // Space complexity;O(n).
    // Auxillary stack space:O(n).
    public static int dynamicPartitioning(int ind, String s) {
        int dp[] = new int[s.length()];
        Arrays.fill(dp, -1);
        return helper(0, s, dp);
    }

    public static int helper(int ind, String s, int dp[]) {
        if (ind == s.length())
            return 0;
        if (dp[ind] != -1)
            return dp[ind];
        int mini = (int) 1e9;
        for (int j = ind; j < s.length(); j++) {
            if (isValid(ind, j, s)) {
                int cuts = 1 + helper(j + 1, s, dp);
                mini = Math.min(mini, cuts);
            }
        }
        return dp[ind] = mini;
    }

    // Tabulation.
    public static int frontPartitioningTabulation(String s) {
        int dp[] = new int[s.length() + 1];
        for (int ind = s.length() - 1; ind >= 0; ind--) {
            int mini = (int) 1e9;
            for (int j = ind; j < s.length(); j++) {
                if (isValid(ind, j, s)) {
                    int cuts = 1 + dp[j + 1];
                    mini = Math.min(mini, cuts);
                }
            }
            dp[ind] = mini;
        }
        return dp[0] - 1;
    }
}
