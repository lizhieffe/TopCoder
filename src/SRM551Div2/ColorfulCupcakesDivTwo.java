package SRM551Div2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ColorfulCupcakesDivTwo {
	public int countArrangements(String cupcakes) {
		if (cupcakes == null || cupcakes.length() == 0)
			return 0;
		if (cupcakes.length() == 1)
			return 1;
		Map<Character, Integer> colors = getColors(cupcakes);
		return getCount(colors, new ArrayList<Character>());
	}

	private Map<Character, Integer> getColors(String cupcakes) {
		Map<Character, Integer> result = new HashMap<Character, Integer>();
		for (int i = 0; i < cupcakes.length(); ++i) {
			char c = cupcakes.charAt(i);
			addToMap(result, c);
		}
		return result;
	}

	private static void addToMap(Map<Character, Integer> map, char c) {
		int val = 1;
		if (map.containsKey(c))
			val = map.get(c) + 1;
		map.put(c, val);
	}

	private static void removeFromMap(Map<Character, Integer> map, char c) {
		if (map.containsKey(c)) {
			int val = map.get(c);
			if (val == 1)
				map.remove(c);
			else
				map.put(c, val - 1);
		}
	}

	private int getCount(Map<Character, Integer> map, List<Character> solution) {
		int result = 0;
		if (map.size() == 0) {				
			return solution.get(0).equals(solution.get(solution.size() - 1)) ? 0 : 1;
		}
		Iterator<Entry<Character, Integer>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Character, Integer> pair = it.next();
			char c = pair.getKey();
			if (solution.size() == 0 || !solution.get(solution.size() - 1).equals(c)) {
				solution.add(c);
				Map<Character, Integer> tmpMap = new HashMap<Character, Integer>(map);
				removeFromMap(tmpMap, c);
				result += getCount(tmpMap, solution);
				solution.remove(solution.size() - 1);
			}
		}
		return result;
	}
}