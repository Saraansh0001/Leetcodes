class Solution {
    public boolean checkValidString(String s) {

        int low = 0;
        int high = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                low++;
                high++;
            }

            else if (c == ')') {
                low--;
                high--;
            }

            else { // '*'
                low--;     // '*' acts as ')'
                high++;    // '*' acts as '('
            }

            // Cannot have negative possible open brackets
            if (high < 0) {
                return false;
            }

            // Minimum cannot go below 0
            low = Math.max(low, 0);
        }

        return low == 0;
    }
}