
public class ST {
	
	/* README
	 * A very basic symbol table, using the index of the given elements in the String array as the key and the corresponding string as the value.
	 */
	
	private String[] elements;
	private int N;
	
	public ST(int size) {
		N = 0;
		elements = new String[size];
	}
	
	//Return the index of the given string
	public int indexOf(String s) {
		
		int i;
		for(i = 0; i < N; i++) {
			if(elements[i].equals(s)) {
				return i;
			}
		}
		return -1;
	}
	
	//Return the corresponding String to the given index
	public String get(int index) {
		return elements[index];
	}
	
	//Add the given string to the symbol table, and if the array is full, double its size and copy the current elements into it
	public void add(String s) {

		if(N >= elements.length) {
			String[] temp = new String[N*2];
			for(int i = 0; i < N; i++) {
				temp[i] = elements[i];
				elements = temp;
			}
		}
		
		elements[N++] = s;
	}
	
	//Return true if the symbol table contains the given string
	public boolean contains(String s) {
		for(int i = 0; i < N; i++) {
			if(elements[i].equals(s)) {
				return true;
			}
		}
		return false;
	}
	
	//Return the amount of elements in the symbol table
	public int size() {
		return N;
	}

}
