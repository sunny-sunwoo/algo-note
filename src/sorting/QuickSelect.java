package sorting;

/**
 * Quick Select
 * 
 * [4,3,1,2] [5] [7,10,6,12]
 * do partitioning & keep selecting range w/target only 
 * find 4 => drop the right subarr bc target is on the left side
 * time complexity: n + n/2 + n/4 + ... => 2n
 * 
 * @author sunnypark
 *
 */
public class QuickSelect {
	public static int quickSelect(int[] arr, int pos) {
		int len = arr.length;
		return quickSelect(arr, 0, len - 1, pos - 1);
	}
	
	private static int quickSelect(int[] arr, int start, int end, int pos) {
		int pivot = doPartition(arr, start, end);
		
		if (pivot == pos) {
			return arr[pos];
		}
		
		if (pivot > pos) {
			return quickSelect(arr, start, pivot - 1, pos);
		}
		
		return quickSelect(arr, start, pivot + 1, pos);
	}
	
	private static int doPartition(int[] arr, int lo, int hi) {
		int pivot = arr[hi];
		int left = lo;
		
		for (int i = lo; i < hi; i++) {
			if (arr[i] < pivot) {
				swap(arr, left++, i);
			}
		}
		
		swap(arr, left, hi);
		return left;
	}
	
	private static void swap(int[] arr, int x, int y) {
		int tmp = arr[x];
		arr[x] = arr[y];
		arr[y] = tmp;
	}
	
	public static void main(String[] args) {
		int[] input = {4,2,3,1,5,7,10,6,12}; // 4,2,3,1// 5 // 7,10,6,12
		System.out.println(quickSelect(input, 5)); // 5
		System.out.println(quickSelect(input, 8)); // 10
	}
}
