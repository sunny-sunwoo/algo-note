package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import practice.ShoppingPatterns.Pair;

/**
 * Nearest Cities
 * https://aonecode.com/amazon-online-assessment-nearest-cities
 * 
	Input:
	numOfPoints = 3
	points = ["p1","p2","p3"]
	xCoordinates = [30, 20, 10]
	yCoordinates = [30, 20, 30]
	numOfQueriedPoints = 3
	queriedPoints = ["p3", "p2", "p1"]


	Output:
	["p1", NONE, "p3"] 

 * [logic]
 * xMap <String, List<Pair>>
 * yMap <String, List<Pair>>
 * 
 * iter thru all input
 *   xCandidates
 *   yCandidates
 *   
 *   => both empty? add NONE to answer
 *   => otherwise? find closest among xCandidates OR yCandidates
 *                 add the pos name to the answer
 *   
 * rt answers
 * 
 * Pair class
 *   String name
 *   int[] pos
 *   
 * @author sunnypark
 *
 */
public class NearestPoints {
	private static String NONE = "NONE";
	private static Map<String, Pair> pointMap;
	private static Map<Integer, Set<Pair>> xMap;
	private static Map<Integer, Set<Pair>> yMap;
	
	public static String[] findNearestCities(int numOfPoints, String[] points, int[] xCoordinates, int[] yCoordinates, int numOfQuriedPoints, String[] queriedPoints) {
		String[] answers = new String[numOfQuriedPoints];
		init(points, xCoordinates, yCoordinates); // TODO: build x and y maps, pointmap

		for (int i = 0; i < numOfQuriedPoints; i++) {
			String input = queriedPoints[i];
			Pair currPos = pointMap.get(input);
			int currX = currPos.getX();
			int currY = currPos.getY();
			
			Set<Pair> xCandidates = new HashSet<>(xMap.getOrDefault(currX, new HashSet<>()));
			Set<Pair> yCandidates = new HashSet<>(yMap.getOrDefault(currY, new HashSet<>()));
			
			xCandidates.remove(currPos);
			yCandidates.remove(currPos);

			if (xCandidates.isEmpty() && yCandidates.isEmpty()) {
				answers[i] = NONE;
				continue;
			}
		
			Set<Pair> candidates = new HashSet<Pair>() {{ addAll(xCandidates); addAll(yCandidates); }};
			answers[i] = findClosest(input, candidates);
		}
		return answers;
	}
	
	private static void init(String[] points, int[] xCoordinates, int[] yCoordinates) {
		xMap = new HashMap<>();
		yMap = new HashMap<>();
		pointMap = new HashMap<>();
		
		for (int i = 0; i < points.length; i++) {
			int xPos = xCoordinates[i];
			int yPos = yCoordinates[i];
			Pair newPos = new Pair(points[i], new int[] {xPos, yPos});
			pointMap.put(newPos.getName(), newPos);

			xMap.computeIfAbsent(xPos, (unused) -> new HashSet<Pair>()).add(newPos);
			yMap.computeIfAbsent(yPos, (unused) -> new HashSet<Pair>()).add(newPos);
		}
	}
	
	private static String findClosest(String input, Set<Pair> candidates) {
		Pair inputPos = pointMap.get(input);
		String closestCityName = "";
		int minDist = Integer.MAX_VALUE;
		for (Pair pos : candidates) {
			int currDist = getDist(inputPos, pos); // TODO
			if (currDist < minDist) {
				closestCityName = pos.getName(); // TODO
			}
		}
		return closestCityName;
	}
	
	private static int getDist(Pair pos1, Pair pos2) {
		int x1 = pos1.getPos()[0];
		int y1 = pos1.getPos()[1];
		
		int x2 = pos2.getPos()[0];
		int y2 = pos2.getPos()[1];
		
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	private static class Pair {
		private String name;
		private int[] pos;
		
		Pair(String name, int[] pos) {
			this.name = name;
			this.pos = pos;
		}
		
		public int[] getPos() {
			return pos;
		}
		
		public String getName() {
			return name;
		}
		
		public int getX() {
			return pos[0];
		}
		
		public int getY() {
			return pos[1];
		}
		
		@Override
		public String toString() {
			return name;
		}
		
		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Pair)) {
				return false;
			}
			Pair other = (Pair) o;
			return this.getPos().equals(other.getPos());
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(this.getPos());
		}
	}

	public static void main(String[] args) {
		int numOfPoints = 3;
		String[] points = {"p1", "p2", "p3"};
		int[] xCoordinates = {30, 20, 10};
		int[] yCoordinates = {30, 20, 30};
		int numOfQuriedPoints = 3;
		String[] queriedPoints = {"p3", "p2", "p1"};
		
		String[] nearestCities = findNearestCities(numOfPoints, points, xCoordinates, yCoordinates, numOfQuriedPoints, queriedPoints);
		System.out.println(Arrays.toString(nearestCities));
	}
}
