package DP;

import java.util.Arrays;

public class MinimumFallingPathSum {
    public static void main(String[] args) {
        int matrix[][] = { { 2, 1, 3 }, { 6, 5, 4 }, { 7, 8, 9 } };
        System.out.println(findingPath(matrix));
        System.out.println(findingPathDynamic(matrix));
        System.out.println(tabulation(matrix));
        System.out.println(spaceOptimization(matrix));
    }

    // Recursive approach.
    // Time complexity:O(n*(3^m*n)).
    // Space complexity:O(1).
    // Auxillary stack space:O(m+n).
    public static int findingPath(int matrix[][]) {
        int res = (int) 1e9;
        for (int i = 0; i < matrix[0].length; i++)
            res = Math.min(res, helper(0, i, matrix));
        return res;
    }

    public static int helper(int row, int col, int matrix[][]) {
        if (row == matrix.length || col < 0 || col == matrix[0].length)
            return (int) 1e9;
        if (row == matrix.length - 1)
            return matrix[row][col];
        int down = matrix[row][col] + helper(row + 1, col, matrix);
        int leftDiagonal = matrix[row][col] + helper(row + 1, col - 1, matrix);
        int rightDiagonal = matrix[row][col] + helper(row + 1, col + 1, matrix);
        return Math.min(down, Math.min(leftDiagonal, rightDiagonal));
    }

    // Dynamic Approach.
    // Time complexity:O(n*(m*n)).
    // Space complexity:O(m*n).
    // Auxillary stack Space:O(m+n).
    public static int findingPathDynamic(int matrix[][]) {
        int dp[][] = new int[matrix.length][matrix[0].length];
        for (int temp[] : dp)
            Arrays.fill(temp, (int) 1e9);
        int res = (int) 1e9;
        for (int i = 0; i < dp[0].length; i++)
            res = Math.min(res, helper(0, i, matrix, dp));
        // for (int temp[] : dp)
        // System.out.println(Arrays.toString(temp));
        return res;
    }

    public static int helper(int row, int col, int matrix[][], int dp[][]) {
        if (row == matrix.length || col < 0 || col == matrix[0].length)
            return (int) 1e9;
        if (row == matrix.length - 1)
            return matrix[row][col];
        if (dp[row][col] != (int) 1e9)
            return dp[row][col];
        int down = matrix[row][col] + helper(row + 1, col, matrix, dp);
        int leftDiagonal = matrix[row][col] + helper(row + 1, col - 1, matrix, dp);
        int rightDiagonal = matrix[row][col] + helper(row + 1, col + 1, matrix, dp);
        return dp[row][col] = Math.min(down, Math.min(leftDiagonal, rightDiagonal));
    }

    // Tabulation.
    // Time complexity:O(n*(m*n)).
    // Space complexity:O(m*n).
    // Auxillary stack space :O(1).
    public static int tabulation(int matrix[][]) {
        int dp[][] = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < dp[0].length; i++)
            dp[0][i] = matrix[0][i];
        for (int row = 1; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                int down = matrix[row][col] + dp[row - 1][col];
                int leftDiagonal = (int) 1e9;
                if (col - 1 >= 0)
                    leftDiagonal = matrix[row][col] + dp[row - 1][col - 1];
                int rightDiagonal = (int) 1e9;
                if (col + 1 <= matrix[0].length - 1)
                    rightDiagonal = matrix[row][col] + dp[row - 1][col + 1];
                dp[row][col] = Math.min(down, Math.min(leftDiagonal, rightDiagonal));
            }
        }
        int res = (int) 1e9;
        for (int i = 0; i < dp[0].length; i++)
            res = Math.min(res, dp[dp[0].length - 1][i]);
        // for (int temp[] : dp)
        // System.out.println(Arrays.toString(temp));
        return res;
    }

    // Space optimization
    public static int spaceOptimization(int matrix[][]) {
        int prev[] = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++)
            prev[i] = matrix[0][i];
        for (int row = 1; row < matrix.length; row++) {
            int current[] = new int[matrix[0].length];
            for (int col = 0; col < matrix[0].length; col++) {
                int down = matrix[row][col] + prev[col];
                int leftDiagonal = (int) 1e9;
                if (col - 1 >= 0)
                    leftDiagonal = matrix[row][col] + prev[col - 1];
                int rightDiagonal = (int) 1e9;
                if (col + 1 <= prev.length - 1)
                    rightDiagonal = matrix[row][col] + prev[col + 1];
                current[col] = Math.min(down, Math.min(leftDiagonal, rightDiagonal));
            }
            prev = current;
        }
        int res = (int) 1e9;
        for (int i = 0; i < prev.length; i++)
            res = Math.min(res, prev[i]);
        return res;
    }
}
