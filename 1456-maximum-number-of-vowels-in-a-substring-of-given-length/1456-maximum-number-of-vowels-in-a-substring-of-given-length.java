class Solution {
    public int maxVowels(String s, int k) {

        int left = 0;
        int noOfVowels = 0;
        int maxVowel = 0;

        // First window
        for (int i = 0; i < k; i++) {

            if (s.charAt(i) == 'a' ||
                s.charAt(i) == 'e' ||
                s.charAt(i) == 'i' ||
                s.charAt(i) == 'o' ||
                s.charAt(i) == 'u') {

                noOfVowels++;
            }
        }

        maxVowel = noOfVowels;

        // Slide the window
        for (int right = k; right < s.length(); right++) {

            // Remove left element
            if (s.charAt(left) == 'a' ||
                s.charAt(left) == 'e' ||
                s.charAt(left) == 'i' ||
                s.charAt(left) == 'o' ||
                s.charAt(left) == 'u') {

                noOfVowels--;
            }

            // Add right element
            if (s.charAt(right) == 'a' ||
                s.charAt(right) == 'e' ||
                s.charAt(right) == 'i' ||
                s.charAt(right) == 'o' ||
                s.charAt(right) == 'u') {

                noOfVowels++;
            }

            left++;

            maxVowel = Math.max(maxVowel, noOfVowels);
        }

        return maxVowel;
    }
}