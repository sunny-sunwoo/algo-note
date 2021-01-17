package practice;

public class LC727_MinimumWindowSubsequence {
	public static String minWindow_dp(String S, String T) {
        int sLen = S.length();
        int tLen = T.length();
        int[][] dp = init(tLen + 1, sLen + 1);
        
        for (int i = 1; i <= tLen; i++) {
            for (int j = 1; j <= sLen; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return buildMinWindow(dp[tLen], S);
    }
    
    private static int[][] init(int row, int col) {
        int[][] dp = new int[row][col];
        for (int i = 0; i < col; i++) {
            dp[0][i] = i;
        }
        
        for (int i = 1; i < row; i++) {
            dp[i][0] = -1;
        }
        return dp;
    }
    
    
    private static String buildMinWindow(int[] lastRow, String s) {
        int left = 0;
        int windowLen = Integer.MAX_VALUE;

        for (int i = 1; i < lastRow.length; i++) {
            if (lastRow[i] != -1) {
                if (i - lastRow[i] < windowLen) {
                    left = lastRow[i];
                    windowLen = i - left;
                }
            }
        }
        return windowLen != Integer.MAX_VALUE ? s.substring(left, left + windowLen) : "";
    }
    
	public static String minWindow(String S, String T) {
	       int left = 0;
	        Pair minWindow = new Pair(0, Integer.MAX_VALUE);
	        while (left < S.length()) {
	            Pair newWindow = findMinWindow(left, S, T);
	            if (newWindow.getLength() < minWindow.getLength()) {
	                minWindow = newWindow;
	            }
	            left = findNextLeft(S, T.charAt(0), left + 1);
	        }
	        return buildWindowSubstring(minWindow, S);
	    }
	    
	    private static int findNextLeft(String s, char t, int i) {
	        while (i < s.length() && s.charAt(i) != t) {
	            i++;
	        }
	        return i;
	    }
	    
	    private static Pair findMinWindow(int l, String s, String t) {
	        int i = 0;
	        int r = l;
	        while (l < s.length() && i < t.length()) {
	            while (r < s.length() && i < t.length() && s.charAt(r) != t.charAt(i)) {
	                r++;
	            }
	            
	            l = r;
	            
	            while (r < s.length() && i < t.length()) {
	                if (s.charAt(r) != t.charAt(i)) {
	                    r++;
	                } else {
	                    r++; 
	                    i++;
	                }
	            }
	        }
	        return i == t.length() ? new Pair(l,r) : new Pair(0, Integer.MAX_VALUE);
	    }
	    
	    private static String buildWindowSubstring(Pair window, String s) {
	        if (window.left >= 0 && window.right < s.length()) {
	            return s.substring(window.left, window.right);
	        }
	        return "";
	    }
	    
	    private static class Pair {
	        int left;
	        int right;
	        
	        Pair(int left, int right) {
	            this.left = left;
	            this.right = right;
	        }
	        
	        int getLength() {
	            return right - left;
	        }
	        
	        @Override
	        public String toString() {
	            return left + " " + right;
	        }
	    }
	    
	    public static void main(String[] args) {
	    	String s = "abcdebdde";
	    	String t = "bde";
	    	System.out.println(minWindow(s,t));
	    }
}
