package SRM151Div2;

public class MergeSort {
	private int count = 0;
	
	public int howManyComparisons(int[] array) {
		if (array == null)
			return 0;
		mergeSort(array, 0, array.length - 1);
		return count;
	}
	
	private void mergeSort(int[] array, int beg, int end) {
		if (end - beg <= 1)
			return;
		int mid = beg + (end - beg) / 2;
		mergeSort(array, beg, mid - 1);
		mergeSort(array, mid, end);
		combine(array, beg, end);
	}
	
	private void combine(int[] array, int beg, int end) {
		int mid = beg + (end - beg) / 2;
		int[] tmp = new int[end - beg + 1];
		int i = beg;
		int j = mid;
		int k = 0;
		while (i < mid && j <= end) {
			if (array[i] <= array[j]) {
				count++;
				tmp[k] = array[i];
				i++;
				k++;
			}
			else {
				count ++;
				tmp[k] = array[j];
				j++;
				k++;
			}
		}
		if (i == mid)
			System.arraycopy(array, j, tmp, k, tmp.length - k);
		else
			System.arraycopy(array, i, tmp, k, tmp.length - k);
		System.arraycopy(tmp, 0, array, beg, tmp.length);
	}
	
	public static void main(String[] args) {
		int[] array = {2, 3, 3};
		new MergeSort().howManyComparisons(array);
	}
}