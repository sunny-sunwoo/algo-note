package practice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Optimizing Box Weight
 * https://leetcode.com/discuss/interview-question/1021441/Amazon-OA-or-optimizating-Box-Weight
 * 
 * Q. We can divide the numbers into two subsets A = [4, 5] and B = [1, 2, 2, 3]. 
 * The sum of A is 9 which is greater than the sum of B which is 8. 
 * There are other ways to divide but A = [4, 5] is of minimal size of 2.
 * 
 * e.g. 
 * Input:
 * nums = [4, 5, 2, 3, 1, 2]
 * 
 * Output:
 * [4, 5]
 * 
 * @author sunnypark
 *
 */
public class OptimizingBoxWeight {
	public static List<Integer> getHeavierSetA(List<Integer> arr) {
		List<Integer> res = new ArrayList<>();
		Collections.sort(arr);
		
		long totalSum = arr.stream().mapToLong(i -> i).sum();
		long currSum = 0;
		
		for (int i = arr.size() - 1; i >= 0; i--) {
			currSum += arr.get(i);
			res.add(arr.get(i));
			if (currSum > totalSum / 2) {
				break;
			}
		}
		reverseList(res);
		return res;
	}
	
	private static void reverseList(List<Integer> list) {
		int i = 0;
		int j = list.size() - 1;
		while (i < j) {
			int tmp = list.get(i);
			list.set(i, list.get(j));
			list.set(j, tmp);
			i++;
			j--;
		}
	}
}
