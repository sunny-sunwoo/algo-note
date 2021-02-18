package list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Q16. equilibrium points finder
 * 
 * input int[] 
 * output: List<Integer>
 * 
 * eq point = specific position where left sum == right sum
 * 
 * 1, 2, 4, 3,
 * ^
 * => [2]
 * 
 * totalSum = 10
 * leftSum = 1
 * rightSum = total - left - curr 
 *
 * 
 * linear scan
 *  if left sum == right sum 
 *     => add idx to res 
 *     
 * @author sunnypark
 *
 */
public class Q16_EquilibriumPointsFinder {
	public static List<Integer> equilibriumPointsFinder(int[] nums) {
		if (nums.length <= 2) {
			throw new IllegalArgumentException("invalid input size");
		}
		int total = IntStream.of(nums).sum();
		int leftSum = 0;
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i < nums.length; i++) {
			leftSum += nums[i - 1];
			int rightSum = total - leftSum - nums[i];
			if (leftSum == rightSum) {
				result.add(i);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 4, 3};
		System.out.print(equilibriumPointsFinder(arr));
	}
}
