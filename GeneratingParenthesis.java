package RecPrac;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GeneratingParenthesis {
    public static void main(String[] args) {
        int n = 3;
        System.out.println(driver(n));
        System.out.println(driver2(n));

    }

    // First approach.



    
    // Time complexity:O(2*n)+O(2*n).
    // Space complexity:O(2*n) --> for stack in validOrNot Method.
    public static List<String> driver(int n) {
        List<String> res = new ArrayList<>();
        generating("", 0, n, res);
        return res;
    }

    public static void generating(String temp, int n, int ran, List<String> res) {
        if (n == ran * 2) {
            if (validOrNot(temp)) {
                res.add(temp);
            }
            return;
        }
        generating(temp + "(", n + 1, ran, res);
        generating(temp + ")", n + 1, ran, res);
    }

    public static boolean validOrNot(String str) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                st.push('(');
            } else {
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
        }
        return st.isEmpty();
    }

    // Second approach.
    // Optimised once 2ms Time complexity on leetcode.
    // Time complexity:O(2^n).
    // Space complexity:O(n) nearly about.
    // Auxillary stack space :O(n)
    public static List<String> driver2(int n) {
        List<String> res = new ArrayList<>();
        generating2(n, n, res, "");
        return res;
    }

    public static void generating2(int closing, int opening, List<String> res, String str) {
        if (closing == 0 && opening == 0) {
            res.add(str);
            return;
        }
        if (closing < 0 || opening < 0) {
            return;
        }
        if (opening > closing) {
            return;
        }
        generating2(closing, opening - 1, res, str + "(");
        generating2(closing - 1, opening, res, str + ")");
    }

}
