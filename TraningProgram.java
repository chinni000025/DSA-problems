import java.util.Arrays;

public class TraningProgram {
    public static void main(String[] args) {
        int mat[][] = { { 8, 7, 3 }, { 10, 6, 3 }, { 1, 1, 4 }, { 10, 2, 9 }, { 2, 10, 6 }, { 4, 3, 6 }, { 3, 6, 9 },
                { 7, 8, 8 }, { 3, 3, 10 }, { 5, 2, 10 } };
        // System.out.println(maxiProfitOnTraning(mat));
        System.out.println(maxiProfitOnTraningDp(mat));
        System.out.println(tabulation(mat));
        System.out.println(spaceoptimization(mat));
    }

    // Recursive approach.
    // Time complexity:O(3^n)*3;
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int maxiProfitOnTraning(int mat[][]) {
        int t1 = maxProfit(mat.length - 1, mat, 2);
        int t2 = maxProfit(mat.length - 1, mat, 1);
        int t3 = maxProfit(mat.length - 1, mat, 0);
        return Math.max(t1, Math.max(t2, t3));
    }

    public static int maxProfit(int day, int mat[][], int prevTask) {
        if (day == 0) {
            int maxProfit = 0;
            for (int i = 0; i < 3; i++) {
                if (i != prevTask) {
                    maxProfit = Math.max(maxProfit, mat[day][i]);
                }
            }
            return maxProfit;
        }
        int maxProfit = 0;
        for (int i = 0; i < 3; i++) {
            if (i != prevTask) {
                maxProfit = Math.max(maxProfit, mat[day][i] + maxProfit(day - 1, mat, i));
            }
        }
        return maxProfit;
    }

    // Dynamic programming.
    // Time complexity:O(n)*3.
    // Space complexity:O(day*3).
    // Auxillary stack space:O(n).
    public static int maxiProfitOnTraningDp(int mat[][]) {
        int dp[][] = new int[mat.length][3];
        for (int d[] : dp) {
            Arrays.fill(d, -1);
        }
        int t1 = helper(mat.length - 1, mat, 2, dp);
        int t2 = helper(mat.length - 1, mat, 1, dp);
        int t3 = helper(mat.length - 1, mat, 0, dp);
        int res = Math.max(t1, Math.max(t2, t3));

        for (int d[] : dp) {
            System.out.println(Arrays.toString(d));
        }
        return res;
    }

    public static int helper(int day, int mat[][], int prevTask, int dp[][]) {
        if (dp[day][prevTask] != -1) {
            return dp[day][prevTask];
        }
        if (day == 0) {
            int maxi = 0;
            for (int i = 2; i >= 0; i--) {
                if (i != prevTask) {
                    maxi = Math.max(maxi, mat[day][i]);
                }
            }
            return dp[day][prevTask] = maxi;
        }
        int maxi = 0;
        for (int i = 2; i >= 0; i--) {
            if (i != prevTask) {
                maxi = Math.max(maxi, mat[day][i] + helper(day - 1, mat, i, dp));
            }
        }
        return dp[day][prevTask] = maxi;
    }

    // Tabulation.
    // Time complexity:O(3*n).
    // space complexity:O(3*n).
    // Auxillary stack space:O(1).
    public static int tabulation(int mat[][]) {
        int dp[][] = new int[mat.length][3];
        for (int i = 0; i < 3; i++) {
            dp[0][i] = mat[0][i];
        }
        for (int day = 1; day < mat.length; day++) {
            for (int prevTask = 0; prevTask < 3; prevTask++) {
                int maxi = 0;
                for (int i = 0; i < 3; i++) {
                    if (i != prevTask) {
                        int res = mat[day][prevTask] + dp[day - 1][i];
                        maxi = Math.max(maxi, res);
                    }
                }
                dp[day][prevTask] = maxi;
            }
        }
        return Math.max(dp[mat.length - 1][0], Math.max(dp[mat.length - 1][1], dp[mat.length - 1][2]));
    }

    // space optimization.
    // Time complexity:O(3*n).
    // space complexity:O(n).
    // Auxillary stack space:O(1).
    public static int spaceoptimization(int mat[][]) {
        int prevArray[] = new int[3];
        for (int i = 0; i < 3; i++) {
            prevArray[i] = mat[0][i];
        }
        for (int day = 1; day < mat.length; day++) {
            int current[] = new int[3];

            for (int prevTask = 0; prevTask < 3; prevTask++) {

                int maxi = 0;

                for (int i = 0; i < 3; i++) {
                    if (i != prevTask) {
                        int res = mat[day][prevTask] + prevArray[i];
                        maxi = Math.max(maxi, res);
                    }
                }
                current[prevTask] = maxi;
            }
            prevArray = current;
        }

        System.out.println(Arrays.toString(prevArray));
        return Math.max(prevArray[0], Math.max(prevArray[1], prevArray[2]));
    }

}
