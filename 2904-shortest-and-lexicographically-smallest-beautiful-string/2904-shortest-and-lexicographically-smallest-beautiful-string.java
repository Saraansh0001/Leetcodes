class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int left = 0;
        int ones = 0;

        String ans = "";

        for (int right = 0; right < s.length(); right++) {

            // Add current character
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Too many 1s -> move left
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Remove unnecessary leading 0s
            while (ones == k && s.charAt(left) == '0') {
                left++;
            }

            // We have exactly k ones
            if (ones == k) {

                String curr = s.substring(left, right + 1);

                // Shorter is better
                if (ans.equals("") || curr.length() < ans.length()) {
                    ans = curr;
                }

                // Same length -> lexicographically smaller
                else if (curr.length() == ans.length()
                        && curr.compareTo(ans) < 0) {
                    ans = curr;
                }
            }
        }

        return ans;
    }
}