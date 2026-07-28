class Solution {
    public int reverse(int x) {

        boolean neg = false;
        int x2 = 0;

        if (x < 0) {
            if (x == Integer.MIN_VALUE) return 0;
            neg = true;
            x = -x;
        }

        while (x != 0) {
            int a = x % 10;

            if (x2 > (Integer.MAX_VALUE - a) / 10)
                return 0;

            x2 = x2 * 10 + a;
            x /= 10;
        }

        return neg ? -x2 : x2;
    }
}