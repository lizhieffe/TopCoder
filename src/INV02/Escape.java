package INV02;


import java.util.*;

public class Escape {

	private static int SIZE = 501;

	public int lowest(String[] harmful, String[] deadly) {
		Node[][] map = getMap(harmful, deadly);
		return dikjestra(map);
	}

	private Node[][] getMap(String[] harmful, String[] deadly) {
		
		Node[][] map = new Node[SIZE][SIZE];
		for (int i = 0; i < SIZE; ++i)
			for (int j = 0; j < SIZE; ++j)
				map[i][j] = new Node(i, j);

		for (String s : harmful) {
			try {
				String[] tmp = s.split(" ");
				int x1 = Integer.parseInt(tmp[0]);
				int y1 = Integer.parseInt(tmp[1]);
				int x2 = Integer.parseInt(tmp[2]);
				int y2 = Integer.parseInt(tmp[3]);
				for (int i = x1; i <= x2; ++i)
					for (int j = y1; j <= y2; ++j)
						map[i][j].type = 1;
			}
			catch (NumberFormatException ex) {

			}
		}

		for (String s : deadly) {
			try {
				String[] tmp = s.split(" ");
				int x1 = Integer.parseInt(tmp[0]);
				int y1 = Integer.parseInt(tmp[1]);
				int x2 = Integer.parseInt(tmp[2]);
				int y2 = Integer.parseInt(tmp[3]);
				for (int i = x1; i <= x2; ++i)
					for (int j = y1; j <= y2; ++j)
						map[i][j].type = 2;
			}
			catch (NumberFormatException ex) {
			
			}
		}

		map[0][0].type = 0;
		map[SIZE - 1][SIZE - 1].type = 0;
		return map;
	}

	private int dikjestra(Node[][] map) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		pq.add(map[0][0]);
		while (pq.size() > 0) {
			Node curr = pq.poll();
			int x = curr.x;
			int y = curr.y;
			if (x == SIZE - 1 && y == SIZE - 1)
				return curr.cost;
			if (x > 0 && !map[x - 1][y].visited && map[x - 1][y].type != 2) {
				map[x - 1][y].cost = curr.cost + curr.type;
				map[x - 1][y].visited = true;
				pq.add(map[x - 1][y]);
			}
			if (x < SIZE - 1 && !map[x + 1][y].visited && map[x + 1][y].type != 2) {
				map[x + 1][y].cost = curr.cost + curr.type;
				map[x + 1][y].visited = true;
				pq.add(map[x + 1][y]);
			}
			if (y > 0 && !map[x][y - 1].visited && map[x][y - 1].type != 2) {
				map[x][y - 1].cost = curr.cost + curr.type;
				map[x][y - 1].visited = true;
				pq.add(map[x][y - 1]);
			}
			if (y < SIZE - 1 && !map[x][y + 1].visited && map[x][y + 1].type != 2) {
				map[x][y + 1].cost = curr.cost + curr.type;
				map[x][y + 1].visited = true;
				pq.add(map[x][y + 1]);
			}
		}
		return -1;
	}
}

class Node implements Comparable <Node> {
	int x;
	int y;
	int cost;
	int type;
	boolean visited;

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(Node n2) {
		if (cost < n2.cost)
			return -1;
		else if (cost > n2.cost)
			return 1;
		else
			return 0;
	}
}