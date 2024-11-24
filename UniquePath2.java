import java.util.Arrays;

public class UniquePath2 {
    public static void main(String[] args) {
        int grid[][] = { { 0, 0, 0, 0 }, { 0, 1, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 1, 0 }, { 0, 0, 0, 0 } };
        System.out.println(gridPathSummation(grid));
        System.out.println(tabulation(grid));
        System.out.println(spaceOptimizatin(grid));
    }

    // Dynamic programming.
    // Time complexity:O(m*n).
    // space complexity:O(m*n);
    // Auxillary stack space:O(m+n).
    public static int gridPathSummation(int grid[][]) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1)
            return 0;
        int res = helper(0, 0, grid, new int[grid.length][grid[0].length]);

        return res;
    }

    public static int helper(int row, int col, int grid[][], int dp[][]) {
        if (row == grid.length - 1 && col == grid[0].length - 1)
            return 1;
        if (row == grid.length || col == grid[0].length || grid[row][col] == 1)
            return 0;
        if (dp[row][col] != 0)
            return dp[row][col];
        int down = helper(row + 1, col, grid, dp);
        int right = helper(row, col + 1, grid, dp);
        return dp[row][col] = down + right;
    }

    // Tabulation.
    // Time complexity:O(m*n).
    // Space complexity:O(m*n).
    // Auxillary stack Space :O(1).
    public static int tabulation(int grid[][]) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1)
            return 0;
        int dp[][] = new int[grid.length][grid[0].length];
        dp[0][0] = 1;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i == 0 && j == 0 || grid[i][j] == 1) {
                    continue;
                }
                int down = 0;
                if (i - 1 >= 0) {
                    down = dp[i - 1][j];
                }
                int right = 0;
                if (j - 1 >= 0) {
                    right = dp[i][j - 1];
                }
                dp[i][j] = down + right;
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }

    // space optimization.
    // Time complexity:O(m*n).
    // Space complexity:O(n).
    // Auxillary stack space:O(1).
    public static int spaceOptimizatin(int grid[][]) {
        if (grid[0][0] == 1 || grid[grid.length - 1][grid[0].length - 1] == 1)
            return 0;
        int[] prev = new int[grid[0].length];
        prev[0] = grid[0][0] == 0 ? 1 : 0;
        for (int j = 1; j < grid[0].length; j++) {
            if (grid[0][j] == 0 && prev[j - 1] == 1) {
                prev[j] = 1;
            } else {
                prev[j] = 0;
            }
        }
        for (int i = 1; i < grid.length; i++) {
            int current[] = new int[grid[0].length];
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    continue;
                }
                int down = prev[j];
                int right = 0;
                if (j - 1 >= 0) {
                    right = current[j - 1];
                }
                current[j] = down + right;

            }
            prev = current;
            System.out.println(Arrays.toString(prev));
        }
        return prev[grid[0].length - 1];
    }
}
