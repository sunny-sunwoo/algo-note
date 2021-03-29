package leetcode;

/**
 * Q. Given an array nums sorted in non-decreasing order, and a number target, 
 * return True if and only if target is a majority element.
 * @author sunnypark
 *
 */
public class LC1150_MajorityElementNumberInSortedArr {
	public boolean isMajorityElement(int[] nums, int target) {
        int candidate1 = nums[nums.length / 2];
        if (candidate1 == target && isMajority(candidate1, nums)) {
            return true;
        }
        
        if (nums.length % 2 == 0) {
            int candidate2 = nums[nums.length/2 - 1];
            if (candidate2 == target && isMajority(candidate2, nums)) {
                return true;
            }
        }
        return false;
    }
    
    // can use bisectLeft and bisectRight
	private static boolean isMajority(int candidate, int[] nums) {
        int leftIdx = bisectLeft(nums, candidate);
        int rightIdx = bisectRight(nums, candidate);
        return (rightIdx - leftIdx + 1) > nums.length/2;
    }

	private static int bisectLeft(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {
			int mid = lo + (hi - lo) / 2;
			if (nums[mid] >= target) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		 return nums[lo] == target ? lo : -1;
	}
	
	// mid val == target ? lo = mid
	// mid val < target ? lo = mid + 1
	// mid val > target ? hi = mid - 1
	private static int bisectRight(int[] nums, int target) {
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) {
			int mid = lo + (hi - lo + 1) / 2;
			if (nums[mid] <= target) {
				lo = mid;
			} else {
				hi = mid - 1;
			}
		}
		return nums[hi] == target ? hi : -1;
	}
	
    private static boolean isMajority_linear(int candidate, int[] nums) {
        int cnt = 0;
        for (int num : nums) {
            if (num == candidate) {
                cnt++;
            }
        }
        return cnt > nums.length / 2;
    }
    
    public static void main(String[] args) {
    	int[] nums = {0,1,1,1,1,1,1,2};
    	System.out.println(bisectLeft(nums,1)); // 1
    	System.out.println(bisectRight(nums,1)); // 6
    	
    	int[] nums2 = {0,1,1,1,1,2,2,2};
    	System.out.println(bisectLeft(nums2,2)); // 5
    	System.out.println(bisectRight(nums,2)); // 7
    }
}
