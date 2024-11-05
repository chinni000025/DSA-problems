package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class GeneratingBinaryNumbers {
    public static void main(String[] args) {
        // System.out.println(generatingBinaryNumbers(3));
        System.out.println(generating(3));
    }

    // Time complexity:O(2^n *n).
    // Space complexity:O(2^n) --> helper 1 .
    // and less

    // than O(2^n) for helper2.

    public static List<String> generatingBinaryNumbers(int n) {
        List<String> res = new ArrayList<>();
        helper2(n, 0, res);
        return res;
    }

    // generating all binary numbers
    public static void helper(int n, int combinations, List<String> res) {
        if (combinations == (int) Math.pow(2, n)) {
            return;
        }
        String temp = "";
        for (int j = n - 1; j >= 0; j--) {
            int bit = combinations & (1 << j);
            if (bit > 0)
                temp += "1";
            else
                temp += "0";
        }
        res.add(temp);
        helper(n, combinations + 1, res);
    }

    // no consecutive ones.
    public static void helper2(int n, int combinations, List<String> res) {
        if (combinations == (int) Math.pow(2, n)) {
            return;
        }
        String temp = "";
        for (int j = n - 1; j >= 0; j--) {
            int bit = combinations & (1 << j);
            if (bit > 0) {
                if (temp.length() > 0 && temp.charAt(temp.length() - 1) == '1') {
                    break;
                } else {
                    temp += "1";
                }
            } else {
                temp += "0";
            }
        }
        if (temp.length() == n) {
            res.add(temp);
        }
        helper2(n, combinations + 1, res);
    }

    // optimized code. -->
    // Time complexity:O(2^n).
    // Space complexity:O(1).
    // Auxillary stack Space :O(2^n).
    public static List<String> generating(int n) {
        List<String> res = new ArrayList<>();
        optimizedHelper(n, "0", res);
        optimizedHelper(n, "1", res);
        return res;
    }

    public static void optimizedHelper(int len, String bin, List<String> res) {
        if (bin.length() == len) {
            res.add(bin);
            return;
        }
        if (bin.charAt(bin.length() - 1) == '0') {
            optimizedHelper(len, bin + "0", res);
            optimizedHelper(len, bin + "1", res);
        }
        if (bin.charAt(bin.length() - 1) == '1') {
            optimizedHelper(len, bin + "0", res);
        }
    }
}
