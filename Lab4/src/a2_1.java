import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class a2_1 {

	public static void main(String[] args) throws IOException {
		
		//Create a buffered reader to read from the text file
		File file = new File("data.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		//Create a symbol table with initial size of 100 elements
		ST c = new ST(100);
		
		//Go trough the text file, when a string is found, if it isn't already in the symbol table, add it.
		String temp = "";
		int a;
		while((a = br.read()) != -1) {
			if(Character.isLetter((char)a)){
				temp += (char)a;
			}
			else if(temp != "") {
				if(!c.contains(temp)) {
					c.add(temp);
				}
				temp = "";
			}
		}
		
		br.close();
		
		//Reset the buffered reader to read through the file again
		br = new BufferedReader(new FileReader(file));
		
		//Create a directed graph with the size of the symbol table which will be the amount of nodes in the text file
		Digraph g = new Digraph(c.size());
		
		String[] tmp = {"", ""};
		
		//Go trough the text file, when two strings are found, consider it an edge of the graph from the first to the second string and add it to the graph.
		int i = 0;
		while((a = br.read()) != -1) {
			if(Character.isLetter((char)a)){
				tmp[i] += (char)a;
			}
			else if(tmp[i] != "") {
				i++;
				if(i == 2) {
					g.addEdge(c.indexOf(tmp[0]), c.indexOf(tmp[1]));
					tmp[0] = tmp[1] = "";
					i = 0;
				}
			}
		}
		
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		
		String source = "AZ";
		String target = "UT";
		
		//Print out if there is a path from the string source to string target using depth first search
		DirDFS reachable = new DirDFS(g, c.indexOf(source));
		System.out.println(source + " reaches " + target + ": " + reachable.marked(c.indexOf(target)));
		
		//Print out if the directed graph has a cycle
		DirCycle cyc = new DirCycle(g);	
		System.out.println("The graph has a cycle: " + cyc.hasCycle());
		
	}
}
