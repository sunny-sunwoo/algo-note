package dynamicProgramming;

import java.util.HashMap;
import java.util.Map;

/**
 * LC523 Continuous Subarray Sum
 * Q. write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k
 * 
 * e.g.
 * Input: [23, 2, 4, 6, 7],  k=6
 * Output: True
 * Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
 * 
 * solution.
 * 
 * HM<I, I> cache => map mod to idx

	[23 2 4 6 7]
	=> accSum: [0, 23, 25, 29, 35, 42]
	=> accSum by mod k: [0, 5, 1, 5, 5, 0]
	
	cache 
	(0 = 0)
	(5 = 1)
	(1 = 2)
	
	when cache has the prev idx with same mod value => multiple of k found! 
	
 * logic.
 *
	iter thru pSum
	 => find prev
	 => if not null, check the idx gap > 2  -> rt true
	 => else, put curr idx to cache

 * @author sunnypark
 *
 */
public class Q38_ContinuousSubarrSum {
	public static boolean checkSubarraySum(int[] nums, int k) {
        int[] pSumByMod = buildPsumArr(nums, k);
        
        Map<Integer, Integer> cache = new HashMap<>(); // psum to idx
        for (int i = 0; i < pSumByMod.length; i++) {
            int curr = pSumByMod[i];
            Integer prev = cache.get(curr);
            if (prev != null) {
                if (i - prev >= 2) {
                    return true;
                }
            } else {
                cache.put(curr, i);
            }
        }
        return false;
    }
    
    private static int[] buildPsumArr(int[] nums, int k) {
        int[] arr = new int[nums.length + 1];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            arr[i + 1] = arr[i] + nums[i];
        }
        
        for (int i = 0; i < arr.length; i++) {
            if (k != 0) {
                arr[i] %= k;
            }
        }
        return arr;
    }
    
    public static void main(String[] args) {
    	int[] nums = new int[]{23, 2, 4, 6, 7};
    	System.out.println(checkSubarraySum(nums, 6));
    }

}
