package mathBit;

/**
 * Q.Given two binary strings a and b, return their sum as a binary string.
 * https://leetcode.com/problems/add-binary/
 * 
 * e.g. "11" , "1" => "100"
 * 
 * start from right end 
 *  -> iter while a/b is available OR carry is remaining
 *  
 *  value to append: (first + second + carry) % 2
 *  value to carry: first + second + carry / 2
 *     
 *  
 * [NOTE]
 * 1) dont forget to decrement i, j. 
 * 2) if condition met -> 1, otherwise -> 0 when creating first and second.
 *    shouldn't be reversed!!!  
 * 
 * @author sunnypark
 *
 */

public class Q4_AddBinary {
	public static String addBinary(String a, String b) {
		int i = a.length() - 1;
        int j = b.length() - 1;
        
        int carry = 0;
        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0 || carry > 0) {
            int first = i >= 0 && a.charAt(i--) == '1' ? 1 : 0;
            int second = j >= 0 && b.charAt(j--) == '1' ? 1 : 0;
            
            int val = (first + second + carry);
            sb.insert(0, val % 2);
            carry = val / 2;
        }

        return sb.toString();
	}
	public static void main(String[] args) {
		System.out.println(addBinary("11", "1"));
	}
}
