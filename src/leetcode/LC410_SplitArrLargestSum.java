package leetcode;

import java.util.stream.IntStream;

/**
 * LC 410 Split Array Largest Sum
 * Q. Given an array nums which consists of non-negative integers and an integer m, 
 * you can split the array into m non-empty continuous subarrays.
 * 
 * return the minimized largest sum among m arrs.
 * 
 * [Approach] binary search
 * possible answer range? largest num ~ total sum
 *                           lo             hi
 * 
 * if valid cut? 
 *   explore more to find smaller sum
 *   
 * otherwise
 *   sum is on the right side (larger num)

	e.g.
	arr [1,2,3,4,5], m = 2
	
	possible sum range => 5 ~ 15
	
	lo = 5, hi = 15, mid = 10 -> valid (try to find smaller sum)
	lo = 5, hi = 9, mid = 7 -> invalid
	lo = 8, hi = 9, mid = 8 -> invalid	
	lo = 9, hi = 9. mid = 9 -> valid
	lo = 9, hi = 8 => ANSWER FOUND @ lo

 * 
 * @author sunnypark
 *
 */
public class LC410_SplitArrLargestSum {
	public static int splitArray(int[] nums, int m) {
        int lo = IntStream.of(nums).max().getAsInt();
        int hi = IntStream.of(nums).sum();
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isValidCut(nums, mid, m)) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    
    private static boolean isValidCut(int[] nums, int bound, int m) {
        int cnt = 1;
        int curSum = 0;
        for (int num : nums) {
            curSum += num;
            if (curSum > bound) {
                cnt++;
                curSum = num;
            }
        }
        return cnt <= m;
    }
    
    public static void main(String[] args) {
    	int[] nums1 = {7,2,5,10,8};
    	System.out.println(splitArray(nums1, 2));
    	
    	int[] nums2 = {1,2,3,4,5};
    	System.out.println(splitArray(nums2, 2));
    	
    	int[] nums3 = {1,4,4};
    	System.out.println(splitArray(nums3, 3));
    }
}
