package leetcode;

public class LC283_MoveZeroes {
    public static void moveZeroes(int[] nums) {
        int ptr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, ptr++, i);
            }
        }
    }
    
    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
