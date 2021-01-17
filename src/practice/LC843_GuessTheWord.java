package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LC843. Guess the Word
 * 
 * Q. We are given a word list of unique words, each word is 6 letters long, 
 * and one word in this list is chosen as secret.
 * 
 * You may call master.guess(word) to guess a word. 
 * The guessed word should have type string and must be from the original list with 6 lowercase letters.
 * 
 * For each test case, you have 10 guesses to guess the word. 
 * there will be 5 additional test cases, each with 100 words in the word list. 
 * 
 * => meaning there's no answer which can be the solution for every possible testcases.
 * e.g. dict = "aaaaaa", "bbbbbb", "cccccc" , ... "zzzzzz" => we can't find the secret in 10 guesses
 * 
 * [Approach] filter out 
 * while call cnt number is less than 10, 
 * - call master.guess with the very first word
 * - after each call, filter out the words which are not candidate
 * 
 * 	 =>  filter function : find words with the same match number!! b/c we are narrowing down to only ONE word. 
 * 
 * [Improved] find the guess word which can filter out the most words! 
 *    => which means, find the word with the biggest similarity with other words in the dict.
 *    => instead of guessing with the very first word. 
 * @author sunnypark
 *
 */
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
    
    public void findSecretWord_optimized(String[] wordlist, Master master) {
        List<String> dict = Arrays.asList(wordlist);
        
        int callCnt = 0;
        while (callCnt < 10) {
            String wordToGuess = findWordToGuess(dict);
            int matchNum = master.guess(wordToGuess);
            callCnt++;
            if (matchNum == STRING_LENGTH) {
                break;
            }
            
            dict = filteredDict(dict, wordToGuess, matchNum);
        }
    }
    
    private String findWordToGuess(List<String> dict) {
        String candidate = "";
        int maxTotalMatch = Integer.MIN_VALUE;
        for (String word : dict) {
            int currTotalMatch = 0;
            for (String otherWord: dict) {
                if (word.equals(otherWord)) {
                    continue;
                }
                currTotalMatch += countMatch(word, otherWord);
            }
            if (currTotalMatch > maxTotalMatch) {
                candidate = word;
                maxTotalMatch = currTotalMatch;
            }
        }
        
        return candidate;
    }
    
    private List<String> filteredDict(List<String> dict, String guessedWord, int matchNum) {
        List<String> newDict = new ArrayList<>();
        
        for (String currWord : dict) {
            if (countMatch(guessedWord, currWord) == matchNum) {
                newDict.add(currWord);
            }
        }
        return newDict;
    }
    
    private int countMatch(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < STRING_LENGTH; i++) {
            char aChar = a.charAt(i);
            char bChar = b.charAt(i);
            
            if (aChar == bChar) {
                cnt++;
            }
        }
        return cnt;
    }
    
    interface Master {
    	public int guess(String word);
    }
}
