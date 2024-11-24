package DP;

import java.util.Arrays;

public class BuyAndSellTheStockWithTwoTransc {
    public static void main(String[] args) {
        int prices[] = { 14, 9, 10, 12, 4, 8, 1, 16 };
        System.out.println(obtainingProfit(prices));
        System.out.println(obtainingProfitDynamic(prices));
        System.out.println(obtainingProfitTabulation(prices));
        System.out.println(spaceOptimization(prices));
    }

    // Recursion.
    // Time complexity:O(2^n*2^n).
    // space complexity:o(1).
    // Auxillary stack space:O(n).
    public static int obtainingProfit(int prices[]) {
        return helper(0, prices, 1, 2);
    }

    public static int helper(int ind, int prices[], int isBuy, int transcation) {
        if (ind == prices.length || transcation <= 0) {
            return 0;
        }
        if (isBuy == 1) {
            int buy = -prices[ind] + helper(ind + 1, prices, 0, transcation);
            int notbuy = helper(ind + 1, prices, isBuy, transcation);
            return Math.max(buy, notbuy);
        } else {
            int sell = prices[ind] + helper(ind + 1, prices, 1, transcation - 1);
            int notsell = helper(ind + 1, prices, isBuy, transcation);
            return Math.max(sell, notsell);
        }
    }

    // Dynamic programming.
    // Time complexity:O(n*2*2).
    // Space complexity:O(n*2).
    // Auxillary stack space:O(n)
    public static int obtainingProfitDynamic(int prices[]) {
        int dp[][][] = new int[prices.length][2][3];
        for (int temp1[][] : dp) {
            for (int temp2[] : temp1)
                Arrays.fill(temp2, (int) -1e9);
        }
        return helper(0, prices, 1, 2, dp);

    }

    public static int helper(int ind, int prices[], int isBuy, int transcation, int dp[][][]) {
        if (ind == prices.length || transcation == 0)
            return 0;
        if (dp[ind][isBuy][transcation] != (int) -1e9)
            return dp[ind][isBuy][transcation];
        if (isBuy == 1) {
            int buy = -prices[ind] + helper(ind + 1, prices, 0, transcation, dp);
            int notbuy = helper(ind + 1, prices, isBuy, transcation, dp);
            return dp[ind][isBuy][transcation] = Math.max(buy, notbuy);
        } else {
            int sell = prices[ind] + helper(ind + 1, prices, 1, transcation - 1, dp);
            int notsell = helper(ind + 1, prices, isBuy, transcation, dp);
            return dp[ind][isBuy][transcation] = Math.max(sell, notsell);
        }
    }

    // Tabulation.
    // Time complexity:O(2*2*n).
    // space complexity:O(2*2*n).
    // Auxillary stack space:O(1).
    public static int obtainingProfitTabulation(int prices[]) {
        int dp[][][] = new int[prices.length + 1][2][3];
        for (int ind = prices.length - 1; ind >= 0; ind--) {
            for (int isBuy = 0; isBuy < 2; isBuy++) {
                for (int trans = 1; trans <= 2; trans++) {
                    if (isBuy == 1) {
                        int buy = -prices[ind] + dp[ind + 1][0][trans];
                        int notbuy = dp[ind + 1][1][trans];
                        dp[ind][isBuy][trans] = Math.max(buy, notbuy);
                    } else {
                        int sell = prices[ind] + dp[ind + 1][1][trans - 1];
                        int notsell = dp[ind + 1][0][trans];
                        dp[ind][isBuy][trans] = Math.max(sell, notsell);
                    }
                }
            }
        }
        return dp[0][1][2];
    }

    // Tabulation.
    // Time complexity:O(2*2*n).
    // space complexity:O(2*n).
    // Auxillary stack space:O(1).
    public static int spaceOptimization(int prices[]) {
        int after[][] = new int[2][3];
        int current[][] = new int[2][3];
        for (int ind = prices.length - 1; ind >= 0; ind--) {
            for (int isBuy = 0; isBuy < 2; isBuy++) {
                for (int trans = 1; trans <= 2; trans++) {
                    if (isBuy == 1) {
                        int buy = -prices[ind] + after[0][trans];
                        int notbuy = after[1][trans];
                        current[isBuy][trans] = Math.max(buy, notbuy);
                    } else {
                        int sell = prices[ind] + after[1][trans - 1];
                        int notsell = after[0][trans];
                        current[isBuy][trans] = Math.max(sell, notsell);
                    }
                }
                after = current;
            }
        }
        return after[1][2];
    }
}
