package DP;

import java.util.Arrays;

public class PartitionArrayForMaximumSum {

    public static void main(String[] args) {
        int ar[] = { 1, 15, 7, 9, 2, 5, 10 };
        int k = 3;
        System.out.println(maxSum(0, ar, k));
        System.out.println(dynamicMax(ar, k));
        System.out.println(tabulationMax(ar, k));
    }

    // Time complexity:O(3^n).
    // space complexity:O(1).
    // Auxillary space complexity:O(n)
    public static int maxSum(int ind, int ar[], int k) {
        if (ind == ar.length) {
            return 0;
        }
        int max = ar[ind];
        int res = (int) -1e9;
        int len = 0;
        for (int j = ind; j < Math.min(ar.length, ind + k); j++) {
            max = Math.max(max, ar[j]);
            len++;
            int ans = (max * len) + maxSum(j + 1, ar, k);
            res = Math.max(res, ans);
        }
        return res;
    }

    // Dynamic programming.
    // Time complexity:O(n).
    // Stack space:O(n).
    // Auxillary stack space:o(n).
    public static int dynamicMax(int ar[], int k) {
        int dp[] = new int[ar.length];
        Arrays.fill(dp, -1);
        return helper(0, ar, k, dp);
    }

    public static int helper(int ind, int ar[], int k, int dp[]) {
        if (ind == ar.length)
            return 0;
        if (dp[ind] != -1)
            return dp[ind];
        int max = ar[ind];
        int res = (int) -1e9;
        int len = 0;
        for (int j = ind; j < Math.min(ar.length, ind + k); j++) {
            max = Math.max(max, ar[j]);
            len++;
            int ans = (max * len) + helper(j + 1, ar, k, dp);
            res = Math.max(res, ans);
        }
        return dp[ind] = res;
    }

    // Tabulation.
    public static int tabulationMax(int ar[], int k) {
        int dp[] = new int[ar.length];
        for (int ind = ar.length - 1; ind >= 0; ind--) {
            int max = ar[ind];
            int res = (int) -1e9;
            int len = 0;
            for (int j = ind; j < Math.min(ar.length, ind + k); j++) {
                max = Math.max(max, ar[j]);
                len++;
                int ans = (max * len) + helper(j + 1, ar, k, dp);
                res = Math.max(res, ans);
            }
            dp[ind] = res;
        }
        return dp[0];
    }
}