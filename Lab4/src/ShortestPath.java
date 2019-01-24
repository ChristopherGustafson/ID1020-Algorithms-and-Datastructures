
public class ShortestPath {

	private Edge[] edgeTo;
	private double[] distTo;
	
	
	
	private void relax(Edge e) {
		int v = e.either();
		int w = e.other(v);
		if(distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
		}
	}
}
