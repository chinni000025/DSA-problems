package DP;

import java.util.Arrays;

public class TargetSum {
    public static void main(String[] args) {
        int ar[] = { 1, 1, 1, 1, 1 };
        int target = 3;
        System.out.println(targetSum(ar, target));
    }

    public static int targetSum(int ar[], int target) {
        int sum = 0;
        for (int ele : ar)
            sum += ele;
        sum += target;
        int s1 = sum / 2;
        int dp[][] = new int[ar.length][s1 + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        return helper(ar.length - 1, s1, ar, dp);

    }

    public static int helper(int ind, int target, int ar[], int dp[][]) {
        if (ind == 0) {
            if (ar[0] == 0 && target == 0)
                return 2;
            else if (ar[0] == target)
                return 1;
            else if (target == 0)
                return 1;
            else
                return 0;
        }
        if (dp[ind][target] != -1)
            return dp[ind][target];
        int take = 0;
        if (ar[ind] <= target)
            take = helper(ind - 1, target - ar[ind], ar, dp);
        int notTake = helper(ind - 1, target, ar, dp);
        return dp[ind][target] = take + notTake;
    }
}
