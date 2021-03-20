package leetcode;

/**
 * Q31 next permutation
 * 
  [approach]
	1. from backward, find starting point where ascending seq appears for the first time
	   (= point i where i val < j val)
	   => no such starting point? rv the entire arr

	2. find swap point
	    => smallest val after i larger than i val
	
	3. swap 2 points above
	
	4. sort the descending part right after starting point
	    => simply reverse because it's in still descending order  / \  
	
	cant permute? sorted arr (lowest possible)
	
	1,3,6,5,4  => find points to start, swap
	  ^     ^
	
	1,4,6,5,3  => still descending after start point
	    <--->
	
	1,4,3,5,6  => sort descending part

  [time complexity] O(N)

 * @author sunnypark
 *
 */
public class LC31_NextPermutation {
	public static void nextPermutation(int[] nums) {
        int startingPoint = findStartingPoint(nums);
        if (startingPoint == -1) {
            reverseArray(nums, 0, nums.length - 1);
            return;
        }
        int swapPoint = findSmallest(nums, startingPoint + 1, nums[startingPoint]);
        swap(nums, startingPoint, swapPoint);
        reverseArray(nums, startingPoint + 1, nums.length - 1);
    }
    
    private static int findStartingPoint(int[] nums) {
        int pos = -1;
        
        for (int j = nums.length - 1; j > 0; j--) {
            if (nums[j - 1] < nums[j]) {
                pos = j - 1;
                break;
            }
        }
        return pos;
    }
    
    private static void reverseArray(int[] arr, int left, int right) {
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }
    
    private static int findSmallest(int[] nums, int pos, int target) {
        int minIdx = pos;
        for (int i = pos; i < nums.length; i++) {
            if (nums[i] <= target) {
                continue;
            }
            if (nums[i] <= nums[minIdx]) {
                minIdx = i;
            }
        }
        return minIdx;
    }

    private static void swap(int[] arr, int x, int y) {
        int tmp = arr[y];
        arr[y] = arr[x];
        arr[x] = tmp;
    }
}
