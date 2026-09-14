class Solution {
    public boolean isAnagram(String s, String t) {

        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character , Integer> sMap = new HashMap<>() ;
        HashMap<Character , Integer> tMap = new HashMap<>() ;

        for ( char c : s.toCharArray()){
            sMap.put( c , sMap.getOrDefault(c , 0) + 1 );
        }
        
        for ( char c : t.toCharArray() ){
            tMap.put( c , tMap.getOrDefault(c , 0) + 1 );
        }

        for (char c : tMap.keySet()) {

            if (!sMap.containsKey(c)) {
                return false;
            }

            if (!sMap.get(c).equals(tMap.get(c))) {
                return false;
            }
        
        }

        return true  ;
    }
}