package SRM551Div2;

import java.util.*;

public class ColorfulBricks {
	public int countLayouts(String bricks) {
		Set<Character> colors = new HashSet<Character>();
		for (int i = 0; i < bricks.length(); ++i) {
			char c = bricks.charAt(i);
			colors.add(c);
		}
		if (colors.size() > 2)
			return 0;
		return colors.size();
	}
}