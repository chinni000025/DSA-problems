package RecPrac;

import java.util.ArrayList;
import java.util.List;

public class PrintingAllSubsequence {
    public static void main(String[] args) {
        String str = "abc";
        printingSubsequence(0, str, new ArrayList<>());
    }

    // Using pick and not pick approach.
    // Time complexity:O(2^n).
    // Space complexity:O(n).
    // Auxillary stack space:O(n).
    public static void printingSubsequence(int index, String str, List<String> ds) {
        if (index == str.length()) {
            return;
        }
        ds.add(Character.toString(str.charAt(index)));
        System.out.println(ds);
        printingSubsequence(index + 1, str, ds);
        ds.remove(ds.size() - 1);
        printingSubsequence(index + 1, str, ds);

    }
}
