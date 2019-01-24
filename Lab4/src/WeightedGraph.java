/* README
 * An undirected weighted graph using a linked list of edge objects to manage the adjecent nodes of a given node
 */

public class WeightedGraph {
	
	private final int V;
	private int E;
	private LinkedList<Edge>[] adjecent;

	//Initialize the graph by setting V to the given amount of nodes and initializing the array of Linked lists representing the adjecent nodes of given nodes.
	public WeightedGraph(int V) {
		this.V = V;
		adjecent = (LinkedList<Edge>[]) new LinkedList[V];
		//Initialize all adjacent lists
		for(int i = 0; i < V; i++) {
			adjecent[i] = new LinkedList<Edge>();
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
	
	//Add an edge by getting the two nodes of the edge and adding them to eachothers list of adjecent nodes
	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		adjecent[v].add(e);
		adjecent[w].add(e);
		E++;
	}
	
	//Return the adjecent nodes of the given node v
	public Iterable<Edge> adjecent(int v){
		return adjecent[v];
	}
	
	//Return a list of all edges in the weighted graph
	public Iterable<Edge> edges(){
		LinkedList<Edge> edges = new LinkedList<Edge>();
		
		for(int v = 0; v < V; v++) {
			for (Edge e : adjecent[v]){
				if(e.other(v) > v) {
					edges.add(e);
				}
			}
		}
		
		return edges;
		
	}

}
