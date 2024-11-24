package DP;

import java.util.Arrays;

public class FrogJump {
    public static void main(String[] args) {
        int heights[] = { 7, 4, 4, 2, 6, 6, 3, 4 };
        System.out.println(frogJumpingRecurively(heights.length - 1, heights));
        System.out.println(frogJumpDynamic(heights));
        System.out.println(tabulation(heights));
        System.out.println(spaceOptimization(heights));

    }

    // Recursion.
    // Time complexity:O(2^n).
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int frogJumpingRecurively(int n, int heights[]) {
        if (n == 0) {
            return 0;
        }
        int fs = Math.abs(heights[n] - heights[n - 1]) + frogJumpingRecurively(n - 1, heights);
        int ss = (int) 1e9;
        if (n > 1) {
            ss = Math.abs(heights[n] - heights[n - 2]) + frogJumpingRecurively(n - 2, heights);
        }
        return Math.min(fs, ss);
    }

    // Dynamic programming.
    // Time complexity:O(N).
    // Space complexity:O(n).
    // Auxillary stack space:O(n).
    public static int frogJumpDynamic(int heights[]) {
        int dp[] = new int[heights.length];
        Arrays.fill(dp, -1);

        int res = helper(heights.length - 1, heights, dp);
        return res;
    }

    public static int helper(int n, int heights[], int dp[]) {
        if (n == 0) {
            return 0;
        }
        if (dp[n] != -1) {
            return dp[n];
        }

        int fs = Math.abs(heights[n] - heights[n - 1]) + helper(n - 1, heights, dp);
        int ss = (int) 1e9;
        if (n > 1) {
            ss = Math.abs(heights[n] - heights[n - 2]) + helper(n - 2, heights, dp);
        }
        return dp[n] = Math.min(fs, ss);
    }

    // Tabulation.
    // Time complexity:O(N).
    // Space complexity:O(n).
    // Auxillary stack space:O(1).
    public static int tabulation(int heights[]) {
        int dp[] = new int[heights.length];

        dp[0] = 0;
        for (int i = 1; i < heights.length; i++) {
            int fs = Math.abs(heights[i] - heights[i - 1]) + dp[i - 1];
            int ss = (int) 1e9;
            if (i > 1) {
                ss = Math.abs(heights[i] - heights[i - 2]) + dp[i - 2];
            }
            dp[i] = Math.min(fs, ss);
        }
        return dp[heights.length - 1];
    }

    // Space optimization.
    // Time complexity:O(N).
    // Space complexity:O(1).
    // Auxillary stack space:O(1).

    public static int spaceOptimization(int heights[]) {

        int prev2 = 0;
        int prev1 = 0;
        for (int i = 1; i < heights.length; i++) {
            int fs = Math.abs(heights[i] - heights[i - 1]) + prev1;
            int ss = (int) 1e9;
            if (i > 1) {
                ss = Math.abs(heights[i] - heights[i - 2]) + prev2;
            }
            int current = Math.min(ss, fs);
            prev2 = prev1;
            prev1 = current;
        }
        return prev1;
    }
}
