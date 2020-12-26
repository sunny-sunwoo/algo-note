package mathBit;
/**
 * Q. given integer, return excel title.
 * 
 * e.g. 
 * 1 -> A
 * 2 -> B
 * ..
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * ..
 * 52 -> AZ
 * 53 -> BA
 * 
 * [approach] 26 digit num
 * tail => use %26
 * nxt => use /26
 * 
 * @author sunnypark
 *
 */
public class Q16_ExcelTitle {
	public static String convertExcelTitle(int num) {
		StringBuilder sb = new StringBuilder();
		while (num > 0) {
			int tail = (num - 1) % 26;
			char c = (char) (tail + 'A'); // 0 -> A
			sb.append(c);
			num = (num - 1) / 26;
		}
		return sb.reverse().toString();
	}
	
	public static void main(String[] args) {
		System.out.println(convertExcelTitle(1));
		System.out.println(convertExcelTitle(26));
		System.out.println(convertExcelTitle(27));
		System.out.println(convertExcelTitle(28));
	}
}
