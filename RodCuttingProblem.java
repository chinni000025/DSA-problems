package DP;

import java.util.Arrays;

public class RodCuttingProblem {
    public static void main(String[] args) {
        int price[] = { 2, 5, 7, 8, 10 };
        int n = 5;
        // System.out.println(maxPrice(price, n));
        // System.out.println(maxPriceDynamic(price, n));
        System.out.println(rodCuttingTabulation(price, n));
        System.out.println(spaceOptimization(price, n));
    }

    // Recursion.
    // Time complexity:O(2^n).
    // space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int maxPrice(int price[], int n) {
        return helper(n - 1, price, n);
    }

    public static int helper(int ind, int price[], int n) {
        if (ind == 0) {
            return (n * price[0]);
        }
        int maxi = (int) -1e9;
        if (ind + 1 <= n) {
            int ans = price[ind] + helper(ind, price, n - (ind + 1));
            maxi = Math.max(maxi, ans);
        }
        return Math.max(maxi, helper(ind - 1, price, n));
    }

    // Dynamic programming.
    // Time complexity:O(n*length).
    // space complexity:O(n*price.length).
    // Auxillary stack space:O(n).
    public static int maxPriceDynamic(int price[], int n) {
        int dp[][] = new int[price.length][n + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        int res = helper(price.length - 1, price, n, dp);
        for (int temp[] : dp)
            System.out.println(Arrays.toString(temp));
        return res;
    }

    public static int helper(int ind, int price[], int n, int dp[][]) {
        if (ind == 0) {
            return (n * price[0]);
        }
        if (dp[ind][n] != -1) {
            return dp[ind][n];
        }
        int maxi = (int) -1e9;
        if (ind + 1 <= n) {
            int ans = price[ind] + helper(ind, price, n - (ind + 1), dp);
            maxi = Math.max(maxi, ans);
        }
        return dp[ind][n] = Math.max(maxi, helper(ind - 1, price, n, dp));
    }

    // Tabulation.
    // Time complexity:O(n*length).
    // space complexity:O(n*price.length).
    // Auxillary stack space:O(1).
    public static int rodCuttingTabulation(int price[], int n) {
        int dp[][] = new int[price.length][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i * price[0];
        }
        for (int ind = 1; ind < price.length; ind++) {
            for (int len = 1; len <= n; len++) {
                int maxi = (int) -1e9;
                if (ind + 1 <= len) {
                    int ans = price[ind] + dp[ind][len - (ind + 1)];
                    maxi = Math.max(maxi, ans);
                }
                dp[ind][len] = Math.max(maxi, dp[ind - 1][len]);
            }
        }
        for (int temp[] : dp)
        System.out.println(Arrays.toString(temp));
        return dp[price.length - 1][n];
    }

    // space optimization.
    // Time complexity:O(n*length).
    // space complexity:O(n).
    // Auxillary stack space:O(1).
    public static int spaceOptimization(int price[], int n) {
        int prev[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prev[i] = i * price[0];
        }
        for (int ind = 1; ind < price.length; ind++) {
            System.out.println(Arrays.toString(prev));
            int current[] = new int[n + 1];
            for (int len = 1; len <= n; len++) {
                int maxi = (int) -1e9;
                if (ind + 1 <= len) {
                    int ans = price[ind] + current[len - (ind + 1)];
                    maxi = Math.max(maxi, ans);
                }
                current[len] = Math.max(maxi, prev[len]);
            }
            prev = current;
        }
        System.out.println(Arrays.toString(prev));
        return prev[n];
    }
}
