class Solution {
    public boolean isPalindrome(int x) {
        String s = "" + x ;
        int i = 0 , j =s.length() - 1 ;

        while ( i <= j){
            if ( s.charAt(i) == s.charAt(j) ){
                i++ ;
                j-- ;
            }else{
                return false ;
            }
        }

        return true ;
    }
}