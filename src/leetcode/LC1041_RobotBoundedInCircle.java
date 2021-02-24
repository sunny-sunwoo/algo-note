package leetcode;

/**
 * 
 * LC 1041. robot bounded in circle
 * 
	dirs = top, right, bottom, left
	
	R: top, right, bottom, left
	=> (idx + 1) % 4 
	
	L: left, bottom, right, top
	=> (idx + 3) % 4 
	
	GGLLGG
	     ^
 * @author sunnypark
 *
 */
public class LC1041_RobotBoundedInCircle {
	 public boolean isRobotBounded(String instructions) {
	        int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
	        int[] currIdx = {0, 0};
	        int dirIdx = 0;
	        int cnt = 0;
	        
	        while (cnt < 4) {
	            for (int i = 0; i < instructions.length(); i++) {
	                char curr = instructions.charAt(i);
	                switch(curr) {
	                    case 'G':
	                        move(currIdx, dirs[dirIdx]);
	                        break;
	                        
	                    case 'L':
	                        dirIdx = (dirIdx + 3) % 4;
	                        break;
	                    
	                    case 'R':
	                        dirIdx = (dirIdx + 1) % 4;
	                        break;
	                        
	                    default:
	                        throw new IllegalArgumentException();
	                }
	            }
	            cnt++;
	        }
	        return isAtStartingPoint(currIdx);
	    }
	    private boolean isAtStartingPoint(int[] currIdx) {
	        return currIdx[0] == 0 && currIdx[1] == 0;
	    }

	    private void move(int[] currIdx, int[] dir) {
	        currIdx[0] += dir[0];
	        currIdx[1] += dir[1];
	    }
}
