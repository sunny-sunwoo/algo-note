package string;

import java.util.Arrays;

/**
 * KMP(Knuth-Morris-Pratt) algorithm for substring search
 * 
 * "How many right shifting is possible in max?"
 * Think about "Pattern"! 
 * 
 * time complexity: O(m+n) where m = string length, n = pattern length
 * 	- lps array building: O(n)
 *  - substring search: o(m)
 * 
 * 
 * [idea]
 * by keeping overlapped length of prefix-suffix,
 *   after mismatch, jump by max steps, instead of 1.
 *   => by checking prefix-suffix pattern right before mismatch
 *   
 *   Java has indexOf but it moves by 1. 
 *   e.g. hay = "aaaaaaaaaaaaaaaa", needle = "aaaaaaab" // N * M time.
 *   
 *   => hay = "abcdabcf......" needle = "abcdabck"
 *                    ^                         ^
 *   in KMP, when mismatch happened, 
 *   jump step = matched number(7) - overlap(3) 
 *             = 4 (max num of right shifting)
 *  
 * @author sunnypark
 *
 */
public class T5_KMP {
	
	/*
	 how to build longest prefix-suffix array
	 */
	public static int[] getLPSArray(String input) {
		int[] lps = new int[input.length()];
		
		int i = 0;
		for (int j = 1; j < input.length(); ) {
			if (input.charAt(i) == input.charAt(j)) {
				lps[j] = i + 1;
				i++;
				j++;
			} else if (i > 0) { // char at i != char at j
				i = lps[i - 1];
			} else { // i == 0
				j++;
			}
		}
		return lps;
	}
	
	public static boolean canFindSubstring(String needle, String haystack) {
		int[] lps = getLPSArray(needle);
		int i = 0;
		int j = 0;

		while (i < needle.length() && j < haystack.length()) {
			if (needle.charAt(i) == haystack.charAt(j)) {
				i++;
				j++;
				if (i == needle.length()) {
					return true;
				}
			} else if(i > 0) {
				i = lps[i - 1];
			} else { // i == 0
				j++;
			}
		}
		
		return false;
	}
	
	public static int strStr(String needle, String haystack) {
		int[] lps = getLPSArray(needle);
		int i = 0;
		int j = 0;

		while (i < needle.length() && j < haystack.length()) {
			if (needle.charAt(i) == haystack.charAt(j)) {
				i++;
				j++;
				if (i == needle.length()) {
					return j - needle.length();
				}
			} else if(i > 0) {
				i = lps[i - 1];
			} else { // i == 0
				j++;
			}
		}
		
		return -1;
	}
	
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(getLPSArray("abcdabca")));
		System.out.println(canFindSubstring("abcdabca", "abcdabckabcdabca"));
		System.out.println(strStr("abcdabca", "abcdabckabcdabca"));
	}

}
