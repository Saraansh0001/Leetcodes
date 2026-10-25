class Solution {
    public int calculateScore(int a, int b) {

        int a2 = Integer.bitCount(a) ;
        int b2 = Integer.bitCount(b) ;

        if ( a2 >= b2) return a2*b2 ;
        else return b2/a2;
    }
}