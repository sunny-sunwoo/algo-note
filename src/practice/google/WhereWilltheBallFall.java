package practice.google;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * LC 1706 Where will the ball fall

 1 = \
-1 = /

Input: grid = [[1,1,1,-1,-1],[1,1,1,-1,-1],[-1,-1,-1,1,1],[1,1,1,1,-1],[-1,-1,-1,-1,-1]]
Output: [1,-1,-1,-1,-1]

Input: grid = [[-1]]
Output: [-1]

Input: grid = [[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1],[1,1,1,1,1,1],[-1,-1,-1,-1,-1,-1]]
Output: [0,1,2,3,4,-1]

Input: [1,1,1,1],[-1,-1,-1,-1],[-1,-1,1,1]
Output: [-1, 0, 3, -1]
 x x 
 \\\\
 ////
 //\\
 x  x 

[public level]
init: queue, res
    queue => add all to falling balls 
    res => initial pos [0, 1, 2, 3]

for each row
  fallingBalls size? (bfs)
  while size-- > 0
     poll
     getNextPos
     res update to nextPos
     if nextPos != -1 ? offer to queue

rt res

[helper method] getNextPos
 // edge case: invalid first cell or last cell
 (cell idx == 0 && value == -1) OR (cell idx == last && value == 1) ? rt -1 

 next = curr cell idx + cell value (+1 or -1)
 curr == next ? can fall to next => rt next idx
 otherwise ? cant => rt -1

 time complexity: O(row num * col num)
 space complexity: O(col num)
 * @author sunnypark
 *
 */
public class WhereWilltheBallFall {
	public static int[] findBall(int[][] grid) {
        int totalCol = grid[0].length;
        
        Deque<Integer> balls = new ArrayDeque<>();
        int[] res = new int[totalCol];
        init(balls, res, totalCol);
        
        for (int i = 0; i < grid.length; i++) {
            int size = balls.size();
            while (size-- > 0) {
                int currBall = balls.removeFirst();
                int nextPos = getNextPos(currBall, grid, res, i);
                
                res[currBall] = nextPos;
                if (nextPos != -1) {
                    balls.addLast(currBall);
                }
            }
        }
        
        return res;
    }
    
    private static int getNextPos(int currBall, int[][] grid, int[] res, int rowNum) {
        int currPos = res[currBall];
        int currCell = grid[rowNum][currPos];
        
        if (isInvalidSide(currPos, currCell, 0, res.length - 1)) {
            return -1;
        }
        
        int nextPos = currPos + currCell;
        int nextCell = grid[rowNum][nextPos];

        return currCell == nextCell ? nextPos : -1;
    }
    
    private static boolean isInvalidSide(int currPos, int currCell, int left, int right) {
        return (currPos == left && currCell == -1) || (currPos == right && currCell == 1);
    }
    
    private static void init(Deque<Integer> balls, int[] res, int col) {
        for (int i = 0; i < col; i++) {
            balls.addLast(i);
            res[i] = i;
        }
    }
    
/*
Input: [1,1,1,1],[-1,-1,-1,-1],[-1,-1,1,1]
Output: [-1, 0, 3, -1]
*/
    public static void main(String[] args) {
    	int[][] grid = new int[3][4];
    	grid[0] = new int[]{1,1,1,1};
    	grid[1] = new int[]{-1,-1,-1,-1};
    	grid[2] = new int[]{-1,-1,1,1};
    	
    	System.out.println(Arrays.toString(findBall(grid)));
    }
    
}
