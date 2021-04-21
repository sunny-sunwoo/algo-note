package leetcode;

/**
 * LC 568 max vacation days
 * 
	flights[i][j] => city i, city j
	[0,1,1],
	[1,0,1],
	[1,1,0]
	
	days[i][j] => city i, week j
	 1 2 3
	[1,3,1],
	[6,0,3],
	[3,3,3]
	
	
	dfs w/memo
	    w0 w1 w2
	--+-----------
	  |        1
	  |        3
	  |        3
	  
	base case:
	i) weekNo == totalWeek
	    rt 0
	
	ii) memo val != -1
	   rt memo val
	   
	logic:
	for each city
	if same city OR flight i -> j available
	    maxVac =  max(maxVac, dfs(j, weekNo + 1) + days[i][weekNo]) 
	
	=> memo the maxVac and return
 * @author sunnypark
 *
 */
public class LC568_MaxVacationDays {
	int totalCity;
    int totalWeek;
    
    public int maxVacationDays(int[][] flights, int[][] days) {
        totalCity = days.length;
        totalWeek = days[0].length;
        Integer[][] memo = new Integer[totalCity][totalWeek];
        return dfs(flights, days, memo, 0, 0); // curr city, week no
    }
    
    private int dfs(int[][] flights, int[][] days, Integer[][] memo, int currCity, int weekNo) {
        if (weekNo == totalWeek) {
            return 0;
        }
        
        if (memo[currCity][weekNo] != null) {
            return memo[currCity][weekNo];
        }
        
        int maxVac = 0;
        for (int nextCity = 0; nextCity < totalCity; nextCity++) {
            if (currCity == nextCity || flights[currCity][nextCity] == 1) {
                maxVac = Math.max(maxVac, days[nextCity][weekNo] + dfs(flights, days, memo, nextCity, weekNo + 1));
            }
        }
        
        return memo[currCity][weekNo] = maxVac;
    }
}
