class Solution {
    public int mostFrequentEven(int[] nums) {

        Arrays.sort(nums);

        int ele = -1;
        int count = 0;

        int i = 0;

        while (i < nums.length) {

            int curr = nums[i];
            int tempCount = 1;

            while (i + 1 < nums.length && nums[i + 1] == nums[i]) {
                tempCount++;
                i++;
            }

            if (curr % 2 == 0 && tempCount > count) {
                count = tempCount;
                ele = curr;
            }

            i++;
        }

        return ele;
    }
}