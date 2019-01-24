import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* README
 * A program which reads in the given data text-file containing the edges of a undirected graph. The program adds the nodes to a symbol table and then creates
 * a graph with the given edges. With the graph it then uses DFS and BFS to calculate one path aswell as the shortest path between two nodes.
 */

public class a1 {
	
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
		
		//Create a graph with the size of the symbol table which will be the amount of nodes in the text file
		Graph g = new Graph(c.size());
		
		String[] tmp = {"", ""};
		
		//Go trough the text file, when two strings are found, consider it an edge of the graph and add it to the graph.
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
		
		String source = "CA";
		String target = "SD";
		
		//Print out a path from string source to string target using depth first search
		System.out.print("A path from " + source + " to " + target + " using DFS: ");
		DFS dfs = new DFS(g, c.indexOf(source));
		for(int b : dfs.pathTo(c.indexOf(target))) {
			System.out.print(c.get(b) + " ");
		}
		
		System.out.print("\n");
		
		//Print out the shortest path from string source to string target using breadth first search
		System.out.print("The shortest path from " + source + " to " + target + " using BFS: ");
		BFS bfs = new BFS(g, c.indexOf(source));
		for(int b : bfs.pathTo(c.indexOf(target))) {
			System.out.print(c.get(b) + " ");
		}
	}

}
