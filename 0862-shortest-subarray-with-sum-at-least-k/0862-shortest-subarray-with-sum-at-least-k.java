class Solution {
    public int shortestSubarray(int[] nums, int k) {

        int n = nums.length;

        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> dq = new ArrayDeque<>();

        int ans = n + 1;

        for (int j = 0; j <= n; j++) {

            while (!dq.isEmpty() &&
                   prefix[j] - prefix[dq.peekFirst()] >= k) {

                ans = Math.min(ans, j - dq.peekFirst());

                dq.pollFirst();
            }

            while (!dq.isEmpty() &&
                   prefix[dq.peekLast()] >= prefix[j]) {

                dq.pollLast();
            }

            dq.addLast(j);
        }

        return ans == n + 1 ? -1 : ans;
    }
}