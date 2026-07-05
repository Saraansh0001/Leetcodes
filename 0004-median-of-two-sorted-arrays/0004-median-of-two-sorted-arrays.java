class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int[] arr = new int[nums1.length + nums2.length];

        int k = 0;

        for (int x : nums1)
            arr[k++] = x;

        for (int x : nums2)
            arr[k++] = x;

        Arrays.sort(arr);

        int n = arr.length;

        if (n % 2 == 1)
            return arr[n / 2];

        return (arr[n / 2] + arr[n / 2 - 1]) / 2.0;
    }
}