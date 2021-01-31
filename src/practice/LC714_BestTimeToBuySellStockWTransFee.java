package practice;
/**
 * LC714 stock selling with transaction fee
 * 
 * prices = {1,3,2,8,4,9}
 * fee = 2
 * 
 * fee is charged for every selling
 * return max profit?
 * 
 * no limit for # of transaction 
 * 
 * {1,3,2,8,4,9}
 *        ^
 *  
 *  int[] maxBuy => not buy, buy
 *        maxBuy[i] = maxBuy[i - 1], maxSell[i - 1] - prices[i]
 *        
 *  int[] maxSell => not sell, sell(consider fee)
 *        maxSell[i] = maxSell[i - 1], maxBuy[i - 1] + prices[i] - fee
 *     
 *  [-1, -1, -1, -1]
 *  [0, 0, 0,  5, x , 8]
 *  
 *  
 *  => rt maxSell[last day]
 *  
 * @author sunnypark
 *
 */
public class LC714_BestTimeToBuySellStockWTransFee {

}
