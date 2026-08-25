class Solution {
    public int maxProfit(int[] prices) {
        
        int minPrice = prices[0] ;
        int maxProfit = 0 ;

        for  ( int i = 0 ; i < prices.length ; i++) {
            int profit ;

            if ( prices[i] < minPrice ) 
                minPrice = prices[i] ;

            profit = prices[i] - minPrice ;

            if  ( profit >  maxProfit ) 
                maxProfit = profit ;
        }

        return maxProfit ;
    }
}