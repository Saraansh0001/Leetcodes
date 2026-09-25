class Solution {
    TreeSet<String> set = new TreeSet<>();

    public List<String> braceExpansionII(String expression) {
        dfs(expression);
        return new ArrayList<>(set);
    }

    private void dfs(String exp) {

        // No braces left → complete word
        int close = exp.indexOf('}');

        if (close == -1) {
            set.add(exp);
            return;
        }

        // Find matching opening brace
        int open = exp.lastIndexOf('{', close);

        String before = exp.substring(0, open);
        String inside = exp.substring(open + 1, close);
        String after = exp.substring(close + 1);

        // Try every option inside {}
        for (String choice : inside.split(",")) {
            dfs(before + choice + after);
        }
    }
}