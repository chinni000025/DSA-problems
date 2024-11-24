package DP;

import java.util.Arrays;

public class CoinChange2 {

    public static void main(String[] args) {
        int coins[] = { 1, 2, 5 };
        int amount = 5;
        // System.out.println(numberOfDenomination(coins.length - 1, coins, amount));
        System.out.println(numberOfDenominationDynamic(coins, amount));
        System.out.println(coinChanging2Tabulation(coins, amount));
    }

    public static int numberOfDenomination(int ind, int coins[], int amount) {
        if (ind == 0) {
            if (amount == 0) {
                return 1;
            } else if (amount % coins[ind] == 0) {
                return 1;
            }
            return 0;
        }
        int take = 0;
        if (amount >= coins[ind]) {
            take = numberOfDenomination(ind, coins, amount - coins[ind]);
        }
        int notTake = numberOfDenomination(ind - 1, coins, amount);
        return take + notTake;
    }

    public static int numberOfDenominationDynamic(int coins[], int amount) {
        int dp[][] = new int[coins.length][amount + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        int res = helper(coins.length - 1, coins, amount, dp);
        for (int temp[] : dp) {
            System.out.println(Arrays.toString(temp));
        }
        return res;
    }

    public static int helper(int ind, int coins[], int amount, int dp[][]) {
        if (ind == 0) {
            if (amount == 0) {
                return 1;
            } else if (amount % coins[ind] == 0) {
                return 1;
            }
            return 0;
        }
        if (dp[ind][amount] != -1) {
            return dp[ind][amount];
        }
        int take = 0;
        if (amount >= coins[ind]) {
            take = helper(ind, coins, amount - coins[ind], dp);
        }
        int notTake = helper(ind - 1, coins, amount, dp);
        return dp[ind][amount] = take + notTake;
    }

    // Tabulation.
    public static int coinChanging2Tabulation(int coins[], int amount) {
        int dp[][] = new int[coins.length][amount + 1];
        for (int i = 0; i < coins.length; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i <= amount; i++) {
            if (i % coins[0] == 0) {
                dp[0][i] = 1;
            }
        }
        for (int ind = 1; ind < coins.length; ind++) {
            for (int amou = 1; amou <= amount; amou++) {
                int take = 0;
                if (amou >= coins[ind]) {
                    take = dp[ind][amou - coins[ind]];
                }
                int notTake = dp[ind - 1][amou];
                dp[ind][amou] = take + notTake;
            }
        }
        for (int temp[] : dp) {
            System.out.println(Arrays.toString(temp));
        }
        return dp[coins.length - 1][amount];
    }
}