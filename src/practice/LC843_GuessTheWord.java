package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC843_GuessTheWord {
	private static final int STRING_LENGTH = 6;
    public void findSecretWord(String[] wordlist, Master master) {
        List<String> dict = Arrays.asList(wordlist);
        
        int callCnt = 0;
        while (callCnt < 10) {
            int matchNum = master.guess(dict.get(0));
            callCnt++;
            if (matchNum == STRING_LENGTH) {
                break;
            }
            dict = filteredDict(dict, dict.get(0), matchNum);
        }
    }
    
    private List<String> filteredDict(List<String> dict, String guessedWord, int matchNum) {
        List<String> newDict = new ArrayList<>();
        
        for (String currWord : dict) {
            if (countMatch(guessedWord, currWord, matchNum) == matchNum) {
                newDict.add(currWord);
            }
        }
        return newDict;
    }
    
    private int countMatch(String a, String b, int targetMatch) {
        int cnt = 0;
        for (int i = 0; i < STRING_LENGTH; i++) {
            char aChar = a.charAt(i);
            char bChar = b.charAt(i);
            
            if (aChar == bChar) {
                cnt++;
            }
            
            if (cnt > targetMatch) { // prunning
                return -1;
            }
        }
        return cnt;
    }
    
    interface Master {
    	public int guess(String word);
    }
}
