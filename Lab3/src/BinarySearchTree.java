import java.util.Iterator;

public class BinarySearchTree <Key extends Comparable<Key>, Value>{

	private Node root;
	
	//Node class describing all the properties of the nodes
	private class Node{
		private Key key;
		private Value value;
		private Node left, right;
		private int N;
	
		public Node(Key key, Value value, int N) {
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}
	
	//Return the size of the binary search tree by returning the size of the root
	public int size() {
		return size(root);
	}
	
	//Return the size of the given node x
	private int size(Node x) {
		if(x == null) {
			return 0;
		}
		else {
			return x.N;
		}
	}
	
	//Return the value corresponding to the given key by recursively going through the search tree
	public Value get(Key k) {
		return get(root, k);
	}
	
	//Return the value corresponding to the given key starting at the given node
	private Value get(Node x, Key k) {
		if(x == null) {
			return null;
		}
		
		//Compare the key to the current nodes key, if larger get key from the right side of the node, if smaller go to the left side
		//and if it is equal return the current nodes value
		int compare = k.compareTo(x.key);
		
		if(compare < 0) {
			return get(x.left, k);
		}
		else if(compare > 0) {
			return get(x.right, k);
		}
		else {
			return x.value;
		}
	}
	
	/* Add the given key and value pair to the tree by recursively comparing the key to the current keys, changing the value if a the
	 * given key exists and adding the key to the bottom of the tree if not. */
	public void put(Key k, Value v) {
		root = put(root, k, v);
	}
	
	private Node put(Node x, Key k, Value v) {
		if(x == null) {
			return new Node(k, v, 1);
		}
		
		int compare = k.compareTo(x.key);
		
		if(compare < 0) {
			x.left = put(x.left, k, v);
		}
		else if(compare > 0) {
			x.right = put(x.right, k, v);
		}
		else {
			x.value = v;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
	//Return the smallest key by recursively going down the left-most side of the tree until a null pointer is found
	public Key min() {
		return min(root).key;
	}
	
	private Node min(Node x) {
		if(x.left == null) {
			return x;
		}
		return min(x.left);
	}

	//Return the highest key by recursively going down the right-most side of the tree until a null pointer is found
	public Key max() {
		return max(root).key;
	}
	
	private Node max(Node x) {
		if(x.right == null) {
			return x;
		}
		return max(x.right);
	}
	
	//Return an iterable queue containing all the elements of the search tree
	public Iterable<Key> keys(){
		return keys(min(), max());
	}
	
	public Iterable<Key> keys(Key low, Key high){
		Queue<Key> q = new Queue<Key>();
		keys(root, q, low, high);
		return q;
	}
	
	private void keys(Node x, Queue<Key> q, Key low, Key high) {
		if(x == null) {
			return;
		}
		
		int compareLow = low.compareTo(x.key);
		int compareHigh = high.compareTo(x.key);
		
		if(compareLow < 0) {
			keys(x.left, q, low, high);
		}
		if(compareLow <= 0 && compareHigh >= 0) {
			q.enqueue(x.key);
		}
		if(compareHigh > 0) {
			keys(x.right, q, low, high);
		}
	}
	
	public class Queue<Item> implements Iterable<Item>{
		
		//Define references for the first and last element of the queue
		private Node first = null;
		private Node last = null;
		
		private class Node{
			Item item;
			Node next;
			Node prev;
		}
		
		//Check if the queue is empty
		public boolean isEmpty() {
			return last == null && first == null;
		}
		
		//Queue an item to the end of the queue
		public void enqueue(Item i) {
			Node n = new Node();
			n.item = i;
			if(isEmpty()) {
				first = n;
				last = n;
			}
			n.next = last;
			last.prev = n;
			last = n;
		}
		
		//Remove the first element of the queue and return it to the user
		public Item dequeue() {
			Item temp = first.item;
			first = first.prev;
			return temp;
		}
		
		public Iterator<Item> iterator() {
			return new QueueIterator();
		}
		
		private class QueueIterator implements Iterator<Item>{

			private Node current = first;	
			
			public boolean hasNext() {
				return current != null;
			}
			
			public Item next() {
				Item i = current.item;
				current = current.prev;
				return i;
			}
			
		}

	}
	
}
