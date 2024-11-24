package DP;

import java.util.Arrays;

public class BrustBallons {
    public static void main(String[] args) {
        int nums[] = { 3, 1, 5, 8 };
        System.out.println(brustingRecursion(nums));
        System.out.println(brustringDynamic(nums));
        System.out.println(brustingTabulation(nums));
    }

    // Time complexity:O(n^n)*O(n).
    // space complexity:O(1).
    // Auxillary stack Space:O(n).
    public static int brustingRecursion(int nums[]) {
        int values[] = new int[nums.length + 2];
        System.arraycopy(nums, 0, values, 1, nums.length);
        values[0] = 1;
        values[values.length - 1] = 1;
        return helper(values, 1, values.length - 2);
    }

    public static int helper(int nums[], int i, int j) {
        if (j < i)
            return 0;
        int maxCost = (int) -1e9;
        for (int k = i; k <= j; k++) {
            int steps = (nums[i - 1] * nums[k] * nums[j + 1]) + helper(nums, i, k - 1) + helper(nums, k + 1, j);
            maxCost = Math.max(maxCost, steps);
        }
        return maxCost;
    }

    // Time complexity:O(n*n)*O(n).
    // space complexity:O(n*n).
    // Auxillary stack Space:O(n).
    public static int brustringDynamic(int nums[]) {
        int values[] = new int[nums.length + 2];
        System.arraycopy(nums, 0, values, 1, nums.length);
        values[0] = 1;
        values[values.length - 1] = 1;
        int dp[][] = new int[values.length][values.length];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        int res = helper(values, 1, values.length - 2, dp);
        for (int temp[] : dp)
            System.out.println(Arrays.toString(temp));
        return res;
    }

    public static int helper(int nums[], int i, int j, int dp[][]) {
        if (j < i)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int maxCost = (int) -1e9;
        for (int k = i; k <= j; k++) {
            int steps = (nums[i - 1] * nums[k] * nums[j + 1]) + helper(nums, i, k - 1, dp) + helper(nums, k + 1, j, dp);
            maxCost = Math.max(maxCost, steps);
        }
        return dp[i][j] = maxCost;
    }

    public static int brustingTabulation(int nums[]) {
        int values[] = new int[nums.length + 2];
        System.arraycopy(nums, 0, values, 1, nums.length);
        values[0] = 1;
        values[values.length - 1] = 1;
        int dp[][] = new int[values.length][values.length];

        for (int i = values.length - 2; i >= 1; i--) {
            for (int j = i; j <= values.length - 2; j++) {
                int maxCost = (int) -1e9;
                for (int k = i; k <= j; k++) {
                    int steps = (values[i - 1] * values[k] * values[j + 1]) + dp[i][k - 1] + dp[k + 1][j];
                    maxCost = Math.max(maxCost, steps);
                }
                dp[i][j] = maxCost;
            }
        }
        return dp[1][dp.length - 2];
    }

}
