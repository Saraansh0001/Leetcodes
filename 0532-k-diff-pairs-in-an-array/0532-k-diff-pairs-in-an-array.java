class Solution {
    public int findPairs(int[] nums, int k) {

        if (k < 0) return 0;

        HashSet<Integer> seen = new HashSet<>();
        HashSet<Integer> pairs = new HashSet<>();

        for (int num : nums) {

            if (k == 0) {
                if (seen.contains(num)) {
                    pairs.add(num);
                }
            } 
            else {
                if (seen.contains(num - k)) {
                    pairs.add(num - k);
                }

                if (seen.contains(num + k)) {
                    pairs.add(num);
                }
            }

            seen.add(num);
        }

        return pairs.size();
    }
}