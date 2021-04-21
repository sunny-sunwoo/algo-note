package leetcode;

/**
 * LC 785 graph bipartition

0 -> 1,3
1 -> 0,2
2 -> 1,3
3 -> 0,2

visited
t,t,t,t

color
f,t,t,f


public:
init visited and color (boolean arr)
visit 0
call dfs from 0

dfs:
for each neighbor
1. not visited? 
   color = !curr color
   visit = true
   dfs w/neighbor
   
2. visited?  
    if same color? rt false
    
 * @author sunnypark
 *
 */
public class LC785_GraphBipartition {
    public static boolean isBipartite(int[][] graph) {
        boolean[] visited = new boolean[graph.length];
        boolean[] color = new boolean[graph.length];
        
        for (int i = 0; i < graph.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            visited[i] = true;
            if (!dfs(visited, color, i, graph)) {
                return false;
            }
        }

        return true;
    }
    
    private static boolean dfs(boolean[] visited, boolean[] color, int curr, int[][] graph) {
        for (int next : graph[curr]) {
            if (!visited[next]) {
                visited[next] = true;
                color[next] = !color[curr];
                if (!dfs(visited, color, next, graph)) {
                    return false;
                }
            } else {
                if (color[next] == color[curr]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
    	// [[1,3],[0,2],[1,3],[0,2]] => true
    	// [[1,2,3],[0,2],[0,1,3],[0,2]] => false
    }
}
