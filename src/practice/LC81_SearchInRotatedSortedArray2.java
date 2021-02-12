package practice;

/**
 * LC 81 Search in Rotated Sorted Array II
 * given an sorted int array which is rotated at some unknown pivot,
 * check if target can be found.
 * 
 * e.g.
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * 
 * [Approach 1] sort first
 * sort array.
 * check see if binary search idx is valid (0 ~ nums.length - 1)
 * 
 * [Approach 2] binary search with 2 ptrs
 * 
 * while lo <= hi
 *  - calc mid and check
 *  - dedup while (lo < mid && lo val == mid val)  
 *     // e.g. [1,0,1,1,1]
 *              ^   ^
 * 
 * 1) left side is sorted <=> arr[lo] <= arr[mid] 
 *      // OR left == mid (only 1 num in left side e.g. [3,1])
 * 
 *    - lo val <= target < mid val ? hi = mid - 1 (explore to left side)
 *    - else ? lo = mid + 1
 * 
 * 2) otherwise(right side sorted)
 *    - mid val < target <= hi val ? lo = mid + 1 (explore to right side)
 *    - else ? hi = mid - 1
 * 
 * @author sunnypark
 *
 */
public class LC81_SearchInRotatedSortedArray2 {
	public static boolean search(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return true;
            }
            
            // dup
            while (lo < mid && nums[lo] == nums[mid]) {
                lo++;
            }

            if (nums[lo] <= nums[mid]) { // left side sorted
                if (nums[lo] <= target && target < nums[mid]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            } else { // right side sorted
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
        }
        return false;
    }
	
	public static void main(String[] args) {
		int[] arr = {2,5,6,0,0,1,2};
		
		System.out.println(search(arr, 0));
		System.out.println(search(arr, 3));
	}
}
