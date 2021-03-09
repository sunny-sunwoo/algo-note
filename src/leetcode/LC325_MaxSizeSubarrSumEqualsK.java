package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LC 325. Maximum Size Subarray Sum Equals k
 * 
 * Approach
	iter thru nums
	- hashmap contains key of acc sum - k
	    => if so, max len can be idx - hm val
	- if hm not contains key ? put curr acc sum in hashmap
	    => bc, we want the longer subarr

 * @author sunnypark
 *
 */
public class LC325_MaxSizeSubarrSumEqualsK {
	public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> hm = new HashMap<>();
        
        int acc = 0;
        int maxLen = 0;
        
        hm.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            acc += nums[i];
            if (hm.containsKey(acc - k)) {
                maxLen = Math.max(maxLen, i - hm.get(acc - k));
            }
            
            if (!hm.containsKey(acc)) {
                hm.put(acc, i);
            }
        }
        
        return maxLen;
    }
}
