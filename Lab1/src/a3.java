import java.util.Iterator;

/* README
 * A generic iterable "First in Last out" queue implemented using a double linked list
 * where every node is connected to both it's next and previous node.
 */

public class a3 {
	
	public static void main(String[] args) {

		a3 a = new a3();
		Queue<String> q = a.new Queue<String>();
		
		q.enqueue("1");
		q.enqueue("2");
		q.enqueue("3");
		
		for(String s: q) {
			System.out.print(s);
		}
		
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
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
