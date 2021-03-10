package practice;

/**
 * Q. Array index and element equality
 * ref: pramp

input: arr = [-8,0,2,5]
output: 2 # since arr[2] == 2

input: arr = [-1,0,3,6]
output: -1 # since no index in arr satisfies arr[i] == i.

[approach1] linear scan O(n)
[approach2] binary search O(logN)
	- possible because input is sorted arr of distinct integers
	
	- while (lo <= hi): lo, hi can point to the same number
	
	   1. if mid value == target?  
        => keep idx to tmp
        => search to the left side in case e.g. [-1,1,2,6,7]

	   2. mid value < target 
	        => search to the right
	  
	   3. otherwise
	        => to the left
	
 * @author sunnypark
 *
 */
public class ArrayIndexElementEquality {
	public static int indexEqualsValueSearch(int[] arr) {
		int lo = 0, hi = arr.length - 1;
		int tmp = -1;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (arr[mid] == mid) {
				tmp = mid;
				hi = mid - 1;
			}

			if (arr[mid] < mid) {
				// search to the right
				lo = mid + 1;
			} else {
				// to the left
				hi = mid - 1;
			}
		}

		return tmp;
	}

	public static void main(String[] args) {
		int[] arr = {-8,0,2,5};
		System.out.println(indexEqualsValueSearch(arr)); // expected: 2
	}
}
