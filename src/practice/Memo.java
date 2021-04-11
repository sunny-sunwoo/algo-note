package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author sunnypark
 *
 */
public class Memo {

	public static void test(int pos) {
		System.out.println("TESTING from: " + pos);
		while (pos < 1025) {
			
			pos += pos & -pos;
			System.out.println(pos);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Integer.toBinaryString(-10));
		System.out.println(Integer.toBinaryString(10));
		System.out.println(-12&12);

		test(17);
		
		Map<Integer, List<Integer>> hm = new HashMap<>();
		hm.computeIfAbsent(1, (unused) -> new ArrayList<>()).add(3);
		hm.computeIfAbsent(1, (unused) -> new ArrayList<>()).add(4);
		System.out.println(hm);
	}

}
