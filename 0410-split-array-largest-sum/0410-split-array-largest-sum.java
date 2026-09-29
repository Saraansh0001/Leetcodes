class Solution {
    
    public int splitArray(int[] nums, int k) {
        
        int lo = 0;
        int hi = 0;
        
        // Find search space
        for (int num : nums) {
            lo = Math.max(lo, num);
            hi += num;
        }
        
        // Binary Search on Answer
        while (lo < hi) {
            
            int mid = lo + (hi - lo) / 2;
            
            if (canSplit(nums, k, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    
    private boolean canSplit(int[] nums, int k, int maxSum) {
        
        int parts = 1;
        int sum = 0;
        
        for (int num : nums) {
            
            if (sum + num <= maxSum) {
                sum += num;
            } 
            else {
                parts++;
                sum = num;
            }
        }
        
        return parts <= k;
    }
}