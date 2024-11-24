package DP;

import java.util.Arrays;

public class HouseRobbed2 {

    public static void main(String[] args) {
        int nums[] = { 1 };
        System.out.println(houseRobbing(nums));
        System.out.println(houseRobbingDynamic(nums));
    }

    // Recursive approach.
    // Time complexity:O(2^n)*2;
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int houseRobbing(int nums[]) {
        int h1 = helper1(nums.length - 1, nums, false);
        int h2 = helper1(nums.length - 2, nums, true);
        return Math.max(h1, h2);
    }

    public static int helper1(int index, int nums[], boolean istake) {
        if (nums.length == 0) {
            return nums[0];
        }
        if (index == 0) {
            if (istake) {
                return nums[0];
            }
            return 0;
        }
        if (index < 0) {
            return 0;
        }
        int rob = nums[index] + helper1(index - 2, nums, istake);
        int notRob = helper1(index - 1, nums, istake);
        return Math.max(rob, notRob);
    }

    // Dynamic programming.
    // Time complexity:O(2*n).
    // Space complexity:O(n).
    // Auxillary stack space:O(n).
    public static int houseRobbingDynamic(int nums[]) {
        if (nums.length == 0) {
            return nums[0];
        }
        int dp[] = new int[nums.length];
        Arrays.fill(dp, -1);
        int h1 = helper2(nums.length - 1, nums, false, dp);
        Arrays.fill(dp, -1);
        int h2 = helper2(nums.length - 2, nums, true, dp);
        return Math.max(h1, h2);
    }

    public static int helper2(int index, int nums[], boolean istake, int dp[]) {
        if (index == 0) {
            if (istake) {
                return nums[0];
            }
            return 0;
        }
        if (index < 0) {
            return 0;
        }
        int rob = nums[index] + helper2(index - 2, nums, istake, dp);
        int notRob = helper2(index - 1, nums, istake, dp);
        return Math.max(rob, notRob);
    }

}