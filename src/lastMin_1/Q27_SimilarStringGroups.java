package lastMin_1;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Q27_SimilarStringGroups {
	public static int numSimilarGroups(String[] strs) {
        DisjointSet uf = new DisjointSet(strs.length);
        
        for (int i = 0; i < strs.length; i++) {
            for (int j = i + 1; j < strs.length; j++) {
                if (isSimilar(strs[i], strs[j])) {
                    uf.merge(i, j);
                    System.out.println(strs[i] + " " + strs[j] + " " + uf);
                }
            }
        }
        
        return uf.getUniqParentNumber();
    }
    
    private static class DisjointSet {
        int[] parents;
        int[] ranks;
        
        public DisjointSet(int n) {
            parents = new int[n];
            ranks = new int[n];
            
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                ranks[i] = 1;
            }
        }
        
        int find(int u) {
            if (parents[u] == u) return parents[u];
            
            parents[u] = find(parents[u]);
            return parents[u];
        }
        
        DisjointSet merge(int u, int v){
            int uPar = find(u);
            int vPar = find(v);
            
            if (uPar == vPar) return this;
            
            if (ranks[uPar] > ranks[vPar]) {
                parents[vPar] = uPar;
            } else {
                parents[uPar] = vPar;
            }
            
            if (ranks[uPar] == ranks[vPar]) {
                ranks[vPar]++;
            }
            
            return this;
        }
        
        int getUniqParentNumber() {
            return IntStream.of(parents).boxed().collect(Collectors.toSet()).size();
        }
        
        @Override
        public String toString() {
        	return Arrays.toString(parents);
        }
    }
    
    private static boolean isSimilar(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            int c1 = s1.charAt(i);
            int c2 = s2.charAt(i);
            
            if (c1 != c2) {
                cnt++;
            }
            
            if (cnt > 2) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
    	String[] strs1 = {"tars","rats","arts","star"};
    	String[] strs2 = {"ajdidocuyh","djdyaohuic","ddjyhuicoa","djdhaoyuic","ddjoiuycha","ddhoiuycja","ajdydocuih","ddjiouycha","ajdydohuic","ddjyouicha"};
    	System.out.println(numSimilarGroups(strs2));
    }
}
