package leetcode;

import java.util.Arrays;

/**
 * Q. Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * @author sunnypark
 *
 */
public class LC169_MajorityElement {
	public int majorityElement_sort(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
	
	public int majorityElement_quickSelect(int[] nums) {
        return quickSelect(nums, 0, nums.length - 1, nums.length/2);
    }
    
    private int quickSelect(int[] nums, int start, int end, int pos) {
        int pivot = doPartition(nums, start, end);
        
        if (pivot == pos) {
            return nums[pivot];
        }
        
        if (pivot > pos) {
            return quickSelect(nums, start, pivot - 1, pos);
        }
        
        return quickSelect(nums, pivot + 1, end, pos);
    }
    
    private int doPartition(int[] arr, int lo, int hi) {
        int left = lo;
        int pivot = arr[hi];
        
        for (int i = left; i < hi; i++) {
            if (arr[i] < pivot) {
                swap(arr, left++, i);
            }
        }
        swap(arr, left, hi);
        return left;
    }
    
    private void swap(int[] arr, int x, int y) {
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
}
