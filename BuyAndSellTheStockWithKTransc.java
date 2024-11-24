package DP;

import java.util.Arrays;

public class BuyAndSellTheStockWithKTransc {
    public static void main(String[] args) {
        int prices[] = { 1, 2 };
        int k = 1;
        System.out.println(stockMarketDynamic(prices, k));
        System.out.println(tabulation(prices, k));
        System.out.println(tabulationSpaceOptimization(prices, k));

    }

    public static int stockMarketDynamic(int prices[], int k) {
        int dp[][][] = new int[prices.length][2][k + 1];

        for (int temp[][] : dp) {
            for (int temp2[] : temp) {
                Arrays.fill(temp2, -1);
            }
        }
        return stockMarketRec(0, 1, prices, k, dp);
    }

    public static int stockMarketRec(int ind, int buy, int prices[], int transcation, int dp[][][]) {
        if (ind == prices.length || transcation <= 0) {
            return 0;
        }
        if (dp[ind][buy][transcation] != -1) {
            return dp[ind][buy][transcation];
        }
        if (buy == 1) {
            int buying = -prices[ind] + stockMarketRec(ind + 1, 0, prices, transcation, dp);
            int notbuying = stockMarketRec(ind + 1, 1, prices, transcation, dp);
            return dp[ind][buy][transcation] = Math.max(buying, notbuying);
        } else {
            int selling = prices[ind] + stockMarketRec(ind + 1, 1, prices, transcation - 1, dp);
            int notselling = stockMarketRec(ind + 1, 0, prices, transcation, dp);
            return dp[ind][buy][transcation] = Math.max(selling, notselling);
        }

    }

    public static int tabulation(int prices[], int k) {
        int dp[][][] = new int[prices.length + 1][2][k + 1];
        for (int ind = prices.length - 1; ind >= 0; ind--) {
            for (int buy = 0; buy < 2; buy++) {
                for (int trans = 1; trans <= k; trans++) {
                    if (buy == 1) {
                        int buying = -prices[ind] + dp[ind + 1][0][trans];
                        int notbuying = dp[ind + 1][1][trans];
                        dp[ind][buy][trans] = Math.max(buying, notbuying);
                    } else {
                        int selling = prices[ind] + dp[ind + 1][1][trans - 1];
                        int notselling = dp[ind + 1][0][trans];
                        dp[ind][buy][trans] = Math.max(selling, notselling);
                    }
                    
                }
            }
        }
        return dp[0][1][k];
    }

    // space optimization.
    public static int tabulationSpaceOptimization(int prices[], int k) {
        int current[][] = new int[2][k + 1];
        int after[][] = new int[2][k + 1];
        for (int ind = prices.length - 1; ind >= 0; ind--) {
            for (int buy = 0; buy < 2; buy++) {
                for (int trans = 1; trans <= k; trans++) {
                    if (buy == 1) {
                        int buying = -prices[ind] +current[0][trans];
                        int notbuying =current[1][trans];
                       after[buy][trans] = Math.max(buying, notbuying);
                   } else {
                       int selling = prices[ind] + current[1][trans - 1];
                       int notselling = current[0][trans];
                       after[buy][trans] = Math.max(selling, notselling);
                   }
                   current = after;
                }
            }
        }
        return after[1][k];
    }
}
