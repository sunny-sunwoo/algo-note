package practice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Compound word search

input: foo, bar, foobar, foobarzaa, zaa
                
output
 foobar: [foo,bar]
 foobarzaa: [foo,bar,zaa], [foobar,zaa]
 
      root
     f  b   z
    o    a   a
   o.t    r   a
  b
 a
r,t
z
a
a.t

1. TrieNode class
	char val,
	Map<Character,TrieNode> children,
	boolean isLast

2. addWord

3. search(String word)

4. private search (node, res, path, ptr, word)
  base case: if ptr is at last?
    => res.add(Pair.of(word, new path))

  curr char = word .charAt(ptr)
  if node.isLast? 
    add substring to path 
    => recursive call with root, ptr + 1
  
  if node.children containsKey
   => recursive call with next node, ptr + 1

 * @author sunnypark
 *
 */
public class CompoundWordSearch {
	TrieNode root;
	List<String> wordList;
	public CompoundWordSearch(List<String> words) {
		root = new TrieNode(' ');
		wordList = words;
		for (String word : words) {
			addWord(word);
		}
	}
	
	private void addWord(String word) {
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
	
	public List<Pair> search() {
		List<Pair> result = new ArrayList<>();
		
		for (String word:  wordList) {
			search(root, result, new ArrayList<>(), 0, 0, word);
		}
		return result;
	}
	
	private void search(TrieNode node, List<Pair> result, List<String> path, int from, int to, String word) {
		if (to == word.length()) {
			if (node.isLast) {
				path.add(word.substring(from, to));
			}
			if (to - from != word.length()) {
				result.add(Pair.of(word, new ArrayList<>(path)));
			}
			return;
		}

		char curr = word.charAt(to);
		TrieNode nextNode = node;
		List<String> currPath = new ArrayList<>(path);

		if (node.isLast) {
			currPath.add(word.substring(from, to));
			nextNode = root;
			for (Map.Entry<Character, TrieNode> entry : nextNode.children.entrySet()) {
				search(entry.getValue(), result, new ArrayList<>(currPath), to, to + 1, word);
			}
		}

		nextNode = node;
		if (node.children.containsKey(curr)) {
			nextNode = node.children.get(curr);
			search(nextNode, result, new ArrayList<>(path), from, to + 1, word);
		}
	}
	
	private static class Pair {
		String word;
		List<String> wordSet;
		
		private Pair(String word, List<String> wordSet) {
			this.word = word;
			this.wordSet = wordSet;
		}
		
		public static Pair of(String word, List<String> wordSet) {
			return new Pair(word, wordSet);
		}
		
		@Override
		public String toString() {
			return word + ": " + wordSet;
		}
		
	}
	
	private class TrieNode {
		char val;
		Map<Character, TrieNode> children;
		boolean isLast;
		
		TrieNode(char val) {
			this.val = val;
			children = new HashMap<>();
		} 
	}
	
	public static void main(String[] args) {
		List<String> words = List.of("foo", "bar", "foobar", "zaa", "foobarzaa");
		CompoundWordSearch searcher = new CompoundWordSearch(words);
		
		System.out.println(searcher.search());
	}

	
}
