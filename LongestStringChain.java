package DP;

import java.util.Arrays;
import java.util.Comparator;

public class LongestStringChain {
    public static void main(String[] args) {
        String words[] = { "xbc", "pcxbcf", "xb", "cxbc", "pcxbc" };
        System.out.println(longestStringChain(words));
    }

    public static int longestStringChain(String words[]) {
        Arrays.sort(words, Comparator.comparing(s -> s.length()));
        System.out.println(Arrays.toString(words));
        int dp[] = new int[words.length];
        int maxi = -1;
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < i; j++) {
                if (isPredecessor(words[j], words[i]) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
            }
        }

        return maxi;
    }

    public static boolean isPredecessor(String str1, String str2) {
        if (str1.length() == str2.length()) {
            return false;
        } else if (str1.length() + 1 != str2.length()) {
            return false;
        }
        int i = 0, j = 0;
        int count = 0;
        while (i < str1.length() && j < str2.length()) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
                count++;
            }
        }
        if (j == str2.length() - 1) {
            return true;
        }
        return count == 1;
    }
}
