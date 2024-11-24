package DP;

import java.util.Arrays;

public class MinimumPathSum {
    public static void main(String[] args) {
        int grid[][] = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        System.out.println(findingMinimumSum(grid));
        System.out.println(findingMinimumSumDynamic(grid));
        System.out.println(tabulation(grid));
        System.out.println(spaceOptimization(grid));
    }

    // Recursive approach.
    // Time complexity:O(2^(m*n)).
    // Space complexity:O(1).
    // Auxillary stack Space:O(m+n).
    public static int findingMinimumSum(int grid[][]) {
        return helper(0, 0, grid);
    }

    public static int helper(int row, int col, int grid[][]) {
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[row][col];
        }
        if (row == grid.length || col == grid[0].length) {
            return (int) 1e9;
        }
        int down = grid[row][col] + helper(row + 1, col, grid);
        int right = grid[row][col] + helper(row, col + 1, grid);
        return Math.min(down, right);
    }

    // Dynamic programming
    // Time complexity:O(m*n)
    // Space complexity:O(m*n).
    // Auxillary stack space:O(m+n).
    public static int findingMinimumSumDynamic(int grid[][]) {
        int dp[][] = new int[grid.length][grid[0].length];
        for (int temp[] : dp) {
            Arrays.fill(temp, -1);
        }
        int res = helper(0, 0, grid, dp);
        for (int temp[] : dp) {
            System.out.println(Arrays.toString(temp));
        }
        return res;
    }

    public static int helper(int row, int col, int grid[][], int dp[][]) {
        if (row == grid.length - 1 && col == grid[0].length - 1)
            return grid[row][col];
        if (row == grid.length || col == grid[0].length)
            return (int) 1e9;
        if (dp[row][col] != -1)
            return dp[row][col];
        int down = grid[row][col] + helper(row + 1, col, grid);
        int right = grid[row][col] + helper(row, col + 1, grid);
        return dp[row][col] = Math.min(down, right);
    }

    // Tabulation.
    // Time complexity:O(m*n).
    // Space complexity:O(m*n).
    // Auxillary stack Space:O(1).
    public static int tabulation(int grid[][]) {
        int dp[][] = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid[0].length; i++) {
            if (i > 0)
                dp[0][i] = dp[0][i - 1] + grid[0][i];
            else
                dp[0][i] = grid[0][i];
        }
        for (int row = 1; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                int down = grid[row][col] + dp[row - 1][col];
                int right = (int) 1e9;
                if (col - 1 >= 0) {
                    right = grid[row][col] + dp[row][col - 1];
                }
                dp[row][col] = Math.min(down, right);
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }

    // space Optimization.
    // Time complexity:O(m*n).
    // Space complexity:O(n).
    // Auxilllary space complexity:O(1).
    public static int spaceOptimization(int grid[][]) {
        int prev[] = new int[grid[0].length];
        for (int i = 0; i < grid[0].length; i++) {
            if (i > 0)
                prev[i] = prev[i - 1] + grid[0][i];
            else
                prev[i] = grid[0][i];
        }
        for (int row = 1; row < grid.length; row++) {
            int current[] = new int[grid[0].length];
            for (int col = 0; col < grid[0].length; col++) {
                int down = grid[row][col] + prev[col];
                int right = (int) 1e9;
                if (col - 1 >= 0) {
                    right = grid[row][col] + current[col - 1];
                }
                current[col] = Math.min(down, right);
            }
            prev = current;
        }

        return prev[grid[0].length - 1];
    }

}
