class Solution {
    public int lengthOfLongestSubstring(String s) {

        HashSet<Character> set = new HashSet<>();

        int left = 0;
        int maxSize = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            while (set.contains(ch)) {
                set.remove(s.charAt(left));
                left++;
            }

            set.add(ch);

            maxSize = Math.max(maxSize, right - left + 1);
        }

        return maxSize;
    }
}