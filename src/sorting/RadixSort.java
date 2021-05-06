package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Radix sort (Bucket sort)

Q. sort base-10 numbers array
e.g. [1, 100, 4, 17, 22]
    => max digit = 3 (100)
    
	List<Integer>[] buckets: array of integer list
	bucket idx = (curr val / exp) % 10
	exp = 1 -> 10 -> 100 -> ... 
	
	idx - list
	0	[100]
	1	[1]
	2	[22]
	3
	4	[4]
	5
	6
	7	[17]
	8
	9
	
	=> [100, 1, 22, 4, 17]: sorted by num @ 1
	=> [100 / 1, 4 / 17 / 22] : sorted by num @ 10
	=> [1, 4, 17, 22, 100]: sorted by num @ 100
	
 [time complexity] O(maxDigit * N)
 
 * @author sunnypark
 *
 */
public class RadixSort {
	public static void radixSort(int[] arr) {
		int maxDigit = (int) Math.log10(IntStream.of(arr).max().getAsInt()) + 1;
		int exp = 1;
		for (int i = 0; i < maxDigit; i++) {
			List<Integer>[] buckets = init();
			
			for (int num : arr) {
				int bucketIdx = (num / exp) % 10;
				buckets[bucketIdx].add(num);
			}
			
			int cnt = 0;
			for (List<Integer> bucket : buckets) {
				if (bucket.isEmpty()) {
					continue;
				}
				for (int val : bucket) {
					arr[cnt++] = val;
				}
			}
			exp *= 10;
		}
		System.out.println(Arrays.toString(arr));
	}
	
	private static List<Integer>[] init() {
		List<Integer>[] buckets = new List[10];
		for (int i = 0; i < 10; i++) {
			buckets[i] = new ArrayList<>();
		}
		return buckets;
	}
	
	public static void main(String[] args) {
		int[] input = {1, 100, 4, 17, 22};
		radixSort(input);
	}
}
