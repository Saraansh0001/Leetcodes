
import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {

        int n = nums.length;

        // Square every element
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] * nums[i];
        }

        // Sort squares in ascending order
        Arrays.sort(nums);

        long ans = 0;
        int half = n / 2;

        // Subtract the smaller half
        for (int i = 0; i < half; i++) {
            ans -= nums[i];
        }

        // Add the larger half
        for (int i = half; i < n; i++) {
            ans += nums[i];
        }

        return ans;
    }
}
