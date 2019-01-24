import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class a3 {
	
	public static void main(String[] args) throws IOException {
		
		File file = new File("data.txt");
		//FileReader br = new FileReader(file);
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		ArrayList<String> c = new ArrayList<String>();
		
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
		
		br = new BufferedReader(new FileReader(file));
		
		Graph g = new Graph(c.size());
		
		String[] tmp = {"", ""};
		
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
		
		//Print out a path from string source to string target
		String source = "CA";
		String target = "SD";
		DFS dfs = new DFS(g, c.indexOf(source));
		for(int b : dfs.pathTo(c.indexOf(target))) {
			System.out.print(c.get(b) + " ");
		}
		
		System.out.print("\n");
		
		BFS bfs = new BFS(g, c.indexOf(source));
		for(int b : bfs.pathTo(c.indexOf(target))) {
			System.out.print(c.get(b) + " ");
		}
	}

}
