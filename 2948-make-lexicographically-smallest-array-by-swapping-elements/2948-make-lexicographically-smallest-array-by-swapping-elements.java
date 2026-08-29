import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] idx = new Integer[n];

        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        // Sort indices according to their values
        Arrays.sort(idx, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = nums.clone();

        int start = 0;

        while (start < n) {
            int end = start;

            // Find all elements that belong to the same connected group
            while (end + 1 < n &&
                   nums[idx[end + 1]] - nums[idx[end]] <= limit) {
                end++;
            }

            // Get original positions of this group
            int[] positions = new int[end - start + 1];

            for (int i = start; i <= end; i++) {
                positions[i - start] = idx[i];
            }

            // Put positions in increasing index order
            Arrays.sort(positions);

            // Values are already sorted because idx is sorted by nums
            for (int i = 0; i < positions.length; i++) {
                ans[positions[i]] = nums[idx[start + i]];
            }

            start = end + 1;
        }

        return ans;
    }
}
