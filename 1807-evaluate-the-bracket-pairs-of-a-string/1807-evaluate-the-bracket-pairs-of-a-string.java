class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        HashMap<String, String> map = new HashMap<>();

        // Store key -> value
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        int i = 0;

        while (i < s.length()) {

            // Normal character
            if (s.charAt(i) != '(') {
                ans.append(s.charAt(i));
                i++;
            } 
            else {
                // Find the closing bracket
                int j = i + 1;

                while (s.charAt(j) != ')') {
                    j++;
                }

                // Extract key
                String key = s.substring(i + 1, j);

                // Add value if present, otherwise ?
                if (map.containsKey(key)) {
                    ans.append(map.get(key));
                } else {
                    ans.append('?');
                }

                // Move after ')'
                i = j + 1;
            }
        }

        return ans.toString();
    }
}