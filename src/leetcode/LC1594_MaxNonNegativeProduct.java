package leetcode;

public class LC1594_MaxNonNegativeProduct {
	private static final int[][] DIRS = {{1, 0}, {0, 1}};
    long candidate = -1;
    long denominator = (long) Math.pow(10, 9) + 7;
    
    public int maxProductPath(int[][] grid) {
        dfs(0, 0, 1, grid);
        return (int) (candidate % denominator);
    }
    
    private void dfs(int row, int col, long productSoFar, int[][] grid) {
        productSoFar *= grid[row][col];
        if (productSoFar == 0) {
            candidate = Math.max(candidate, 0);
            return;
        }
        if (row == (grid.length - 1) && col == (grid[0].length - 1)) {
            if (productSoFar >= 0) {
                candidate = Math.max(candidate, productSoFar);
            }
            return;
        }
        
        for (int[] dir : DIRS) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
            if (!isValidRange(newRow, newCol, grid.length - 1, grid[0].length - 1)) {
                continue;
            }
            dfs(newRow, newCol, productSoFar, grid);
        }
    }
    
    private boolean isValidRange(int r, int c, int rBound, int cBound) {
        return (r <= rBound) && (c <= cBound);
    }
}
