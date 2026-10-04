class Solution {
    public int[] searchRange(int[] nums, int target) {

        int[] position = new int[2] ;
        position[0] = -1 ;
        position[1] = -1 ;

        int n = nums.length ;
        int lo = 0 , hi = n - 1 ;

        while( lo <= hi ){
            int mid = lo + ( hi - lo ) / 2 ;

            if ( nums[mid] == target){
                position[0] = mid ;
                hi = mid - 1 ;
            }else if ( nums[mid] < target ){
                lo = mid + 1 ;
            }else {
                hi = mid - 1 ;
            }
        }

        lo = 0;
        hi = n - 1 ;

        while( lo <= hi ){
            int mid = lo + ( hi - lo ) / 2 ;

            if ( nums[mid] == target){
                position[1] = mid ;
                lo = mid + 1 ;
            }else if ( nums[mid] < target ){
                lo = mid + 1 ;
            }else {
                hi = mid - 1 ;
            }
        }

        return position ;
    }
}