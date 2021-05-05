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
 * sol 1) 2d dp
 *  row: coin nums + 1, col = amt + 1
 *  
 *  dp[i][j] => coin(0..i-1), amt=j
 *    => not-pick + pick(if applicable)
 *       dp[i-1][j] + dp[i][j - coin[i-1]]
 * 
 * sol 2) 
 *  combinations[], len = amt + 1
 *  base case: memo[0] = 1
 *  
 *  if combinations[amt] != -1 ? combinations[amt]
 *  
 *  6 =>  ways = combinations(6-1) + combinations(6-2) + combinations(6-5)
 *  
 *  iter thru all coins
 *   for amt 1..end
 *     curr coin <= amt?
 *      ways += memo[amt - curr coin]  
 *  
 *  rt memo[amt]
 *                
 *           0 1 2 3 4 5 6
 *  memo =>  1 1 2 1 3 2 5
 *               
 * @author sunnypark
 *
 */
public class Q35_CoinChangeWays {
	public static int coinChangeWays(int[] coins, int amount) {
		int[] ways = new int[amount + 1];
		ways[0] = 1;
		
		for (int coin : coins) {
			for (int i = 1; i < ways.length; i++) {
				if (coin <= i) {
					ways[i] += ways[i - coin];
				}
			}
		}
		return ways[amount];
	}
	
	public static void main(String[] args) {
		int[] coins = {1, 2, 5};
		// 1,1,1,1,1
		// 1,1,1,1,2
		// 1,1,2,2
		// 2,2,2
		// 1,5
		System.out.println(coinChangeWays(coins, 6));
		System.out.println(coinChangeWays(coins, 12));
	}
}
