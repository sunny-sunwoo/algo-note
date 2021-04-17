package dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Q. find min jump path. 
 * 
 * e.g. 
 * Input: nums = [2,3,1,1,4]
 * Output: [0, 1]
 * 
 * leetcode) 
 * jump game2: find number of min jump
 * https://leetcode.com/problems/jump-game-ii/solution/
 * @author sunnypark
 * 
 */
public class Q07_ArrayHooper {
	public static List<Integer> findMinJumpPath(int[] arr) {
		List<Integer> path = new ArrayList<>();
		int currMax = 0; // reachable index in current jump
		int possibleMax = 0; // possibly reachable index with max jump from current index 
		int possibleMaxIdx = 0; // index for possible max jump
		
		for (int i = 0; i < arr.length; i++) {
			
			// 1) if index passes the curr max
			if (currMax < i) {
				 // edge case: [2, 0, 0, 0]
				if (possibleMax == currMax) {
					return List.of();
				}
				path.add(possibleMaxIdx);
				currMax = possibleMax;
			}

			// 2) if possible max needs update
			// which means jump from curr index should be picked
			if (possibleMax < i + arr[i]) {
				possibleMax = i + arr[i];
				possibleMaxIdx = i;
			}
		}

		return path;
    }
    
    public static void main(String[] args) {
        int[] input1 = {2,3,1,1,2,4,2,0,1,1};
        int[] input2 = {2,0,0,0};
        System.out.println(findMinJumpPath(input1));
        System.out.println(findMinJumpPath(input2));
    }
}
