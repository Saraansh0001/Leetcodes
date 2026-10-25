class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i <= n; i++) {
            int curr = (i == n) ? 0 : heights[i];

            while (!st.isEmpty() && curr < heights[st.peek()]) {
                int height = heights[st.pop()];
                int width;

                if (st.isEmpty()) {
                    width = i;
                } else {
                    width = i - st.peek() - 1;
                }

                maxArea = Math.max(maxArea, height * width);
            }

            st.push(i);
        }

        return maxArea;
    }
}