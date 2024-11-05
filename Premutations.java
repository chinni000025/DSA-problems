package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class Premutations {
    public static void main(String[] args) {
        int ar[] = { 1, 2, 3 };
        approach1(ar);
        approach2(ar);
    }

    // Approach 1.
    // Time complexity :O(2^n).
    // Space complexity:O(n) --> for extra check array.
    // Auxillary stack space:O(n).
    public static void approach1(int ar[]) {
        List<List<Integer>> res = new ArrayList<>();
        boolean check[] = new boolean[ar.length];
        helper1(ar, 0, check, res, new ArrayList<>());
        System.out.println(res);
    }

    public static void helper1(int ar[], int index, boolean check[], List<List<Integer>> res, List<Integer> temp) {
        if (temp.size() == ar.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < ar.length; i++) {
            if (!check[i]) {
                temp.add(ar[i]);
                check[i] = true;
                helper1(ar, i + 1, check, res, temp);
                temp.remove(temp.size() - 1);
                check[i] = false;
            }
        }
    }

    // Approach 2
    // Most optimized technique --> swapping technique.
    // Time complexity:O(n!)*O(n).
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static List<List<Integer>> approach2(int ar[]) {
        List<List<Integer>> res = new ArrayList<>();
        helper2(0, ar, res);
        return res;
    }

    public static void helper2(int index, int ar[], List<List<Integer>> res) {
        if (index == ar.length) {
            List<Integer> li = new ArrayList<>();
            for (int temp : ar) {
                li.add(temp);
            }
            res.add(li);
        }
        for (int i = index; i < ar.length; i++) {
            swap(ar, i, index);
            helper2(index + 1, ar, res);
            swap(ar, i, index); // for backTracking.
        }

    }

    public static void swap(int ar[], int p1, int p2) {
        int temp = ar[p1];
        ar[p1] = ar[p2];
        ar[p2] = temp;
    }

}
