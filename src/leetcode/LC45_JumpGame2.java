package leetcode;

public class LC45_JumpGame2 {
	public static int getMinJump(int[] nums) {
        int possibleMax = 0;
        int currentMax = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > currentMax) {
                if (currentMax == possibleMax && currentMax < nums.length - 1) { // e.g. [2,2,0,0,0]
                    return -1;
                }
                currentMax = possibleMax;
                count++;
            }
            possibleMax = Math.max(possibleMax, i + nums[i]);
        }
        return count;
    }
	
	// if we can assume that you can always reach the last index.
	public static int jump_leetcode(int[] nums) {
        int count = 0, currentMax = 0, possibleMax = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
        	possibleMax = Math.max(possibleMax, i + nums[i]);
            
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentMax) {
            	count++;
                currentMax = possibleMax;
            }
        }
        return count;
    }
	
	public static void main(String[] args) {
		int[] nums1 = {2,2,0,0,4};
		int[] nums2 = {2,3,0,0,4};
		int[] nums3 = {2,2,0,1,4};
		System.out.println(getMinJump(nums1)); // -1
		System.out.println(getMinJump(nums2)); // 2
		System.out.println(getMinJump(nums3)); // 3
	}
}
