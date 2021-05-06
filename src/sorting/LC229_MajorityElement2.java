package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Q. Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.
 *   1 <= nums.length <= 5 * 10^4
 *   -10^9 <= nums[i] <= 10^9
 *   
 * Approach1. Boyer-Moore Voting Algorithm
 * 
 * 1,1,1,3,3,2,2,2
 *               ^
 * num1 = 1
 * cnt1 = 1
 * 
 * num2 = 2
 * cnt2 = 1
 * 
 * 1. iter thru arr and find candidates for num1, num2
 * num1 == curr val
 *   => cnt1 ++
 *   
 * num2 == curr val
 *   => cnt2 ++
 *   
 * cnt1 == 0?
 *  => assign as num1, cnt1++
 *   
 * cnt2 == 0?
 *  => assign as num2, cnt2++
 *  
 * otherwise?
 *  => decrement
 *  
 * 2. verify and build res
 * 
 * => tc: O(N)
 * 
 * Approach2. Quick Select
 *  => QS will provide what's the number at position X
 *  
 * 1,1,2,2,2,3,3,3
 *     ^     ^
 * quick select to check major positions: k - 1, 2k - 1, ...
 *  
 * verify and build res
 * @author sunnypark
 *
 */
public class LC229_MajorityElement2 {
	public List<Integer> majorityElement_quickSelect(int[] nums) {
        if (nums.length <= 1) {
            return IntStream.of(nums).boxed().collect(Collectors.toList());
        }
        
        List<Integer> res = new ArrayList<>();
        
        int pos1 = (int)Math.ceil(nums.length/3.0) - 1;
        int pos2 = (int)Math.ceil(nums.length/3.0)*2 - 1;
        Integer num1 = null;
        Integer num2 = null;
        
        num1 = quickSelect(nums, 0, nums.length - 1, pos1);
        
        if (pos2 != pos1) {
            num2 = quickSelect(nums, 0, nums.length - 1, pos2);
        }
        
        int cnt1 = 0;
        int cnt2 = 0;
        for (int num : nums) {
            if (num1 != null && num == num1) {
                cnt1++;
            } else if (num2 != null && num == num2) {
                cnt2++;
            }
        }
        
        if (cnt1 > nums.length / 3) {
            res.add(num1);
        }
        
        if (cnt2 > nums.length / 3) {
            res.add(num2);
        }
        
        return res;
    }
	
	private static int quickSelect(int[] nums ,int lo, int hi, int pos) {
		int pivot = doPartition(nums, lo, hi);
		if (pivot == pos) {
			return nums[pivot];
		}
		
		if (pivot > pos) {
			return quickSelect(nums, lo, pivot - 1, pos);
		}
		
		return quickSelect(nums, pivot + 1, hi, pos);
	}
	
	private static int doPartition(int[] nums, int lo, int hi) {
		int left = lo;
		int lastNum = nums[hi];
		
		for (int i = lo; i < hi; i++) {
			if (nums[i] < lastNum) {
				swap(nums, left++, i);
			}
		}
		swap(nums, left, hi);
		return left;
	}
	
	private static void swap(int[] nums, int x, int y) {
		int tmp = nums[x];
		nums[x] = nums[y];
		nums[y] = tmp;
	}
	
	public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();        
        Integer num1 = null;
        Integer num2 = null;
        int cnt1 = 0, cnt2 = 0;

        for (int num : nums) {
            if (num1 != null && num1 == num) {
                cnt1++;
            } else if (num2 != null && num2 == num) {
                cnt2++;
            } else if (cnt1 == 0) {
                num1 = num;
                cnt1++;
            } else if(cnt2 == 0) {
                num2 = num;
                cnt2++;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (num1 != null && num == num1) {
                cnt1++;
            } else if (num2 != null && num == num2) {
                cnt2++;
            }
        }
        
        if (cnt1 > nums.length / 3) {
            res.add(num1);
        }
        
        if (cnt2 > nums.length / 3) {
            res.add(num2);
        }
        
        return res;
    }
}
