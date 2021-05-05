package dynamicProgramming;

import java.util.Arrays;

/**
 * Q36 Min Number Coin Changes
 * 
 *  coins = [1,2,5]
 *  amt = 12
 *  5+5+2 => 3 coins used
 *  
 * sol1) 2d dp table 
 *         0 1 2 3 4 5 6 7 8 9 10 11 12
 * []      0 
 * [1]     0 1 2 3 4 5 6 7 8 9 10 11 12
 * [1,2]   0 1 1 
 * [1,2,5] 0
 * 
 *  => dp[i][j] = num of coins for amount j with coin(0..i-1)
 *              => pick min(used + 1 , not-used)
 *  for each row,
 *   iter thru each col (1..amt)
 *     i) if curr coin <= amt ? 
 *           dp[i - 1][j] VS dp[i][j - curr coin val] + 1
 *     
 *     ii) otherwise? 
 *           dp[i-1][j]
 * 
 *  (complexity) pseudo-polynomial
 *   time: O(coin num * amt)
 *   space: O(coin num * amt)
 *   
 * sol2) recursion w/ memo
 *          0 1 2 3 4 5 6 7 8 9 10 11 12
 * memo =>  0
 *  
 *  1. base case
 *   if memo has val for curr amt? rt val
 *   
 *  2. logic
 *   for each coin
 *     if coin <= amt 
 *        curr choice = dfs(amt - coin)
 *        keep min val
 *   
 *  3. answer 
 *   rt memo[cur amt] = min val + 1
 *  
 *  (complexity)
 *   time: O(coin num * amt)
 *   space: O(amt)
 * @author sunnypark
 *
 */
public class Q36_MinNumberCoinChanges {
	public static int getMinCoinChange(int[] coins, int amt) {
		Integer[] memo = new Integer[amt + 1];
		memo[0] = 0;
		return dfs(memo, coins, amt);
	}
	
	private static int dfs(Integer[] memo, int[] coins, int amt) {
		// base
		if (memo[amt] != null) {
			return memo[amt];
		}
		
		// logic
		int minVal = Integer.MAX_VALUE;
		for (int coin : coins) {
			if (coin <= amt) {
				minVal = Math.min(minVal, dfs(memo, coins, amt - coin));
			}
		}

		// answer
		return memo[amt] = minVal == Integer.MAX_VALUE ? minVal : minVal + 1;
	}
	
	public static void main(String[] args) {
		int[] coins = {2,5};
		int amt = 6;
		
		System.out.println(getMinCoinChange(coins, amt)); // expected: 3
	}
}
