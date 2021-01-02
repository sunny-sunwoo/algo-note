package practice;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC394_decodeStrings {
	public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        
        Deque<String> numStack = new ArrayDeque<>();
        Deque<String> charStack = new ArrayDeque<>();
        
        int i = 0;
        String numSoFar = "";
        String strSoFar = "";
        
        int cnt = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
            	numSoFar += String.valueOf(c);
            	
            	if (!strSoFar.isEmpty()) {
            		if (cnt == 0) {
            			sb.append(strSoFar);
            		}
            		charStack.addLast(strSoFar);
            		strSoFar = "";
            	}
            } else if ('a' <= c && c <= 'z') {
                strSoFar += String.valueOf(c);
            } else if (c == '[') {
            	cnt++;
            	numStack.addLast(numSoFar);
            	numSoFar = "";
            } else if (c == ']'){ 
            	cnt--;
            	if (!strSoFar.isEmpty()) {
            		charStack.addLast(strSoFar);
            		strSoFar = "";
            	}
            	int repeatingNum = Integer.parseInt(numStack.removeLast());
                StringBuilder nxtStr = new StringBuilder(charStack.removeLast().repeat(repeatingNum));
                
                if (cnt != 0) {
                    charStack.addLast(nxtStr.insert(0, charStack.removeLast()).toString());
                } else {
                	sb.append(nxtStr);
                }
            }
            i++;
        }
        
        return strSoFar.isEmpty() ? sb.toString() : sb.append(strSoFar).toString();
    }
    
    public static void main(String[] args) {
    	System.out.println(decodeString("ab3[a]2[bc]"));
    	System.out.println(decodeString("3[a2[c]]"));
    	System.out.println(decodeString("2[abc]3[cd]ef"));
    	
    }
}
