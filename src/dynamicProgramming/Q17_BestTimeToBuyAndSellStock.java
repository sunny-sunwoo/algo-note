package dynamicProgramming;

/**
 * Best Time to Buy and Sell Stock
 * 
 * Q1. one transaction(LC 121)
 * 		profit = minSoFar - prices
 * 
 * Q2. at most two transactions(LC 123)
 * 		iter1) build profit array
 * 		iter2) find max profit from 2nd transaction
 * 			   by keeping max from backward
 * 				=> possible max profit = max(profit[i], profit[i - 1] - prices[i] + max)
 * 
 * [Q3-5] fill maxBuy, maxSell arr to keep status building max profit
 * maxBuy = max of (not buy, buy)
 * maxSell = max of (not sell, sell)
 * 
 * Q3. cool-down(LC 309)
 * 		=> 1 constraint for buy: should've sold 2 days ago and have at least 1 cool-down day
 * 
 * Q4. k times transaction(LC 188)
 * 		=> keep maxBuy & maxSell for each 1~k transaction (ref: knapsack)
 * 
 * Q5. with transaction fee(LC 714)
 * 		=> when selling(finishing up current buy-sell transaction), fee is added to profit status
 * 
 * 
 * @author sunnypark
 *
 */
public class Q17_BestTimeToBuyAndSellStock {
	public int maxProfit_Q5(int[] prices, int fee) {
        int len = prices.length;
        int[] maxBuy = new int[len];
        int[] maxSell =  new int[len];
        
        maxBuy[0] = -prices[0];
        
        for (int i = 1; i < len; i++) {
            maxBuy[i] = Math.max(maxBuy[i-1], maxSell[i-1] - prices[i]);
            maxSell[i] = Math.max(maxSell[i-1], maxBuy[i-1] + prices[i] - fee);
        }
        return maxSell[len-1];
    }
}
