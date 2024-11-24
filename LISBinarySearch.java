package DP;

import java.util.ArrayList;
import java.util.List;

public class LISBinarySearch {
    public static void main(String[] args) {
        int ar[] = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15 };
        System.out.println(lisLength(ar));
    }

    // Time complexity:O(nlong).
    // space complexity:O(n).
    public static int lisLength(int ar[]) {
        List<Integer> li = new ArrayList<>();
        li.add(ar[0]);
        for (int i = 1; i < ar.length; i++) {
            int index = inserting(ar[i], li);
            if (index >= li.size()) {
                li.add(ar[i]);
            } else {
                li.set(index, ar[i]);
            }
        }
        System.out.println(li);
        return li.size();
    }

    public static int inserting(int ele, List<Integer> li) {
        int low = 0, high = li.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (li.get(mid) < ele) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high + 1;
    }
}
