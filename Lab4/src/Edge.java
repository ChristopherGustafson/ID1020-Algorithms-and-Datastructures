/* README
 * An edge object class representing a weighted edge of a graph between two nodes v and w. 
 */
public class Edge implements Comparable<Edge>{

	private final int v;
	private final int w;
	private final double weight;
	
	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}
	
	//Return the weight of the edge
	public double weight() {
		return weight;
	}
	
	//Return one of the edges nodes
	public int either() {
		return v;
	}
	
	//Return the node at the edge that is not the given edge
	public int other(int vertex) {
		if(vertex == v) {
			return w;
		}
		else if(vertex == w) {
			return v;
		}
		else {
			return -1;
		}
	}
	
	//Compare this edges weight to a given edge e
	public int compareTo(Edge e) {
		if(this.weight() < e.weight()) {
			return -1;
		}
		else if(this.weight() > e.weight()) {
			return 1;
		}
		else {
			return 0;
		}
	}
}