package SRM153Div2;

public class Inventory {

	public int monthlyOrder(int[] sales, int[] daysAvailable) {
		if (sales == null || sales.length == 0)
			return 0;
		double result = 0;
		for (int i = 0; i < sales.length; i++)
			result += (sales[i] * 30)/ (daysAvailable[i]);
		if (result % 1 != 0)
			return (int)result + 1;
		return (int)result;
	}
	
	public static void main(String[] args) {
		System.out.println(4.2 % 1);
	}
}
