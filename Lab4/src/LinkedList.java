import java.util.Iterator;

public class LinkedList<Item> implements Iterable<Item>{
	
	private Node first;
	private Node last;
	
	public class Node{
		Node next;
		Item item;
		
		public Node(Item i) {
			this.item = i;
		}
	}
	
	//Add an item to the list by setting the last nodes next reference to a new node with the given item
	public void add(Item i) {
		if(first == null) {
			first = new Node(i);
			last = first;
		}
		last.next = new Node(i);
		last = last.next;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Item>{

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
