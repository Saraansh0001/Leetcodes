class Solution {
    public int minRotations(String s) {

        int current = 0;
        int total = 0;

        for (int i = 0; i < s.length(); i++) {

            int target = s.charAt(i) - '0';

            int diff = Math.abs(current - target);

            int rotations = Math.min(diff, 10 - diff);

            total += rotations;

            current = target;
        }

        return total;
    }
}