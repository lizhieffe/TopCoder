package SRM184Div2;


public class TeamBuilder {
	public int[] specialLocations(String[] paths) {
		boolean[][] map = buildMap(paths);
		boolean[][] connectionMap = FloydWarshall(map);
		int[] result = {0, 0};
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[0].length; ++j)
				if (!connectionMap[i][j])
					break;
			++result[0];
		}
		for (int i = 0; i < map.length; ++i) {
			for (int j = 0; j < map[0].length; ++j)
				if (i != j && !connectionMap[j][i])
					break;
			++result[1];
		}
		return result;
	}

	private boolean[][] buildMap(String[] paths) {
		boolean[][] result = new boolean[paths.length][paths.length];
		for (int i = 0; i < paths.length; ++i) {
			String s = paths[i];
			for (int j = 0; j < paths.length; ++j) {
				if (i != j && s.charAt(j) == '1')
					result[i][j] = true;
			}
		}
		return result;
	}

	private boolean[][] FloydWarshall(boolean[][] map) {
		int num = map.length;
		boolean[][] result = new boolean[num][num];
		for (int i = 0; i < num; ++i)
			System.arraycopy(map[i], 0, result[i], 0, num);
		for (int k = 0; k < num; ++k) {
			for (int i = 0; i < num; ++i) {
				for (int j = 0; j < num; ++j) {
					if (i != j && i != k && j != k && !result[i][j] && (map[i][k] && map[k][j]))
						result[i][j] = true;
				}
			}
		}
		return result;
	}
}