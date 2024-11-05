package RecPrac;

public class GoodNumber {
    public static void main(String[] args) {
        long n = 50;
        System.out.println(goodNumber(n));
        System.out.println(goodNumber2(n));
        
    }

    // Time complexity:O(n). --> TLE.
    // space complexity:O(1).
    public static int goodNumber(long n) {
        if (n == 1) {
            return 5;
        }
        if (n % 2 == 0) {
            return 4 * goodNumber(n - 1) % ((int) 1e9 + 7);
        } else {
            return 5 * goodNumber(n - 1) % ((int) 1e9 + 7);
        }
    }

    // Time complexity:O(log n).
    // space complexity:O(1).
    static final int mod = (int) 1e9 + 7;
    public static int goodNumber2(long n) {

        return (int) (findingPow(4, (n / 2)) * findingPow(5, (n + 1) / 2) % mod);

    }
    public static long findingPow(long num, long pow) {
        if (pow == 0)
            return 1;

        if (pow % 2 == 0) {
            if (pow < 0) {
                pow = pow * -1;
            }
            long temp = findingPow(num, pow / 2);
            return (temp * temp)%mod;

        } else {
            if (pow < 0) {
                pow *= -1;
            }
            return (num * findingPow(num, pow - 1))%mod;
        }
    }

}
