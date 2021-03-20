package grokking.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Q. unsorted arr, 4 list
 * Input: [4, 1, 2, -1, 1, -3], target=1
 * Output: [-3, -1, 1, 4], [-3, 1, 1, 2]
 * 
 * => 2^n
 * 
 * [approach] backtracking
 * sort from public level // nlogn
 * 
 * terminated condition:
 * 	  if path size = 4
 *       -> if sum == target ? add to res
 *       -> otherwise, rt 
 * 
 * for each num
 *  => path with curr num // recursive call
 *  => path without curr num // recursive call
 *  
 * @author sunnypark
 *
 */
public class Practice {
	public static Set<List<Integer>> findFourSum(int[] arr, int target) {
		Arrays.sort(arr);
		Set<List<Integer>> res = new HashSet<>();
		for (int i = 0; i < arr.length; i++) {
			backtrack(arr, i, target, res, new ArrayList<>());
		}
		return res;
	}
	
	private static void backtrack(int[] arr, int pos, int target, Set<List<Integer>> res, List<Integer> path) {
		
		if (path.size() == 4) {
			if (target == 0) {
				res.add(new ArrayList<>(path));
			}
			return;
		}
		
		if (pos >= arr.length) {
			return;
		}
		
		path.add(arr[pos]);
		backtrack(arr, pos + 1, target - arr[pos], res, path);
		
		path.remove(path.size() - 1);
		backtrack(arr, pos + 1, target, res, path);
	}
	
	public static void main(String[] args) {
		int[] arr = {4, 1, 2, -1, 1, -3};
		System.out.println(findFourSum(arr, 1));  // expected: [[-3, -1, 1, 4], [-3, 1, 1, 2]]
	}
}
