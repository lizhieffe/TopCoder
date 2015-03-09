package SRM169Div1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MineField {
	private static int SIZE = 9;

	public String[] getMineField(String mineLocations) {
		List<Mine> mines = getMines(mineLocations);
		return generateMap(mines);
	}

	private List<Mine> getMines(String mineLocations) {
		List<Mine> mines = new ArrayList<Mine>();
		String[] s = mineLocations.split("\\(");
		for (int i = 0; i < s.length; ++i) {
			if (s[i].length() > 0) {
				String[] pos = s[i].substring(0, s[i].length() - 1).split(",");
				try {
					int x = Integer.parseInt(pos[0]);
					int y = Integer.parseInt(pos[1]);
					mines.add(new Mine(x, y));
				}
				catch (NumberFormatException e) {}
			}
		}
		return mines;
	}

	private String[] generateMap(List<Mine> mines) {
		char[][] map = new char[SIZE][SIZE];
		for (char[] array : map)
			Arrays.fill(array, '0');
		for (Mine mine : mines)
			addMineToMap(map, mine);
		String[] result = new String[SIZE];
		for (int i = 0; i < SIZE; ++i)
			result[i] = String.valueOf(map[i]);
		return result;
	}

	private void addMineToMap(char[][] map, Mine mine) {
		int x = mine.x;
		int y = mine.y;
		if (map[x][y] == 'M')
			return;
		map[x][y] = 'M';
		for (int deltaX = -1; deltaX <= 1; ++deltaX) {
			for (int deltaY = -1; deltaY <= 1; ++deltaY) {
				int tmpX = x + deltaX;
				int tmpY = y + deltaY;
				if (tmpX < 0 || tmpX >= SIZE || tmpY < 0 || tmpY >= SIZE || (tmpX == x && tmpY == y))
					continue;
				if (map[tmpX][tmpY] == 'M')
					continue;
				map[tmpX][tmpY] = (char)(map[tmpX][tmpY] + 1);
			}
		}
	}
	
	@Test
	public void tc01() {
		String mineLocations = "(1,1)";
		new MineField().getMineField(mineLocations);
	}
}

class Mine {
	int x;
	int y;
	Mine(int x, int y) {
		this.x = x;
		this.y = y;
	}
}