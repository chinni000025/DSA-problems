package DP;

import java.util.Arrays;

public class PartitioningSubSet {
    public static void main(String[] args) {
        int ar[] = { 1, 5, 11, 5 };
        System.out.println(partitioning(ar));
        System.out.println(dynamicSubSetSum(ar));
    }

    // Recursion.
    // Time complexity:O(2^n)*O(n).
    // space complexity:O(1).
    // Auxillarys stack space:O(n). --> where n is the length of the array.
    public static boolean partitioning(int ar[]) {
        int sum = 0;
        for (int el : ar)
            sum += el;
        if (sum % 2 != 0)
            return false;
        sum /= 2;
        return subSetSum(ar.length - 1, sum, ar);
    }

    public static boolean subSetSum(int ind, int target, int ar[]) {
        if (target == 0)
            return true;
        if (ind == 0)
            return target == ar[ind];
        boolean take = false;
        if (target >= ar[ind])
            take = subSetSum(ind - 1, target - ar[ind], ar);
        boolean notTake = subSetSum(ind - 1, target, ar);
        return take || notTake;
    }

    // Dynamic programming.
    // Time complexity:O(n*k)*O(n).
    // Space complexity:O(n*target).
    // Auxillary stack space:O(n).
    public static boolean dynamicSubSetSum(int ar[]) {
        int target = 0;
        for (int el : ar)
            target += el;
        if (target % 2 != 0)
            return false;
        target /= 2;
        int dp[][] = new int[ar.length][target + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        boolean res = helper(ar.length - 1, ar, target, dp);
        return res;
    }

    public static boolean helper(int ind, int ar[], int target, int dp[][]) {
        if (target == 0)
            return true;
        if (ind == 0) {
            boolean res = target == ar[ind];
            if (res)
                dp[ind][target] = 1;
            else
                dp[ind][target] = 0;
        }
        if (dp[ind][target] != -1)
            return dp[ind][target] == 0 ? false : true;
        boolean take = false;
        if (target >= ar[ind])
            take = helper(ind - 1, ar, target - ar[ind], dp);
        boolean notTake = helper(ind - 1, ar, target, dp);
        int res = (take || notTake) ? 1 : 0;
        dp[ind][target] = res;
        return (take || notTake);
    }

    // Tabulation.
    // Time complexity:O(n*target)+O(n).
    // Space complexity:O(n*tagert).
    // Auxillary stack space:O(1).
    public static boolean tabulation(int ar[]) {
        int target = 0;
        for (int el : ar)
            target += el;
        if (target % 2 != 0)
            return false;
        target /= 2;
        boolean dp[][] = new boolean[ar.length][target + 1];
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = true;
        if (ar[0] <= target)
            dp[0][ar[0]] = true;
        for (int i = 1; i < ar.length; i++) {
            for (int j = 1; j <= target; j++) {
                boolean take = false;
                if (j >= ar[i])
                    take = dp[i - 1][j - ar[i]];
                boolean notTake = dp[i - 1][j];
                dp[i][j] = take || notTake;
            }
        }
        return dp[ar.length - 1][target];
    }
}
