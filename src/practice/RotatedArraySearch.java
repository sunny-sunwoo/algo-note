package practice;

/**
 * lo val < mid val
 *   -> lo <= target < mid ?  => to left
 *   -> else ?  => to right
 * 
 * else? // right sorted 
 *   -> mid < target <= hi ? => to right
 *   -> else to left 
 * @author sunnypark
 *
 */
public class RotatedArraySearch {
	static int shiftedArrSearch(int[] shift, int num) {
	    // your code goes here
	    int lo = 0;
	    int hi = shift.length - 1;
	    
	    while (lo <= hi) {
	      int mid = lo + (hi - lo) / 2;
	      if (shift[mid] == num) {
	        return mid;
	      }
	      
	      // right part is sorted
	      if (shift[mid] < shift[hi]) {
	        if (shift[mid] < num) {
	          lo = mid + 1;
	        } else {
	          hi = mid - 1;
	        }
	      } else { // otherwise
		      if (shift[mid] < num) {
		        lo = mid + 1;
		      } else {
		        if (shift[lo] > num) {
		          lo = mid + 1;
		        } else {
		          hi = mid - 1;
		        }
		      }
	      }
	     
	    }
	    
	    return -1;
	  }

	  public static void main(String[] args) {
	    int[] shiftArr = {9, 12, 17, 2, 4, 5};
	    System.out.println (shiftedArrSearch(shiftArr, 2)); // expected: 3
	    System.out.println (shiftedArrSearch(shiftArr, 12)); // expected: 1
	  }
}
