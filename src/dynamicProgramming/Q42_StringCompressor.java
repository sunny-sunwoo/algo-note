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
 * dp[start][end] 
 * => compressed string for string(start..end)
 * => final result @ dp[0][string length - 1]
 * 
 * [logic]
 * iter thru len (1 ~ sLen)
 *  => iter thru start (0 ~ sLen - len)
 *  => pick min length strings from:
 *  	1) substring (start .. len)
 *  	2) combination of (start..m) + (m+1..len)
 *  	3) new compressed str
 *  
 * [time complexity]
 * iter thru all lens (1 ~ string length)
 * starting from all pos
 *   => n^2
 *   
 * find answer from intermediate compressed strings 
 *   => n (m can be all pos between s,e)
 *   
 * time complexity: n^3
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
			for (int start = 0; start <= sLen - len; start++) {
				int end = start + len - 1;

				// 1) substring
				dp[start][end] = s.substring(start, end + 1);

				// 2) combination of left, right 
				for (int m = start + 1; m <= end - 1; m++) {
					String left = dp[start][m];
					String right = dp[m + 1][end];
					
					if (left.length() + right.length() < dp[start][end].length()) {
						dp[start][end] = left+right;
					}
				}

				// 3) new compressed string
				String newCompressed = compress(start, end, s);
				if (newCompressed.length() <= dp[start][end].length()) {
					dp[start][end] = newCompressed;
				}
			}
		}

		return dp[0][sLen - 1];
	}
	
	private static String compress(int s, int e, String str) {
		String curr = str.substring(s, e + 1);
		int pos = curr.repeat(2).indexOf(curr, 1);
		if (pos >= curr.length()) {
			return curr;
		}
		int repeatingNum = curr.length() / pos;
		return String.valueOf(repeatingNum) + "[" + dp[s][s + pos - 1] + "]";
	}
	
	private static String[][] init(int len) {
		String[][] dp = new String[len][len];
		for (String[] each : dp) {
			Arrays.fill(each, "");
		}
		return dp;
	}
	
	private static void print(String[][] dp) {
		for (String[] each: dp) {
			System.out.println(Arrays.toString(each));
		}
	}
	
	public static void main(String[] args) {
		String s1 = "abbbabbbcabbbabbbc";
		System.out.println(encodeString(s1)); // expected: 2[2[abbb]c]
		
		String s2 = "aabcaabcd";
		System.out.println(encodeString(s2)); // expected: 2[aabc]d
		
		String s3 = "aaaaaaaaaa";
		System.out.println(encodeString(s3)); // expected: 10[a]
//		print(dp);
	}
}
