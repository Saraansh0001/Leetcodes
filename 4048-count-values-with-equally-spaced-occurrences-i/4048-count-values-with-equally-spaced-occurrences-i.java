class Solution {
    public int countSpecialIntegers(int[] nums) {

        HashMap<Integer,Integer> map = new HashMap<>() ;

        for( int num : nums ){
            map.put(num , map.getOrDefault(num,0) + 1) ;
        }

        int ans = 0 ;
        HashSet<Integer> seen = new HashSet<>() ;

        for ( int num : nums ){

            if (seen.contains(num)){
                continue ;
            }

            seen.add(num ) ;
            
            // int freq = map.getOrDefault ( num , 0) + 1 ;
            if ( map.get(num) == 3) {
                int first = -1 , sec = -1 , thir = -1 ; 

                for ( int i = 0 ; i < nums.length ; i ++){
                    if ( nums[i] == num ){

                        
                        if ( first == -1 ){
                            first = i ;
                        } else if ( sec == -1 ){
                            sec = i ;
                        } else {
                            thir = i ;
                        }
                    }
                }
                if ( sec - first == thir - sec ) ans ++ ;
            }
        }

        return ans ;
        
    }
}