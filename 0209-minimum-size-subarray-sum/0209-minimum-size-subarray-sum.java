class Solution {
    public int minSubArrayLen(int target, int[] nums) {

        int n = nums.length;
        int i1 = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            sum += nums[i];

            while (sum >= target) {

                int length = i - i1 + 1;

                minLength = Math.min(minLength, length);

                sum -= nums[i1];
                i1++;
            }
        }

        if (minLength == Integer.MAX_VALUE)
            return 0;

        return minLength;
    }
}