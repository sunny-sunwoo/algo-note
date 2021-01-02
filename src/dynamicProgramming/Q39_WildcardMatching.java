package dynamicProgramming;

/**
 * ? = single letter
 * * = all letter
 * 
 * e.g. 
 * he?p, heap (t)
 * *p*, help (t)  but hello(f)
 *     * p * 
 *   h
 *   e     X
 *   l   X +
 *   p
 * 
 * pattern p, string s 
 * boolean[][] dp =>  s.length + 1, p.length + 1
 * 
 * dp[i][j]
 * 
 * 1) s[i - 1] == p[j - 1] OR p[j-1] == ? 
 *     => dp[i-1][j-1]
 *     
 * 2) * 
 *    => dp[i - 1][j] | dp[i][j - 1]
 * 
 * 3) false
 * @author sunnypark
 *
 */
public class Q39_WildcardMatching {

}
