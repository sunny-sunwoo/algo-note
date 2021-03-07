package leetcode;

/**
 * LC 41. First Missing Positive
 * e.g. 
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * 
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * 
	1. check if the value is within 1 ~ nums.length
	  => if not, make it as nums.length + 1
	
	2. mark it in the corresponding index
	    * -1 to nums [value - 1] 
	
	3. if positive number found? => rt the pos + 1 
	   => otherwise, length

 * @author sunnypark
 *
 */
public class LC41_FirstMissingPositive {
	public int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                nums[i] = nums.length + 1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            int currVal = Math.abs(nums[i]);
            if (currVal == nums.length + 1) {
                continue;
            }
            if (nums[currVal - 1] > 0) {
                nums[currVal - 1] *= -1;
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        
        return nums.length + 1;
    }
}
