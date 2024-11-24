package DP;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        int coins[] = { 1, 2, 5 };
        int amount = 11;
        System.out.println(coinsChanging(coins, amount));
        System.out.println(changingTabulation(coins, amount));
        System.out.println(changingSpaceOptimization(coins, amount));
    }

    public static int coinsChanging(int coins[], int amount) {
        int dp[][] = new int[coins.length][amount + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        int res = changing(coins.length - 1, amount, coins, dp);
        if (res == (int) 1e9)
            return -1;
        for (int tem[] : dp) {
            System.out.println(Arrays.toString(tem));
        }
        return res;
    }

    // Recursive approach.
    // Time complexity:O(2^amount).
    // Space complexity:O(1).
    // Auxillary stack space:O(amount).
    public static int changing(int ind, int amount, int coins[]) {
        if (ind == 0) {
            if (coins[0] == amount) {
                return 1;
            } else if (amount % coins[0] == 0) {
                return amount / coins[0];
            } else if (amount == 0) {
                return 0;
            } else {
                return (int) 1e9;
            }
        }
        int mini = (int) 1e9;
        if (coins[ind] <= amount) {
            int ans = 1 + changing(ind, amount - coins[ind], coins);
            mini = Math.min(mini, ans);
        }
        return Math.min(changing(ind - 1, amount, coins), mini);
    }

    // Dynamic programming.
    // Time complexity:O(n*amount).
    // space complexity:O(n*amount).
    // Auxillary stack space:O(amount).
    public static int changing(int ind, int amount, int coins[], int dp[][]) {
        if (ind == 0) {
            if (coins[0] == amount) {
                return dp[ind][amount] = 1;
            } else if (amount % coins[0] == 0) {
                return dp[ind][amount] = amount / coins[0];
            } else if (amount == 0) {
                return dp[ind][amount] = 0;
            } else {
                return (int) 1e9;
            }
        }
        if (dp[ind][amount] != -1) {
            return dp[ind][amount];
        }
        int mini = (int) 1e9;
        if (coins[ind] <= amount) {
            int ans = 1 + changing(ind, amount - coins[ind], coins, dp);
            mini = Math.min(mini, ans);
        }
        return dp[ind][amount] = Math.min(changing(ind - 1, amount, coins, dp), mini);
    }

    // Tabulation.
    // Time complexity:O(n*amount).
    // Space complexity:O(n*amount).
    // Auxillary stack space:O(1).
    public static int changingTabulation(int coins[], int amount) {
        if (amount == 0) {
            return 0;
        }
        int dp[][] = new int[coins.length][amount + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, (int) 1e9);
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 0;
        for (int i = 1; i <= amount; i++) {
            if (i % coins[0] == 0)
                dp[0][i] = i / coins[0];
        }
        for (int ind = 1; ind < coins.length; ind++) {
            for (int amou = 1; amou <= amount; amou++) {
                int mini = (int) 1e9;
                if (coins[ind] <= amou) {
                    int ans = 1 + dp[ind][amou - coins[ind]];
                    mini = Math.min(mini, ans);
                }
                dp[ind][amou] = Math.min(dp[ind - 1][amou], mini);
            }
        }
        int res = dp[coins.length - 1][amount];
        if (res == (int) 1e9)
            return -1;
        for (int tem[] : dp) {
            System.out.println(Arrays.toString(tem));
        }
        return res;
    }

    // space optimization.
    // Time complexity:O(n*amount).
    // Space complexity:O(amount).
    // Auxillary stack space:O(1).
    public static int changingSpaceOptimization(int coins[], int amount) {
        if (amount == 0) {
            return 0;
        }
        int prev[] = new int[amount + 1];
        Arrays.fill(prev, (int) 1e9);
        prev[0] = 0;
        for (int i = 1; i <= amount; i++) {
            if (i % coins[0] == 0)
                prev[i] = i / coins[0];
        }
        for (int ind = 1; ind < coins.length; ind++) {
            int current[] = new int[amount + 1];
            for (int amou = 1; amou <= amount; amou++) {
                int mini = (int) 1e9;
                if (coins[ind] <= amou) {
                    int ans = 1 + current[amou - coins[ind]];
                    mini = Math.min(mini, ans);
                }
                current[amou] = Math.min(prev[amou], mini);
            }
            prev = current;
            prev[0] = 0;
        }
        int res = prev[amount];
        if (res == (int) 1e9)
            return -1;
        return res;
    }

}
