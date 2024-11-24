package DP;

import java.util.Arrays;

public class NumberOfLIS {
    public static void main(String[] args) {
        int ar[] = { 2, 2, 2, 2, 2 };
        System.out.println(numberOfLIS(ar));
    }

    public static int numberOfLIS(int ar[]) {
        int dp[] = new int[ar.length];
        Arrays.fill(dp, 1);
        int count[] = new int[ar.length];
        Arrays.fill(count, 1);
        int maxIndex = 0;
        for (int i = 1; i < ar.length; i++) {
            for (int j = 0; j < i; j++) {
                if (ar[i] > ar[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    count[i] = count[j];
                } else if (ar[i] > ar[j] && dp[j] + 1 == dp[i]) {
                    count[i] += count[j];
                }
            }
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(count));
        int lis = dp[maxIndex];
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == lis) {
                res += count[i];
            }
        }
        return res;
    }
}
