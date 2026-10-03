class Solution {
    public int pivotIndex(int[] nums) {

        int n = nums.length;

        int[] prefixSum = new int[n + 1];
        int[] postfixSum = new int[n + 1];

        // Prefix sum
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        // Postfix sum
        for (int i = n - 1; i >= 0; i--) {
            postfixSum[i] = postfixSum[i + 1] + nums[i];
        }

        // Check pivot
        for (int i = 0; i < n; i++) {

            int leftSum = prefixSum[i];

            int rightSum = postfixSum[i + 1];

            if (leftSum == rightSum) {
                return i;
            }
        }

        return -1;
    }
}