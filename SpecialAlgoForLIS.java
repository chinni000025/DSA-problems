package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SpecialAlgoForLIS {
    public static void main(String[] args) {
        int ar[] = { 0, 10, 9, 2, 5, 3, 7, 101, 18 };
        System.out.println(printingLIS(ar));
    }

    public static int printingLIS(int ar[]) {
        int dp[] = new int[ar.length];
        Arrays.fill(dp, 1);
        int hash[] = new int[ar.length];
        int maxi = -1;
        int pos = -1;
        for (int i = 0; i < ar.length; i++) {
            hash[i] = i;
            for (int j = 0; j < i; j++) {
                if (ar[i] > ar[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    hash[i] = j;
                }
            }
            if (maxi < dp[i]) {
                maxi = dp[i];
                pos = i;
            }
        }
        // System.out.println(Arrays.toString(dp));
        // System.out.println(Arrays.toString(hash));
        ArrayList<Integer> li = new ArrayList<Integer>();
        while (hash[pos] != pos) {
            li.add(ar[pos]);
            pos = hash[pos];
        }
        li.add(ar[pos]);
        Collections.reverse(li);
        System.out.println(li);
        return maxi;
    }

    // Special type of algorithm. --> for find out the length of the LIS.
    // Time complexity:O(n^2).
    // Space complexity:O(2*n).
    public static int lis(int ar[]) {
        int dp[] = new int[ar.length];
        int maxi = (int) -1e9;
        Arrays.fill(dp, 1);
        for (int i = 0; i < ar.length; i++) {
            int temp = dp[i];
            for (int j = 0; j < i; j++) {
                if (ar[i] > ar[j]) {
                    temp = Math.max(temp, dp[j] + 1);
                }
            }
            dp[i] = temp;
            maxi = Math.max(maxi, temp);
        }
        System.out.println(Arrays.toString(dp));
        return maxi;
    }

    // Time complexity:O(n^2)+O(n).
    // Space complexity:O(2*n).

}
