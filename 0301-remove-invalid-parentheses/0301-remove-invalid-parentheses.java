class Solution {

    Set<String> ans = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRemove = 0;
        int rightRemove = 0;

        // Find minimum number of '(' and ')' to remove
        for (char c : s.toCharArray()) {

            if (c == '(') {
                leftRemove++;
            }

            else if (c == ')') {

                if (leftRemove > 0) {
                    leftRemove--;
                }
                else {
                    rightRemove++;
                }
            }
        }

        // DFS
        dfs(s, 0, leftRemove, rightRemove, 0, 0, "");

        return new ArrayList<>(ans);
    }


    private void dfs(
        String s,
        int index,
        int leftRemove,
        int rightRemove,
        int leftCount,
        int rightCount,
        String curr
    ) {

        // End of string
        if (index == s.length()) {

            if (leftRemove == 0 &&
                rightRemove == 0 &&
                leftCount == rightCount) {

                ans.add(curr);
            }

            return;
        }


        char c = s.charAt(index);


        // OPTION 1: REMOVE current character
        if (c == '(' && leftRemove > 0) {

            dfs(
                s,
                index + 1,
                leftRemove - 1,
                rightRemove,
                leftCount,
                rightCount,
                curr
            );
        }

        else if (c == ')' && rightRemove > 0) {

            dfs(
                s,
                index + 1,
                leftRemove,
                rightRemove - 1,
                leftCount,
                rightCount,
                curr
            );
        }


        // OPTION 2: KEEP current character

        if (c == '(') {

            dfs(
                s,
                index + 1,
                leftRemove,
                rightRemove,
                leftCount + 1,
                rightCount,
                curr + c
            );
        }

        else if (c == ')') {

            // Can't have ')' before '('
            if (rightCount < leftCount) {

                dfs(
                    s,
                    index + 1,
                    leftRemove,
                    rightRemove,
                    leftCount,
                    rightCount + 1,
                    curr + c
                );
            }
        }

        else {
            // Normal character
            dfs(
                s,
                index + 1,
                leftRemove,
                rightRemove,
                leftCount,
                rightCount,
                curr + c
            );
        }
    }
}