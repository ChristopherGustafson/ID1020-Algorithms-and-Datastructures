import java.io.FileReader;
import java.io.IOException;

/* README
 * A program which reads data from a text file (6.txt in this instance) and checks whether the text file has
 * correctly written parentheses. That is that all opened parentheses are closed and no closing parentheses isn't
 * found without it being opened.	
 */

public class a6 {
	
	public static void main(String[] args) throws IOException {
		a6 a = new a6();
		FileReader file = new FileReader("6.txt");
		System.out.println(a.checkParentheses(file));
	}

	public boolean checkParentheses(FileReader input) throws IOException {
		Stack stack = new Stack();
		boolean check = true;
		int c = input.read();
		
		//Loop until the end of file is reached or we have found an error
		while(c != -1 && check != false){
			switch(c) {
				//If a left parentheses is read, push it to the stack
				case '(':
				case '[':
				case '{':
					stack.push(c);
					break;
				/* If a right parentheses is read, check the latest pushed element. If it doesn't corresponds to the 
				 * latest left parentheses or the stack is empty, the parentheses is misplaced and the function returns
				 * false.
				 */
				case ')':
					if(stack.isEmpty() || stack.pop() != '(') {
						check = false;
					}
					break;
				case ']':
					if(stack.isEmpty() || stack.pop() != '[') {
						check = false;
					}
					break;
				case '}':
					if(stack.isEmpty() || stack.pop() != '{') {
						check = false;
					}
					break;
			}
			
			c = input.read();
		}
		//If the stack isn't empty when the end of file is reached, some parantheses isn't closed and we return false
		if(!stack.isEmpty()) {
			check = false;
		}
		return check;
	}
	
	/* Self-implemented stack, push() puts and element on top of the stack, pop() returns and removes the 
	 * element on top of the stack.3
	 */
	public static class Stack{
			
		private Node first = null;
		private class Node{
			int c;
			Node next;
		}
		
		public boolean isEmpty() {
			return first == null;
		}
		
		public void push(int c) {
			Node temp = first;
			first = new Node();
			first.c = c;
			first.next = temp;
		}
		
		public int pop() {
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
