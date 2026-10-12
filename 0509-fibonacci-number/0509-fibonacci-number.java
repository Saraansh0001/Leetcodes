class Solution {
    public int fib(int n) {

        return ( nthFibo(n) );
        
    }

    private int nthFibo ( int n ) {

        if ( n == 0 ) return 0 ;
        if ( n == 1 || n == 2) return 1 ;

        return nthFibo(n-1) + nthFibo(n-2) ;
        
    }
}