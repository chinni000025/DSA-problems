package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class GeneratingSubSetsUsingPowerSet {
    public static void main(String[] args) {
        int ar[] = { 1, 2, 3 };
        // System.out.println(driver(ar));
        driver2(ar);
    }

    public static List<List<Integer>> driver(int ar[]) {
        List<List<Integer>> res = new ArrayList<>();
        generatingElements(ar, res, new ArrayList<>(), 0);
        return res;
    }

    // Using power Set Algorithm.
    // Time complexity:O(2^n).
    // space complexity:O(2^n*n).
    public static void generatingElements(int ar[], List<List<Integer>> res, List<Integer> temp, int comb) {
        if (comb == Math.pow(2, ar.length)) {
            return;
        }
        for (int i = 0; i < ar.length; i++) {
            int pos = comb & (1 << i);
            if (pos > 0) {
                temp.add(ar[i]);
            }
        }
        res.add(temp);
        generatingElements(ar, res, new ArrayList<>(), comb + 1);
    }

    // using pick and not pick approach
    // Time complexity:O(2^n).
    // space complexity:O(2^n*n).
    public static void driver2(int ar[]) {
        List<List<Integer>> res = new ArrayList<>();
        generatingSubSet(ar, 0, res, new ArrayList<>());
        System.out.println(res);
    }

    public static void generatingSubSet(int ar[], int index, List<List<Integer>> res, List<Integer> temp) {
        if (index == ar.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        temp.add(ar[index]);
        generatingSubSet(ar, index + 1, res, temp);
        temp.remove(temp.size() - 1);
        generatingSubSet(ar, index + 1, res, temp);

    }

}
