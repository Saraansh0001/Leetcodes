class Solution {
    public int countGoodRotations(int[] nums) {

        int n = nums.length;

        long leftSum = 0;
        long rightSum = 0;

        int mid = n / 2;

        for (int i = 0; i < mid; i++) {
            leftSum += nums[i];
        }

        for (int i = mid; i < n; i++) {
            rightSum += nums[i];
        }

        int goodRotation = 0;

        for (int i = 0; i < n; i++) {

            if (leftSum > rightSum) {
                goodRotation++;
            }

            if (i < n - 1) {

                long leaving = nums[i];
                long entering = nums[(i + mid) % n];

                leftSum = leftSum - leaving + entering;
                rightSum = rightSum - entering + leaving;
            }
        }

        return goodRotation;
    }
}