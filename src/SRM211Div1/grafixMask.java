package SRM211Div1;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class grafixMask {
	private static int rows = 400;
	private static int columns = 600;
	
	public int[] sortedAreas(String[] rectangles) {
		boolean isHole[][] = new boolean[rows][columns];
		init(isHole, rectangles);
		List<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < columns; ++j) {
				int area = calArea(i, j, isHole);
				if (area != 0)
					result.add(area);
			}
		}
		int[] array = new int[result.size()];
		for (int i = 0; i < array.length; ++i)
			array[i] = result.get(i);
		return array;
	}
	
	private void init(boolean isHole[][], String[] rectangles) {
		for (String s : rectangles) {
			String[] tmp = s.split(" ");
			int x1 = Integer.parseInt(tmp[0]);
			int x2 = Integer.parseInt(tmp[2]);
			int y1 = Integer.parseInt(tmp[1]);
			int y2 = Integer.parseInt(tmp[3]);
			for (int i = x1; i <= x2; ++i)
				for (int j = y1; j <= y2; ++j)
					isHole[i][j] = true;
		}
	}
	
	private int calArea(int row, int column, boolean[][] isHole) {
		if (isHole[row][column])
			return 0;
		Stack<Position> stack = new Stack<Position>();
		stack.push(new Position(row, column));
		int result = 1;
		isHole[row][column] = true;
		while (stack.size() > 0) {
			Position p = stack.pop();
			if (p.x > 0 && !isHole[p.x - 1][p.y]) {
				stack.push(new Position(p.x - 1, p.y));
				++result;
				isHole[p.x - 1][p.y] = true;
			}
			if (p.x < rows - 1 && !isHole[p.x + 1][p.y]) {
				stack.push(new Position(p.x + 1, p.y));
				++result;
				isHole[p.x + 1][p.y] = true;
			}
			if (p.y > 0 && !isHole[p.x][p.y - 1]) {
				stack.push(new Position(p.x, p.y - 1));
				++result;
				isHole[p.x][p.y - 1] = true;
			}
			if (p.y < columns - 1 && !isHole[p.x][p.y + 1]) {
				stack.push(new Position(p.x, p.y + 1));
				++result;
				isHole[p.x][p.y + 1] = true;
			}
		}
		return result;
	}
}

class Position {
	int x;
	int y;
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}