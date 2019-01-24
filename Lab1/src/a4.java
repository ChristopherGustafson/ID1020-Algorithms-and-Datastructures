import java.util.Iterator;

/* README
 * A queue implemented using a circular double linked list. The queue has methods to queue/dequeue
 * both the last and first items of the queue.
 */

public class a4 {

	public static void main(String[] args) {
		a4 a = new a4();
		Queue<String> q = a.new Queue<String>();
		
		String s1 = "s1";
		String s2 = "s2";
		String s3 = "s3";
		String s4 = "s4";
		String s5 = "s5";
		
		q.enqueueLast(s1);
		q.enqueueLast(s2);
		q.enqueueLast(s3);
		q.enqueueFirst(s4);
		q.enqueueFirst(s5);
		
		for(String s: q) {
			System.out.print(s);
		}
		
		for(int i = 0; i < 5; i++) {
			System.out.println(q.dequeueLast());
		}
		
		
		System.out.println(q);
	}
	
	public class Queue<Item> implements Iterable<Item>{
		
		private Node first = null;
		private Node last = null;
		
		private class Node{
			Item item;
			Node next;
			Node prev;
		}
		
		//Check if the queue is empty
		public boolean isEmpty() {
			return first == null && last == null;
		}
		
		public void enqueueFirst(Item i) {
			Node newNode = new Node();
			newNode.item = i;
			
			//If the queue is empty, the enqueued item becomes the first and last element.
			if(isEmpty()) {
				first = newNode;
				last = newNode;
				newNode.next = newNode;
				newNode.prev = newNode;
			}
			/* Otherwise the new element is added to the first spot of the queue and the references
			 * of the adjecent items is changed to point towards the new item
			 */
			else {
				last.next = newNode;
				first.prev = newNode;
				newNode.next = first;
				newNode.prev = last;
				first = newNode;
			}
		}
		
		public void enqueueLast(Item i) {
			Node newNode = new Node();
			newNode.item = i;
			
			//If the queue is empty, the enqueued item becomes the first and last element.
			if(isEmpty()) {
				first = newNode;
				last = newNode;
				newNode.next = newNode;
				newNode.prev = newNode;
			}
			else {
				last.next = newNode;
				first.prev = newNode;
				newNode.next = first;
				newNode.prev = last;
				last = newNode;
			}
		}
		
		public Item dequeueFirst() {
			if(first == last) {
				Item temp = first.item;
				first = null;
				last = null;
				return temp;	
			}
			else {
				Item temp = first.item;
				last.next = first.next;
				first.next.prev = last;
				first = first.next;
				return temp;
			}
		}
		
		public Item dequeueLast() {
			if(first == last) {
				Item temp = first.item;
				first = null;
				last = null;
				return temp;	
			}
			else {
				Item temp = last.item;
				first.prev = last.prev;
				last.prev.next = first;
				last = last.prev;
				return temp;
			}
		}
		
		//Return a string representation of the current queue
		public String toString() {
			String s = "";
			for(Item i: this) {
				s += "[" + i.toString() + "], ";
			}
			return s;
		}
		
		public Iterator<Item> iterator() {
			return new QueueIterator();
		}
		
		private class QueueIterator implements Iterator<Item>{

			private Node current = first;	
			
			//If the current item isn't the last item, there exists a next item
			public boolean hasNext() {
				return current != last;
			}
			
			public Item next() {
				Item i = current.item;
				current = current.next;
				return i;
			}
		}
	}
}
