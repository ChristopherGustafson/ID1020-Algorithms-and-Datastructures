import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* READ ME
 * A program which implements a frequency counter which finds the most frequently occuring word in a text file. The frequency 
 * counter is then used to compare the execution times of various ways to implement a symbol table, that is an ordered array, 
 * a binary search tree and a red-black search tree.
 */

public class a2 {
	
	public static void main(String[] args) throws IOException {
		
		//Define a symbol table using ordered arrays
		//OrderedST<String, Integer> st = new OrderedST<String, Integer>(1000000);
		
		//Define a binary search tree
		//BinarySearchTree<String, Integer> st = new BinarySearchTree<String, Integer>();
		
		//Define a red-black balanced search tree
		RedBlack<String, Integer> st = new RedBlack<String, Integer>();
		
		File file = new File("text.txt");
		FileReader fr = new FileReader(file);
		
		//Define a variable for the amount of words for limiting the amount of words read.
		int wordCount = 0;
		//Temporary string variable
		String temp = "";
		int a;
		/* While the input from the text file isn't the end of file character, read and add the characters to the current 
		 * temporary string, when a character that is not a letter is found, add the word to the symbol table, if it already
		 * exists, increment it's value in the symbol table.
		 */
		while((a = fr.read()) != -1) {
			if(Character.isLetter((char)a)) {
				String b = Character.toString((char)a);
				temp += b;
			}
			else if(temp != ""){
				if(st.get(temp) == null) {
					st.put(temp, 1);
					temp = "";
				}
				else {
					st.put(temp, st.get(temp)+1);
					temp = "";
				}
				wordCount++;
			}
		}
		
		String max = "";
		st.put(max, 0);
		
		
		//Initialize timer
		Stopwatch timer = new Stopwatch();
		
		//Iterate through the symbol table and find the word with the highest value, that is the most frquent word in the text
		for(String word : st.keys()) {
			if(st.get(word) > st.get(max)) {
				max = word;	
			}
		}
	
		fr.close();
		
		System.out.println("\n" + max + " " + st.get(max));
		
		//Print out the elapsed time since the timer was started, estimating the time efficiency of the current symbol table
		System.out.println(timer.elapsedTime() + " seconds");
		
	}
	
	

}
