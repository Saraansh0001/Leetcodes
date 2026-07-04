class Solution {
    public boolean isMiddleElementUnique(int[] nums) {
        int midValue = nums[nums.length / 2];

        int count = 0;
        for (int x : nums) {
            if (x == midValue) count++;
        }

        return count == 1;
    }
}