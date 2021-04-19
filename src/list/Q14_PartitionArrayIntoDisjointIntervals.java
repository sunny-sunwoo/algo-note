package list;

/**
 * input: int[] => {5,0,3,8,6,4,6, // 10}
 *                              ^
 * output: 3
 * 
 * leftMax:  8 
 * totalMax: 8
 * res: 3
 * 
 *  left group < right group
 *  rt right group starting point
 * 
 * iter thru arr
 * case1) curr val <= left max?
 *  res = i + 1
 *  left max = total max
 *  
 * case2) curr val > total max?
 *  total max = curr val
 *  
 * @author sunnypark
 *
 */
public class Q14_PartitionArrayIntoDisjointIntervals {
	public static int getPartitionPos(int[] arr) {
		int leftMax = 0;
		int totalMax = 0;
		int pos = 0;
		
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] <= leftMax) {
				pos = i + 1;
				leftMax = totalMax;
				continue;
			}
			
			if (arr[i] > totalMax) {
				totalMax = arr[i];
			}
		}
		
		return pos;
	}
	
	public static void main(String[] args) {
		int[] input = {5,0,3,8,6};
		System.out.println(getPartitionPos(input)); // output: 3
	}
}
