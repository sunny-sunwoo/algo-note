package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * LC 939 Minimum Area Rectangle
 * 
 * Q. Given a set of points in the xy-plane, determine the minimum area 
 * of a rectangle formed from these points, with sides parallel to the x and y axes.
 * If there isn't any rectangle, return 0.
 * 
 * e.g.
 * Input: [[1,1],[1,3],[3,1],[3,3],[2,2]]
 * Output: 4
 * 
 * 1) TreeMap x => yList
	x=1: [1,1] [1,3] 
	x=2: [2,2]
	x=3: [3,1] [3,3]
	x=4: [4,1] [4,3]
	
   2) HashMap
	          xPos
	=> (1,3): 3
	               
	points => sort by y 
	
	iter thru points and build map
	=> key: x, value: point list
	
	iter thru entrySet to build HM
	
	 if value size < 2 ? continue
	
	 iter thru the value list
	  curr height = (y1, y2)
	  => if hm containsKey height ? keep min area
	  => key: (y1, y2), value: xPos

 * @author sunnypark
 *
 */
public class LC939_MinAreaRectangle {
	public int minAreaRect(int[][] points) {
        // sort by y
        Arrays.sort(points, (a,b) -> a[1] - b[1]);
        
        // treemap same x => point list
        TreeMap<Integer, List<Integer>> sortByXpos = new TreeMap<>();
        for (int[] point : points) {
            sortByXpos.computeIfAbsent(point[0], (unused) -> new ArrayList<>()).add(point[1]);
        }

        // possible height => latest X        
        Map<Height, Integer> yMap = new HashMap<>();
        int minArea = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> entry: sortByXpos.entrySet()) {
            List<Integer> yPointList = entry.getValue();
            int curX = entry.getKey();
            
            if (yPointList.size() < 2) {
                continue;
            }

            Collections.sort(yPointList);
            for (int i = 0; i < yPointList.size() - 1; i++) {
                for (int j = i + 1; j < yPointList.size(); j++) {
                    int y1 = yPointList.get(i);
                    int y2 = yPointList.get(j);
                    Height curHeight = Height.of(y1, y2);
                    if (yMap.containsKey(curHeight)) {
                        int prevX = yMap.get(curHeight);
                        minArea = Math.min(minArea, (curX - prevX) * curHeight.getLength());
                    }
                    yMap.put(curHeight, curX);
                }
            }
        }
        
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }
    
    private static class Height {
        int y1;
        int y2;
        
        Height (int y1, int y2) {
            this.y1 = y1;
            this.y2 = y2;
        }
        
        static Height of(int y1, int y2) {
            return new Height(y1,y2);
        }
        
        int getLength(){
            return y2 - y1;
        }
        
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Height)) {
                return false;
            }
            
            Height other = (Height) o;
            return other.y1 == this.y1 && other.y2 == this.y2;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(y1, y2);
        }
        
        @Override
        public String toString() {
            return y1 + ", " + y2;
        }
    }
}
