package leetcode;

/**
 * lc42. trapping rain water
 * 
  [Approach 1] build leftMax and rightMax
	water to fill at pos 
		= Math.min(leftMax[pos], rightMax[pos]) - height[pos]

  [Approach 2] iter forward and backward
	[0,1,0,2,1,0,1,3,2,1,2,1]
	     1   1 2 1     1
	
	iter thru the height arr 
	
	max <= height[i] ?
	    total += curr
	    curr = 0
	    max = height[i]
	
	otherwise?
	    curr += (max - height[i])
	
	
	check last from backward
	while height[i] != max
	    same logic. 

 * @author sunnypark
 *
 */
public class LC42_TrappingRainWater {
	public int trap_approach1(int[] height) {
        int[] leftMax = buildLeftMax(height);
        int[] rightMax = buildRightMax(height);

        int total = 0;
        for (int i = 0; i < height.length; i++) {
            int maxHeight = Math.min(leftMax[i], rightMax[i]);
            total += (maxHeight - height[i]);
        }
        return total;
    }
    
    private int[] buildLeftMax(int[] height) {
        int[] maxArr = new int[height.length];
        
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            max = Math.max(max, height[i]);
            maxArr[i] = max;
        }
        
        return maxArr;
    }
    
    private int[] buildRightMax(int[] height) {
        int[] maxArr = new int[height.length];
        
        int max = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            max = Math.max(max, height[i]);
            maxArr[i] = max;
        }
        
        return maxArr;
    }
    
    public int trap_approach2(int[] height) {
        if (height.length == 0) {
            return 0;
        }

        int total = 0;
        int max = 0;
        int curr = 0;
        
        // iter from left
        for (int i = 0; i < height.length; i++) {
            if (max <= height[i]) {
                total += curr;
                curr = 0;
                max = height[i];
            } else {
                curr += (max - height[i]);
            }
        }
        
        // confirm from backward
        int rightMax = 0;
        curr = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            if (rightMax <= height[i]) {
                total += curr;
                curr = 0;
                rightMax = height[i];
            } else {
                curr += (rightMax - height[i]);
            }
            
            if (height[i] == max) {
                break;
            }
        }
        
        return total;
    }
}
