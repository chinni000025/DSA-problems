package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class PrintingSubsequenceWhoseSumIsEqualToK {
    public static void main(String[] args) {
        int ar[] = { 1, 2, 1 };
        int sum = 2;
        printingSubSequeceWhoseSumIsK(0, ar, sum, new ArrayList<>());
        System.out.println(checkingSubsequeceWhoseSumIsK(0, ar, sum));
    }

    // Time complexity :o(2^n).
    // Space complexity:O(n).
    // Auxillary stack space :O(n).
    public static void printingSubSequeceWhoseSumIsK(int index, int ar[], int target, List<Integer> ds) {
        if (target == 0) {
            System.out.println(ds);
            return;
        }
        if (index == ar.length || target < 0) {
            return;
        }
        ds.add(ar[index]);
        printingSubSequeceWhoseSumIsK(index + 1, ar, target - ar[index], ds);
        ds.remove(ds.size() - 1);
        printingSubSequeceWhoseSumIsK(index + 1, ar, target, ds);

    }

    // check if there exits a subsequence with sum is k.

    // Time complexity :o(2^n).
    // Space complexity:O(n).
    // Auxillary stack space :O(n).
    public static boolean checkingSubsequeceWhoseSumIsK(int index, int ar[], int target) {
        if (target == 0) {
            return true;
        }
        if (index == ar.length || target < 0) {
            return false;
        }
        boolean pick = checkingSubsequeceWhoseSumIsK(index + 1, ar, target - ar[index]);
        if (pick) {
            return true;
        }
        return checkingSubsequeceWhoseSumIsK(index + 1, ar, target);
    }
    
}
