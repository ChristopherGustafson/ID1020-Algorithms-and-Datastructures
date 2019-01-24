
/* README
 * A linked list implementation of an int queue, which always will keep the elements in ascending order. Removing an element will
 * remove the first element in the queue and adding an element will add it to the right place in the list to keep the ascending 
 * order.
 */

public class a7 {
	
	public static void main(String[] args) {
		a7 a = new a7();
		Queue q = a.new Queue();
		q.enqueue(6);
		System.out.println(q);
		q.enqueue(1);
		System.out.println(q);
		q.enqueue(5);
		System.out.println(q);
		q.enqueue(3);
		System.out.println(q);
		q.enqueue(0);
		System.out.println(q);
		q.enqueue(2);
		System.out.println(q);
		q.enqueue(4);
		System.out.println(q);
		q.enqueue(3);
		System.out.println(q);
	}
	
	//A linked list implementation of a queue which keeps ascending order
	public class Queue{
		
		Node first = null;
		private class Node{
			int value;
			Node next;
		}
		
		//Check if the queue is empty
		public boolean isEmpty() {
			return first == null;
		}
		
		//Add an element to the queue in the proper place to keep ascending order
		public void enqueue(int i) {
			Node n = new Node();
			n.value = i;
			//If the queue is empty, add the element to the first position
			if(isEmpty()) {
				first = n;
			}
			
			else { 
				//If the added value is smaller than the first element, add it as the first element
				if(n.value < first.value) {
					n.next = first;
					first = n;
				}
				else {
					//Loop through the queue until a value larger than the given value is found
					Node current = first;
					while(current.next != null && current.next.value < n.value) {
						current = current.next;
					}
					//Add the element in front of the found element
					n.next = current.next;
					current.next = n;	
				}
			}
		}
		
		//Remove and return the first element of the queue
		public int dequeue() {
			int temp = first.value;
			first = first.next;
			return temp;
		}
		
		//Return a string representation of the current state of the queue
		public String toString() {
			String s = "";
			
			Node current = first;
			while(current.next != null){
				s += current.value + " - ";
				current = current.next;
			}
			s += current.value + " - ";

			return s;
		}
	}
}
