package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LargestDivisibleSubset {
    public static void main(String[] args) {
        int ar[] = { 3, 4, 16, 8 };
        System.out.println(longestDivisibleSubSet(ar));
    }

    // Time complexity:O(nlogn)+O(n^2)+O(n). --> for sorting , for nested loops,for
    // Backtracking.
    // Space complexity:O(2*n).
    public static List<Integer> longestDivisibleSubSet(int ar[]) {
        Arrays.sort(ar);
        List<Integer> li = new ArrayList<>();
        int dp[] = new int[ar.length];
        Arrays.fill(dp, 1);
        int hash[] = new int[ar.length];
        int maxi = -1;
        int pos = -1;
        for (int i = 0; i < ar.length; i++) {
            hash[i] = i;
            for (int j = 0; j < i; j++) {
                if (ar[i] % ar[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    hash[i] = j;
                }
            }
            if (dp[i] > maxi) {
                maxi = dp[i];
                pos = i;
            }
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(hash));
        while (hash[pos] != pos) {
            li.add(ar[pos]);
            pos = hash[pos];
        }
        li.add(ar[pos]);
        Collections.reverse(li);
        return li;
    }
}
