package DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrianglePathSum {

    public static void main(String[] args) {
        List<List<Integer>> li = new ArrayList<>();
        li.add(new ArrayList<>());
        li.add(new ArrayList<>());
        li.add(new ArrayList<>());
        li.add(new ArrayList<>());
        li.get(0).add(2);
        li.get(1).add(3);
        li.get(1).add(4);
        li.get(2).add(6);
        li.get(2).add(5);
        li.get(2).add(7);
        li.get(3).add(4);
        li.get(3).add(1);
        li.get(3).add(8);
        li.get(3).add(3);
        System.out.println(miniPathSum(li));
        System.out.println(miniPathSumDynamic(li));
        System.out.println(tabulation(li));
        System.out.println(spaceOptimization(li));
    }

    // Recursion.
    // Time complexity:O(2^(n*m)). { where n is list Size. and m is width of the
    // triangle.}
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static int miniPathSum(List<List<Integer>> li) {
        return helper(0, 0, li);
    }

    public static int helper(int row, int index, List<List<Integer>> li) {
        if (row == li.size())
            return 0;
        int sameIndex = li.get(row).get(index) + helper(row + 1, index, li);
        int nextIndex = li.get(row).get(index) + helper(row + 1, index + 1, li);
        return Math.min(sameIndex, nextIndex);

    }

    // Dynamic programming. { where n is list Size.}
    // Time complexity:O(n).
    // Space complexity:O(n*m).
    // Auxillary stack space:O(n).
    // note : its better to use 2d array instead of list of list for the dp table ,
    // if you are using list of list
    // it might be give memory limit exceed error.
    public static int miniPathSumDynamic(List<List<Integer>> li) {
        int dp[][] = new int[li.size()][li.size()];
        for (int temp[] : dp)
            Arrays.fill(temp, -1);
        return helper(0, 0, li, dp);
    }

    public static int helper(int row, int index, List<List<Integer>> li, int[][] dp) {
        if (row == li.size())
            return 0;
        if (dp[row][index] != -1)
            return dp[row][index];
        int sameIndex = li.get(row).get(index) + helper(row + 1, index, li, dp);
        int nextIndex = li.get(row).get(index) + helper(row + 1, index + 1, li, dp);
        return dp[row][index] = Math.min(sameIndex, nextIndex);
    }

    // Tabulation.
    // Time complexity:O(n).
    // Space complexity:O(n*m).
    // Auxillary stack space:O(1).
    public static int tabulation(List<List<Integer>> li) {
        int dp[][] = new int[li.size()][li.size()];
        dp[0][0] = li.get(0).get(0);
        for (int row = 1; row < li.size(); row++) {
            for (int index = 0; index < li.get(row).size(); index++) {
                int sameIndex = (int) 1e9;
                if (index < li.get(row - 1).size())
                    sameIndex = li.get(row).get(index) + dp[row - 1][index];
                int nextIndex = (int) 1e9;
                if (index - 1 >= 0)
                    nextIndex = li.get(row).get(index) + dp[row - 1][index - 1];
                dp[row][index] = Math.min(sameIndex, nextIndex);
            }
        }
        int mini = (int) 1e9;
        for (int i = 0; i < li.get(li.size() - 1).size(); i++)
            mini = Math.min(mini, dp[li.size() - 1][i]);
        return mini;
    }

    // Space optimization.
    // Time complexity:O(n).
    // Space complexity:O(n).
    // Auxillary stack space:O(1).
    public static int spaceOptimization(List<List<Integer>> li) {
        int prev[] = new int[li.get(li.size() - 1).size()];
        prev[0] = li.get(0).get(0);
        for (int row = 1; row < li.size(); row++) {
            int current[] = new int[li.size()];
            for (int index = 0; index < li.get(row).size(); index++) {
                int sameIndex = (int) 1e9;
                if (index < li.get(row - 1).size())
                    sameIndex = li.get(row).get(index) + prev[index];
                int nextIndex = (int) 1e9;
                if (index - 1 >= 0)
                    nextIndex = li.get(row).get(index) + prev[index - 1];
                current[index] = Math.min(sameIndex, nextIndex);
            }
            prev = current;
        }
        int mini = (int) 1e9;
        for (int i = 0; i < li.get(li.size() - 1).size(); i++)
            mini = Math.min(mini, prev[i]);
        return mini;
    }

}