import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* READ ME
 * A program which takes in a text files and replaces all characters which is not a letter, a blank space and a new line with a 
 * blank space.
 */
public class a1 {
	
	public static void main(String[] args) {
		filter("98-0.txt");
	}
	
	
	public static void filter(String filePath) {	
		try{
			//Define the input file, a temporary file and a filewriter to write to the temporary file
			File file = new File(filePath);
			FileReader fr = new FileReader(file);
			File tempFile = File.createTempFile("buffer", ".tmp", new File("B:\\Workspace\\Lab3"));
			FileWriter fw = new FileWriter(tempFile);
			int a;
			/* While end of file isn't reached, read the characters from the file and replace all characters that is not a letter,
			 * blank space or new line with a blank space by writing this to the temp file while writing the character directly to the
			 * temp file if not.
			 */
			while((a = fr.read()) != -1) {
				if(Character.isLetter((char)a) || (char)a == ' ' || (char)a == '\n'){
					fw.write(a);
				}
				else {
					fw.write(' ');
				}
			}
			fw.close();
			fr.close();
			
			//Delete the original file and rename the temporary file with the name of the original
			file.delete();
			tempFile.renameTo(new File(filePath));
				
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
