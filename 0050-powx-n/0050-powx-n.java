
class Solution {
    public double myPow(double x, int n) {
        long exp = n;

        if (exp < 0) {
            exp = -exp;
            return 1.0 / powHelper(x, exp);
        }

        return powHelper(x, exp);
    }

    private double powHelper(double x, long n) {
        if (n == 0) return 1.0;

        double half = powHelper(x, n / 2);

        if (n % 2 == 0) {
            return half * half;
        }

        return x * half * half;
    }
}
