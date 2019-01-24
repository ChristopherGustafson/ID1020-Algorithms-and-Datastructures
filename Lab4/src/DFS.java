/* README
 * A implementation of the depth first search algorithm. The object takes a graph and a node and finds out if there is a path to any given node aswell as finds one of the potential paths.
 */
public class DFS {
	
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	//Initialize the array for marking nodes aswell as the array of previous nodes, then perform the dfs algorithm recursively starting from the given node s
	public DFS(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		dfs(G, s);
	}
	
	//Recusivly go trough the graph, at every reached node, mark it, save the previous node in edgeTo[] and check its adjecent nodes for nodes that hasn't been visited
	private void dfs(Graph G, int v) {
		marked[v] = true;
		
		for(int w : G.adjecent(v)) {
			if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}
	
	//Return true if the initially given node has a path to the given node v
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	//If there is a path to the given node v, return it
	public Iterable<Integer> pathTo(int v){
		if(!hasPathTo(v)) {
			return null;
		}
		Stack<Integer> path = new Stack<Integer>();
		for(int x = v; x != s; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(s);
		return path;
	}
}
