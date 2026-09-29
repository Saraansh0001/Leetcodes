class Solution {
    public int arrangeCoins(int n) {

        int lo = 0 , hi = n ;

        while ( lo <= hi ){

            int mid = lo + ( hi - lo ) / 2 ;

            long a = (long) mid*(mid+1) / 2 ;
            if ( a == n ) return mid ;
            else if ( a > n ) hi = mid - 1 ;
            else {
                lo = mid + 1 ;
            }
        }
        
        return hi ;
    }
}