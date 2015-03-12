package SRM278Div1;

import java.util.*;

public class UnitsMoving {
	public double bestTime(String[] start, String[] finish) {
		PriorityQueue<Node> times = getTimes(start, finish);
		int num = finish.length;
		int[] begRem = new int[num];
		int[] endRem = new int[num];
		Arrays.fill(begRem, num);
		Arrays.fill(endRem, num);
		while (true) {
			Node n = times.poll();
			if (begRem[n.beg] == 1 || endRem[n.end] == 1)
				return n.time;
			else {
				--begRem[n.beg];
				--endRem[n.end];
			}
		}
	}

	private PriorityQueue<Node> getTimes(String[] start, String[] finish) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		int num = start.length;
		int[][] end = new int[num][2];
		for (int i = 0; i < num; ++i) {
			String[] s = finish[i].split(" ");
			try {
				end[i][0] = Integer.parseInt(s[0]);
				end[i][1] = Integer.parseInt(s[1]);
			}
			catch (NumberFormatException e) {}
		}
		for (int i = 0; i < num; ++i) {
			String[] s = start[i].split(" ");
			try {
				int begx = Integer.parseInt(s[0]);
				int begy = Integer.parseInt(s[1]);
				int speed = Integer.parseInt(s[2]);
				for (int j = 0; j < num; ++j) {
					double time = 0;
					if (speed <= 0)
						time = Integer.MAX_VALUE;
					else
						time = Math.pow(Math.pow((begx - end[j][0]), 2) + Math.pow((begy - end[j][1]), 2), 0.5) / speed;
					Node n = new Node(i, j, time);
					pq.add(n);
				}
			}
			catch (NumberFormatException e) {}
		}
		return pq;
	}
}

class Node implements Comparable <Node> {
	int beg;
	int end;
	double time;
	Node (int beg, int end, double time) {
		this.beg = beg;
		this.end = end;
		this.time = time;
	}
	@Override
	public int compareTo(Node n) {
		if (time > n.time)
			return -1;
		else if (time < n.time)
			return 1;
		else
			return 0;
	}
}
