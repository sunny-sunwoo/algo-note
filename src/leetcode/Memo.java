package leetcode;

import java.util.Arrays;

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
	}

}
