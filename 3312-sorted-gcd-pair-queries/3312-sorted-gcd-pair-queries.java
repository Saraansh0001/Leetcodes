class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {

        int max = 0;
        for (int x : nums) max = Math.max(max, x);

        int[] freq = new int[max + 1];
        for (int x : nums) freq[x]++;

        int[] divisible = new int[max + 1];

        // Count numbers divisible by each i
        for (int i = 1; i <= max; i++) {
            for (int j = i; j <= max; j += i) {
                divisible[i] += freq[j];
            }
        }

        long[] exact = new long[max + 1];

        // Inclusion-Exclusion:
        // exact[g] = pairs divisible by g - pairs already assigned to multiples
        for (int g = max; g >= 1; g--) {
            long c = divisible[g];
            long pairs = c * (c - 1) / 2;

            for (int m = g + g; m <= max; m += g) {
                pairs -= exact[m];
            }

            exact[g] = pairs;
        }

        long[] prefix = new long[max + 1];
        for (int i = 1; i <= max; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long q = queries[i] + 1; // prefix is 1-based

            int l = 1, r = max;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (prefix[mid] >= q)
                    r = mid;
                else
                    l = mid + 1;
            }

            ans[i] = l;
        }

        return ans;
    }
}