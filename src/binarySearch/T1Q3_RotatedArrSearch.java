package binarySearch;

/**
 * T1Q3
 * LC33 RotatedArraySearch
 * 
 * lo val < mid val
 *   -> lo <= target < mid ?  => to left
 *   -> else ?  => to right
 * 
 * else? // right sorted 
 *   -> mid < target <= hi ? => to right
 *   -> else to left 
 * @author sunnypark
 * @author sunnypark
 *
 */
public class T1Q3_RotatedArrSearch {
	public static int shiftedArrSearch(int[] shift, int target) {
		// your code goes here
		int lo = 0;
		int hi = shift.length - 1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (shift[mid] == target) {
				return mid;
			}

			// left side is sorted
			if (shift[lo] < shift[mid]) {
				if (shift[lo] <= target && target < shift[mid]) { // lo-target-mid
					hi = mid - 1;
				} else {
					lo = mid + 1;
				}
			} else { // otherwise
				if (shift[mid] < target && target <= shift[hi]) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
		}

		return -1;
	}

	  public static void main(String[] args) {
	    int[] shiftArr = {9, 12, 13, 2, 4, 5};
	    System.out.println (shiftedArrSearch(shiftArr, 2)); // expected: 3
	    System.out.println (shiftedArrSearch(shiftArr, 12)); // expected: 1
	    System.out.println (shiftedArrSearch(shiftArr, 1)); // expected: -1
	  }
}
