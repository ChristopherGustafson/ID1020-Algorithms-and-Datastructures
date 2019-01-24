/* README
 * A directed graph implementation using a linked list to manage the adjecent nodes of given nodes.
 */
public class Digraph {
	
	private final int V;
	private int E;
	private LinkedList<Integer>[] adjecent;

	//Initialize the graph by setting V to the given amount of nodes and initializing the array of Linked lists representing the adjecent nodes of given nodes
	public Digraph(int V) {
		this.V = V;
		this.E = 0;
		adjecent = (LinkedList<Integer>[]) new LinkedList[V];
		//Initialize all adjacent lists
		for(int i = 0; i < V; i++) {
			adjecent[i] = new LinkedList<Integer>();
		}
	}
	
	//Return the amount of nodes in the graph
	public int V() {
		return V;
	}
		
	//return the amount of edges in the graph
	public int E() {
		return E;
	}
	
	//Add an edge by adding the target node to the adjecent nodes of the source node
	public void addEdge(int v, int w) {
		adjecent[v].add(w);
		E++;
	}
	
	//Return all adjecent nodes to the given node v
	public Iterable<Integer> adjecent(int v){
		return adjecent[v];
	}

	//Return a copy of the graph where all edges are reversed, that is pointing in the opposite direction
	public Digraph reverse() {
		Digraph R = new Digraph(V);
		for(int v = 0; v < V; v++) {
			for(int w : adjecent(v)) {
				R.addEdge(w, v);
			}
		}
		return R;
	}
}

