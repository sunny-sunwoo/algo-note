package leetcode;

/**
 * LC 152. Maximum Product Subarray
 * 
 *  2 choices at each point
 *  1) continue subarray: 
 *      max => max(minSoFar * curr, maxSoFar* curr)
 *      min => min(minSoFar * curr, maxSoFar* curr)
 *  2) restart subarray: curr val
 *  
 *  => curr max = max(candidate, curr)
 *  => curr min = min(candiate, curr)
 *  
 *   * e.g.
// {6, -3, -10, 0, 2} => 180 
// {-1, -3, -10, 0, 60} => 60
// {-2, -3, 0, -2, -40} => 80
// {3, -1, -2} => 6
 * @author sunnypark
 *
 */
public class LC152_MaxProductSubarr {
	public static int findLargestProduct(int[] input) {
	    int[] max_dp = new int[input.length];
	    int[] min_dp = new int[input.length];
	    
	    int first = input[0];
	    max_dp[0] = first;
	    min_dp[0] = first;
	    int maxProduct = first;
	      
	    for (int i = 1; i < input.length; i++) {
	      // pick from 1) continuous subarr, 2) new subarr
	      int curr = input[i];
	      int maxCandidate = Math.max(max_dp[i-1] * curr, min_dp[i-1] * curr);
	      int minCandidate = Math.min(max_dp[i-1] * curr, min_dp[i-1] * curr);
	      
	      max_dp[i] = Math.max(curr, maxCandidate);
	      min_dp[i] = Math.min(curr, minCandidate);
	      
	      maxProduct = Math.max(maxProduct, max_dp[i]);
	    }
	    
	    return maxProduct;
	  }
	  public static void main(String[] args) {
	    int[] input1 = {-1, -3, -10, 0, 60};
	    int[] input2 = {-2, -3, 0, -2, -40};
	    int[] input3 = {3, -1, -2};
	    int[] input4 = {-1,-2,-9,-6};
	    
	    System.out.println(findLargestProduct(input1)); // expected: 60
	    System.out.println(findLargestProduct(input2)); // expected: 80
	    System.out.println(findLargestProduct(input3)); // expected: 6
	    System.out.println(findLargestProduct(input4)); // expected: 108
	  }
}
