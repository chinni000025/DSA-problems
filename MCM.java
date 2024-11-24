package DP;

import java.util.Arrays;

public class MCM {
    public static void main(String[] args) {
        int ar[] = { 2, 1, 3, 4 };
        System.out.println(mcm(ar, 1, ar.length - 1));
        System.out.println(mcmDynamic(ar));
        System.out.println(mcmTabulation(ar));
    }

    // Time complexity:O(n^n)*O(n).
    // space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int mcm(int ar[], int i, int j) {
        if (i == j) {
            return 0;
        }
        int mini = (int) 1e9;
        for (int k = i; k < j; k++) {
            int steps = (ar[i - 1] * ar[k] * ar[j]) + mcm(ar, i, k) + mcm(ar, k + 1, j);
            mini = Math.min(mini, steps);
        }
        return mini;
    }

    // Dynamic programming.
    // Time complexity:O(n*n)*O(n).
    // space complexity:O(n^2)
    // Auxillary stack space:O(n).
    public static int mcmDynamic(int ar[]) {
        int dp[][] = new int[ar.length][ar.length];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        int res = helper(ar, 1, ar.length - 1, dp);
        for (int temp[] : dp)
            System.out.println(Arrays.toString(temp));
        return res;
    }

    public static int helper(int ar[], int i, int j, int dp[][]) {
        if (i == j)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int mini = (int) 1e9;
        for (int k = i; k < j; k++) {
            int steps = (ar[i - 1] * ar[k] * ar[j]) + helper(ar, i, k, dp) + helper(ar, k + 1, j, dp);
            mini = Math.min(mini, steps);
        }
        return dp[i][j] = mini;
    }

    // Tabulation.
    // Time complexity:O(n*n*n).
    // space complexity:O(n*n).
    // Auxillary stack space:O(n).
    public static int mcmTabulation(int ar[]) {
        int dp[][] = new int[ar.length][ar.length];
        for (int i = 0; i < ar.length; i++) {
            dp[i][i] = 0;
        }

        for (int i = ar.length - 1; i >= 1; i--) {
            for (int j = i + 1; j < ar.length; j++) {
                int mini = (int) 1e9;
                for (int k = i; k < j; k++) {
                    int steps = (ar[i - 1] * ar[k] * ar[j]) + dp[i][k] + dp[k + 1][j];
                    mini = Math.min(mini, steps);
                }
                dp[i][j] = mini;
            }
        }
        // for (int temp[] : dp)
        // System.out.println(Arrays.toString(temp));
        return dp[ar.length - (ar.length - 1)][ar.length - 1];
    }
}
