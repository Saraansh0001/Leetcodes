class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {

        HashMap<Integer, Integer> n1 = new HashMap<>();
        HashMap<Integer, Integer> n2 = new HashMap<>();

        int size = Math.max(nums1.length, nums2.length);
        int[] result = new int[size];

        for (int num : nums1) {
            n1.put(num, n1.getOrDefault(num, 0) + 1);
        }

        for (int num : nums2) {
            n2.put(num, n2.getOrDefault(num, 0) + 1);
        }

        int fSize = 0;

        for (int num : nums1) {

            if (n2.containsKey(num)) {

                while (n1.containsKey(num) && n2.containsKey(num)) {

                    result[fSize] = num;
                    fSize++;

                    n1.put(num, n1.get(num) - 1);
                    n2.put(num, n2.get(num) - 1);

                    if (n1.get(num) == 0)
                        n1.remove(num);

                    if (n2.get(num) == 0)
                        n2.remove(num);
                }
            }
        }

        int[] finalArray = new int[fSize];

        for (int i = 0; i < fSize; i++) {
            finalArray[i] = result[i];
        }

        return finalArray;
    }
}