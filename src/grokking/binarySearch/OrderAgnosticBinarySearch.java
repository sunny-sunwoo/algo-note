package grokking.binarySearch;

/**
 * Q. Order-agnostic Binary Search
 * 
 * Given a sorted array of numbers, find if a given number ‘key’ is present in the array. 
 * Though we know that the array is sorted, we don’t know if it’s sorted in ascending or descending order.
 * 
 * e.g.
 * Input: [10, 6, 4], key = 10
 * Output: 0
 * 
 * [approach] binary sesarch O(logN)
 * 
 * isAscending? compare first and last val
 * 
 * while lo <= hi
 * 
 * mid = lo + (hi - lo) / 2
 * 
 * mid val == target? rt idx
 * 
 * if ascending?
 *   mid val > target? move to left
 *   mid val < target? move to right
 *   
 * if descending?
 * 	 mid val > target ? move to right
 *   mid val < target ? move to left
 * 
 * dedup?
 *     |
 * 1,1,1,1,2,2
 * ^         ^
 * 
 * i) mid val => target ? move to left (**hi = mid)
 * ii) else ? move to right (lo = mid + 1)
 * 
 * @author sunnypark
 *
 */
public class OrderAgnosticBinarySearch {
	public static int search(int[] arr, int key) {
	    int start = 0, end = arr.length - 1;
	    boolean isAscending = arr[start] < arr[end];
	    while (start <= end) {
	      // calculate the middle of the current range
	      int mid = start + (end - start) / 2;

	      if (key == arr[mid])
	        return mid;

	      if (isAscending) { // ascending order
	        if (key < arr[mid]) {
	          end = mid - 1; // the 'key' can be in the first half
	        } else { // key > arr[mid]
	          start = mid + 1; // the 'key' can be in the second half
	        }
	      } else { // descending order        
	        if (key > arr[mid]) {
	          end = mid - 1; // the 'key' can be in the first half
	        } else { // key < arr[mid]
	          start = mid + 1; // the 'key' can be in the second half
	        }
	      }
	    }
	    return -1; // element not found
	  }
}
