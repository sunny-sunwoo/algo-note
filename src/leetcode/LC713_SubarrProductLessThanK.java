package leetcode;

/**
 * LC713 subarr product less than k
 * 

 |
10, 5, 2, 6
    ^
 
 right = 0, prod = 10
   => [10]
   
 right = 1, prod = 50
   =>     [5] 
   => [10, 5]
   
 right = 2, prod = 100
  -> left = 1, prod = 10
    =>   [2]
    => [5,2]
    
 
 right = 3, prod = 60
  left = 1 
    =>     [6]
    =>   [2,6]
    => [5,2,6]
  

[approach] 2 ptrs
 left = 0, prod = 1
 
 right = 0 ~ len
   prod = prod * right val
   
   while prod >= k ? 
       left++
       prod = prod / left
   
   cnt += right - left + 1
   
  
 * @author sunnypark
 *
 */
public class LC713_SubarrProductLessThanK {
	public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int left = 0;
        int product = 1;
        int cnt = 0;

        for (int right = 0; right < nums.length; right++) {
            product *= nums[right];
            
            while (product >= k && left <= right) {
                product /= nums[left];
                left++;
            }
            
            cnt += right - left + 1;
        }
        
        return cnt;
    }
	
	public static void main(String[] args) {
		int[] nums = {10, 5, 2, 6};
		System.out.println(numSubarrayProductLessThanK(nums, 100));
	}
}
