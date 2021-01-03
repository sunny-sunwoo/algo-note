package practice;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC394_decodeStrings {
	public static String decodeString_stack(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ']') {
                stack.addLast(c);
            } else { // ']'
                buildCurrStep(stack);
            }
        }
        
        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }
        // build sb from stack
        return sb.reverse().toString();
    }
    
    private static void buildCurrStep(Deque<Character> stack) {
        StringBuilder tmpStr = new StringBuilder();
        while (!stack.isEmpty() && stack.peekLast() != '[') {
            tmpStr.append(stack.removeLast()); // build str part
        }
        
        stack.removeLast(); // rm [
        
        StringBuilder tmpDigit = new StringBuilder();
        while (!stack.isEmpty() && Character.isDigit(stack.peekLast())) {
            tmpDigit.append(stack.removeLast()); // build digit part
        }
        
        // build repeating num
        // stack: 3, [, a, 1, 2, [, b, c 
        // tmpstr = cb, tmpdigit = 21
        
        // push back
        int repeatingNum = Integer.parseInt(tmpDigit.reverse().toString());

        while (repeatingNum > 0) {
            for (int i = tmpStr.length() - 1 ; i >= 0; i--) {
                stack.addLast(tmpStr.charAt(i));
            }
            repeatingNum--;
        }
    }
    
    public static String decodeString_recursive(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                Triple res = findNext(s, i);
                int digit = res.digit;
                int left = res.left;
                int right = res.right;
                
                String inside = decodeString_recursive(s.substring(left, right));
                sb.append(inside.repeat(digit));
                
                if (right == s.length()) {
                    break;
                }
                
                i = right + 1; // ptr
            } else { // alphabet
                sb.append(s.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
    
    private static Triple findNext(String s, int ptr) {
        int cnt = 0;
        Triple res = new Triple(-1, -1, -1);
        for (int i = ptr; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c) && res.left == -1) {
                if (res.digit == -1) {
                    res.digit = Character.getNumericValue(c);
                } else {
                    res.digit = res.digit * 10 + Character.getNumericValue(c);
                }
            } else if(c == '[') {
                if (res.left == -1) {
                    res.left = i + 1;
                }
                cnt++;
            } else if (c == ']'){ // c == ']'
                cnt--;
                if (cnt == 0) {
                    res.right = i;
                    break;
                }
            }
        }
        
        return res;
    }
    
    private static class Triple {
        int digit;
        int left;
        int right;
        
        Triple(int digit, int left, int right) {
            this.digit = digit;
            this.left = left; 
            this.right = right;
        }
    }
    
    public static void main(String[] args) {
    	System.out.println(decodeString_stack("ab3[a]2[bc]")); // abaaabcbc
    	System.out.println(decodeString_stack("3[a2[c]]")); // accaccacc
    	System.out.println(decodeString_stack("2[abc]3[cd]ef")); // abcabccdcdcdef

    	System.out.println(decodeString_recursive("ab3[a]2[bc]"));
    	System.out.println(decodeString_recursive("3[a2[c]]"));
    	System.out.println(decodeString_recursive("2[abc]3[cd]ef"));
    	
    }
}
