package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class CombinationsSum1 {
    public static void main(String[] args) {
        int ar[] = { 2, 3, 5 };
        int target = 8;
        List<List<Integer>> res = new ArrayList<>();
        System.out.println(driver(ar, target));
    }

    // Time complexity:O(2^n).
    // Space complexity:O(n).
    public static List<List<Integer>> driver(int ar[], int target) {
        List<List<Integer>> res = new ArrayList<>();
        combinationSum(0, ar, target, 0, new ArrayList<>(), res);
        return res;
    }

    public static void combinationSum(int index, int ar[], int target, int sum, List<Integer> ds,
            List<List<Integer>> res) {
        if (index == ar.length) {
            if (sum == target) {
                res.add(new ArrayList<>(ds));
            }
            return;
        }
        if (sum > target) {
            return;
        }
        ds.add(ar[index]);
        combinationSum(index, ar, target, sum + ar[index], ds, res);
        ds.remove(ds.size() - 1);
        combinationSum(index + 1, ar, target, sum, ds, res);

    }
}
