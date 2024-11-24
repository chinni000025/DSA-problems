package DP;

public class LongestBitonicSubsequence {

    public static void main(String[] args) {
        int ar[] = { 2, 44, 38, 15, 21 };
        System.out.println(longestBitonicSubsequence(ar));
    }

    // Time complexity:O(n^2)+O(n^2)+O(n).
    // space complexity:O(n)+o(n).
    public static int longestBitonicSubsequence(int ar[]) {
        int dp1[] = new int[ar.length];
        for (int i = 0; i < ar.length; i++) {
            dp1[i] = 1;
            for (int j = 0; j < i; j++) {
                if (ar[i] > ar[j] && dp1[j] + 1 > dp1[i]) {
                    dp1[i] = dp1[j] + 1;
                }
            }
        }
        int dp2[] = new int[ar.length];
        for (int i = ar.length - 1; i >= 0; i--) {
            dp2[i] = 1;
            for (int j = ar.length - 1; j > i; j--) {
                if (ar[i] > ar[j] && dp2[j] + 1 > dp2[i]) {
                    dp2[i] = dp2[j] + 1;
                }
            }
        }
        // System.out.println(Arrays.toString(dp1));
        // System.out.println(Arrays.toString(dp2));
        int maxi = 0;
        for (int i = 0; i < ar.length; i++) {
            maxi = Math.max(maxi, (dp1[i] + dp2[i]) - 1);
        }
        return maxi;
    }
}