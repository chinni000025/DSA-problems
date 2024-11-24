package DP;

import java.util.Arrays;

public class CherryPickUp {

    public static void main(String[] args) {
        int cheeries[][] = { { 2, 3, 1, 2 }, { 3, 4, 2, 2 }, { 5, 6, 3, 5 } };
        System.out.println(maxCherriesPickUp(cheeries));
        System.out.println(dynamicMaxCherriesPickUp(cheeries));

    }

    // Recursion approach.
    // Time complexity:O(3^n)*O(3^n).
    // space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int maxCherriesPickUp(int cherries[][]) {
        return helper(0, 0, cherries[0].length - 1, cherries);
    }

    public static int helper(int row, int aliceCol, int bobCol, int cherries[][]) {
        if (aliceCol < 0 || aliceCol == cherries[0].length || bobCol < 0 || bobCol == cherries[0].length)
            return (int) -1e9;
        if (row == cherries.length - 1) {
            if (aliceCol == bobCol)
                return cherries[row][aliceCol];
            else
                return cherries[row][aliceCol] + cherries[row][bobCol];
        }
        int maxi = (int) -1e9;
        for (int aliceDelta = -1; aliceDelta <= 1; aliceDelta++) {
            for (int bobDelta = -1; bobDelta <= 1; bobDelta++) {
                int currentCherries;
                if (aliceDelta == bobDelta) {
                    currentCherries = cherries[row][aliceCol]
                            + helper(row + 1, aliceCol + aliceDelta, bobCol + bobDelta, cherries);
                } else {
                    currentCherries = cherries[row][aliceCol] + cherries[row][bobCol]
                            + helper(row + 1, aliceCol + aliceDelta, bobCol + bobDelta, cherries);
                }
                maxi = Math.max(currentCherries, maxi);
            }
        }
        return maxi;
    }

    // Dynamic proagramming.
    // Time complexity:O(n*m*m)*9.
    // Space complexity:O(n*m*m).
    // Auxillary stack space :O(n).
    public static int dynamicMaxCherriesPickUp(int cheeries[][]) {
        int dp[][][] = new int[cheeries.length][cheeries[0].length][cheeries[0].length];
        for (int temp1[][] : dp) {
            for (int temp2[] : temp1) {
                Arrays.fill(temp2, -1);
            }
        }
        return helper(0, 0, cheeries[0].length - 1, cheeries, dp);
    }

    public static int helper(int row, int aliceCol, int bobCol, int cherries[][], int dp[][][]) {
        if (aliceCol < 0 || aliceCol == cherries[0].length || bobCol < 0 || bobCol == cherries[0].length)
            return (int) -1e9;
        if (row == cherries.length - 1) {
            if (aliceCol == bobCol)
                return cherries[row][aliceCol];
            else
                return cherries[row][aliceCol] + cherries[row][bobCol];
        }
        if (dp[row][aliceCol][bobCol] != -1) {
            return dp[row][aliceCol][bobCol];
        }
        int maxi = (int) -1e9;
        for (int aliceDelta = -1; aliceDelta <= 1; aliceDelta++) {
            for (int bobDelta = -1; bobDelta <= 1; bobDelta++) {
                int currentCherries;
                if (aliceDelta == bobDelta) {
                    currentCherries = cherries[row][aliceCol]
                            + helper(row + 1, aliceCol + aliceDelta, bobCol + bobDelta, cherries,dp);
                } else {
                    currentCherries = cherries[row][aliceCol] + cherries[row][bobCol]
                            + helper(row + 1, aliceCol + aliceDelta, bobCol + bobDelta, cherries,dp);
                }
                maxi = Math.max(currentCherries, maxi);
            }
        }
        return dp[row][aliceCol][bobCol] = maxi;
    }

}