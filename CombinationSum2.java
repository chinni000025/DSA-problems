package RecPrac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
    public static void main(String[] args) {
        int ar[] = { 2, 5, 2, 1, 2};
        int target = 5;
        System.out.println(driver(ar, target));
    }

    public static List<List<Integer>> driver(int ar[], int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(ar);
        subsetSum(0, ar, target, 0, res, new ArrayList<>());
        return res;
    }

    public static void subsetSum(int index, int ar[], int target, int sum, List<List<Integer>> res,
            List<Integer> temp) {
        if (sum == target) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = index; i < ar.length; i++) {
            if (i > index && ar[i] == ar[i - 1]) {
                continue;
            }
            if (sum + ar[i] <= target) {
                temp.add(ar[i]);
                subsetSum(i + 1, ar, target, sum + ar[i], res, temp);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
