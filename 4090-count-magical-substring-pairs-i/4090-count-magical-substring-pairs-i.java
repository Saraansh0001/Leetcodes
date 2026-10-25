class Solution {
    public int countMagicalPairs(String s) {

        int countMagicalPairs = 0 ;
        int n = s.length() ;

        for ( int i = 0 ; i < n-1 ; i++){
            boolean[] seen = new boolean[26];
            int[] rem = new int[27] ;
            int diff = 0 ;
            for ( int j = i ; j >= 0 ; j-- ){
                int c = s.charAt(j) - 'a' ;

                if (!seen[c]){
                    seen[c] = true ;
                    diff ++ ;
                }

                rem[diff]++ ;
            }

            seen = new boolean[26] ;
            diff = 0 ;

            for ( int j = i+1 ; j < n ; j++){
                int c = s.charAt(j) - 'a' ;

                if (!seen[c]){
                    seen[c] = true ;
                    diff ++ ;
                }
                
                countMagicalPairs += rem[diff] ;
            }
        }

        return countMagicalPairs ;
    }
}