package DP;

public class CountingSquareSubMatricesWithAllOnes {

    public static void main(String[] args) {
        int matrix[][] = { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } };
        System.out.println(counting(matrix));
    }

    public static int counting(int matrix[][]) {
        int dp[][] = new int[matrix.length][matrix[0].length];
        int count = 0;
        for (int i = 0; i < matrix[0].length; i++) {
            dp[0][i] = matrix[0][i];
            count += dp[0][i];
        }
        for (int i = 1; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0];
            count += dp[i][0];
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
                count += dp[i][j];
            }
        }
        return count;
    }
}