package dynamicProgramming;
/**
 * 6
 * 1,2,5
 * => count ways
 * 
 * 1,1,1,1,1,1
 * 1,1,1,1,2
 * 1,1,2,2
 * 1,5
 * 
 * i) 2d dp
 *  row: coin nums + 1, col = amt + 1
 *  
 *  dp[i][j] => coin(0..i-1), amt=j
 *    => not-pick + pick(if applicable)
 *       dp[i-1][j] + dp[i][j - coin[i-1]]
 * 
 * ii) recur + memo
 *  memo[], len = amt + 1
 *  base case: memo[0] = 1
 *  
 *  if memo[amt] != -1 ? memo[amt]
 *  
 *  6 =>  ways = dfs(6-1) + dfs(6-2) + dfs(6-5)
 *  iter thru all coins
 *   coins >= amt
 *     ways += dfs(amt - curr coin)  
 *  
 *  rt memo[amt] = ways
 *  
 * @author sunnypark
 *
 */
public class Q35_CoinChangeWays {
	public static int coinChangeWaysBottomUp(int[] coins, int amount) {
		int[][] dp = new int[coins.length + 1][amount + 1];
		
		for (int i = 0; i < dp.length; i++) {
			dp[i]
		}
		return dp[coins.length][amount];
	}
}
