class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {

        int maxL = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {

            if (nums[i] == 0) {
                continue;
            }

            int a = 0;

            while (i < n && nums[i] == 1) {
                a++;
                i++;
            }

            maxL = Math.max(maxL, a);
        }

        return maxL;
    }
}