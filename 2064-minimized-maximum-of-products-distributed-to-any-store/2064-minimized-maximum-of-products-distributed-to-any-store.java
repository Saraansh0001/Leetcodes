class Solution {
    public int minimizedMaximum(int n, int[] quantities) {

        int lo = 1 , hi = Integer.MIN_VALUE , ans = 0;

        for ( int num : quantities ){
            hi = Math.max ( hi , num );
        }

        while ( lo <= hi){
            int mid = lo + ( hi - lo ) / 2 ;

            int stores = 0 ;

            for ( int num : quantities){
                stores += ( num + mid - 1 ) / mid ;
            }

            if ( stores <= n ){
                ans = mid ;
                hi = mid - 1 ;
            } else {
                lo = mid + 1 ;
            }
        }

        return ans ;
        
    }
}