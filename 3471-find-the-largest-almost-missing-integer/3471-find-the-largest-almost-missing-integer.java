import java.util.*;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;

        Map<Integer, Integer> count = new HashMap<>();

        Map<Integer, Integer> last = new HashMap<>();

        int max = -1;

        for (int i = 0; i < n; i++) {
            int x = nums[i];

            int prev = last.getOrDefault(x, -1);

 
            int left = Math.max(0, i - k + 1);
            int right = Math.min(i, n - k);

            left = Math.max(left, prev + 1);

            if (left <= right) {
                count.put(x, count.getOrDefault(x, 0) + (right - left + 1));
            }

            last.put(x, i);
        }

        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                max = Math.max(max, entry.getKey());
            }
        }

        return max;
    }
}