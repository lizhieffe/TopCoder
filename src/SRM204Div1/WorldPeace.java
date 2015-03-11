package SRM204Div1;

import java.util.*;

public class WorldPeace {
	public long numGroups(int k, int[] countries) {
		long result = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(countries.length, Collections.reverseOrder());
		for (int num : countries) {
			if (num > 0)
				pq.add(num);
		}
		while (pq.size() >= k) {
			int[] tmp = new int[k];
			for (int i = 0; i < k; ++i)
				tmp[i] = pq.poll();
			if (pq.size() > 0) {
				int next = pq.peek();
				int toRemove = tmp[k - 1] - next + 1;
				result += toRemove;
				for (int i = 0; i < k; ++i)
					if (tmp[i] - toRemove > 0)
						pq.add(tmp[i] - toRemove);
			}
			else {
				result += tmp[k - 1];
			}

		}
		return result;

	}
}