class Solution {
    public int mostFrequentEven(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Count frequency
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;
        int smallest = Integer.MAX_VALUE;

        // Find most frequent even number
        for (int num : map.keySet()) {

            if (num % 2 == 0) {

                int freq = map.get(num);

                if (freq > maxFreq || 
                   (freq == maxFreq && num < smallest)) {

                    maxFreq = freq;
                    smallest = num;
                }
            }
        }

        return smallest == Integer.MAX_VALUE ? -1 : smallest;
    }
}