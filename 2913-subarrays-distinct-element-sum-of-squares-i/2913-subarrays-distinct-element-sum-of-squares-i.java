import java.util.*;

class Solution {
    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        int ans = 0;

        // Enumerate all subarrays
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                set.add(nums.get(j)); // add element to distinct set
                int distinctCount = set.size();
                ans += distinctCount * distinctCount; // add square of distinct count
            }
        }

        return ans;
    }
}
