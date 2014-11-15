package SRM151Div2;
import java.util.Map;
import java.util.HashMap;

public class PrefixCode {
	public String isOne(String[] s) {
		if (s == null || s.length < 2)
			return "Yes";
		TrieNode root = new TrieNode(' ');
		for (int i = 0; i < s.length; i++) {
			TrieNode cNode = root;
			for (int j = 0; j < s[i].length(); j++) {
				Character c = s[i].charAt(j);
				if (!cNode.hasChild(c)) {
					cNode.addChild(c, new TrieNode(c));
				}
				cNode = cNode.getChild(c);
			}
		}
		for (int i = 0; i < s.length; i++) {
			TrieNode cNode = root;
			for (int j = 0; j < s[i].length(); j++) {
				Character c = s[i].charAt(j);
				cNode = cNode.getChild(c);
				if (j == s[i].length() && cNode.children.size() > 0) 
					return "No, " + i;
			}
		}
		return "Yes";
	}
	
	private class TrieNode {
		Character c;
		Map<Character, TrieNode> children;
		TrieNode (Character c){
			this.c = c;
			children = new HashMap<Character, TrieNode>();
		}
		void addChild (Character c, TrieNode node) {
			children.put(c, node);
		}
		TrieNode getChild (Character c) {
			return children.get(c);
		}
		boolean hasChild (Character c) {
			return children.containsKey(c);
		}
	}
}