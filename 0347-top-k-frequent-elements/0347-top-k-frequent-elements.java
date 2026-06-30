class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Arrays.sort(nums);

        int n = nums.length;

        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<Integer> freq = new ArrayList<>();

        int i = 0;

        while (i < n) {
            int curr = nums[i];
            int count = 0;

            while (i < n && nums[i] == curr) {
                count++;
                i++;
            }

            values.add(curr);
            freq.add(count);
        }

        int m = values.size();

        for (i = 0; i < m - 1; i++) {
            for (int j = 0; j < m - 1 - i; j++) {

                if (freq.get(j) < freq.get(j + 1)) {

                    int tempFreq = freq.get(j);
                    freq.set(j, freq.get(j + 1));
                    freq.set(j + 1, tempFreq);

                    int tempVal = values.get(j);
                    values.set(j, values.get(j + 1));
                    values.set(j + 1, tempVal);
                }
            }
        }

        int[] ans = new int[k];

        for (i = 0; i < k; i++) {
            ans[i] = values.get(i);
        }

        return ans;
    }
}