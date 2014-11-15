package SRM152Div2;

public class LeaguePicks {
	public int[] returnPicks(int position, int friends, int picks) {
		int i = 1;
		int[] result = new int[picks];
		while (i <= picks) {
			if (i % 2 == 1)
				result[i] = friends * (i - 1) + position;
			else
				result[i] = friends * (i - 1) + (friends - position + 1);
		}
		return result;
	}
}
