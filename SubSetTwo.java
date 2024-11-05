package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class SubSetTwo {
    public static void main(String[] args) {
        int ar[] = { 1, 2, 2 };
        System.out.println(driver(ar));
    }

    // Time complexity:O(2^n).
    // Space complexity:O(n).
    public static List<List<Integer>> driver(int ar[]) {
        List<List<Integer>> res = new ArrayList<>();
        subSet2(ar, 0, new ArrayList<>(), res);
        return res;
    }

    public static void subSet2(int ar[], int index, List<Integer> res, List<List<Integer>> fr) {
        fr.add(new ArrayList<>(res));
        for (int i = index; i < ar.length; i++) {
            if (i > index && ar[i] == ar[i - 1])
                continue;
            res.add(ar[i]);
            subSet2(ar, i + 1, res, fr);
            res.remove(res.size() - 1);
        }
    }
}
