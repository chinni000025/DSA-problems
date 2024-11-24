package DP;

import java.util.Arrays;

public class KnapSack {
    public static void main(String[] args) {
        int value[] = { 1, 1 };
        int weight[] = { 2, 1 };
        int maxWeight = 3;
        System.out.println(knapSack(value, weight, maxWeight));
        System.out.println(knapSackDynamic(value, weight, maxWeight));
        System.out.println(tabulation(value, weight, maxWeight));
        System.out.println(spaceOptimization(value, weight, maxWeight));
    }

    // Recursion.
    // Time complexity:O(2^maxweight).
    // Space complexity:O(1).
    // Auxillary stack Space:O(maxWeight).
    public static int knapSack(int value[], int weight[], int maxWeight) {
        return helperRec(value.length - 1, value, weight, maxWeight);
    }

    public static int helperRec(int ind, int value[], int weight[], int maxWeight) {
        if (ind < 0) {
            return 0;
        }
        int maxi = (int) -1e9;
        if (maxWeight >= weight[ind]) {
            int ans = value[ind] + helperRec(ind, value, weight, maxWeight - weight[ind]);
            maxi = Math.max(maxi, ans);
        }
        return Math.max(helperRec(ind - 1, value, weight, maxWeight), maxi);
    }

    // Dynamic programming.
    // Time complexity:O(n*maxWeight)--> n is number of elements.
    // Space complexity:O(n+1*maxweight+1)
    // Auxillary stack space:O(maxweight).
    public static int knapSackDynamic(int value[], int weight[], int maxWeight) {
        int dp[][] = new int[value.length][maxWeight + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        int res = helperRec(value.length - 1, value, weight, maxWeight, dp);
        for (int temp[] : dp)
            System.out.println(Arrays.toString(temp));
        return res;
    }

    public static int helperRec(int ind, int value[], int weight[], int maxWeight, int dp[][]) {
        if (ind == 0) {
            if (maxWeight == 0) {
                return 0;
            }
            int sum = 0;
            while (maxWeight >= weight[0]) {
                sum += value[0];
                maxWeight -= weight[0];
            }
            return sum;
        }

        if (dp[ind][maxWeight] != -1)
            return dp[ind][maxWeight];
        int maxi = (int) -1e9;
        if (maxWeight >= weight[ind]) {
            int ans = value[ind] + helperRec(ind, value, weight, maxWeight - weight[ind], dp);
            maxi = Math.max(maxi, ans);
        }
        return dp[ind][maxWeight] = Math.max(helperRec(ind - 1, value, weight, maxWeight, dp), maxi);
    }

    // Tabulation.
    // Time complexity:O(n*maxWeight).
    // Space complexity:O((n+1)*(maxWeight+1)).
    // Auxillary stack space:O(maxWeight).
    public static int tabulation(int value[], int weight[], int maxWeight) {
        int dp[][] = new int[value.length][maxWeight + 1];
        for (int w = 1; w <= maxWeight; w++) {
            dp[0][w] = (w / weight[0]) * value[0];
        }
        for (int ind = 1; ind < value.length; ind++) {
            for (int w = 1; w <= maxWeight; w++) {
                int maxi = (int) -1e9;
                if (w >= weight[ind]) {
                    int ans = value[ind] + dp[ind][w - weight[ind]];
                    maxi = Math.max(maxi, ans);
                }
                dp[ind][w] = Math.max(dp[ind - 1][w], maxi);
            }
        }
        for (int temp[] : dp) {
            System.out.println(Arrays.toString(temp));
        }
        return dp[value.length - 1][maxWeight];
    }

    // space optimization.
    public static int spaceOptimization(int value[], int weight[], int maxWeight) {
        int prev[] = new int[maxWeight + 1];
        for (int w = 1; w <= maxWeight; w++) {
            prev[w] = (w / weight[0]) * value[0];
        }
        prev[0] = 0;
        for (int ind = 1; ind < value.length; ind++) {
            int current[] = new int[maxWeight + 1];
            for (int w = 1; w <= maxWeight; w++) {
                int maxi = (int) -1e9;
                if (w >= weight[ind]) {
                    int ans = value[ind] + current[w - weight[ind]];
                    maxi = Math.max(maxi, ans);
                }
                current[w] = Math.max(prev[w], maxi);
            }
            prev = current;
        }
        return prev[maxWeight];
    }

}
