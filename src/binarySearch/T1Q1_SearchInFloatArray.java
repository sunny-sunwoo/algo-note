package binarySearch;

import java.util.Arrays;

public class T1Q1_SearchInFloatArray {
	public static int binarySearchInFloatArray(float[] arr, int t) {
		int pos = Arrays.binarySearch(arr, (float) t);
		if (pos < 0) {
			pos = Math.abs(pos + 1);
		}
		
		return Math.abs(t - arr[pos - 1]) < Math.abs(t - arr[pos]) ? pos - 1 : pos;
	}
	
	public static void main(String[] args) {
		float[] input = {1.2f, 2.3f, 3.3f};
		System.out.println(binarySearchInFloatArray(input, 2));
	}
}
