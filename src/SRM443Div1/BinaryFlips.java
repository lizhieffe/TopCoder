package SRM443Div1;

import java.util.*;

public class BinaryFlips {
	public int minimalMoves(int A, int B, int K) {
		boolean[][] visited = new boolean[A + B + 1][A + B + 1];
		return BFS(visited, A, B, 0, K, K);
	}

	private int BFS(boolean[][] visited, int x1, int y1, int x2, int y2, int K) {
		Queue<Point> queue = new LinkedList<Point>();
		queue.offer(new Point(x1, y1));
		visited[x1][y1] = true;
		while (queue.size() > 0) {
			Point p = queue.poll();
			if (p.x == x2 && p.y == y2)
				return p.cost;
			List<Point> neighbors = findNeighbors(p.x, p.y, K);
			for (Point neighbor : neighbors) {
				int x = neighbor.x;
				int y = neighbor.y;
				if (!visited[x][y]) {
					visited[x][y] = true;
					neighbor.cost = p.cost + 1;
					queue.add(neighbor);
				}
			}
		}
		return -1;
	}

	private List<Point> findNeighbors(int x, int y, int k) {
		int beg = Math.max(0, k - y);
		int end = Math.min(x, k);
		Set<Point> neighbors = new HashSet<Point>();
		for (int i = beg; i <= end; ++i) {
			int tmpX = x - i + (k - i);
			int tmpY = x + y - tmpX;
			neighbors.add(new Point(tmpX, tmpY));
		}
		return new ArrayList<Point>(neighbors);
	}
}

class Point implements Comparable <Point> {
	int x;
	int y;
	int cost;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int compareTo(Point p) {
		int l1 = (int)(Math.pow(x, 2) + Math.pow(y, 2));
		int l2 = (int)(Math.pow(p.x, 2) + Math.pow(p.y, 2));
		if (l1 < l2)
			return -1;
		else if (l1 > l2)
			return 1;
		else if (x == p.x && y == p.y)
			return 0;
		else if (x < p.x)
			return -1;
		else 
			return 1;
	}
}