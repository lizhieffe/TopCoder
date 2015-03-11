package SRM236Div1;

import java.util.*;

public class Parking {
	int cars;
	int parks;

	public int minTime(String[] park) {
		PriorityQueue<Node> pq = getParkTime(park);
		int[] remainingParks = new int[cars];
		int[] remainingCars = new int[parks];
		int impossibleParks = 0;
		Arrays.fill(remainingParks, parks);
		Arrays.fill(remainingCars, cars);
		if (remainingParks.length < remainingCars.length)
			return -1;
		while (true) {
			Node n = pq.poll();
			if (remainingParks[n.carIndex] == 1 || (remainingCars[n.parkIndex] == 1 && impossibleParks == parks - cars)) {
				if (n.cost == Integer.MAX_VALUE)
					return -1;
				else
					return n.cost;
			}
			else if (remainingCars[n.parkIndex] == 1) {
				++impossibleParks;
			}
			--remainingParks[n.carIndex];
			--remainingCars[n.parkIndex];
		}
	}

	private PriorityQueue<Node> getParkTime(String[] park) {
		List<Pos> c = new ArrayList<Pos>();
		List<Pos> p = new ArrayList<Pos>();
		boolean[][] map = new boolean[park.length][park[0].length()];
		for (int i = 0; i < park.length; ++i) {
			char[] ch = park[i].toCharArray();
			for (int j = 0; j < ch.length; ++j) {
				if (ch[j] == 'C') {
					c.add(new Pos(i, j));
					++cars;
				}
				else if (ch[j] == 'P') {
					p.add(new Pos(i, j));
					++parks;
				}
				else if (ch[j] == 'X')
					map[i][j] = true;
			}
		}
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (int i = 0; i < c.size(); ++i)
			for (int j = 0; j < p.size(); ++j) {
				int cost = findCost(map, c.get(i).x, c.get(i).y, p.get(j).x, p.get(j).y);
				pq.add(new Node(i, j, cost));
			}
		return pq;
	}

	private int findCost(boolean[][] map, int x1, int y1, int x2, int y2) {
		boolean[][] visited = new boolean[map.length][map[0].length];
		LinkedList<Pos> queue = new LinkedList<Pos>();
		queue.add(new Pos(x1, y1, 0));
		visited[x1][y1] = true;
		while (queue.size() > 0) {
			Pos p = queue.removeFirst();
			if (p.x == x2 && p.y == y2)
				return p.cost;
			if (p.x > 0 && !map[p.x - 1][p.y] && !visited[p.x - 1][p.y]) {
				visited[p.x - 1][p.y] = true;
				queue.add(new Pos(p.x - 1, p.y, p.cost + 1));
			}
			if (p.x < visited.length - 1 && !map[p.x + 1][p.y] && !visited[p.x + 1][p.y]) {
				visited[p.x + 1][p.y] = true;
				queue.add(new Pos(p.x + 1, p.y, p.cost + 1));
			}
			if (p.y > 0 && !map[p.x][p.y - 1] && !visited[p.x][p.y - 1]) {
				visited[p.x][p.y - 1] = true;
				queue.add(new Pos(p.x, p.y - 1, p.cost + 1));
			}
			if (p.y < visited[0].length - 1 && !map[p.x][p.y + 1] && !visited[p.x][p.y + 1]) {
				visited[p.x][p.y + 1] = true;
				queue.add(new Pos(p.x, p.y + 1, p.cost + 1));
			}
		}
		return Integer.MAX_VALUE;
	}
}

class Pos {
	int x;
	int y;
	int cost;
	Pos(int x, int y) {
		this.x = x;
		this.y = y;
	}
	Pos(int x, int y, int cost) {
		this.x = x;
		this.y = y;
		this.cost = cost;
	}
}

class Node implements Comparable <Node> {
	int carIndex;
	int parkIndex;
	int cost;
	Node (int carIndex, int parkIndex, int cost) {
		this.carIndex = carIndex;
		this.parkIndex = parkIndex;
		this.cost = cost;
	}
	public int compareTo(Node n) {
		if (cost > n.cost)
			return -1;
		if (cost < n.cost)
			return 1;
		return 0;
	}
}