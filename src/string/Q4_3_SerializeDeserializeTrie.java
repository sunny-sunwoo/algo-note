package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Q4_3
 * trie -> string without parent id
 * 
 *      root 
 *    a      b
 *  t,s,r    a
 *  l
 * 
 * how to serialize/deserialize?
 * 
 * e.g. root{   s{t{l},s,r},     b{a}    }
 *          ^                            ^
 * 
 * [DESERIALIZE]
 * 1. find first {, last } 
 * 2. build a new TrieNode using value before { 
 * 
 * 3. build string list for next recursive calls
 * 
 * 4. iter thru string list and build children list
 *     TrieNode curr = deserialze(parsed string)
 *     
 * 5. newNode.children.addAll(childrenList)
 * 6. rt root
 * 
 * [SERIALIZE]
 *     => base case(leaf): NODE
 *     => NODE + { +  CHILD1, CHILD2 +  }
 * @author sunnypark
 *
 */
public class Q4_3_SerializeDeserializeTrie {
	public static TrieNode deserialize(String s) {
		int openIdx = s.indexOf("{");
		int closeIdx = s.lastIndexOf("}");
		
		String rootVal = s.substring(0, openIdx);
		TrieNode root = new TrieNode(Integer.valueOf(rootVal));
		return root;
		
	}
	private static class TrieNode {
		private static int id = 1;
		int val;
		List<TrieNode> children;
		
		TrieNode() {
			this.val = id;
			id++;
			children = new ArrayList<>();
		}
		
		TrieNode(int val) {
			this.val = val;
			children = new ArrayList<>();
		}
		
		@Override
		public String toString() {
			if (children.size() == 0) {
				return String.valueOf(val);
			}
			
			StringBuilder childSb = new StringBuilder();
			for (TrieNode child : children) {
				childSb.append(child).append(",");
			}
			childSb.setLength(childSb.length() - 1); // removing last ,
			
			return String.valueOf(val) + "{" + childSb + "}";
		}
	}
	
	public static void main(String[] args) {
		TrieNode t1 = new TrieNode();
		TrieNode t2 = new TrieNode();
		TrieNode t3 = new TrieNode();
		TrieNode t4 = new TrieNode();
		TrieNode t5 = new TrieNode();
		TrieNode t6 = new TrieNode();
		
		List<TrieNode> l1 = Arrays.asList(t2, t3);
		List<TrieNode> l2 = Arrays.asList(t4, t5, t6);
		
		t1.children.addAll(l1);
		t2.children.addAll(l2);
		
		System.out.println(t1);
		String query = t1.toString();
		TrieNode root = deserialize(query);
		System.out.println(root);
	}
}
