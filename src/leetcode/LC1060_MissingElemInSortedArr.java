package leetcode;

/**
 * LC 1060. Missing Element in Sorted Array
 * 
 * 
	 A = [4,7,9,10]
	        l r
	 left, right 
	 missing = nums[right] - nums[left] - (right - left)
	 
	 1) missing < k ? 
	    find answer from right side
	
	 2) binary search while left + 1 < right (until we find the last range L <-> R)
	    left, mid, right
	    missingMid => num of missing ones on the left side. 
	    
	    missingMid < k
	        k -= missingMid
	        search to the right side <==> left = mid

	    else right = mid
	
	3) answer @ nums[left] + k
	
 * @author sunnypark
 *
 */
public class LC1060_MissingElemInSortedArr {
	public int missingElement(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        int missing = nums[right] - nums[left] - (right - left);
        
        if (missing < k) {
            return nums[right] + (k - missing);
        }
        
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int missingMid = nums[mid] - nums[left] - (mid - left);
            
            if (missingMid < k) {
                k -= missingMid;
                left = mid;
            } else {
                right = mid;
            }
        }
        
        return nums[left] + k;
    }
}
