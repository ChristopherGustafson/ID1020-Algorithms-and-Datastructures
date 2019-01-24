/* README
 * An implementation of the lazy version of Prim's algorithm to find minimal spanning trees of a given weighted graph.
 */
public class MST {
	private boolean[] marked;
	private Queue<Edge> mst;
	private MinPQ<Edge> pq;
	
	//Initialize the marked array, the minimial spanning tree queue, the priority queue and find the MST
	public MST(WeightedGraph G) {
		pq = new MinPQ<Edge>(1000);
		marked = new boolean[G.V()];
		mst = new Queue<Edge>();
		
		/* Start at node 0, compare all outgoing edges from the MST and add the smallest to the MST, then visit the node
		 * the added edge goes to and do the same thing again until all nodes have been visited.
		 */
		visit(G, 0);
		while(!pq.isEmpty()) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if(marked[v] && marked[w]) {
				continue;
			}
			mst.enqueue(e);
			if(!marked[v]) {
				visit(G, v);
			}
			if(!marked[w]) {
				visit(G, w);
			}
		}
	}
	
	//Visit a node of the graph by marking the node and adding all its outgoing edges to the priority queue
	private void visit(WeightedGraph G, int v) {
		marked[v] = true;
		for(Edge e : G.adjecent(v)) {
			if(!marked[e.other(v)]) {
				pq.insert(e);
			}
		}
	}
	
	//Return the edges of the found MST
	public Iterable<Edge> edges(){
		return mst;
	}

}
