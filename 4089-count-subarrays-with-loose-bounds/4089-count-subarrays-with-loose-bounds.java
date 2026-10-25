class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {

        long countSubarrays = 0L ;
        int n = nums.length ;
        int j = 0 ;

        while ( j < n ){
            if ( nums[j] < minK || nums[j] > maxK ){
                j++ ;
                continue ;
            }

            int temp = j ;

            while ( j < n && nums[j] >= minK && nums[j] <= maxK ){
                j++ ;
            }
            long len = j - temp ;
            countSubarrays += len*(len+1) / 2  ;
        }

        return countSubarrays ;
         
    }
}