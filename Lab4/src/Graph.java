/* README
 * An undirected graph implementation using a linked list to manage the adjecent nodes of given nodes.
 */

public class Graph {
	
	private final int V;
	private int E;
	private LinkedList<Integer>[] adjecent;

	//Initialize the graph by setting V to the given amount of nodes and initializing the array of Linked lists representing the adjecent nodes of given nodes.
	public Graph(int V) {
		this.V = V;
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
		
	//Return the amount of edges in the graph
	public int E() {
		return E;
	}
	
	//Add an edge by setting the first node to be adjecent to the second node and vice versa
	public void addEdge(int v, int w) {
		adjecent[v].add(w);
		adjecent[w].add(v);
		E++;
	}
	
	//Return all the adjecent nodes of the given node v
	public Iterable<Integer> adjecent(int v){
		return adjecent[v];
	}

}
