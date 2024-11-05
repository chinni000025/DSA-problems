package RecPrac;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int ar[] = { 74, 0, 13, 9, 5, 3, 6, 36, 79 };
        quicksort(ar, 0, ar.length - 1);
        System.out.println(Arrays.toString(ar));

    }

    public static void quicksort(int ar[], int low, int high) {
        if (low >= high) {
            return;
        }
        int pp = helper(ar, low, high);
        quicksort(ar, low, pp-1);
        quicksort(ar, pp + 1, high);
    }

    public static int helper(int ar[], int low, int high) {
        int pivot = low;
        int lp = low;
        int rp = high;
        while (lp <= rp) {
            while (lp <= high && ar[lp] <= ar[pivot]) {
                lp++;
            }
            while (rp >= lp && ar[rp] >= ar[pivot]) {
                rp--;
            }
            if (lp < rp) {
                swap(ar, lp, rp);
            } else if (rp >= 0) {
                swap(ar, pivot, rp);
                return rp;
            }

        }
        return rp;
        
    }

    public static void swap(int ar[], int p1, int p2) {
        int temp = ar[p1];
        ar[p1] = ar[p2];
        ar[p2] = temp;
    }

}
