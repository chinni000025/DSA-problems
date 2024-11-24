package DP;

public class MinimumInsertionToMakeStringPalindrome {
    public static void main(String[] args) {
        String str = "leetcode";
        System.out.println(minimumInsertionToMakeStringPalindrome(str));
    }

    public static int minimumInsertionToMakeStringPalindrome(String str) {
        StringBuffer strb = new StringBuffer(str);
        strb = strb.reverse();
        return str.length() - lcsTabulation(str, strb.toString());
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
