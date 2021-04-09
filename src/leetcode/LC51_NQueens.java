package leetcode;

import java.util.ArrayList;
import java.util.List;

public class LC51_NQueens {
	private static final char QUEEN = 'Q';
    private static final char DOT = '.';
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backtrack(result, path, 0, n);
        return result;
    }
    
    private static void backtrack(List<List<String>> result, List<Integer> path, int row, int n) {
        if (row == n) {
            result.add(buildResult(path));
            return;
        }
        
        for (int i = 0; i < n; i++) {
            if (!isValidPos(path, row, i, n)) { // row or col or diag is invalid
                continue;
            }
            path.add(i);
            backtrack(result, path, row + 1, n);
            path.remove(path.size() - 1);
        }
    }
    
    private static boolean isValidPos(List<Integer> path, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            int colDiff = Math.abs(path.get(i) - col);
            int rowDiff = Math.abs(i - row);
            
            if (colDiff == 0 || rowDiff == colDiff) {
                return false;
            }
        }
        return true;
    }
    
    
    // 1,3,2,0 => .Q.., ...Q,
    private static List<String> buildResult(List<Integer> path) {
        List<String> newPath = new ArrayList<>();
        for (int i = 0; i < path.size(); i++) {
            StringBuilder sb = new StringBuilder();
            int col = path.get(i);
            for (int j = 0; j < path.size(); j++) {
                if (j == col) {
                    sb.append(QUEEN);
                } else {
                    sb.append(DOT);
                }
            }
            newPath.add(sb.toString());
        }
        return newPath;
    }
}
