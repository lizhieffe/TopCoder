package SRM153Div2;

import java.util.List;
import java.util.ArrayList;

public class PickTeam {
	public String[] pickPeople(int teamSize, String[] people) {
		if (people == null || people.length > teamSize || teamSize <= 0)
			return new String[0];
		String[] names = new String[people.length];
		int[][] relations = new int[people.length][people.length];
		for (int i = 0; i < people.length; i++) {
			String[] tmp = people[i].split(" ");
			names[i] = tmp[0];
			for (int j = 0; j < people.length; j++)
				relations[i][j] = Integer.parseInt(tmp[j + 1]);
		}
		
		List<List<Integer>> teams = getTeams(teamSize);
		int id = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < teams.size(); i++) {
			List<Integer> teamMembers = teams.get(i);
			int val = getScore(teamMembers, relations);
			if (val > max) {
				id = i;
				max = val;
			}
		}
		String[] result = new String[teamSize];
		for (int i = 0; i < result.length; i++)
			result[i] = names[teams.get(id).get(i)];
		return result;
	}
	
	private List<List<Integer>> getTeams(int size) {
		int[] id = new int[size];
		for (int i = 0; i < id.length; i++)
			id[i] = i;
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> solution = new ArrayList<Integer>();
		getTeams(size, 0, id, result, solution);
		return result;
	}
	
	private void getTeams(int size, int beg, int[] id, List<List<Integer>> result, List<Integer> solution) {
		if (size == 0)
			result.add(solution);
		if (beg >= id.length)
			return;
			
		List<Integer> tmp = new ArrayList<Integer>(solution);
		tmp.add(id[beg]);
		getTeams(size - 1, beg + 1, id, result, tmp);
		
		getTeams(size, beg + 1, id, result, solution);
	}
	
	private int getScore(List<Integer> list, int[][] relations) {
		int result = 0;
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = 0; j < list.size(); j++)
				result += relations[list.get(i)][list.get(j)];
		}
		return result;
	}
}