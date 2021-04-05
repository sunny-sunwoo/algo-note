package leetcode;

/**
 * LC 44
 * ? = Matches any single character.
 * * = Matches any sequence of characters (including the empty sequence).
 * 
 * e.g.
 * s = "aa", p = "*" // true
 * s = "acdcb", p = "a*c?b" // false
 * s = "acdcb", p = "a*c??b" // true
 * 
 *      - a * c ? ? b
 * ---+---------------
 *  - | t f f f f f f
 *  a | f t t f f f f
 *  c | f f t t f f f
 *  d | f f t f t t f
 *  c | f f t t f t f
 *  b | f           t
 *  
 *  
 * @author sunnypark
 *
 */
public class LC44_WildcardMatching {
	public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        
        // first row init
        dp[0][0] = true;
        for (int j = 1; j < dp[0].length; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }
                
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] | dp[i - 1][j];
                    continue;
                }
                // otherwise, false
            }
        }
        return dp[s.length()][p.length()];
    }
}
