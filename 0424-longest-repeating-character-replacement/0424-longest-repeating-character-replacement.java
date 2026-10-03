class Solution {
    public int characterReplacement(String s, int k) {

        int left = 0;
        int maxFreq = 0;
        int ans = 0;

        int[] freq = new int[26];

        for (int right = 0; right < s.length(); right++) {

            // Add current character
            freq[s.charAt(right) - 'A']++;

            // Find the character appearing the most
            maxFreq = Math.max(
                maxFreq,
                freq[s.charAt(right) - 'A']
            );

            // If we need more than k replacements,
            // shrink the window
            while ((right - left + 1) - maxFreq > k) {

                freq[s.charAt(left) - 'A']--;
                left++;
            }

            // Store the largest valid window
            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}