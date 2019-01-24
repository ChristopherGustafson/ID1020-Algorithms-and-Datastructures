/* README
 * An implementation of the depth first search algorithm for directed graphs
 */
public class DirDFS {
	
	private boolean[] marked;
	
	public DirDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		dfs(G, s);
	}
	
	//Find all reachable nodes recursively by going trough the graph, marking the visited nodes, and proceeding to all ajdecent nodes
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for(int w : G.adjecent(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
	}
	
	//Return true if the given node v is reachable from the source node
	public boolean marked(int v) {
		return marked[v];
	}
	
}
