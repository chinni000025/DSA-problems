package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class KthPremutations {

    public static void main(String[] args) {
        System.out.println(kthPremutation(4, 17));
    }

    // Kth premutation
    // Time complexity:O(n)+O(n) loop will run n times
    // and whenever you perform the operation on middle data structure(list) it will
    // take O(n).
    // space complexity:O(n).
    public static String kthPremutation(int n, int k) {
        String res = "";
        int fact = 1;
        // zero base index.
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            fact = fact * i;
            numbers.add(i);
        }
        numbers.add(n);
        k--;
        while (true) {
            char ch = (char) (numbers.get(k / fact) + '0');
            res += ch;
            numbers.remove(k / fact);
            if (numbers.isEmpty()) {
                break;
            }
            k = k % fact;
            fact = fact / numbers.size();
        }
        return res;
    }
}