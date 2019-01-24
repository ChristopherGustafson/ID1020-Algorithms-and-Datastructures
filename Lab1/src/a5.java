/* README
 * A linked list implementation of a queue with a function which allows the user to
 * removed the element of a preferred index, where the first element has index 1.
 */
public class a5 {

	public static void main(String[] args) {
		
		Queue<String> q = new Queue<String>();
		
		String s1 = "1";
		String s2 = "2";
		String s3 = "3";
		String s4 = "4";
		String s5 = "5";
		String s6 = "6";
		
		q.queue(s1);
		q.queue(s2);
		q.queue(s3);
		q.queue(s4);
		q.queue(s5);
		q.queue(s6);
		
		q.removeIndex(3);
		
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
	
	}
	
	public static class Queue<Item>{
		
		private Node first = null;
		private Node last = null;
		
		private class Node{
			Node next;
			Item item;
		}
		
		public boolean isEmpty() {
			return first == null && last == null;
		}
		public void queue(Item i) {
			Node newItem = new Node();
			newItem.item = i;
			if(isEmpty()) {
				first = newItem;
				last = newItem;
			}
			else {
				last.next = newItem;
				last = newItem;
			}
		}
		
		public Item dequeue() {
			Item temp = first.item;
			first = first.next;
			return temp;
		}
		
		public void removeIndex(int index) {
			
			//If the requested index is 1, remove the first element
			Node temp = first;
			if(index == 1) {
				first = first.next;
			}
			//Otherwise loop through the queue until the requested index is reached and remove that element
			else {
				for(int i = 1; i < index-1; i++) {
					temp = temp.next;
				}
				temp.next = temp.next.next;
			}
		}
		
		//Return the string representation of the current queue
		public String toString() {
			String s = "";
			Node current = first;
			s += "[" + first.item + "], ";
			while(current.next != null) {
				s += "[" + current.next.item + "], ";
			}
			return s;
		}
		
	}
}
