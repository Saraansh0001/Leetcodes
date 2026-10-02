class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    void generate(List<String> result, String current, int open, int close, int n) {
        // If complete
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // Add '('
        if (open < n) {
            generate(result, current + "(", open + 1, close, n);
        }

        // Add ')'
        if (close < open) {
            generate(result, current + ")", open, close + 1, n);
        }
    }
}