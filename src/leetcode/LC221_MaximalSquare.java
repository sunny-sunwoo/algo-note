package leetcode;

/**
 * LC 221 Maximal Square
 * 
 * approach) dp 
 
 - init dp table with row + 1, col + 1 size 
 - iter thru 2d arr
  => if curr = '1' ?
        min of (\, |, <-) + 1
        update max
  => else ? 0
  
 - rt area (= max*max)

 * @author sunnypark
 *
 */
public class LC221_MaximalSquare {
	public static int maximalSquare(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        
        int[][] dp = new int[row + 1][col + 1];
        int max = 0;

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                	dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max*max;
    }
}
