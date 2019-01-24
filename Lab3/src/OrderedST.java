import java.util.Iterator;
import java.util.Queue;

public class OrderedST<Key extends Comparable<Key>, Value>{
	
	//Define the key and value arrays for the symbol table, these are used in parallel to create the key-value relation
	public Key[] keys;
	public Value[] values;
	public int N;
	
	public OrderedST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}
	
	//Return the current number of elements in the ST
	public int size() {
		return N;
	}
	
	//Return the value corresponding to the given key
	public Value get(Key k) {
		int i = rank(k);
		if(i < N && keys[i].compareTo(k) == 0) {
			return values[i];
		}
		else {
			return null;
		}
	}
	
	//Add the key and value given to the symbol table, if the key is already in the symbol table, update its value
	public void put(Key k, Value v) {
		int i = rank(k);
		if(i < N && keys[i].compareTo(k) == 0) {
			values[i] = v;
			return;
		}
		for(int j = N; j > i; j--) {
			keys[j] = keys[j-1];
			values[j] = values[j-1];
		}
		keys[i] = k;
		values[i] = v;
		N++;
	}
	
	//Return the amount of elements with a lower key the the given key, by using binary search
	public int rank(Key k) {
		int low = 0, high = N-1;
		while(low <= high) {
			int mid = low + (high-low)/2;
			int compare = k.compareTo(keys[mid]);
			if(compare < 0) {
				high = mid-1;
			}
			else if(compare > 0) {
				low = mid+1;
			}
			else {
				return mid;
			}
		}
		return low;
	}
	
	//Return a iterable queue with all the elements in the symbol table enqueued
	public Iterable<Key> keys(){
		Queue<Key> q = new Queue<Key>();
		for(int i = 0; i < N; i++) {
			q.enqueue(keys[i]);
		}
		return q;
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
