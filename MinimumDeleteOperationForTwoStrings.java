package DP;

public class MinimumDeleteOperationForTwoStrings {
    public static void main(String[] args) {
        String str1 = "mart";
        String str2 = "karma";
        System.out.println(minimumDeletionToMakeStringEqual(str1, str2));
    }

    public static int minimumDeletionToMakeStringEqual(String str1, String str2) {
        int res = lcsTabulation(str1, str2);
        return ((str1.length() - res) + (str2.length() - res));
    }

    public static int lcsTabulation(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[str1.length()][str2.length()];
    }
}
