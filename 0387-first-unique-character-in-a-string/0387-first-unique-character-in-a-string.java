class Solution {
    public int firstUniqChar(String s) {

        HashMap<Character, Integer> freq = new HashMap<>();
        HashMap<Character, Integer> index = new HashMap<>();

        int i = 0;

        for (char ch : s.toCharArray()) {

            freq.put(ch, freq.getOrDefault(ch, 0) + 1);

            if (!index.containsKey(ch)) {
                index.put(ch, i);
            }

            i++;
        }

        for (char ch : s.toCharArray()) {

            if (freq.get(ch) == 1) {
                return index.get(ch);
            }
        }

        return -1;
    }
}