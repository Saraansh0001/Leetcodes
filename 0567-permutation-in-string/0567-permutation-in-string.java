class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] count = new int[26];

        for (char c : s1.toCharArray()) {
            count[c - 'a']++;
        }

        for (int i = 0; i < s2.length(); i++) {
            count[s2.charAt(i) - 'a']--;

            if (i >= s1.length()) {
                count[s2.charAt(i - s1.length()) - 'a']++;
            }

            if (i >= s1.length() - 1) {
                boolean valid = true;

                for (int j = 0; j < 26; j++) {
                    if (count[j] != 0) {
                        valid = false;
                        break;
                    }
                }

                if (valid) {
                    return true;
                }
            }
        }

        return false;
    }
}