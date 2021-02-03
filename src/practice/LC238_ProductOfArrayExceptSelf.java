package practice;

/**
 * LC238 ProductOfArrayExceptSelf
 * 
      1     2    3    4
=>         1   1,2  1,2,3
    2,3,4  3,4  4
 * @author sunnypark
 *
 */
public class LC238_ProductOfArrayExceptSelf {
	public static int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
       
        int right = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = right;
            right *= nums[i];
        }
        
        int left = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= left;
            left *= nums[i];
        }
        
        return result;
    }
	
	public static void main(String[] args) {
		int[] arr = {1,2,3,4};
		System.out.println(productExceptSelf(arr));
	}
}
