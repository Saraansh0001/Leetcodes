class Solution {
    public List<Integer> majorityElement(int[] nums) {

        ArrayList<Integer> list = new ArrayList<>() ;
        HashMap<Integer,Integer> map = new HashMap<>() ;
        HashMap<Integer,Integer> seen = new HashMap<>() ;
        int n = nums.length ;

        for ( int num : nums ){
            map.put ( num , map.getOrDefault( num , 0 ) + 1 );
        }

        for ( int num : nums ) {
            if ( seen.containsKey(num) ) continue ;
            if ( map.get(num) > n/3 ){
                list.add(num) ;
                seen.put(num , seen.getOrDefault( num , 0 ) +1 ) ;
            }
        }

        return list ;
        
    }
}