package SRM153Div2;

public class MostProfitable {
	String bestItem(int[] costs, int[] prices, int[] sales, String[] items) {
		int id = -1;
		int max = 0;
		if (costs == null || costs.length == 0)
			return "";
		for (int i = 0; i < costs.length; i++) {
			if (costs[i] >= prices[i])
				continue;
			int val = sales[i] * (prices[i] - costs[i]);
			if (val > max) {
				id = i;
				max = val;
			}
		}
		return id >= 0 ? items[id] : "";
	}
}