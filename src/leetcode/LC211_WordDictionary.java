package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LC211_WordDictionary {
    TrieNode root;
    /** Initialize your data structure here. */
    public LC211_WordDictionary() {
        root = new TrieNode(' ');
    }

    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);
            if (!node.children.containsKey(curr)) {
                TrieNode newNode = new TrieNode(curr);
                node.children.put(curr, newNode);
            }
            node = node.children.get(curr);
            if (i == word.length() - 1) {
                node.isLast = true;
            }
        }
    }
    
    public boolean search(String word) {
        return search(word, root);
    }
    
    private boolean search(String word, TrieNode node) {
        for (int i = 0; i < word.length(); i++) {
            char curr = word.charAt(i);

            if (curr == '.') {// mad, ma.
                return searchNext(node.children, word, i);
            }

            if (!node.children.containsKey(curr)) {
                return false;  
            }

            node = node.children.get(curr);
            if (i == word.length() - 1) {
                break;
            }
        }
        return node.isLast;
    }
    
    private boolean searchNext(Map<Character, TrieNode> children, String word, int i) {
        for (Map.Entry<Character, TrieNode> entry : children.entrySet()) {
            if (i == word.length() - 1) {
                if (entry.getValue().isLast) {
                    return true;
                }
                continue;
            }
            if (search(word.substring(i + 1), entry.getValue())) {
                return true;
            }
        }
        return false;
    }

    private class TrieNode {
        char val;
        Map<Character, TrieNode> children;
        boolean isLast;
        
        public TrieNode(char val) {
            this.val = val;
            this.children = new HashMap<>();
        }
    }
}
