
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;

        // Case 1: Rotate the original array
        int k = 0;
        while (nums[k] != 0) {
            k++;
        }

        boolean sorted = true;
        for (int i = 0; i < n; i++) {
            if (nums[(k + i) % n] != i) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            ans = Math.min(ans, Math.min(k, n - k + 2));
        }

        // Case 2: Reverse the array, then rotate
        int[] rev = new int[n];

        for (int i = 0; i < n; i++) {
            rev[i] = nums[n - 1 - i];
        }

        k = 0;
        while (rev[k] != 0) {
            k++;
        }

        sorted = true;
        for (int i = 0; i < n; i++) {
            if (rev[(k + i) % n] != i) {
                sorted = false;
                break;
            }
        }

        if (sorted) {
            ans = Math.min(ans, Math.min(k + 1, n - k + 1));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
