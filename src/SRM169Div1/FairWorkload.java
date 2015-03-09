package SRM169Div1;

import java.util.Arrays;


public class FairWorkload {
	public int getMostWork(int[] folders, int workers) {
		int result = 0;
		if (workers >= folders.length) {
			for (int f : folders)
				result = Math.max(result, f);
			return result;
		}
		int[][] dp  = new int[workers][folders.length];
		for (int[] array : dp)
			Arrays.fill(array, -1);
		return helper(0, 0, workers, folders, dp);
	}

	private int helper(int worker, int folder, int workers, int[] folders, int[][] dp) {
		if (dp[worker][folder] != -1)
			return dp[worker][folder];
		int result = 0;
		if (worker == workers - 1)
			for (int i = folder; i < folders.length; ++i)
				result += folders[i];
		else {
			result = Integer.MAX_VALUE;
			int soFar = 0;
			for (int i = folder; i < folders.length - (workers - worker - 1); ++i) {
				soFar += folders[i];
				int tmpResult = soFar + helper(worker + 1, i + 1, workers, folders, dp);
				result = Math.min(result, tmpResult);
			}
		}
		dp[worker][folder] = result;
		return result;
	}
}