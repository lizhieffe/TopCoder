package Practice;

import static org.junit.Assert.*;

import org.junit.Test;

public class RemoveLowestNonZeroBit {
	public static int remove(int val) {
		return val ^ (val & ((-1) * val));
	}
	
	@Test
	public void test() {
		int val = 3;
		assertEquals(remove(val), 2);
	}
}
