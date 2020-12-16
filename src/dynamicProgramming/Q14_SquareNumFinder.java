package dynamicProgramming;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 1 0 1
 * 1 1 0
 * 1 1 0
 * => 7 (1*1 square: 6, 2*2 square: 1)
 * LC221. maximal square
 * @author sunnypark
 *
 */
public class Q14_SquareNumFinder {
	public static int findSquare(int[][] m) {
		int[][] cache = new int[m.length + 1][m[0].length + 1];
		
		for (int i = 1; i < cache.length; i++) {
			for (int j = 1; j < cache[i].length; j++) {
				if (m[i - 1][j - 1] == 0) continue;
				cache[i][j] = Math.min(cache[i - 1][j - 1], Math.min(cache[i][j - 1], cache[i - 1][j])) + 1;
			}
		}
		
		return getSum(cache);
	}
	
	private static int getSum(int[][] cache) {
		int res = 0;
		for (int[] eachRow: cache) {
			res += IntStream.of(eachRow).sum();
		}
		return res;
	}
	
	public static void main(String[] args) {
		int[][] input = {{1, 0, 1}, {1, 1, 0}, {1, 1, 0}};
		System.out.println(findSquare(input));
	}

}
