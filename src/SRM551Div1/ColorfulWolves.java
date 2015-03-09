package SRM551Div1;

import java.util.*;

public class ColorfulWolves {
	public int getmin(String[] colormap) {
		if (colormap == null || colormap.length == 0)
			return -1;
		if (colormap.length == 1)
			return 1;
		boolean[][] map = buildMap(colormap);
		return dikjestra(map);
	}

	private boolean[][] buildMap(String[] colormap) {
		int num = colormap.length;
		boolean[][] result = new boolean[num][num];
		for (int i = 0; i < num; ++i) {
			String s = colormap[i];
			for (int j = 0; j < num; ++j) {
				result[i][j] = (s.charAt(j) == 'Y') ? true : false;
			}
		}
		return result;
	}

	private int dikjestra(boolean[][] map) {
		int num = map.length;
		List<Node> nodes = new ArrayList<Node>();
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		for (int i = 0; i < num; ++i) {
			Node n = null;
			if (i == 0)
				n = new Node(i, 0);
			else
				n = new Node(i, Integer.MAX_VALUE);
			nodes.add(n);
			pq.add(n);
		}
		while (pq.size() > 0) {
			Node curr = pq.poll();
			if (curr.cost == Integer.MAX_VALUE)
				return -1;
			if (curr.id == num - 1)
				return curr.cost;
			int costSoFar = 0;
			for (int i = 0; i < num; ++i) {
				if (i != curr.id && map[curr.id][i])
					if (curr.cost + costSoFar < nodes.get(i).cost) {
						Node tmpNode = nodes.get(i);
						pq.remove(tmpNode);
						tmpNode.cost = curr.cost + costSoFar;
						pq.add(tmpNode);
					}
					++costSoFar;
			}
		}
		return -1;
	}

	private class Node implements Comparable <Node> {
		int id;
		int cost;
		Node (int id, int cost) {
			this.id = id;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node n) {
			if (cost < n.cost)
				return -1;
			else if (cost > n.cost)
				return 1;
			else
				return 0;
		}
	}
}