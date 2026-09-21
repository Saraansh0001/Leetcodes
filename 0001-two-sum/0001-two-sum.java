class Solution {
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int i = 0;

        for (int num : nums) {
            map.put(num, i);
            i++;
        }

        i = 0;

        for (int num : nums) {

            int rem = target - num;

            if (map.containsKey(rem) && map.get(rem) != i) {
                return new int[]{i, map.get(rem)};
            }

            i++;
        }

        return new int[]{-1, -1};
    }
}