package backtracking;

/**
 * Q5. dice 
 * given List<Dice> dices, String s
 *   => can build string s ? 
 *   
 * dice 1 = a,b,r,...
 * dice 2 = b,c,o,..
 * dice 3 = k,j,p,...
 *
 * bka => 231
 * 
 * build all
 * 
 * 
 * boolean[] used, SB path, 
 * 
 * base case: path len == s len 
 *    => rt isAnagram(res, s)
 *    
 * logic:
 *  for each dice in dices 
 *    - if used ? ctn
 *    - used check
 *      => for all char in dice
 *          append to path
 *          build(used, path)
 *          rm from path
 *    - used uncheck
 *    
 * rt false
 * 
 * @author sunnypark
 *
 */
public class Q5 {

}
