class Solution {
    public long[] resultArray(int[] nums, int k) {

        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {

            long[] newDp = new long[k];

            // Start a new subarray with only num
            int rem = num % k;
            newDp[rem] = 1;

            // Extend all previous subarrays
            for (int i = 0; i < k; i++) {
                int newRem = (int)((1L * i * rem) % k);
                newDp[newRem] += dp[i];
            }

            // Add current subarrays to answer
            for (int i = 0; i < k; i++) {
                result[i] += newDp[i];
            }

            dp = newDp;
        }

        return result;
    }
}