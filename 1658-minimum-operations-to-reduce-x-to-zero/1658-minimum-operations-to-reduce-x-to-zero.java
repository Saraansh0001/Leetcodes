class Solution {
    public int minOperations(int[] nums, int x) {

        int total = 0;

        for (int num : nums) {
            total += num;
        }

        int target = total - x;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int prefix = 0;
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {

            prefix += nums[i];

            if (map.containsKey(prefix - target)) {
                maxLen = Math.max(maxLen,
                        i - map.get(prefix - target));
            }

            map.putIfAbsent(prefix, i);
        }

        return maxLen == 0 && target != 0 ? -1 : nums.length - maxLen;
    }
}