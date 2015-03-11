package SRM236Div1;

public class BusinessTasks {
	public String getTask(String[] list, int n) {
		boolean[] deleted = new boolean[list.length];
		int index = 0;
		int remaining = deleted.length;
		while (remaining > 1) {
			for (int i = 0; i < n % remaining; ++i)
				index = next(deleted, index);
			deleted[index] = true;
			--remaining;
		}
		return list[index];
	}
	private int next(boolean[] deleted, int index) {
		while (true) {
			index = (index + 1) % deleted.length;
			if (!deleted[index])
				return index;
		}
	}
}