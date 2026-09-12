class Solution {
    public int mostFrequentEven(int[] nums) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int maxFreq = 0;
        int ans = -1;

        for (int num : nums) {

            if (num % 2 != 0)
                continue;

            int freq = map.getOrDefault(num, 0) + 1;
            map.put(num, freq);

            if (freq > maxFreq || 
               (freq == maxFreq && (ans == -1 || num < ans))) {

                maxFreq = freq;
                ans = num;
            }
        }

        return ans;
    }
}