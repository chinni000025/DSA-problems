package DP;

import java.util.Arrays;

public class MinimumCostToAStick {
    public static void main(String[] args) {
        int cuts[] = { 1, 3, 4, 5 };
        System.out.println(cuttingRecursion(cuts, 7));
        System.out.println(dynamicCutting(cuts, 7));
        System.out.println(cuttingTabulation(cuts, 7));
    }

    // Time complexity:O(n^n)*n
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int cuttingRecursion(int cuts[], int length) {
        Arrays.sort(cuts);
        int newCuts[] = new int[cuts.length + 2];
        System.arraycopy(cuts, 0, newCuts, 1, cuts.length);
        newCuts[newCuts.length - 1] = length;
        return cutting(newCuts, 1, newCuts.length - 2);
    }

    public static int cutting(int cuts[], int i, int j) {
        if (j < i) {
            return 0;
        }
        int mini = (int) 1e9;
        for (int k = i; k <= j; k++) {
            int steps = (cuts[j + 1] - cuts[i - 1]) + cutting(cuts, i, k - 1) + cutting(cuts, k + 1, j);
            mini = Math.min(mini, steps);
        }
        return mini;
    }

    // Time complexity:O(n*n)*O(n).
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int dynamicCutting(int cuts[], int length) {
        Arrays.sort(cuts);
        int newCuts[] = new int[cuts.length + 2];
        int dp[][] = new int[newCuts.length][newCuts.length];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        System.arraycopy(cuts, 0, newCuts, 1, cuts.length);
        newCuts[newCuts.length - 1] = length;
        return helper(newCuts, 1, newCuts.length - 2, dp);
    }

    public static int helper(int cuts[], int i, int j, int dp[][]) {
        if (j < i)
            return 0;
        if (dp[i][j] != -1)
            return dp[i][j];
        int mini = (int) 1e9;
        for (int k = i; k <= j; k++) {
            int steps = (cuts[j + 1] - cuts[i - 1]) + helper(cuts, i, k - 1, dp) + helper(cuts, k + 1, j, dp);
            mini = Math.min(mini, steps);
        }
        return dp[i][j] = mini;
    }

    // Time complexity:O(n*n*n).
    // Space complexity:O(1).
    // Auxillary stack space:O(1).
    public static int cuttingTabulation(int cuts[], int length) {
        Arrays.sort(cuts);
        int newCuts[] = new int[cuts.length + 2];
        System.arraycopy(cuts, 0, newCuts, 1, cuts.length);
        newCuts[newCuts.length - 1] = length;
        int dp[][] = new int[newCuts.length][newCuts.length];
        for (int i = newCuts.length - 2; i >= 1; i--) {
            for (int j = i; j <= newCuts.length - 2; j++) {
                int mini = (int) 1e9;
                for (int k = i; k <= j; k++) {
                    int steps = (newCuts[j + 1] - newCuts[i - 1]) + dp[i][k - 1] + dp[k + 1][j];
                    mini = Math.min(mini, steps);
                }
                dp[i][j] = mini;
            }
        }
        return dp[1][dp.length - 2];

    }
}
