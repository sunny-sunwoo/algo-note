package sorting;

import java.util.Arrays;

/**
 * Merge Sort
 * 
 * 3 steps using recursion
 *   1. sort first half
 *   2. sort second half
 *   3. merge the sorted 2 halves
 *  
 * time complexity: O(NlogN)
 * 
 * @author sunnypark
 * 
 */
public class MergeSort {
	public static void mergesort(int[] nums) {
		sort(nums, 0, nums.length - 1);
	}
	
	private static void sort(int[] nums, int lo, int hi) {
		if (lo >= hi) return;
		
		int mid = lo + (hi - lo) / 2;
		sort(nums, lo, mid);
		sort(nums, mid + 1, hi);
		merge(nums, lo, mid, hi);
		
		System.out.println(Arrays.toString(nums));
	}
	
	private static void merge(int[] nums, int left, int mid, int right) {
		int leftLen = mid - left + 1;
		int rightLen = right - mid;
		
		int[] leftArr = new int[leftLen];
		int[] rightArr = new int[rightLen];
		
		System.arraycopy(nums, left, leftArr, 0, leftLen);
		System.arraycopy(nums, mid + 1, rightArr, 0, rightLen);
		
		int i = 0, j = 0;
		int ptr = left;
		
		while (i < leftLen && j < rightLen) {
			if (leftArr[i] < rightArr[j]) {
				nums[ptr++] = leftArr[i++];
			} else {
				nums[ptr++] = rightArr[j++];
			}
		}
		
		while (i < leftLen) {
			nums[ptr++] = leftArr[i++];
		}
		
		while (j < rightLen) {
			nums[ptr++] = rightArr[j++];
		}
	}
	
	public static void main(String[] args) {
		int[] nums = {12, 11, 13, 5, 6, 7, 1, 2};
		mergesort(nums);
	}

}
