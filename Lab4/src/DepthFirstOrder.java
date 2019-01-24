/* README
 * A depth first implementaion to find thre reverse post order of a directed graph
 */
public class DepthFirstOrder {

	private boolean[] marked;
	private Stack<Integer> reversePost;
	
	//Initialize the stack consisting of the reverse post order and the marked array
	public DepthFirstOrder(Digraph G) {
		reversePost = new Stack<Integer>();
		marked = new boolean[G.V()];
		
		for(int v = 0; v < G.V(); v++) {
			if(!marked[v]) {
				dfs(G, v);
			}
		}
	}
	
	//Go trough the graph recursivly starting at node v, end with pushing the node onto a stack in the post order
	//Removing the nodes from the stack will then give the reverse post order
	private void dfs(Digraph G, int v) {
		marked[v] = true;
		for(int w : G.adjecent(v)) {
			if(!marked[w]) {
				dfs(G, w);
			}
		}
		reversePost.push(v);
	}
	
	//Return the reverse post order of the given graph
	public Iterable<Integer> reversePost(){
		return reversePost;
	}
}
