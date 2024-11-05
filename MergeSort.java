package RecPrac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {
    public static void main(String[] args) {
        int ar[] = { 74, 0, 13, 9, 5, 3, 6, 36, 79 };
        mergeSort(ar, 0, ar.length - 1);
        System.out.println(Arrays.toString(ar));
    }

    public static void mergeSort(int ar[], int low, int high) {
        if (low >= high) {
            return;
        }
        int mid = low + (high - low) / 2;
        mergeSort(ar, low, mid);
        mergeSort(ar, mid + 1, high);
        helper(ar, low, high, mid);
    }

    public static void helper(int ar[], int low, int high, int mid) {
        int p1 = low;
        int p2 = mid + 1;
        List<Integer> li = new ArrayList<>();
        while (p1 <= mid && p2 <= high) {
            if (ar[p1] < ar[p2]) {
                li.add(ar[p1++]);
            } else {
                li.add(ar[p2++]);
            }
        }
        while (p1 <= mid) {
            li.add(ar[p1++]);
        }
        while (p2 <= high) {
            li.add(ar[p2++]);
        }
        // for copy the li to the original array.
        int index = 0;
        for (int i = low; i <= high; i++) {
            ar[i] = li.get(i - low);
        }
    }
}
