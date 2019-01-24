/* README
 * A implementation of the breadth first search algorithm. The object takes a graph and a node and finds out if there is a path to any given node aswell as finds the shortest path.
 */

public class BFS {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;
	
	//Initialize the array for marking nodes aswell as the array of previous nodes, then perform the bfs algorithm iteratively starting from the given node s
	public BFS(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		this.s = s;
		bfs(G, s);
	}
	
	//Go trough the graph starting at the source node and traverse trough the nodes with larger and larger distance to the source. At the end the reachable nodes will be marked and edgeTo[] will contain the shortest paths
	private void bfs(Graph G, int s) {
		Queue<Integer> q = new Queue<Integer>();
		marked[s] = true;
		q.enqueue(s);
		while(!q.isEmpty()) {
			int v  = q.dequeue();
			for(int w : G.adjecent(v)) {
				if(!marked[w]) {
					edgeTo[w] = v;
					marked[w] = true;
					q.enqueue(w);
				}
			}
		}
	}
	
	//Return true if there is a path between the source node and the given node v
	public boolean hasPathTo(int v) {
		return marked[v];
	}
	
	//Return the shortest path from the source node to the given node v, if one exists
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
