package sorting;

/**
 * Q. Dutch sort
 * Given an array containing 0s, 1s and 2s, sort the array in-place. 
 * You should treat numbers of the array as objects, hence, we can’t count 0s, 1s, and 2s to recreate the array.
 * 
 * Input: [1, 0, 2, 1, 0]
 * Output: [0 0 1 1 2]
 * 
 * lo = 0, hi = 2
 * 
 *     | |
 * 0,0,1,1,2
 *       ^
 *   
 * lo ptr = 0,
 * hi ptr = last idx
 * 
 * while i < arr length
 * 1. if lo ? swap with lo ptr++, i++
 * 2. else if hi ? swap with hi ptr--, 
 * 	  (i doesnt proceed to check swapped number again)
 * 3. otherwise(in-btw) i++ 
 * @author sunnypark
 *
 */
public class DutchSort {
	public static void dutchSort(int[] nums) {
		int lo = 0, hi = nums.length - 1;
		int i = 0;
		while (i < nums.length) {
			if (nums[i] == 0) {
				swap(nums, lo, i);
				i++;
				lo++;
			} else if (nums[i] == 1) {
				i++;
			} else {
				swap(nums, i, hi);
				hi--;
			}
		}
	}
	
	private static void swap(int[] nums, int i, int j) {
		
	}
}
