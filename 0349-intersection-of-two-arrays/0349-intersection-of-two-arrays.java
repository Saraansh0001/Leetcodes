class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] arr = new int[Math.min(nums1.length, nums2.length)];

        int count = 0;

        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {

            if (nums1[i] == nums2[j]) {

                arr[count] = nums1[i];
                count++;

                // Skip duplicates
                while (i + 1 < nums1.length && nums1[i] == nums1[i + 1]) {
                    i++;
                }

                while (j + 1 < nums2.length && nums2[j] == nums2[j + 1]) {
                    j++;
                }

                i++;
                j++;

            } else if (nums1[i] > nums2[j]) {
                j++;

            } else {
                i++;
            }
        }

        return Arrays.copyOf(arr, count);
    }
}
