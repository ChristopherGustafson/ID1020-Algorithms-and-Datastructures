import java.util.Iterator;

/* Self-implemented stack, push() puts and element on top of the stack, pop() returns and removes the 
 * element on top of the stack.3
 */
public class Stack<Item> implements Iterable<Item>{
		
	private Node first = null;
	private class Node{
		Item item;
		Node next;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void push(Item item) {
		Node temp = first;
		first = new Node();
		first.item = item;
		first.next = temp;
	}
	
	public Item pop() {
		Node temp = first;
		first = first.next;
		return temp.item;
	}
	
	//Returns a string representation of the current stack
	public String toString() {
		Node temp = first;
		String s  = "[" + temp.item + "], ";
		while(temp.next != null) {
			s += "[" + temp.next.item + "], ";
			temp = temp.next;
		}
		return s;
		
	}

	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<Item>{

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
