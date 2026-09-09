class Solution {
    public long countCommas(long n) {

        long count = 0;

        if (n >= 1_000)
            count += n - 1_000 + 1;

        if (n >= 1_000_000)
            count += n - 1_000_000 + 1;

        if (n >= 1_000_000_000)
            count += n - 1_000_000_000L + 1;

        if (n >= 1_000_000_000_000L)
            count += n - 1_000_000_000_000L + 1;

        if (n >= 1_000_000_000_000_000L)
            count += n - 1_000_000_000_000_000L + 1;

        if (n >= 1_000_000_000_000_000_000L)
            count += n - 1_000_000_000_000_000_000L + 1;

        return count;
    }
}