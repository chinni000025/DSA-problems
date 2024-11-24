package DP;

import java.util.Arrays;

public class FrogJumpsKSteps {
    public static void main(String[] args) {
        int heights[] = { 10, 30, 40, 50, 20 };
        int k = 3;
        System.out.println(frogJumping(heights.length - 1, heights, k));
        System.out.println(frogJumpingDynamic(heights, k));
        System.out.println(tabulation(heights, k));
    }

    // Time complexity:O(2^n*k).
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int frogJumping(int index, int heights[], int k) {
        if (index == 0) {
            return 0;
        }
        int res = (int) 1e9;
        for (int i = 1; i <= k; i++) {
            int step = (int) 1e9;
            if (index - i >= 0) {
                step = Math.abs(heights[index] - heights[index - i]) + frogJumping(index - i, heights, k);
            }
            res = Math.min(res, step);
        }
        return res;
    }

    // Dynamic programming.
    // Time complexity:O(n*k).
    // Space comlexity:O(n).
    // Auxillary stack space:O(n).
    public static int frogJumpingDynamic(int heights[], int k) {
        int dp[] = new int[heights.length];
        Arrays.fill(dp, -1);
        int res = helper(heights.length - 1, heights, k, dp);
        System.out.println(Arrays.toString(dp));
        return res;
    }

    public static int helper(int index, int heights[], int k, int dp[]) {
        if (index == 0) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int res = (int) 1e9;
        for (int i = 1; i <= k; i++) {
            int step = (int) 1e9;
            if (index - i >= 0) {
                step = Math.abs(heights[index] - heights[index - i]) + helper(index - i, heights, k, dp);
            }
            res = Math.min(res, step);
        }
        return dp[index] = res;
    }

    // Tabulation.
    // Time complexity:O(n*k).
    // space complexity:O(n).
    // Auxillary stack space :O(1).
    public static int tabulation(int heights[], int k) {
        int dp[] = new int[heights.length];
        dp[0] = 0;
        for (int index = 1; index < heights.length; index++) {
            int res = (int) 1e9;
            for (int i = 1; i <= k; i++) {
                int step = (int) 1e9;
                if (index - i >= 0) {
                    step = Math.abs(heights[index] - heights[index - i]) + dp[index - i];
                }
                res = Math.min(res, step);
            }
            dp[index] = res;
        }
        // System.out.println(Arrays.toString(dp));
        return dp[heights.length - 1];
    }
    // The space optimization is not possible because k==n we need n variable so
    // that's not efficient.

}
