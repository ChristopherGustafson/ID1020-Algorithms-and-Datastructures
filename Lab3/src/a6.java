import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/* READ ME
 * A program which gives the character indexes in a text files that a given string is found at. The program accomplishes this 
 * by using the previously implemented binary search tree.
 */
public class a6 {
	
	public static void main(String[] args) throws IOException {
		
		//Define a binary search tree, with the key being a string and the value being a linked list, used to store all the indexes
		//where the key occur in the text.
		BinarySearchTree<String, LinkedList<Integer>> st = new BinarySearchTree<String, LinkedList<Integer>>();
		
		//Define the input file
		File file = new File("text.txt");
		FileReader fr = new FileReader(file);
		
		
		int characterIndex = 0;
		String temp = "";
		int a;
		
		/* Go trough the text file and add the found words to the binary search tree, adding the indexes of the occuring word to
		 * the linked list corresponding to the words key.
		 */
		while((a = fr.read()) != -1) {
			characterIndex++;
			if(Character.isLetter((char)a)) {
				String b = Character.toString((char)a);
				temp += b;
			}
			else if(temp != ""){
				
				if(st.get(temp) == null) {
					st.put(temp, new LinkedList<Integer>());
					st.get(temp).add(characterIndex - temp.length());
				}
				else {
					st.get(temp).add(characterIndex - temp.length());
				}
				
				temp = "";
				
			}
		}
		
		//Define a string and print out the indexes that the string occurs in the given text file.
		String s = "muffler";
		System.out.print(s + " occurs on char indexes: ");
		for(Integer i : st.get(s)) {
			System.out.print(i + " ");
		}			
	}
}
