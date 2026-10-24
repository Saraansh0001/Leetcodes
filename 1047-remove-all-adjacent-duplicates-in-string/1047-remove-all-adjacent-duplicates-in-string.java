class Solution {
    public String removeDuplicates(String s) {
        Stack<Character> st = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (!st.isEmpty() && ch == st.peek()) {
                st.pop();
            } else {
                st.push(ch);
            }
        }

        StringBuilder ans = new StringBuilder();

        for (char ch : st) {
            ans.append(ch);
        }

        return ans.toString();
    }
}