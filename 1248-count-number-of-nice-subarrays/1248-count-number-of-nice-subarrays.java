class Solution {
    public int numberOfSubarrays(int[] nums, int k) {

        int left = 0;
        int odd = 0;
        int count = 0;
        int evenBefore = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] % 2 != 0) {
                odd++;
            }

            if (odd > k) {
                evenBefore = 0;

                while (odd > k) {
                    if (nums[left] % 2 != 0) {
                        odd--;
                    }
                    left++;
                }
            }

            if (odd == k) {

                while (left <= right && nums[left] % 2 == 0) {
                    evenBefore++;
                    left++;
                }

                count += evenBefore + 1;
            }
        }

        return count;
    }
}