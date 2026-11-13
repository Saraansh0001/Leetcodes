class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        int LOG = 18;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        // Next greater element
        int[] next = new int[n + 1];
        int[] st = new int[n];
        int top = -1;

        for (int i = n - 1; i >= 0; i--) {
            while (top >= 0 && nums[st[top]] <= nums[i]) {
                top--;
            }

            next[i] = (top == -1) ? n : st[top];
            st[++top] = i;
        }

        next[n] = n;

        // Binary lifting
        int[][] up = new int[LOG][n + 1];
        long[][] cost = new long[LOG][n + 1];

        for (int i = 0; i <= n; i++) {
            up[0][i] = (i == n) ? n : next[i];

            if (i < n) {
                int j = next[i];

                cost[0][i] =
                    (long) nums[i] * (j - i)
                    - (prefix[j] - prefix[i]);
            }
        }

        for (int p = 1; p < LOG; p++) {
            for (int i = 0; i <= n; i++) {
                int mid = up[p - 1][i];

                up[p][i] = up[p - 1][mid];

                cost[p][i] =
                    cost[p - 1][i] + cost[p - 1][mid];
            }
        }

        // Sliding window
        long ans = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {

            while (left <= right &&
                   getCost(left, right, nums, prefix, up, cost) > k) {
                left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }

    private long getCost(
        int left,
        int right,
        int[] nums,
        long[] prefix,
        int[][] up,
        long[][] cost
    ) {
        long total = 0;
        int pos = left;
        int LOG = up.length;

        // Consume complete next-greater segments
        for (int p = LOG - 1; p >= 0; p--) {
            int nxt = up[p][pos];

            if (nxt <= right) {
                total += cost[p][pos];
                pos = nxt;
            }
        }

        // Remaining partial segment
        total += (long) nums[pos] * (right - pos + 1)
                 - (prefix[right + 1] - prefix[pos]);

        return total;
    }
}
