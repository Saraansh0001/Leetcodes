class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {

        HashSet<Integer> n1 = new HashSet<>();
        HashSet<Integer> n2 = new HashSet<>();

        int size = Math.max(nums1.length, nums2.length);
        int[] result = new int[size];

        for (int num : nums1) {
            n1.add(num);
        }

        for (int num : nums2) {
            n2.add(num);
        }

        int fSize = 0;

        for (int num : n1) {

            if (n2.contains(num)) {
                result[fSize] = num;
                fSize++;
            }
        }

        int[] finalArray = new int[fSize];

        for (int i = 0; i < fSize; i++) {
            finalArray[i] = result[i];
        }

        return finalArray;
    }
}