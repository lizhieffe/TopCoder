package SRM152Div2;

public class FixedPointTheorem {
	public double cycleRange(double R) {
		double min = Integer.MAX_VALUE;
		double max = Integer.MIN_VALUE;
		double x = 0.25;
		for (int i = 0; i < 201000; i++) {
			x = R * x * (1 - x);
			if (i >= 200000) {
				if (x > max)
					max = x;
				else if (x < min)
					min = x;
			}
		}
		return max - min;
	}
}