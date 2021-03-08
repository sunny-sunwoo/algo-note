package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

/**
 * LC 239 Sliding Window Maximum
 * 
	Input: 
	nums = [1,3,-1,-3,5,3,6,7], k = 3
	                      ^
	Output: [3,3,5,5,6,7]
	
	queue = []
	 => peek first: should be max value
	 => queue will keep possible max values only
	 
	 1. check obsolete -> evict if idx too far (k <= curr idx - peek first idx)
	 2. smaller ones -> rm (bc/ smaller nums than curr val can't be max for any window moving forward)
 	 3. add curr to queue
 
 * @author sunnypark
 *
 */
public class LC239_SlidingWindowMaximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        
        if (k == nums.length) {
            return new int[]{ IntStream.of(nums).max().getAsInt() };
        }

        Deque<Integer> queue = new ArrayDeque<>();
        
        for (int i = 0; i < k - 1; i++) {
            int curr = nums[i];
            while (!queue.isEmpty() && nums[queue.peekLast()] < curr) {
                queue.removeLast();
            }
            queue.addLast(i);
        }
        
        int[] result = new int[nums.length + 1 - k];
        for (int i = k - 1; i < nums.length; i++) {
            if (!queue.isEmpty() && i - queue.peekFirst() >= k) { // evict idx too far
                queue.removeFirst();
            }
            
            while (!queue.isEmpty() && nums[queue.peekLast()] < nums[i]) { // rm smaller 
                queue.removeLast();
            }
            
            queue.addLast(i);
            result[i - k + 1] = nums[queue.peekFirst()];
        }
        
        return result;
    }
}
