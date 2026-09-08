class Solution {
    public int countLargestGroup(int n) {

        int[] arr = new int[37];

        for (int i = 1; i <= n; i++) {

            int temp = i;
            int sum = 0;

            while (temp != 0) {
                sum += temp % 10;
                temp /= 10;
            }

            arr[sum]++;
        }

        int maxFreq = 0;

        for (int i = 1; i < arr.length; i++) {
            maxFreq = Math.max(maxFreq, arr[i]);
        }

        int count = 0;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == maxFreq) {
                count++;
            }
        }

        return count;
    }
}