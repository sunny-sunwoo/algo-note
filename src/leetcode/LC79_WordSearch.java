package leetcode;

/**
 * LC 79. Word Search
 * Q. Given an m x n board and a word, find if the word exists in the grid.
 * 
 * @author sunnypark
 *
 */
public class LC79_WordSearch {
private static int[][] DIRS = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    public static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != word.charAt(0)) {
                    continue;
                }
                
                boolean [][] visited = new boolean[board.length][board[0].length];
                visited[i][j] = true;
                if (dfs(board, word, visited, i, j, 1)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private static boolean dfs(char[][] board, String word, boolean[][] visited, int y, int x, int pos) {
        if (pos >= word.length()) {
            return true;
        }
        
        for (int[] dir : DIRS) {
            int nextY = y + dir[0];
            int nextX = x + dir[1];
            
            if (!isValidIndex(nextY, nextX, board.length, board[0].length) || visited[nextY][nextX] || board[nextY][nextX] != word.charAt(pos)) {
                continue;
            }
            
            visited[nextY][nextX] = true;
            if (dfs(board, word, visited, nextY, nextX, pos + 1)) {
                return true;
            }
            
            visited[nextY][nextX] = false;
        }
        
        return false;
    }
    
    private static boolean isValidIndex(int y, int x, int yBound, int xBound) {
        if (y < 0 || x < 0 || y >= yBound || x >= xBound) {
            return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
    	char[][] board = {{'A','B', 'C', 'E'},{'S', 'F', 'C', 'S'},{'A', 'D', 'E', 'E'}};
    	String word = "ABCCED";
    	System.out.println(exist(board, word));
    }
}
