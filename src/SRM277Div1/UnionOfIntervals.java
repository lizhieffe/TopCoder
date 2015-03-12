package SRM277Div1;

public class UnionOfIntervals {
	public int nthElement(int[] lowerBound, int[] upperBound, int n) {
		int firstPart = upperBound[0] - lowerBound[0] + 1;
		if (n - 1 < firstPart)
			return lowerBound[0] + n;
		else
			return lowerBound[0] + n - firstPart;
	}
}