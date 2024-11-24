package DP;

import java.util.Arrays;

public class ClimbingStairs {

    public static void main(String[] args) {
        int n = 35;
        System.out.println(climbingR(n));
        System.out.println(climbingDp(n));
        System.out.println(tabulation(n));
        System.out.println(spaceOptimization(n));
    }

    // Recursion.
    // Time complexity:O(2^n).
    // Space comlexity:O(1).
    // Auxillary stack :O(n).
    public static int climbingR(int n) {
        if (n <= 1) {
            return 1;
        }
        return climbingR(n - 1) + climbingR(n - 2);
    }

    // Dynamic programming.
    // Time complexity:O(n).
    // Space comlexity:O(n).
    // Auxillary stack :O(n).
    public static int climbingDp(int n) {
        int dp[] = new int[n + 1];
        Arrays.fill(dp, -1);
        return helper(n, dp);
    }

    public static int helper(int n, int dp[]) {
        if (dp[n] != -1) {
            return dp[n];
        }
        if (n <= 1) {
            return dp[n] = 1;
        }
        return dp[n] = helper(n - 1, dp) + helper(n - 2, dp);
    }

    // Tabulation.
    // Time complexity:O(n).
    // Space comlexity:O(n).
    // Auxillary stack :O(1).
    public static int tabulation(int n) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Space optimization.
    // Time complexity:O(n).
    // Space comlexity:O(1).
    // Auxillary stack :O(1).
    public static int spaceOptimization(int n) {
        int firstStep = 1, secondStep = 1;
        int currentStep = 0;
        for (int i = 2; i <= n; i++) {
            currentStep = firstStep + secondStep;
            firstStep = secondStep;
            secondStep = currentStep;
        }

        return secondStep;
    }
}