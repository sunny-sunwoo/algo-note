package leetcode;

import java.util.Arrays;

/**
 * Q. Given an integer array nums, return the number of longest increasing subsequences.
 * Notice that the sequence has to be strictly increasing.
 * 
 * e.g. 
 * Input: nums = [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
 * 
 * 		  1,3,5,4,7
 *      	  ^
 *       	    ^
 * len = [1,2,3,3,4]      
 * cnt = [1,1,1,1,2]
 * 
 * when right = 4, 
 *   -> left = 2 ? len = 4, cnt = 1
 *   -> left = 3 ? len = 4, cnt = 2
 *   
 * right = 1 ~ len
 *  left = 0 ~ i - 1
 *  
 *   i) left val < right val && left len + 1 > right len ?  // 1,3,5,(7)
 *      => cnt = left cnt
 *      => len  = left len + 1
 *      
 *   ii) left val < right val && left len + 1 == right len ? 1,3,5,4,(7)
 *      => cnt += left cnt
 *   
 *   => keep maxLen
 *   
 * sum all cnts where len = maxLen
 * 
 * @author sunnypark
 *
 */
public class LC673_NumOfLIS {
	public static int findNumberOfLIS(int[] nums) {
        int[] len = new int[nums.length];
        int[] cnt = new int[nums.length];
        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);
        
        int maxLen = 1;
        for (int right = 1; right < nums.length; right++) {
            for (int left = 0; left < right; left++) {
                if (nums[left] >= nums[right]) { // skip any non-lis
                    continue;
                }
                
                // case 1
                if (len[left] + 1 > len[right]) {
                    len[right] = len[left] + 1;
                    cnt[right] = cnt[left]; // need to continue prev sum
                }
                
                // case 2
                else if (len[left] + 1 == len[right]) {
                    cnt[right] += cnt[left]; // need to accumulate sum 
                }
            }
            maxLen = Math.max(maxLen, len[right]);
        }
        
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            if (len[i] == maxLen) {
                result += cnt[i];
            }
        }

        return result;
    }
}
