class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        for (char ch : word.toCharArray()) {
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);

        int pushes = 0;
        int cost = 1;

        for (int i = 25, cnt = 0; i >= 0; i--, cnt++) {
            if (freq[i] == 0) break;

            if (cnt == 8) cost = 2;
            else if (cnt == 16) cost = 3;
            else if (cnt == 24) cost = 4;

            pushes += freq[i] * cost;
        }

        return pushes;
    }
}