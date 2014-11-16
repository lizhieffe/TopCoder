package SRM154Div2;

public class ProfitCalculator {

	public int percent(String[] items) {
		if (items == null || items.length == 0)
			return 0;
		double total = 0;
		double cost = 0;
		for (int i = 0; i < items.length; i++) {
			String[] tmp = items[i].split(" ");
			total += Double.parseDouble(tmp[0]);
			cost += Double.parseDouble(tmp[1]);
		}
		return (int)((total - cost) * 100 / total);
	}
}
