package RecPrac;

import java.util.ArrayList;

public class SubSetSum {

    public static void main(String[] args) {
        int ar[] = { 2, 3 };
        System.out.println(driver(ar));
    }

    // Time complexity:O(2^n).
    // Space complexity:O(n).
    public static ArrayList<Integer> driver(int ar[]) {
        ArrayList<Integer> res = new ArrayList<>();
        subSetSum(ar, 0, res, 0);
        return res;
    }

    public static void subSetSum(int ar[], int index, ArrayList<Integer> res, int sum) {
        if (index == ar.length) {
            res.add(sum);
            return;
        }
        sum = sum + ar[index];
        subSetSum(ar, index + 1, res, sum);
        sum = sum - ar[index];
        subSetSum(ar, index + 1, res, sum);
    }
}