package SRM236Div1;

import java.util.*;

public class HammingNumbers {
	public int getNumber(int[] factors, int n) {
		Set<Long> appeared = new HashSet<Long>();
		PriorityQueue<Long> pq = new PriorityQueue<Long>();
		appeared.add((long)1);
		pq.add((long) 1);
		for (int i = 0; i < n - 1; ++i) {
			long val = pq.poll();
			for (int factor : factors) {
				long tmp = factor * val;
				if (!appeared.contains(tmp)) {
					appeared.add(tmp);
					pq.add(tmp);
				}
			}
		}
		return (int)(long)(pq.poll());
	}
}