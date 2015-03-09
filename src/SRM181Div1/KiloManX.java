package SRM181Div1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KiloManX {
	public int leastShots(String[] damageChart, int[] bossHealth) {
		int[][] shots = buildShotsMatrix(damageChart, bossHealth);
		int[] minShots = dikjestra(shots);
		int result = 0;
		for (int i : minShots)
			result = Math.max(i, result);
		return result;
	}

	private int[][] buildShotsMatrix(String[] damageChart, int[] bossHealth) {
		int num = damageChart.length + 1;
		int[][] result = new int[num][num - 1];
		for (int[] array : result)
			Arrays.fill(array, Integer.MAX_VALUE);
		for (int i = 0; i < num - 1; ++i)
			result[0][i] = bossHealth[i];
		for (int i = 1; i < num; ++i) {
			int[] damage = getDamage(damageChart[i - 1]);
			for (int j = 0; j < num - 1; ++j) {
				result[i][j] = bossHealth[j] / damage[j];
			}
		}
		return result;
	}

	private int[] getDamage(String damage) {
		int[] result = new int[damage.length()];
		for (int i = 0; i < damage.length(); ++i) {
			result[i] = (int)(damage.charAt(i) - '0');
		}
		return result;
	}

	private int[] dikjestra(int[][] shots) {
		int num = shots.length;
		int[] result = new int[num - 1];
		Map<Integer, DNode> tmpShots = new HashMap<Integer, DNode>();
		PriorityQueue<DNode> pq = new PriorityQueue<DNode>();
		for (int i = 0; i < num - 1; ++i) {
			DNode node = new DNode(i, Integer.MAX_VALUE);
			tmpShots.put(i, node);
			pq.add(node);
		}
		pq.add(new DNode(-1, 0));
		while (pq.size() > 0) {
			DNode curr = pq.poll();
			int id = curr.id;
			if (id != -1)
				result[id] = curr.shots;
			for (int i = 0; i < num - 1; ++i) {
				if (i != id) {
					DNode neighbor = tmpShots.get(i);
					if (neighbor.shots > curr.shots + shots[id][i]) {
						pq.remove(neighbor);
						neighbor.shots = curr.shots + shots[id][i];
						pq.add(neighbor);
					}
				}
			}
		}
		return result;
	}

	private class DNode implements Comparable <DNode> {
		int id;
		int shots;
		DNode(int id, int shots) {
			this.shots = shots;
		}

		@Override
		public int compareTo(DNode node) {
			if (shots < node.shots)
				return -1;
			else if (shots > node.shots)
				return 1;
			else
				return 0;
		}
	}
}
