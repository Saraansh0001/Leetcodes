class Solution {
    public int similarPairs(String[] words) {

        HashMap<String, Integer> map = new HashMap<>();

        int ans = 0;

        for (String word : words) {

            boolean[] seen = new boolean[26];

            for (char c : word.toCharArray()) {
                seen[c - 'a'] = true;
            }

            String key = "";

            for (int i = 0; i < 26; i++) {
                if (seen[i]) {
                    key += (char) ('a' + i);
                }
            }

            ans += map.getOrDefault(key, 0);

            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return ans;
    }
}