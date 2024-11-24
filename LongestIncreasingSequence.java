package DP;

import java.util.Arrays;

public class LongestIncreasingSequence {

    public static void main(String[] args) {
        int ar[] = { 10, 9, 2, 5, 3, 7, 101, 18 };
        System.out.println(countingLIS(0, ar, -1));
        System.out.println(countingLISDynamic(ar));
        System.out.println(countingLISTabulation(ar));
        System.out.println(spaceOptimization(ar));
    }

    // Recursion approach.
    // Time complexity:O(2^n).
    // space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int countingLIS(int ind, int ar[], int prevIndex) {
        if (ind == ar.length) {
            return 0;
        }
        int take = 0;
        if (prevIndex == -1 || ar[ind] > ar[prevIndex]) {
            take = 1 + countingLIS(ind + 1, ar, ind);
        }
        int nottake = countingLIS(ind + 1, ar, prevIndex);
        return Math.max(take, nottake);
    }

    // Dynamic programming.
    // Time complexity:O(n*n).
    // Space complexity:O(n)
    // Auxillary stack space:O(n).
    public static int countingLISDynamic(int ar[]) {
        int dp[][] = new int[ar.length][ar.length + 1];
        for (int temp[] : dp)
            Arrays.fill(temp, (int) -1e9);
        int res = helper(0, ar, -1, dp);
        return res;
    }

    public static int helper(int ind, int ar[], int prevIndex, int dp[][]) {
        if (ind == ar.length)
            return 0;
        if (dp[ind][prevIndex + 1] != (int) -1e9)
            return dp[ind][prevIndex + 1];
        int take = 0;
        if (prevIndex == -1 || ar[ind] > ar[prevIndex]) {
            take = 1 + helper(ind + 1, ar, ind, dp);
        }
        int nottake = helper(ind + 1, ar, prevIndex, dp);
        return dp[ind][prevIndex + 1] = Math.max(take, nottake);
    }

    // Tabulation.
    // Time complexity:O(n*n).
    // Space complexity:O(n*n).
    // Auxillary stack space:O(1).
    public static int countingLISTabulation(int ar[]) {
        int dp[][] = new int[ar.length + 1][ar.length + 1];
        for (int ind = ar.length - 1; ind >= 0; ind--) {
            for (int prevIndex = ind - 1; prevIndex >= -1; prevIndex--) {
                int take = 0;
                if (prevIndex == -1 || ar[ind] > ar[prevIndex]) {
                    take = 1 + dp[ind + 1][ind + 1];
                }
                int nottake = dp[ind + 1][prevIndex + 1];
                dp[ind][prevIndex + 1] = Math.max(take, nottake);
            }
        }
        return dp[0][0];
    }

    // Space optimization.
    // Time complexity:O(n*n).
    // space complexity:O(2*n).
    public static int spaceOptimization(int ar[]) {
        int next[] = new int[ar.length + 1];
        for (int i = ar.length - 1; i >= 0; i--) {
            int current[] = new int[ar.length + 1];
            for (int prev = i - 1; prev >= -1; prev--) {
                int take = 0;
                if (prev == -1 || ar[i] > ar[prev]) {
                    take = 1 + next[i + 1];
                }
                int notTake = next[prev + 1];
                current[prev + 1] = Math.max(take, notTake);
            }
            next = current;
        }
        return next[0];
    }

   
    
}