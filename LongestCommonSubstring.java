package DP;

public class LongestCommonSubstring {

    public static void main(String[] args) {
        String str1 = "tyfg";
        String str2 = "cvbnuty";
        System.out.println(lcsTabulation(str1, str2));
    }

    public static int lcsTabulation(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        int count = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    count = Math.max(dp[i][j], count);
                } else
                    dp[i][j] = 0;
            }
        }
        return count;
    }
}