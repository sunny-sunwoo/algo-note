package leetcode;

/**
 * 
 * LC 04. Median of Two Sorted Arrays
 * 
 *  T.C. should be O(log(m + n))
 *  => binary search 
 *  
 *  idea) public level
	len = nums1.length + nums2.length
	res = findKth(nums1, 0, nums2, 0, len / 2 + 1)
	
	if len is even?
	res += findKth(nums1, 0, nums2, 0, len / 2)
	res = res / 2;
	
	
	idea) findKth logic
	
	e.g. k = 4
	  |
	1,3,4,6
	3,5,6,8
	  |
	
	=> edge case:
	i) pos1/pos2 - out of bound?
	ii) k == 1? smaller from pos1/pos2
	
	=> decide pos for comparison between arr1, 2
	arr1 pos to compare = pos1 + k/2 - 1 or last elem
	arr2 pos to compare = pos2 + k/2 - 1 or last elem
	
	=> proceed with arr1 or 2 based on comparison
	arr1[comp1] < arr2[comp2] ? comp1, pos2, k/2
	else? pos1, comp2, k/2
	
	
	[NOTE]
	- come up with edge cases first.
	- for the next call, k -> k/2 is not always the case! need to calculate the # of remaining manually
	
 * @author sunnypark
 *
 */

public class LC04_MedianOfTwoSortedArrays {
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        int res = findKth(nums1, 0, nums2, 0, len / 2 + 1);

        if (len % 2 != 0) {
            return Double.valueOf(res);
        }
        
        res += findKth(nums1, 0, nums2, 0, len / 2);
        return Double.valueOf(res) / 2.0;
    }
    
    private static int findKth(int[] nums1, int pos1, int[] nums2, int pos2, int k) {
        if (nums1.length <= pos1) {
            return nums2[pos2 + k - 1];
        }
        
        if (nums2.length <= pos2) {
            return nums1[pos1 + k - 1];
        }
        
        if (k == 1) {
            return Math.min(nums1[pos1], nums2[pos2]);
        }
        
        int comp1 = Math.min(pos1 + k/2 - 1, nums1.length - 1);
        int comp2 = Math.min(pos2 + k/2 - 1, nums2.length - 1);
        
        if (nums1[comp1] < nums2[comp2]) { // proceed with arr1
            int explored = comp1 - pos1 + 1;
            return findKth(nums1, comp1 + 1, nums2, pos2, k - explored);
        } else {
            int explored = comp2 - pos2 + 1;
            return findKth(nums1, pos1, nums2, comp2 + 1, k - explored);
        }
    }
}
