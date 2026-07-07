class Solution {
    public long sumAndMultiply(int n) {

        long x = 0;
        long x1 = 0;
        int sum = 0;

        while (n > 0) {
            int a = n % 10;

            x1 *= 10;
            x1 += a;      // Store the reversed number

            sum += a;
            n /= 10;
        }

        while (x1 > 0) {
            int a = (int)(x1 % 10);

            if (a != 0) {
                x *= 10;
                x += a;
            }

            x1 /= 10;     // Move to the next digit
        }

        return x * sum;
    }
}