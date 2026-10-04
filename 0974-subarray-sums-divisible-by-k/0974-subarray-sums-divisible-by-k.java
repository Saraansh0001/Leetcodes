class Solution {
    public int subarraysDivByK(int[] nums, int k) {

        HashMap<Integer,Integer> rem = new HashMap<>() ;

        rem.put(0,1) ;

        int pairs = 0 ;
        int sum = 0 ;

        for ( int num : nums ){
            sum += num ;

            int rem2 = sum%k ;

            if ( rem2 < 0){
                rem2 += k ;
            }

            if ( rem.containsKey(rem2) ){
                pairs += rem.get(rem2) ;
            }

            rem.put( rem2 , rem.getOrDefault( rem2 , 0 ) + 1 ) ;
        }

        return pairs ;
        
    }
}