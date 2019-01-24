import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/* README
 * A program which reads in the given data text-file containing the edges of a weighted undirected graph. The program adds the nodes to a symbol table and then creates
 * a graph with the given edges and randomizes a weight of every edge. The program then finds a minimal spanning tree using the lazy version of Prim's algorithm
 */

public class a4 {
	
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
		
		//Create a weighted graph with the size of the symbol table which will be the amount of nodes in the text file
		WeightedGraph g = new WeightedGraph(c.size());
		
		String[] tmp = {"", ""};
		
		//Go trough the text file, when two strings are found, consider it an edge of the graph and add it with a randomized weight.
		int i = 0;
		while((a = br.read()) != -1) {
			if(Character.isLetter((char)a)){
				tmp[i] += (char)a;
			}
			else if(tmp[i] != "") {
				i++;
				if(i == 2) {
					Edge e = new Edge(c.indexOf(tmp[0]), c.indexOf(tmp[1]), Math.random()*10);
					g.addEdge(e);
					tmp[0] = tmp[1] = "";
					i = 0;
				}
			}
		}
		
		//Find the minimal spanning tree and print it out
		MST mst = new MST(g);
		System.out.println("The following edges make up the minimum spanning tree: ");
		for(Edge e : mst.edges()) {
			System.out.print(c.get(e.either()) + "-" + c.get(e.other(e.either())) + ", ");
		}
	}

}
