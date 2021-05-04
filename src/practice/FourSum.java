package practice;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Four Sum
 * 
input:  arr = [2, 7, 4, 0, 9, 5, 1, 3], s = 20             
output: [0, 4, 7, 9]

[public]
1. sort arr
   [0, 1, 2, 3, 4, 7, 9]
    ^
    
2.  iter thru the input arr
    fix the first num
    find the answer for 3 sum
  

[private] 3 sum (arr, target)
  iter thru the arr
    - fix second num
    - find answer for 2 sum
    
  
[private] 2 sum (arr, target)
  [0, 4, 7, 9]
   ^        ^
  lo        hi
  
  curr sum = lo val + hi val
  
  curr sum == target => Pair.of(lo, hi)
  
  curr sum < target 
      => move to right
      
  curr sum > target
      => move left
    
   
 [time complexity] 
  N^3 * logN    

 [brute force] O(N^4)
  for => N
   for => N
     for    => NlogN
       for 
 * @author sunnypark
 *
 */
public class FourSum {
	// time: O(N^3 + NlogN)
	// space: O(1)
	static int[] findArrayQuadruplet(int[] arr, int s) {
		// sort
		Arrays.sort(arr);

		// fix the first num, and 3 sum
		for (int i = 0; i < arr.length; i++) { 
			int first = arr[i];
			Deque<Integer> candidate = getThreeSum(arr, i + 1, s - first);

			if(candidate.size() == 3) {
				candidate.addFirst(first);
				return postprocess(candidate); // q -> int arr
			}
		}
		return new int[0];
	}

	private static int[] postprocess(Deque<Integer> candidate) {
		int[] result = new int[4];
		for (int i = 0; i < result.length; i++) {
			result[i] = candidate.removeFirst();
		}
		return result;
	}

	private static Deque<Integer> getThreeSum(int[] arr, int idx, int target) {
		for (int i = idx; i < arr.length; i++) {
			int second = arr[i];
			Deque<Integer> candidate = getTwoSum(arr, i + 1, target - second);

			if (candidate.size() == 2) {
				candidate.addFirst(second);
				return candidate;
			}
		}

		return new ArrayDeque<>();
	}

	private static Deque<Integer> getTwoSum(int[] arr, int idx, int target) { // O(N) with sorted arr
		int lo = idx;
		int hi = arr.length - 1;

		Deque<Integer> pair = new ArrayDeque<>();
		while (lo < hi) {
			int currSum = arr[lo] + arr[hi];
			if (currSum == target) {
				pair.addLast(arr[lo]);
				pair.addLast(arr[hi]);
				return pair;
			}

			if (currSum < target) {
				lo++;
			} else {
				hi--;
			}
		}
		return pair;
	}
	
	public static void main(String[] args) {
		int[] arr = {4,4,4,4};
		System.out.println(Arrays.toString(findArrayQuadruplet(arr, 16)));
	}
	
}
