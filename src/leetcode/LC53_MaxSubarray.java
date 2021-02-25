package leetcode;

/**
 * LC 53 maximum subarray 
 * q. find the contiguous subarray with the largest sum, and return its sum
 * 
               
	nums =   [-2,1,-3,4,-1,2,1,-5,4]
             ^   
	choice: 1 OR sum so far + 1 
		
	curr sum = max (nums[i], curr sum + nums[i])
	max sum = max(maxSum, currSum)

	TC: O(N)

 * @author sunnypark
 *
 */
public class LC53_MaxSubarray {
	public static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(currSum + nums[i], nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }
}
