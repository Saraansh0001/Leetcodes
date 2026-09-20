class Solution {
    public long countIntersectingIntervals(int[][] intervals) {
        
        long count = 0 ; 
        int n = intervals.length ;
        
        int[] start = new int[n] ;
        int[] end = new int[n] ;

        for ( int i = 0 ; i < n ; i ++){
            start[i] = intervals[i][0] ;
            end[i] = intervals[i][1] ;
        }

        Arrays.sort(start) ;        
        Arrays.sort(end) ;        

        int j = 0 ;

        for ( int i = 0 ; i < n ; i++ ){
            while ( j <n && end[j]<start[i] ){
                j++ ;
            }
            count += i-j ;
        }

        return count ;
    }
}