package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
    public static void main(String[] args) {
        int num[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int k = 3, n = 9;
        System.out.println(combinationSumThree(num, k, n));
    }

    // Time complexity:O(2^n).
    // Space complexity:O(n).
    public static List<List<Integer>> combinationSumThree(int nums[], int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        helper(0, nums, k, n, 0, new ArrayList<>(), res);
        return res;
    }

    public static void helper(int index, int nums[], int k, int n, int sum, List<Integer> temp,
            List<List<Integer>> res) {
        if (k == 0 && sum == n) {
            res.add(new ArrayList<>(temp));
            return;
        }
        if (index == nums.length) {
            return;
        }
        for (int i = index; i < nums.length; i++) {
            if (sum + nums[i] <= n) {
                temp.add(nums[i]);
                helper(i + 1, nums, k - 1, n, sum + nums[i], temp, res);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
