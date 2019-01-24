import java.util.Iterator;

// Self implemented FIFO-Queue, implemented using a Linked List structure
 
public class Queue<Item> implements Iterable<Item>{
	
	private Node first;
	private Node last;
	
	private class Node{
		Item item;
		Node next;
		
		public Node(Item item) {
			this.item = item;
		}
	}
	
	//Return true if the current queue is empty
	public boolean isEmpty() {
		return first == null;
	}
	
	//Add the given item to the end of the queue
	public void enqueue(Item i) {
		if(isEmpty()) {
			first = last = new Node(i);
		}
		else {
			Node temp = last;
			last = new Node(i);
			last.next = null;
			temp.next = last;
		}
	}
	
	//Remove and return the first item in the queue
	public Item dequeue() {
		Item i = first.item;
		first = first.next;
		return i;
	}
	
	public Iterator<Item> iterator(){
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator<Item>{
		private Node current = first;
	
		public boolean hasNext() {
			return current != null;
		}
		
		public Item next() {
			Item i = current.item;
			current = current.next;
			return i;
		}
	}
}
