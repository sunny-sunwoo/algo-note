package leetcode;

/**
 * LC 69 sqrt
 * 
 * [note]
 *    1) 2 <= possibleSqrt <= x/2
 *    2) consider overflow! when mid * mid calculated
 *  
 * e.g. x = 10
 * lo = 2, hi = 5, mid = 3
 *   => 3 * 3 < 10 
 *   
 * lo = 4, hi = 5, mid = 4
 *   => 4 * 4 > 10
 *   
 * lo = 4, hi = 3 !!! 
 * 
 * when search range goes back to smaller numbers,
 *   -> lo, hi overlapping because hi passes lo
 *   -> sqrt found from hi at the end
 * 
 * @author sunnypark
 *
 */
public class LC69_sqrt {
	public int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        
        int lo = 2;
        int hi = x/2;
        
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long midPower = (long) mid * mid;
            
            if (midPower == x) {
                return mid;
            }
            
            if (midPower <  x) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        
        return hi;
    }
}
