/* README
 * The program checks if a directed graph has a cycle by using the depth first algorithm
 */
public class DirCycle {

	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;
	private boolean[] onStack;
	
	//Initialize the used arrays and a stack used to find potential cycles, then start going trough the graph recursively
	public DirCycle(Digraph G) {
		onStack = new boolean[G.V()];
		edgeTo = new int[G.V()];
		marked = new boolean[G.V()];
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
			}
		}
	}

	//Go trough the graph using DFS as usual, if a node that has already been visited is found, backtrack from that node and add the nodes to the cycle stack
	private void dfs(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for(int w : G.adjecent(v)) {
			if(this.hasCycle()) {
				return;
			}
			else if(!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
			else if(onStack[w]) {
				cycle = new Stack<Integer>();
				for(int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}	
		onStack[v] = false;
	}
	
	//Return true if the graph has a cycle
	public boolean hasCycle() {
		return cycle != null;
	}
	
	//If there is a cycle, return the nodes the cycle consits of
	public Iterable<Integer> cycle(){
		return cycle;
	}
}
