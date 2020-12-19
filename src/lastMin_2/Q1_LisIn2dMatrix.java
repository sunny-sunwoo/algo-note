package lastMin_2;

import java.util.Arrays;

/**
 *
[PUBLIC level]

int[][] cache
iter thru mat (i, j)
    max of res, getLis(cache, mat, i, j)
=> rt res

[HELPER: getLis]
if (cache[cur] != -1) rt value in the cache

int res = 1;
for 4 next points (nextY, nextX)
    if !validRange || !lis(<==>mat[nxt] > mat[cur]) => continue
    res = max(res, cache[nxt] + 1);
    
=> rt cache[cur] = res;

[NOTE]
make sure to make a recursive CALL instead of checking values from cache!

 * @author sunnypark
 *
 */
public class Q1_LisIn2dMatrix {
	private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] cache = new int[matrix.length][matrix[0].length];
        for (int[] eachRow: cache) {
            Arrays.fill(eachRow, -1);
        }
        int res = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                res = Math.max(res, getLis(cache, matrix, i, j));
            }
        }
        return res;
    }
    
    private static int getLis(int[][] cache, int[][] matrix, int i, int j) {
        if (cache[i][j] != -1) {
            return cache[i][j];
        }
        
        int res = 1;
        
        for (int[] dir : DIRS) {
            int nextY = i + dir[0];
            int nextX = j + dir[1];
            
            if (!isValidRange(nextY, nextX, matrix) || !isLis(nextY, nextX, i, j, matrix)) {
                continue;
            }
            res = Math.max(res, getLis(cache, matrix, nextY, nextX) + 1);
        }
        return cache[i][j] = res;
    }
    
    private static boolean isValidRange(int y, int x, int[][] matrix) {
        if (y < 0 || x < 0 || y >= matrix.length || x >= matrix[0].length) {
            return false;
        }
        return true;
    }
    
    private static boolean isLis(int nextY, int nextX, int y, int x, int[][] matrix) {
        return matrix[nextY][nextX] > matrix[y][x];
    }
    
    public static void main(String[] args) {
		int[][] input = { { 9, 9, 4 }, { 6, 6, 8 }, { 2, 1, 1 } };
		System.out.print(longestIncreasingPath(input));
    }
}
