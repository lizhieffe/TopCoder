package TCCC03;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class Circuits {
	public int howLong(String[] connects, String[] costs) {
		if (connects == null || costs == null || connects.length == 0 || connects.length != costs.length)
			return 0;
		int[][] con = readConnects(connects, costs);
		List<Integer> topology = getTopology(con);
		int[] maxDistanceFromSource = findMaxDistancesFromAllSources(con, topology);
		int result = 0;
		for (int dis : maxDistanceFromSource)
			result = Math.max(dis, result);
		return result;
	}
	
	private int[][] readConnects(String[] connects, String[] costs) {
		int[][] result = new int[connects.length][connects.length];
		for (int i = 0; i < connects.length; ++i) {
			String[] tmpCon = connects[i].split(" ");
			String[] tmpCos = costs[i].split(" ");
			for (int j = 0; j < tmpCon.length; ++j) {
				try {
					int target = Integer.parseInt(tmpCon[j]);
					int cost = Integer.parseInt(tmpCos[j]);
					result[i][target] = cost;
				}
				catch (NumberFormatException ex) {
					
				}
			}
		}
		return result;
	}
	
	private List<Integer> getTopology(int[][] con) {
		int num = con.length;
		boolean[] visited = new boolean[num];
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < num; ++i)
			DFS(i, con, visited, result);
		return result;
	}

	private void DFS(int source, int[][] con, boolean[] visited, List<Integer> topology) {
		int num = con.length;
		if (visited[source] == true)
			return;
		visited[source] = true;
		for (int i = 0; i < num; ++i) {
			if (con[source][i] != 0 && !visited[i])
				DFS(i, con, visited, topology);
		}
		topology.add(source);
	}

	private int[] findMaxDistancesFromAllSources(int[][] con, List<Integer> topology) {
		int num = con.length;
		int[] result = new int[num];
		for (int i = 0; i < num; ++i) {
			int source = topology.get(i);
			for (int j = 0; j < num; ++j) {
				int cost = con[source][j];
				if (cost > 0)
					result[source] = Math.max(result[source], cost + result[j]);
			}
		}
		return result;
	}
	
	@Test
	public void testReadConnects() {
		String[] connects = {"1 0", ""};
		String[] costs = {"2 3", ""};
		Circuits service = new Circuits();
		service.readConnects(connects, costs);
	}
}	
