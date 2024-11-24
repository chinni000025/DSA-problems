import java.util.Arrays;

public class UniquePaths {
    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        System.out.println(uniquePaths(m, n));
        System.out.println(tabulation(m, n));
        System.out.println(spaceOptimizatin(m, n));
    }

    // Dynamic programming.
    // Time complexity:O(m*n).
    // space complexity:O(m*n);
    // Auxillary stack space:O(m+n).
    public static int uniquePaths(int m, int n) {
        int mat[][] = new int[m][n];
        mat[m - 1][n - 1] = 1;
        int res = helper(0, 0, mat);
        for (int temp[] : mat) {
            System.out.println(Arrays.toString(temp));
        }
        return res;
    }

    public static int helper(int row, int col, int mat[][]) {
        if (row >= mat.length || col >= mat[0].length) {
            return 0;
        }
        if (mat[row][col] != 0) {
            return mat[row][col];
        }
        int down = helper(row + 1, col, mat);
        int right = helper(row, col + 1, mat);
        return mat[row][col] = down + right;
    }

    // Tabulation.
    // Time complexity:O(m*n).
    // Space complexity:O(m*n).
    // Auxillary stack Space :O(1).
    public static int tabulation(int m, int n) {
        int dp[][] = new int[m][n];
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
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
        for (int temp[] : dp) {
            System.out.println(Arrays.toString(temp));
        }
        return dp[m - 1][n - 1];
    }

    // Space optimization.
    // Time complexity:O(m*n).
    // Space complexity:O(n).
    // Auxillary stack space:O(1).
    public static int spaceOptimizatin(int m, int n) {
        int prev[] = new int[n];
        Arrays.fill(prev, 1);
        for (int i = 1; i < m; i++) {
            int current[] = new int[n];
            for (int j = 0; j < n; j++) {
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
        return prev[n - 1];
    }
}
