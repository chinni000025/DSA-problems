package DP;

import java.util.Arrays;

public class HouseRobber1 {
    public static void main(String[] args) {
        int nums[] = { 2, 1, 4, 9 };
        // System.out.println(houseRobber(nums));
        // System.out.println(dynamic(nums));
        // System.out.println(tabulation(nums));
        System.out.println(spaceOptimization(nums));
    }

    // Time complexity:O(2^n);
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int houseRobber(int nums[]) {
        return houseRobbering(nums.length - 1, nums);
    }

    public static int houseRobbering(int index, int nums[]) {
        if (index < 0) {
            return 0;
        }
        int rob = nums[index] + houseRobbering(index - 2, nums);
        int notrob = houseRobbering(index - 1, nums);
        return Math.max(rob, notrob);
    }

    // Dynamic programming.
    // Time complexity:O(n).
    // Space complexity:O(n).
    // Auxillary stack space:O(n).
    public static int dynamic(int nums[]) {
        int dp[] = new int[nums.length];
        Arrays.fill(dp, -1);
        int res = helper(nums.length - 1, nums, dp);
        System.out.println(Arrays.toString(dp));
        return res;
    }

    public static int helper(int index, int nums[], int dp[]) {
        if (index < 0) {
            return 0;
        }
        if (dp[index] != -1) {
            return dp[index];
        }
        int rob = nums[index] + helper(index - 2, nums, dp);
        int notrob = helper(index - 1, nums, dp);
        return dp[index] = Math.max(rob, notrob);
    }

    // Tabulation.
    // Time complexity:O(n).
    // Space complexity:O(n).
    // Auxillary stack space:O(1).
    public static int tabulation(int nums[]) {
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        for (int index = 1; index < nums.length; index++) {
            int rob = nums[index];
            if (index - 2 >= 0) {
                rob = rob + dp[index - 2];
            }
            int notrob = dp[index - 1];
            dp[index] = Math.max(rob, notrob);

        }
        return dp[nums.length - 1];
    }

    // Space optimization.
    // Time complexity:O(n).
    // Space complexity:O(1).
    // Auxillary stack space:O(1).
    public static int spaceOptimization(int nums[]) {
        int prev1 = nums[0];
        int prev2 = 0;
        for (int index = 1; index < nums.length; index++) {
            int rob = nums[index];
            if (index - 2 >= 0) {
                rob = rob + prev2;
            }
            int notrob = prev1;
            int current = Math.max(rob, notrob);
            prev2 = prev1;
            prev1 = current;

        }
        return prev1;
    }
}
