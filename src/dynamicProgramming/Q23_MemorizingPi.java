package dynamicProgramming;

import java.util.Arrays;

/**
 * Q23 memorizing pi
 * 
 * analyze min level 3 ~ 5-len 
 * 
 * dp[i] => i ~ end level
 *  
 *   	12341234
 * i)  str(s..s+3) + remaining lv (3..len)
 * ii) str(s..s+4) + remaining lv (4..len)
 * iii) str(s..s+5) + remaining lv(5..len)
 * 
 * => dp[s] = min (option1, option2, option3) 
 * 
 * => base case: 
 *    s >= input.length()
 *     -> rt 0
 *      
 *    dp[s] != -1 
 *     -> rt the val 
 *      
 * => dp[0]
 * 
 * @author sunnypark
 *
 */
public class Q23_MemorizingPi {
	public static int getMinLevel(String input) {
		Integer[] dp = new Integer[input.length()];
		return memorize(input, dp, 0);
	}
	
	private static int memorize(String input, Integer[] dp, int pos) {
		if (pos >= input.length()) {
			return 0;
		}
		
		if (dp[pos] != null) {
			return dp[pos];
		}
		
		int res = Integer.MAX_VALUE;
		for (int len = 3; len <= 5; len++) {
			if (pos + len > input.length()) {
				continue;
			}
			int currLv = memorize(input, dp, pos + len);
			if (currLv == Integer.MAX_VALUE) {
				continue;
			}

			currLv += analyze(input.substring(pos, pos + len));
			res = Math.min(res, currLv);
		}
		return dp[pos] = res;
	}
	
	private static int analyze(String s) {
		int level = 10;
		return level;
	}
	
	public static void main(String[] args) {
		System.out.println(getMinLevel("12341234"));
	}
}
