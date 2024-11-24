package DP;

import java.util.Arrays;

public class BuyAndSellTheStockWithTranscFee {
    public static void main(String[] args) {
        int prices[] = { 1, 3, 2, 8, 4, 9 };
        int fee = 2;
        System.out.println(obtainingMaxProfit(0, prices, 1, fee));
        System.out.println(obtainingMaxProfitDynamic(prices, fee));
        System.out.println(obtainingMaxProfitTabulation(prices, fee));
        System.out.println(spaceOptimization(prices, fee));
    }

    // Recursion.
    // Time complexity:O(2^n).
    // Space complexity:O(1).
    // Auxillary stack space:O(1).
    public static int obtainingMaxProfit(int ind, int prices[], int isBuy, int fee) {
        if (ind == prices.length)
            return 0;
        if (isBuy == 1) {
            int buy = -prices[ind] + obtainingMaxProfit(ind + 1, prices, 0, fee);
            int notbuy = obtainingMaxProfit(ind + 1, prices, isBuy, fee);
            return Math.max(buy, notbuy);

        } else {
            int sell = prices[ind] + obtainingMaxProfit(ind + 1, prices, 1, fee) - fee;
            int notSell = obtainingMaxProfit(ind + 1, prices, isBuy, fee);
            return Math.max(sell, notSell);
        }
    }

    // Dynamic programming.
    // Time complexity:O(2*n).
    // Space complexity:O(2*n).
    // Auxillary stack space:O(1).
    public static int obtainingMaxProfitDynamic(int ar[], int fee) {
        int dp[][] = new int[ar.length][2];
        for (int temp[] : dp) {
            Arrays.fill(temp, (int) -1e9);
        }
        int res = helper(0, ar, 1, fee, dp);
        return res;
    }

    public static int helper(int ind, int prices[], int isBuy, int fee, int dp[][]) {
        if (ind == prices.length)
            return 0;
        if (dp[ind][isBuy] != (int) -1e9)
            return dp[ind][isBuy];
        if (isBuy == 1) {
            int buy = -prices[ind] + helper(ind + 1, prices, 0, fee, dp);
            int notbuy = helper(ind + 1, prices, isBuy, fee, dp);
            return dp[ind][isBuy] = Math.max(buy, notbuy);

        } else {
            int sell = prices[ind] + helper(ind + 1, prices, 1, fee, dp) - fee;
            int notSell = helper(ind + 1, prices, isBuy, fee, dp);
            return dp[ind][isBuy] = Math.max(sell, notSell);
        }
    }

    // Tabulation.
    // Time complexity:O(2*n).
    // space complexity:O(2*n).
    // Auxillary stack space:o(1).
    public static int obtainingMaxProfitTabulation(int prices[], int fee) {
        int dp[][] = new int[prices.length + 1][2];

        for (int i = prices.length - 1; i >= 0; i--) {
            for (int j = 1; j >= 0; j--) {
                if (j == 1) {
                    int buy = -prices[i] + dp[i + 1][0];
                    int notbuy = dp[i + 1][1];
                    dp[i][j] = Math.max(buy, notbuy);

                } else {
                    int sell = prices[i] + dp[i + 1][1] - fee;
                    int notSell = dp[i + 1][0];
                    dp[i][j] = Math.max(sell, notSell);
                }
            }
        }
        // for (int temp[] : dp) {
        // System.out.println(Arrays.toString(temp));
        // }
        return dp[0][1];
    }

    public static int spaceOptimization(int prices[], int fee) {
        int prev[] = new int[2];
        for (int i = prices.length - 1; i >= 0; i--) {
            int current[] = new int[2];
            for (int j = 1; j >= 0; j--) {
                if (j == 1) {
                    int buy = -prices[i] + prev[0];
                    int notbuy = prev[1];
                    current[j] = Math.max(buy, notbuy);

                } else {
                    int sell = prices[i] + prev[1] - fee;
                    int notSell = prev[0];
                    current[j] = Math.max(sell, notSell);
                }
            }
            prev = current;
        }
        // for (int temp[] : dp) {
        // System.out.println(Arrays.toString(temp));
        // }
        return prev[1];
    }
}
