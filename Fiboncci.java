package DP;

import java.util.Arrays;

public class Fiboncci {
    public static void main(String[] args) {
        int n = 15;
        System.out.println(topDown(n));
        System.out.println(bottomUp(0, n));
        System.out.println(dpTopDown(n));
        System.out.println(tabulationBottomUP(n));
        System.out.println(spaceOptimization(n));
    }

    // Recursion.

    // Top Down approach.
    // Time complexity:O(2^n).
    // Space complexity:O(1).
    // Auxillary stack space:O(n). --> depth of the recursion.
    public static long topDown(int n) {
        if (n < 2) {
            return n;
        }
        return (topDown(n - 1) + topDown(n - 2)) % ((int) 1e9 + 7);
    }

    // Bottom Up Approach
    // Time complexity:O(2^n).
    // Space complexity:O(1).
    // Auxillary stack space:O(n). --> depth of the recursion.
    public static long bottomUp(int n, int st) {
        if (n >= st - 1) {
            return st - n;
        }
        return (bottomUp(n + 1, st) + bottomUp(n + 2, st)) % ((int) 1e9 + 7);
    }

    // Dynamic programming.

    // Top Down approach.

    // Time complexity:O(n).
    // Space complexity:O(n).
    // Auxillary stack space:O(n). --> depth of the recursion.
    public static long dpTopDown(int n) {
        long dp[] = new long[n + 1];
        Arrays.fill(dp, -1);
        return dpTopDownHelper(n, dp);
    }

    public static long dpTopDownHelper(int n, long dp[]) {
        if (dp[n] != -1) {
            return dp[n];
        }
        if (n < 2) {
            return dp[n] = n;

        }
        return dp[n] = (dpTopDownHelper(n - 1, dp) + dpTopDownHelper(n - 2, dp)) % ((int) 1e9 + 7);
    }

    // Bottom up
    // Tabulation.
    // Time complexity:O(n).
    // Space complexity:O(n).
    // Auxillary stack space:O(1). --> depth of the recursion.
    public static long tabulationBottomUP(int n) {
        long dp[] = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % ((int) 1e9 + 7);
        }
        return dp[n];
    }

    // Space optimization. --> optimized version of the code
    // Time complexity:O(n).
    // Space complexity:O(1).
    // Auxillary stack space:O(1). --> depth of the recursion.
    public static long spaceOptimization(int n) {

        long firstPrevious = 0, secondPrevious = 1;
        long present = firstPrevious + secondPrevious;
        for (int i = 2; i <= n; i++) {
            present = firstPrevious + secondPrevious;
            firstPrevious = secondPrevious;
            secondPrevious = present;

        }
        return present;
    }
}
