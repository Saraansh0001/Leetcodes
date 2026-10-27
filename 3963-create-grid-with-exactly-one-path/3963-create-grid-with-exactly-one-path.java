class Solution {
    public String[] createGrid(int m, int n) {
        
        String [] grid = new String[m];

        for( int i = 0 ; i < m ; i++){
            String row = "" ;
            for ( int j = 0 ; j < n ; j++){
                if ( i == 0 || j == n-1){
                    row += "." ;
                } else {
                    row += "#" ;
                }
            }
            grid[i] = row ;
        }
        return grid;
    }
}