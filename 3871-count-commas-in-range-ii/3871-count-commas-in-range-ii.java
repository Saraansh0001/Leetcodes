class Solution {
    public long countCommas(long n) {

        if (n < 1000) return 0;

        long count = 0;
        long temp = n;
        long power = 1000;

        while (temp >= 1000) {

            count += n - power + 1;

            temp /= 1000;
            power *= 1000;
        }

        return count;
    }
}