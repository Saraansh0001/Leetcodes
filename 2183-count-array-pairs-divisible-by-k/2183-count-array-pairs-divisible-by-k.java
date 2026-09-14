class Solution {
    public long countPairs(int[] nums, int k) {

        HashMap<Integer, Long> map = new HashMap<>();
        long ans = 0;

        for (int num : nums) {

            int g = gcd(num, k);

            for (int key : map.keySet()) {
                if ((long) g * key % k == 0) {
                    ans += map.get(key);
                }
            }

            map.put(g, map.getOrDefault(g, 0L) + 1);
        }

        return ans;
    }

    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}