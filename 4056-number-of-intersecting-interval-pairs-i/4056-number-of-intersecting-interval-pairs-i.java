class Solution {
    public int countIntersectingIntervals(int[][] intervals) {

        int count = 0 ; 

        for ( int i =  0 ; i < intervals.length ; i ++){
            for ( int j =  i+1 ; j < intervals.length ; j ++){

                int start1 = intervals [i][0] ;
                int start2 = intervals [j][0] ;

                int end1 = intervals[i][1] ;
                int end2 = intervals[j][1] ;

                if ( start1 <= end2 &&  start2<=end1 ) count++ ;
            }
        }

        return count ;
    }
}