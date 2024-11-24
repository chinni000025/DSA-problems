package DP;

import java.util.Arrays;

public class CountingSubSet {
    public static void main(String[] args) {
        int ar[] = { 0, 0, 1 };
        int target = 1;
        System.out.println(counting(ar.length - 1, ar, target));
        System.out.println(dynamicApproach(ar, target));
        System.out.println(tabulation(ar, target));
        System.out.println(spaceOptimization(ar, target));
    }

    // Recursion.
    // Time complexity:O(2^n).
    // Space complexity:(1).
    // Auxillary stack space:O(n).
    public static int counting(int ind, int ar[], int target) {
        if (ind == 0) {
            if (ar[0] == 0 && target == 0)
                return 2;
            if (target == 0)
                return 1;
            if (ar[0] == target)
                return 1;
            return 0;
        }
        int take = 0;
        if (target >= ar[ind]) {
            take = counting(ind - 1, ar, target - ar[ind]);
        }
        int notTake = counting(ind - 1, ar, target);
        return (take + notTake);
    }

    // Dynamic programming.
    // Time complexity:O(n*target).
    // Space complexity:(n*target).
    // Auxillary stack space:O(n).
    public static int dynamicApproach(int ar[], int target) {
        int dp[][] = new int[ar.length][target + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        return helper(ar.length - 1, ar, target, dp);
    }

    public static int helper(int ind, int ar[], int target, int dp[][]) {
        if (ind == 0) {
            if (ar[0] == 0 && target == 0)
                return 2;
            if (target == 0)
                return 1;
            if (ar[0] == target)
                return 1;
            return 0;
        }
        if (dp[ind][target] != -1)
            return dp[ind][target];
        int take = 0;
        if (target >= ar[ind]) {
            take = helper(ind - 1, ar, target - ar[ind], dp);
        }
        int notTake = helper(ind - 1, ar, target, dp);
        return dp[ind][target] = (take + notTake);
    }

    // Tabulation.
    // Time complexity:O(n*target).
    // Space complexity:(n*target).
    // Auxillary stack space:O(1).
    public static int tabulation(int ar[], int target) {
        int dp[][] = new int[ar.length][target + 1];
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 1;
        if (ar[0] <= target)
            dp[0][ar[0]] = 1;
        for (int ind = 1; ind < ar.length; ind++) {
            for (int tar = 1; tar <= target; tar++) {
                int take = 0;
                if (ar[ind] <= tar) {
                    take = dp[ind - 1][tar - ar[ind]];
                }
                int notTake = dp[ind - 1][tar];
                dp[ind][tar] = take + notTake;
            }
        }
        for (int temp[] : dp) {
            System.out.println(Arrays.toString(temp));
        }
        return dp[ar.length - 1][target];
    }

    // Space optimizaition.
    // time complexity:O(n*target).
    // Space complexity:O(target).
    // Auxillary stack space:O(1).
    public static int spaceOptimization(int ar[], int target) {
        int prev[] = new int[target + 1];
        prev[0] = 1;
        if (ar[0] <= target)
            prev[ar[0]] = 1;
        for (int ind = 1; ind < ar.length; ind++) {
            int current[] = new int[target + 1];
            for (int tar = 1; tar <= target; tar++) {
                int take = 0;
                if (ar[ind] <= tar) {
                    take = prev[tar - ar[ind]];
                }
                int notTake = prev[tar];
                current[tar] = take + notTake;
            }
            prev = current;
            prev[0] = 1;
        }
        return prev[target];
    }

}
