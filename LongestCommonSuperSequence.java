package DP;

public class LongestCommonSuperSequence {

    public static void main(String[] args) {
        String str1 = "brute";
        String str2 = "groot";
        System.out.println(commonSuperSequence(str1, str2));
    }

    public static String commonSuperSequence(String str1, String str2) {
        int dp[][] = new int[str1.length() + 1][str2.length() + 1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1))
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        StringBuffer strb = new StringBuffer();
        int i = dp.length - 1, j = dp[0].length - 1;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                strb.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j - 1] < dp[i - 1][j]) {
                strb.append(str1.charAt(i - 1));
                i--;
            } else {
                strb.append(str2.charAt(j - 1));
                j--;
            }
        }
        while (i > 0) {
            strb.append(str1.charAt(i - 1));
            i--;
        }
        while (j > 0) {
            strb.append(str2.charAt(j - 1));
            j--;
        }
        strb = strb.reverse();
        return strb.toString();
    }
}