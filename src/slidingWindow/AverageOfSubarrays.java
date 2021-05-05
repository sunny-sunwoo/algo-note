package slidingWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array, find the average of all contiguous subarrays of size ‘K’ in it.
 * input: Array: [1, 3, 2, 6, -1, 4, 1, 8, 2], K=5
 * Output: [2.2, 2.8, 2.4, 3.6, 2.8]
 * @author sunnypark
 * \
 *
 */
public class AverageOfSubarrays {
	public static double[] findSubarrayAverage(int[] arr, int k) {
		double[] result = new double[arr.length - k + 1];
		double windowSum = 0;
		int windowStart = 0;
		
		for (int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
			windowSum += arr[windowEnd];
			
			if (windowEnd >= k - 1) {
				result[windowStart] = (windowSum / k);
				windowSum -= arr[windowStart];
				windowStart++;
			}
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 3, 2, 6, -1, 4, 1, 8, 2};
		double[] output = findSubarrayAverage(arr, 5);
		System.out.println(Arrays.toString(output));
	}
}
