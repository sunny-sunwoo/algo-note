package leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * LC 528. Random Pick with Weight
 * e.g. [1,4]
 * using rand, pick from 0<=...<4 => 0,1,2,3 (+1) => 1,2,3,4
   binary search: 1 => 0, 2.3.4 => 1
   
 * [NOTE]
 * 1. random will give you range from ZERO. need to be calibrated accordingly.
 * 2. binary search will return right bound! first index on the right side(where to get in) when value doesnt exist.
 * 
 * @author sunnypark
 *
 */
public class LC528_RandomPickWithWeight {
	private static Random rand;
	private static int[] acc;
    
    public LC528_RandomPickWithWeight(int[] w) {
        rand = new Random();
        acc = new int[w.length];
        
        int sum = 0;
        for (int i = 0; i < w.length; i++) {
            sum += w[i];
            acc[i] = sum;
        }
    }
    
    public static int pickIndex() {
        int valueToPick = rand.nextInt(acc[acc.length - 1]) + 1; 
        
        int idxToPick = Arrays.binarySearch(acc, valueToPick);
        if (idxToPick < 0) {
            idxToPick = Math.abs(idxToPick + 1);
        }
        return idxToPick;
    }
    
    public static void main(String[] args) {
    	int[] w = new int[]{1, 3};
    	LC528_RandomPickWithWeight picker = new LC528_RandomPickWithWeight(w);
    	System.out.println(pickIndex());
    	System.out.println(pickIndex());
    	System.out.println(pickIndex());
    	System.out.println(pickIndex());
    	System.out.println(pickIndex());
    }
}
