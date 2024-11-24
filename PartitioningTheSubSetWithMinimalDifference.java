package DP;

public class PartitioningTheSubSetWithMinimalDifference {
    public static void main(String[] args) {
        int ar[] = { 3, 1, 5, 2, 8 };
        System.out.println(findingMinimumDifference(ar));
    }

    public static int findingMinimumDifference(int ar[]) {
        int sum = 0;
        for (int ele : ar)
            sum += ele;
        boolean dp[][] = new boolean[ar.length][sum + 1];
        for (int i = 0; i < dp.length; i++)
            dp[i][0] = true;
        if (ar[0] <= sum)
            dp[0][ar[0]] = true;
        for (int i = 1; i < ar.length; i++) {
            for (int j = 1; j <= sum; j++) {
                boolean take = false;
                if (j >= ar[i])
                    take = dp[i - 1][j - ar[i]];
                boolean notTake = dp[i - 1][j];
                dp[i][j] = take || notTake;
            }
        }
        int mini = (int) 1e9;
        for (int ps1 = 1; ps1 <= sum / 2; ps1++) {
            if (dp[ar.length - 1][ps1]) {
                int ps2 = sum - ps1;
                mini = Math.min(mini, Math.abs(ps1 - ps2));
            }
        }
        return mini;
    }

}
