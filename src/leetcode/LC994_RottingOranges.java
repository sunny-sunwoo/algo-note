package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LC 994. rotting oranges
 * 
 	in-place bfs using deque, visited

	1. put all rotten orange as a starting point 
	2. while queue is not empty
	    - i = 0, size = curr size of queue
	    -> repeat current layer i < size
	        => removeFirst & check 4 neighbors 
	            => if visited OR out of range OR empty cell? ctn
	            => fresh orange ? offer to the queue & change to 2 in the grid
	    -> totalMin++
	    
	3. rt totalMin
	
	TC: mn

 * @author sunnypark
 *
 */
public class LC994_RottingOranges {
	private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int orangesRotting(int[][] grid) {
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        
        // offer starting points to queue, mark visited
        init(queue, visited, grid);

        int totalMin = 0;
        while (!queue.isEmpty()) {
            int i = 0;
            int size = queue.size();
            int tmpCnt = 0;
            while (i < size) {
                int[] top = queue.removeFirst();
                for (int[] dir : DIRS) {
                    int nextY = top[0] + dir[0];
                    int nextX = top[1] + dir[1];
                    
                    if (!isWithinRange(nextY, nextX, grid.length, grid[0].length)) {
                        continue;
                    }
                    
                    if (visited[nextY][nextX]) {
                        continue;
                    }
                    
                    if (grid[nextY][nextX] != 1) {
                        continue;
                    }
                    
                    visited[nextY][nextX] = true;
                    grid[nextY][nextX] = 2;
                    queue.addLast(new int[]{nextY, nextX});
                    tmpCnt++;
                }
                i++;
            }
            
            if (tmpCnt > 0) {
                totalMin++;
            }
        }
        
        if (freshIsStillRemaining(grid)) {
            return -1;
        }
        
        return totalMin;
    }
    
    private void init(Deque<int[]> queue, boolean[][] visited, int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = true;
                    queue.addLast(new int[]{i, j});
                }
            }
        }
    }
    
    private boolean isWithinRange(int y, int x, int yBound, int xBound) {
        if (y < 0 || y >= yBound || x < 0 || x >= xBound) {
            return false;
        }
        return true;
    }
    
    private boolean freshIsStillRemaining(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
