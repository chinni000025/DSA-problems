package RecPrac;

public class CountingSubsequencesWithSumIsK {
    public static void main(String[] args) {
        int ar[] = { 12, 14, 3, 18, 2 };
        int sum = 13;
        System.out.println(countingSubsequence(0, ar, sum));
    }

    // Time complexity:O(2^n).
    // Space complexity:O(1).
    // Auxillary stackSpace :O(n).
    public static int countingSubsequence(int index, int ar[], int target) {
        if (index == ar.length || target < 0) {
            if (target == 0) { // until the last element of the array.
                return 1;
            }
            return 0;
        }
        int take = countingSubsequence(index + 1, ar, target - ar[index]);
        int notTake = countingSubsequence(index + 1, ar, target);
        return take + notTake;

    }
}
