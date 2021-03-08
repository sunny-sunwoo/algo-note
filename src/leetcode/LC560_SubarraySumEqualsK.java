package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * LC560 Subarray Sum Equals K
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * 
 *  
 * 0 1 2 3
 * 1 1 1 
 * ^ 
 * if (sum - k) was marked! 
 *  starting point
 *  
 * @author sunnypark
 *
 */
public class LC560_SubarraySumEqualsK {
	 public int subarraySum(int[] nums, int k) {
	        Map<Integer, Integer> hm = new HashMap<>();
	        hm.put(0, 1); // base case
	        
	        int res = 0;
	        int sum = 0;
	        
	        for (int i = 0; i < nums.length; i++) {
	            sum += nums[i];
	            if (hm.containsKey(sum - k)) {
	                res += hm.get(sum - k);
	            }
	            
	            hm.put(sum, hm.getOrDefault(sum, 0) + 1);
	        }
	        
	        return res;
	    }
}
