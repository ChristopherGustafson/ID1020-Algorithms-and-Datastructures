import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

/* READ ME
 * A program which checks how evenly the built in hash function for strings is in java. The program checks how even the words
 * of the given text file is being distributed over a given amount of equal size intervalls
 */

public class a5 {
	
	public static void main(String[] args) throws IOException {
		
		//Define the input file
		File file = new File("text.txt");
		FileReader fr = new FileReader(file);
		
		//Define the amount of intervalls the hashcodes are placed in
		int arrayCapacity = 64;
		int[] array = new int[arrayCapacity];
		
		//Read the characters from the text files, when a whole word is found, increment the index corresponding to its hash value
		String temp = "";
		int a;
		while((a = fr.read()) != -1) {
			if(Character.isLetter((char)a)) {
				String b = Character.toString((char)a);
				temp += b;
			}
			else if(temp != ""){
				array[hash(temp, arrayCapacity)]++;
				temp = "";
			}
		}
		
		//Print out the distribution of the words in the array
		for(int v : array) {
			System.out.print(v + " ");
		}
		
	}
	
	//Return the hash value of the given string calculated using modular division of a given cap
	private static int hash(String s, int cap) {
		return (s.hashCode() & 0x7ffffff) % cap;
	}
}
