package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * LC 289 game of life
 * 
	8 DIRS
	1 == live
	0 == dead
	
	iter thru each cell
	  check neighbor, 
	    - out of bound? continue
	    - if applied for any of 4 rules? mark the cell
	  
	flip marked cells
	
	TC: O(mn) (m=row, n=col)

 * @author sunnypark
 *
 */
public class LC289_GameOfLife {
    private static final int DEAD = 0;
    private static final int LIVE = 1;
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {1, 1}, {-1, 1}, {1, -1}};
    
    public void gameOfLife(int[][] board) {
        Set<int[]> cellsToFlip = new HashSet<>();
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int liveNbrNum = countLiveNeighbors(i, j, board);
                if (board[i][j] == DEAD) {
                    if (liveNbrNum == 3) {
                        cellsToFlip.add(new int[]{i, j});
                    }
                } else {
                    if (liveNbrNum < 2 || liveNbrNum > 3) {
                        cellsToFlip.add(new int[]{i, j});
                    }
                }
            }
        }
        flip(board, cellsToFlip);
    }
    
    private void flip(int[][] board, Set<int[]> cellsToFlip) {
        for (int[] cell : cellsToFlip) {
            int y = cell[0];
            int x = cell[1];
            if (board[y][x] == DEAD) {
                board[y][x] = LIVE;
            } else {
                board[y][x] = DEAD;
            }
        }
    }
    
    private int countLiveNeighbors(int i, int j, int[][] board) {
        int cnt = 0;
        for (int[] dir: DIRS) {
            int nbrY = i + dir[0];
            int nbrX = j + dir[1];

            if (!withinValidRange(nbrY, nbrX, board.length, board[0].length)) {
                continue;
            }

            if (board[nbrY][nbrX] == LIVE) {
                cnt++;
            }
        }
        return cnt;
    }
    
    private boolean withinValidRange(int y, int x, int yBound, int xBound) {
        if (y < 0 || y >= yBound || x < 0 || x >= xBound) {
            return false;
        }
        return true;
    }
}
