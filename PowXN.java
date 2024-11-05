package RecPrac;

public class PowXN {
    public static void main(String[] args) {
        int num = 2;
        int pow = -2;
        double res = findingPow(num, pow);
        if (pow < 0) {
            res = 1 / res;
        }
        System.out.println(res);

    }
    // Time complexity:O(log n).
    // Space complexity:O(1).

    public static int findingPow(int num, int pow) {
        if (pow == 0)
            return 1;

        if (pow % 2 == 0) {
            if (pow < 0) {
                pow = pow * -1;
            }
            int temp = findingPow(num, pow / 2);
            return temp * temp;

        } else {
            if (pow < 0) {
                pow *= -1;
            }
            return num * findingPow(num, pow - 1);
        }
    }
}
