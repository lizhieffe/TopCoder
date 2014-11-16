package SRM155Div2;

import java.util.LinkedList;

public class Quipu {

	int readKnots(String knots) {
		LinkedList<Character> stack = new LinkedList<Character>();
		int result = 0;
		for (int i = 1; i < knots.length() - 1; i++) {
			Character c = knots.charAt(i);
			if (c == 'X')
				stack.add(c);
			else {
				result = result * 10 + stack.size();
				if (stack.size() > 0)
					stack.clear();
			}
			if (stack.size() > 0)
				result = result * 10 + stack.size();
		}
		return result;
	}
}
