package dynamicProgramming;

import java.util.stream.IntStream;

/**
 * Arithmetic Sequence
 * [1, 3, 5, 11, 14, 7, 9, 13]
 *  => [1,3,5,7,9]
 *  => return 5
 *  
 *  2: (1,3) (3,5) (5,7) (7,9)
 *      0 1   1 2   2 5   5 6
 *  4:
 *  
 *  1. build hash map   
 *     diff => pair(idx1, idx2)
 *     
 *  2. iter thru entry set
 *     0 1 2 3 4 5 6 7
 *     1 2 3     4 5
 *     
 *     res[second] = res[first] + 1
 *  
 *  3. keep max candidate 
 *     IntStream.of(res).mapToInt().max()
 *     
 * @author sunnypark
 *
 */
public class Q9_ArithmeticSequence {
	public static void main(String[] args) {
		int[] arr = {1, 3, 5};
		System.out.print(IntStream.of(arr).max().getAsInt());
		// guava: Ints.max(arr)
	}
}
