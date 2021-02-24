package leetcode;

public class LC415_AddStrings {
	public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        int cnt = 1;
        
        int len1 = num1.length();
        int len2 = num2.length();
        
        while (carry != 0 || cnt <= len1 || cnt <= len2) {
            int first = cnt <= len1 ? num1.charAt(len1 - cnt) - '0': 0;
            int second = cnt <= len2 ? num2.charAt(len2 - cnt) - '0' : 0;
            
            int val = (first + second + carry) % 10;
            carry = (first + second + carry)  / 10;
            sb.append(val);

            cnt++;
        }
        return sb.reverse().toString();
    }
}
