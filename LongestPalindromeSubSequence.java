package DP;

public class LongestPalindromeSubSequence {
    public static void main(String[] args) {
        String str1 = "bbbab";
        System.out.println(longestSubsequence(str1));
    }

    public static int longestSubsequence(String str1) {
        StringBuffer strb = new StringBuffer(str1);
        strb = strb.reverse();
        String str2 = strb.toString();
        return lcsTabulation(str1, str2);
    }

    // Tabulation.
    // Time complexity:O(n*m).
    // space complexity:O(n*m).
    // Auxillary stack space:O(1).
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
