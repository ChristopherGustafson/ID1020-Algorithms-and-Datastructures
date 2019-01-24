import java.util.Iterator;

public class RedBlack <Key extends Comparable<Key>, Value>{
	
	private Node root;
	
	//Define final boolean values for the color of the nodes parent link
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	
	//Node class describing all the properties of the nodes
	private class Node{
		Key key;
		Value value;
		Node left, right;
		int N;
		boolean color;
		
		public Node(Key key, Value value, int N, boolean color) {
			this.key = key;
			this.value = value;
			this.N = N;
			this.color = color;
		}
	}
	
	//Return true if the parent link of the current node is red
	private boolean isRed(Node x) {
		if(x == null) {
			return false;
		}
		return x.color == RED;
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
	
	//"Rotate" a node to the left making a potential red link leaning to the right instead leaning to the left, but remaining the
	//logical position and properties of the nodes children. Do this by making the parent and the child connected with the red link
	//change roles.
	private Node rotateLeft(Node h) {
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	private Node rotateRight(Node h) {
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
		
	}
	
	//Make the color of the parent link to the given node red, and its children links black, flipping the colors. This makes a
	//theoretical 4-node into two 2-nodes and making the parent a part of a 3-node.
	private void flipColors(Node h) {
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;					
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
		root.color = BLACK;
	}
	
	private Node put(Node h, Key k, Value v) {
		if(h == null) {
			return new Node(k, v, 1, RED);
		}
		
		int compare = k.compareTo(h.key);
		
		if(compare < 0) {
			h.left = put(h.left, k, v);
		}
		else if(compare > 0) {
			h.right = put(h.right, k, v);	
		}
		else {
			h.value = v;
		}
		
		//Check three conditions to keep the balance of the search tree
		
		//If the right link is red and not the left, rotate the node to the left
		if(isRed(h.right) && !isRed(h.left)) {
			h = rotateLeft(h);
		}
		
		//If both the left link and the left link of the left child is red, rotate the node to the right
		if(isRed(h.left) && isRed(h.left.left)) {
			h = rotateRight(h);
		}
		
		//If both the left and red links are red, flip the colors of the parent link and the children links
		if(isRed(h.left) && isRed(h.right)) {
			flipColors(h);
		}
		
		//Calculate the size of the current node
		h.N = size(h.left) + size(h.right) + 1;
		return h;
		
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
		
		@Override
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














