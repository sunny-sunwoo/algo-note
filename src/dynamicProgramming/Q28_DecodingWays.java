package dynamicProgramming;

/**
 * LC 91. Decode ways
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * 
 * e.g. 
 * Input: s = "226"
 * Output: 3
 * Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 * 
 * solution. memoization
 * memo arr: length of str length + 1 
 * 
 * memo[0] = 1 // base case
 * memo[1] = 0 or 1 // based on s.charAt(0)
 * 
 * iter thru memo[2] ~ memo[memo.length] // up to string length
 * => oneDigit: acc from i-1 cell
 * => twoDigit: acc from i-2 cell
 *
 * 
 * @author sunnypark
 *
 */
public class Q28_DecodingWays {
	public static int numDecodings(String s) {
        int[] memo = new int[s.length() + 1];
        memo[0] = 1; // base case
        memo[1] = s.charAt(0) == '0' ? 0 : 1;
        
        for (int i = 2; i < memo.length; i++) {
            int oneDigit = Integer.parseInt(s.substring(i - 1, i));
            if (1 <= oneDigit && oneDigit <= 9) {
                memo[i] += memo[i - 1];
            }
            
            int twoDigit = Integer.parseInt(s.substring(i - 2, i));
            if (10 <= twoDigit && twoDigit <= 26) {
                 memo[i] += memo[i - 2];
            }
        }
        return memo[s.length()];
    }
	
	public static void main(String[] args) {
		System.out.println(numDecodings("226")); // BBF, BZ, VF
		System.out.println(numDecodings("111")); // AAA, AK, KA
	}
}
