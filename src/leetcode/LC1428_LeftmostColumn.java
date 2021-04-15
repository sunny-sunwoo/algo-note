package leetcode;

import java.util.List;

public class LC1428_LeftmostColumn {
	public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> dimentions = binaryMatrix.dimensions();
        int row = dimentions.get(0);
        int col = dimentions.get(1);
        
        int i = 0; // ++
        int j = col - 1; // --
        int res = -1;
        while (i < row && j >= 0) {
            int curr = binaryMatrix.get(i,j);
            if (curr == 1) {
                res = j;
                j--;
            } else { // 0?
                i++;
            }
        }
        return res;
    }
	
	interface BinaryMatrix {
		public int get(int row, int col);
		public List<Integer> dimensions();
	};
}
