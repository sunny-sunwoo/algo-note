package practice.google;

/**
 * [approach] dfs w/memo
 *  base case: 
 *   i) curr cell = exit? rt 1
 *   ii) memo != -1 ? rt memo @ curr cell
 * 
 *  logic:  
 *   ways = 0
 *   for 3 next points
 *     out of bound? -1
 *     ways += recursive call w/ next points
 *   
 *   rt memo(curr cell) = ways
 * 
 * M=3, N=4
 *        col0 col1 col2 col3
 *      +---------------------  
 * row0 |             0   0
 * row1 |         2   1   0
 * row2 | init    2   1  exit
 * 
 * way 1: (2,0) - (2,1) - (2,2) - (2,3)
 * way 2: (2,0) - (1,1) - (2,2) - (2,3)
 * way 3: (2,0) - (2,1) - (1,2) - (2,3)
 * way 4: (2,0) - (1,1) - (1,2) - (2,3)
 * 
 * time = O(MN)
 * space = O(MN)
 *         -> O(M)
 * 
 * [Optimized] use O(M) space
 *  => keep curr col only
 * 
 * base case: last col? 
 *   exit row? keep 1
 *   otherwise? rt
 * 
 * for each dir
 *  next row
 *  next col
 *  sum up ways <= dfs w/next row, next col
 *  
 * rt memo[row] = ways; 
 * @author sunnypark
 *
 */
public class NumberOfWaysInMatrix {
	private static final int[][] DIRS = {{-1, 1}, {1, 1}, {0, 1}};
	private static int exitRow;
	private static int exitCol;
	
	public static int findNumberOfWays(int rows, int cols) {
		exitRow = rows - 1;
		exitCol = cols - 1;

		Integer[][] memo = new Integer[rows][cols];
		return dfs(memo, exitRow, 0);
	}
	
	private static int dfs(Integer[][] memo, int row, int col) {
		if (row == exitRow && col == exitCol) {
			return 1;
		}
		
		if (memo[row][col] != null) {
			return memo[row][col];
		}
		
		int ways = 0;
		for (int[] dir : DIRS) {
			int nextRow = row + dir[0];
			int nextCol = col + dir[1];
			
			if (!isWithinRange(nextRow, nextCol, exitRow, exitCol)) {
				continue;
			}
			
			ways += dfs(memo, nextRow, nextCol);
		}
		
		return memo[row][col] = ways;
	}
	
	private static boolean isWithinRange(int row, int col, int rowBound, int colBound) {
		if (row < 0) {
			return false;
		}
		
		return (row <= rowBound) && (col <= colBound);
	}
	
	public static int findNumberOfWays_optimized(int rows, int cols) {
		exitRow = rows - 1;
		exitCol = cols - 1;

		int[] memo = new int[rows];
		return dfs_optimized(memo, exitRow, 0);
	}
	
	private static int dfs_optimized(int[] memo, int row, int col) {
		// base case
		if (col == exitCol) {
			if (row != exitRow) {
				return memo[row] = 0;
 			}
			return memo[row] = 1;
		}
		
		// logic
		int ways = 0;
		for (int[] dir : DIRS) {
			int nextRow = row + dir[0];
			int nextCol = col + dir[1];
			
			if (!isWithinRange(nextRow, nextCol, exitRow, exitCol)) {
				continue;
			}

			ways += dfs_optimized(memo, nextRow, nextCol);
		}

		// memo and rt
		return memo[row] = ways;
	}
	
	public static void main(String[] args) {
		System.out.println(findNumberOfWays(3,4));
		System.out.println(findNumberOfWays_optimized(3,4));
	}
}
