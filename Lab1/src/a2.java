import java.util.Scanner;

/* README
 * A program which reverses the input of characters read from the standard input using
 * a self-implemented stack.
 */

public class a2 {
	
	public static void main(String[] args) {
		a2 h = new a2();
		h.reverseChar();
	}
	
	public void reverseChar() {
		//Define a scanner which reads from standard input
		Scanner sc = new Scanner(System.in); 
		Stack stack = new Stack();
		
		//Read the nextLine from the scanner, which will be all the characters up until a new line appears
		String s = sc.nextLine();
		sc.close();
		
		//Push the individual characters on to the stack
		for(int i = 0; i < s.length(); i++) {
			stack.push(s.charAt(i));
		}
		
		System.out.println(stack);
		
		//Pop and print the characters from the stack, reversing the order of the original characters
		for(int i = s.length()-1; i >=0; i--) {
			System.out.print(stack.pop());
		}
	}
	
	/* Self-implemented stack, push() puts and element on top of the stack, pop() returns and removes the 
	 * element on top of the stack.3
	 */
	public static class Stack{
			
		private Node first = null;
		private class Node{
			char c;
			Node next;
		}
		
		public boolean isEmpty() {
			return first == null;
		}
		
		public void push(char c) {
			Node temp = first;
			first = new Node();
			first.c = c;
			first.next = temp;
		}
		
		public char pop() {
			Node temp = first;
			first = first.next;
			return temp.c;
		}
		
		//Returns a string representation of the current stack
		public String toString() {
			Node temp = first;
			String s  = "[" + temp.c + "], ";
			while(temp.next != null) {
				s += "[" + temp.next.c + "], ";
				temp = temp.next;
			}
			return s;
			
		}
		
	}	

}	



