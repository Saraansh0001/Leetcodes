class Solution {

    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();

        // Prefix sum of digits
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (s.charAt(i) - '0');
        }

        // Store non-zero digits and their positions
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') cnt++;
        }

        int[] pos = new int[cnt];
        int[] digit = new int[cnt];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '0') {
                pos[idx] = i;
                digit[idx] = s.charAt(i) - '0';
                idx++;
            }
        }

        // Powers of 10
        long[] pow = new long[cnt + 1];
        pow[0] = 1;
        for (int i = 1; i <= cnt; i++) {
            pow[i] = (pow[i - 1] * 10) % MOD;
        }

        // Prefix hash of concatenated non-zero digits
        long[] hash = new long[cnt + 1];
        for (int i = 1; i <= cnt; i++) {
            hash[i] = (hash[i - 1] * 10 + digit[i - 1]) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            int L = left + 1;
            int R = right + 1;
            int len = R - L + 1;

            long x = (hash[R] - (hash[L - 1] * pow[len]) % MOD + MOD) % MOD;

            long sum = prefix[r + 1] - prefix[l];

            ans[i] = (int) ((x * sum) % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] < target)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }

    private int upperBound(int[] arr, int target) {
        int lo = 0, hi = arr.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (arr[mid] <= target)
                lo = mid + 1;
            else
                hi = mid;
        }
        return lo;
    }
}