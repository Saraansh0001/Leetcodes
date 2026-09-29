class Solution {
    public int[] searchRange(int[] nums, int target) {

        int low = 0, high = nums.length - 1;
        int first = -1, last = -1;

        // Find first occurrence
        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                first = mid;
                high = mid - 1;     // keep searching left
            } 
            else if (nums[mid] > target) {
                high = mid - 1;
            } 
            else {
                low = mid + 1;
            }
        }

        low = 0;
        high = nums.length - 1;

        // Find last occurrence
        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                last = mid;
                low = mid + 1;      // keep searching right
            } 
            else if (nums[mid] > target) {
                high = mid - 1;
            } 
            else {
                low = mid + 1;
            }
        }

        return new int[]{first, last};
    }
}