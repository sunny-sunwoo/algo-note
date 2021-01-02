package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class T2_PrefixTrie {
	
	private final Node trie;
	public T2_PrefixTrie(Set<String> dictionary) {
		trie = new Node("");
		for (String word : dictionary) {
			insert(word);
		}
	}
	
	public void insert(String word) {
		Node curr = trie;
		for (int i = 0; i < word.length(); i++) {
			char c = word.charAt(i);
			Node child = curr.children.get(c);
			
			if (child == null) {
				curr.children.put(c, new Node(word.substring(0, i + 1)));
			}
			
			// check last char
		}
	}
	
	public List<String> getUniquePrefix() {
		List<String> result = new ArrayList<>();
		return result;
	}
	
	private class Node {
		String prefix;
		int counter;
		Map<Character, Node> children;
		boolean isWord;
		
		Node(String prefix) {
			this.prefix = prefix;
			counter = 0;
			children = new HashMap<>();
			isWord = false;
		}
	}
    
    public static void main(String[] args) {
    }
}
