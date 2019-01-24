import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/* README
 * A program which takes in a directed graph and finds a topological order of a graph, if the graph is acyclic
 */
public class Topological {
	
	private Iterable<Integer> order;
	
	//Check if the graph has any cycles, if not retreive the reverse post order of the graph, which willbe a topological order
	public Topological(Digraph G) {
		DirCycle cyclefind = new DirCycle(G);
		if(!cyclefind.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
		}
	}
	
	//Return the topological order of the graph
	public Iterable<Integer> order(){
		return order;
	}
	
	//Return true if there isn't a topological order, that is the directed graph consits of atleast on cycle and is not a DAG
	public boolean isDAG() {
		return order == null;
	}
	
	public static void main(String[] args) throws IOException {
		
		//Create a buffered reader to read from the text file
		File file = new File("data2.txt");
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
		
		//Find and print out a topological order of the directed graph
		Topological tp = new Topological(g);
		for(int node : tp.order()) {
			System.out.print(c.get(node) + " - ");
		}
	}
}
