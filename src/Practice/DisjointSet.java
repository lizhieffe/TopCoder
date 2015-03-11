package Practice;

/**
 * Time complexity for all the 3 operations is O(1)
 * http://help.topcoder.com/data-science/competing-in-algorithm-challenges/algorithm-tutorials/disjoint-set-data-structures/
 */
public class DisjointSet {
	
	public static DSNode createSet(int val) {
		return new DSNode(val);
	}
	
	public static DSNode findSet(DSNode node) {
		if (node.parent == node)
			return node.parent;
		node.parent = findSet(node.parent);
		return node.parent;
	}
	
	public static void mergetSets(DSNode node1, DSNode node2) {
		DSNode p1 = findSet(node1);
		DSNode p2 = findSet(node2);
		if (p1.rank > p2.rank)
			p2.parent = p1;
		else
			p1.parent = p2;
		if (p1.rank == p2.rank)
			++p2.rank;
	}
}

class DSNode {
	int val;
	int rank;
	DSNode parent;
	DSNode(int val) {
		this.val = val;
		this.rank = 0;
	}
}
