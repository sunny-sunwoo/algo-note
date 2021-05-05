package leetcode;

/**
 * LC 33

3,4,5,1,2
^   ^   ^

lo, hi

mid => lo + (hi - lo) / 2
 check mid -> rt answer if possible
 
 otherwise, narrow down w/binary search approach
 
 i) left side sorted?  
      ==> nums[lo] <= nums[mid]  
      ==> NOTE: dont forget '=' sign (edge case: [3,1], target=1)
   
   lo val <= target < mid val? 
     => explore to left
     
   otherwise?
     => explore to right
     
 ii) right side sorted?
   mid val < target <= hi val?
     => explore to right
     
   otherwise? 
     => explore to left
    
rt -1 if not found

time complexity: O(logN)
 * @author sunnypark
 *
 */
public class LC33_SearchInRotatedSortedArr {
	public int search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            
            // answer found
            if (nums[mid] == target) {
                return mid;
            }
            
            // left side sorted?
            if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target < nums[mid]) { // explore to left
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else { // right side sorted?
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        
        return -1;
    }
}
