package leetcode;

public class LC77_Combinations {
	public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        
        combine(result, path, n, k, 1);
        return result;
    }
    
    private void combine(List<List<Integer>> result, List<Integer> path, int n, int k, int curr) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        int spaceLeft = k - path.size();
        for (int i = curr; i <= n && spaceLeft <= (n - i + 1); i++) {
            path.add(i);
            combine(result, path, n, k, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
