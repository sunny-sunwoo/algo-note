package dynamicProgramming;

import java.util.Arrays;

/**
 * Q42 String compressor.
 * LC 471. Encode String with Shortest Length
 * 
 * e.g. 
 * Input: s = "aabcaabcd"
 * Output: "2[aabc]d"
 * 
 * [solution] build dp table
 * dp[start][len] 
 * 	=> compressed string for string(start..len)
 *  => final result @ dp[0][string length - 1]
 * 
 * iter thru len (1 ~ sLen)
 *  => iter thru start (0 ~ sLen - len)
 *  => pick min length strings from:
 *  	1) substring (start .. len)
 *		2) combination of (start..m) + (m+1..len)
 *		3) new compressed str 
 *
 *     
 * @author sunnypark
 *
 */
public class Q42_StringCompressor {
	private static String[][] dp;
	public static String encodeString(String s) {
		int sLen = s.length();
		dp = init(sLen);
		
		for (int len = 1; len <= sLen; len++) {
			for (int start = 0; start < sLen - len + 1; start++) {
				int end = start + len;
				// 1) substring
				dp[start][end] = s.substring(start, start + len);
				printDp(dp);
				
				// 2) combination of left, right 
				for (int m = start; m < end; m++) {
					String left = dp[start][m];
					String right = dp[m+1][end];
					
					if (left.length() == 0 || right.length() == 0) {
						continue;
					}
					
					if (left.length() + right.length() < dp[start][end].length()) {
						dp[start][end] = left+right;
					}
				}
				
				printDp(dp);
				
				// 3) new compressed string
				String newCompressed = compress(start, end, s);
				System.out.println("NEW: " + start + " " + end + "  " +  newCompressed);
				if (newCompressed.length() < dp[start][end].length()) {
					dp[start][end] = newCompressed;
				}
				
			}
		}
		return dp[0][sLen];
	}
	
	private static String compress(int s, int e, String str) {
		String curr = str.substring(s, e);
		int pos = curr.repeat(2).indexOf(curr, 1);
		if (pos >= curr.length()) {
			return curr;
		}
		
		int repeatingNum = curr.length() / pos;
		return String.valueOf(repeatingNum) + "[" + dp[s][s + pos] + "]";
	}
	
	private static String[][] init(int len) {
		String[][] dp = new String[len + 1][len + 1];
		for (String[] each : dp) {
			Arrays.fill(each, "");
		}
		return dp;
	}
	
	private static void printDp(String[][] dp) {
		for (String[] each: dp) {
			System.out.println(Arrays.toString(each));
		}
		System.out.println("-----");
	}
	
	public static void main(String[] args) {
		String s1 = "ababab";
		System.out.println(encodeString(s1));
		
		printDp(dp);
	}
	
	
}
