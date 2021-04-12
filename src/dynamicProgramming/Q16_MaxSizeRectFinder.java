package dynamicProgramming;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * max recycling in 2d arr
 * int[][] input = [
 * [1,0,1,1],
 * [1,1,1,1],
 * [0,0,1,1],
 * [0,1,0,1]
 * ]
 * 
 * output => 6
 * 
 * for each row
 * 	build heights list
 * 	max rect area w/ heights 
 *  keep max area
 *  
 *  
 *  [2,1,3,4,2,0]
 *   ^
 *         |
 *       | |
 *   |   | | |
 *   | | | | | _
 *     1     4 5 
 *   
 *  stack = [   ]
 *  
 *  tallestIdx = stack.pop()
 *  width = tallestIdx - stastack.peek() - 1 OR i
 *  height = heights.get(tallestIdx);
 *  curr area = w * h
 * 
 *  keep valid heights 
 * @author sunnypark
 *
 */
public class Q16_MaxSizeRectFinder {
	public static int maxRectArea(int[] input) {
		int[] heights = new int[input.length + 2];
		System.arraycopy(input, 0, heights, 1, input.length);
		Deque<Integer> stack = new ArrayDeque<>();
		stack.addLast(0);
		int maxArea = 0;

		for (int i = 1; i < heights.length; i++) {
			int curr = heights[i];
			while (!stack.isEmpty() && heights[stack.peekLast()] > curr) {
				int tallestIdx = stack.removeLast();
				int height = heights[tallestIdx];
				int width = i - stack.peekLast() - 1;
				maxArea = Math.max(maxArea, width * height);
			}
			stack.addLast(i);
		}
		return maxArea;
	}
	
	public static void main(String[] args) {
		int[] input = {2,1,3,4,2};
		System.out.println(maxRectArea(input));
	}
}
