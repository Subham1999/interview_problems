import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * 
 * @author : Subham Santra
 * 
 * @apiNote :
 * 
 * - makeSet(val)
 * this api will make a new set with only one value as 'val' 
 * 
 * 
 * - isSameSet(val1, val2)
 * this api will retrun true is val1 and val2 comes under same set, 
 * false otherwise
 * 
 * 
 * - joinSet(val1, val2)
 * this method will join two sets 
 * 
 * */
public class UnionSet<T> {

	private class Node<T> {
		final T val;
		int rank;
		Node<T> parent;

		public Node(T val) {
			this.val = val;
			this.rank = 0;
			this.parent = null;
		}

		@Override
		public String toString() {
			return String.format("%s", val);
		}
	}

	private final List<Node<T>> set;
	private final Map<T, Integer> indexMap;

	public UnionSet() {
		this.set = new ArrayList<>();
		this.indexMap = new HashMap<>();
	}

	private Node<T> parent(final Node<T> node) {
		if (node == null) {
			return null;
		}
		if (node.parent == null) {
			return node; 
		}
		return parent(node.parent);
	}

	private Node<T> getNode(T val) {
		Integer index = indexMap.get(val);
		if (index != null) {
			return set.get(index);
		}
		return null;
	}


	public void makeSet(final T val) {
		final Node<T> newNode = new Node<>(val);
		set.add(newNode);
		indexMap.put(val, set.size() - 1);
	}

	public void joinSet(final T val1, final T val2) {
		final Node<T> node1 = getNode(val1);
		final Node<T> node2 = getNode(val2);

		final Node<T> parent1 = parent(node1);
		final Node<T> parent2 = parent(node2);

		if (parent1 == parent2) { // already in same set
			return;
		}

		if (parent1.rank > parent2.rank) {
			parent1.rank += Math.max(1, parent2.rank);
			parent2.parent = parent1;
			parent2.rank = 0;
		} else {
			parent2.rank += Math.max(1, parent1.rank);
			parent1.parent = parent2;
			parent1.rank = 0;
		}
	}

	public boolean isSameSet(T val1, T val2) {
		final Node<T> node1 = getNode(val1);
		final Node<T> node2 = getNode(val2);

		final Node<T> parent1 = parent(node1);
		final Node<T> parent2 = parent(node2);

		return parent1 == parent2;
	}

	public int countSet() {
		int count = 0;
		for (Node<T> node : set) {
			if (node.parent == null) {
				count++;
			}
		}

		return count;
	}

	public static void main(String[] args) {

		UnionSet<Integer> us = new UnionSet<Integer>();

		us.makeSet(1);
		us.makeSet(2);
		us.makeSet(3);
		us.makeSet(4);
		us.makeSet(5);
		us.makeSet(6);
		us.makeSet(7);

		// Now sets are {1}, {2}, {3}, {4}, {5}, {6}, {7}
		System.out.printf("CountSet : %d\n", us.countSet());

		us.joinSet(1, 2);
		us.joinSet(3, 2);
		us.joinSet(4, 2);

		// Now sets are {1, 2, 3, 4}, {5}, {6}, {7}
		System.out.printf("CountSet : %d\n", us.countSet());

		System.out.printf("Is same set : %s\n", us.isSameSet(3, 4));		
		System.out.printf("Is same set : %s\n", us.isSameSet(3, 7));


		us.joinSet(5, 7);	
		us.joinSet(5, 6);
		us.joinSet(6, 7);

		// Now sets are {1, 2, 3, 4}, {5, 6, 7}
		System.out.printf("CountSet : %d\n", us.countSet());
		System.out.printf("Is same set : %s\n", us.isSameSet(7, 5));		
		System.out.printf("Is same set : %s\n", us.isSameSet(7, 2));		


		us.joinSet(3, 7);

		// Now sets are {1, 2, 3, 4, 5, 6, 7}
		System.out.printf("CountSet : %d\n", us.countSet());
		System.out.printf("Is same set : %s\n", us.isSameSet(1, 7));		
	}
}
