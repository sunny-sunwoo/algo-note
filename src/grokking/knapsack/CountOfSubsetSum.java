package grokking.knapsack;

/**
 * 
 * Q. Count of Subset Sum (hard)
 * 
 *             0 1 2 3 4
 * ----------+-----------
 * []        | 1 0 0 0 0
 * [1]       | 1 1 0 0 0 
 * [1,1]     | 1 2 1 0 0
 * [1,1,2]   | 1 2 2 2 1 
 * [1,1,2,3] | 1 2 2 3 3
 * 
 * => # of not-pick + # of pick 
 *                    (if cur val <= cur sum)
 * 
 * => dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]]  (if curr sum has enough value)
 * 
 * => TC: O(N * Sum) // pseudo-polynomial
 * 
 * @author sunnypark
 *
 */
public class CountOfSubsetSum {
	public static int countSubsetSum(int[] nums, int sum) {
		int[][] dp = init(nums.length, sum);
		for (int i = 1; i <= nums.length; i++) {
			for (int j = 1; j <= sum; j++) {
				int currNum = nums[i - 1];
				
				// not pick
				dp[i][j] = dp[i - 1][j];
				
				// pick if possible
				if (currNum <= j) {
					dp[i][j] += dp[i - 1][j - currNum];
				}
			}
		}
		
		return dp[nums.length][sum];
	}
	
	private static int[][] init(int rowLen, int colLen) {
		int[][] dp = new int[rowLen + 1][colLen + 1];
		for (int i = 0; i <= rowLen; i++) {
			dp[i][0] = 1;
		}
		return dp;
	}
	
	public static void main(String[] args) {
		int[] nums = {1,1,2,3};
		System.out.println(countSubsetSum(nums, 4));
	}
}
