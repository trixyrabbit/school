/******************************************************************************
 *  Compilation:  javac DepthFirstSearch.java
 *  Execution:    java DepthFirstSearch filename.txt s
 *  Dependencies: Graph.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41graph/tinyG.txt
 *
 *  Run depth first search on an undirected graph.
 *  Runs in O(E + V) time.
 *
 *  % java DepthFirstSearch tinyG.txt 0
 *  0 1 2 3 4 5 6 
 *  NOT connected
 *
 *  % java DepthFirstSearch tinyG.txt 9
 *  9 10 11 12 
 *  NOT connected
 *
 ******************************************************************************/

/**
 *  The <tt>DepthFirstSearch</tt> class represents a data type for 
 *  determining the vertices connected to a given source vertex <em>s</em>
 *  in an undirected graph. For versions that find the paths, see
 *  {@link DepthFirstPaths} and {@link BreadthFirstPaths}.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>
 *  (in the worst case),
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  It uses extra space (not including the graph) proportional to <em>V</em>.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41graph">Section 4.1</a>   
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
 
public class DepthFirstSearch {
     Color[] marked;    // marked[v] = is there an s-v path?
     int count;           // number of vertices connected to s
	 int[] dis; //discovery time
	 int[] fin; //finish time
	 int[] low; //parent node
	 int[] parent;
	 boolean[] crit;

    /**
     * Computes the vertices in graph <tt>G</tt> that are
     * connected to the source vertex <tt>s</tt>.
     * @param G the graph
     * @param s the source vertex
     */
    public DepthFirstSearch(Graph G) {
        marked = new Color[G.V()];
		parent = new int[G.V()];
		crit = new boolean[G.V()];
		dis = new int[G.V()];
		fin = new int[G.V()];
		low = new int[G.V()];
		count = 0;
		for(int i = 0; i<G.V(); i++) {
			marked[i] = Color.WHITE;
			parent[i] = -1;
			crit[i] = false;
		}
		//start loop from 1?????????
		for(int i = 1; i<G.V(); i++) 
			if(marked[i] == Color.WHITE){
				dfs(G, i); //TODO find someway to iterate on every vertex.
			} 
    }

    // depth first search from v
    private void dfs(Graph G, int v) {   // PARENT IS V
        marked[v] = Color.GRAY;
		count++;
		dis[v] = count;
		low[v] = count;
		int children = 0;
        for (int w : G.adj(v)) { //iterate through the adjacency list
            if (marked[w] == Color.WHITE) {
				parent[w] = v;
                dfs(G, w); //recursive call	
				children++;
				low[v] = Math.min(low[v], low[w]);
				// if it is a root node  in a cycle and it has more than one child
				if(parent[v] == -1 && children > 1)
					crit[v] = true;
				// if the it is not the root node and the low value of the child is is more than discovery of w.
				if(parent[v] != -1 && low[w] >= dis[v])
					crit[v] = true;
            }
            else if (w != parent[v])
                low[v]  = Math.min(low[v], dis[w]);
        }
		marked[v] = Color.BLACK;
		count++;
		fin[v] = count;
    }

    /**
     * Is there a path between the source vertex <tt>s</tt> and vertex <tt>v</tt>?
     * @param v the vertex
     * @return <tt>true</tt> if there is a path, <tt>false</tt> otherwise
     */
    public Color marked(int v) {
        return marked[v];
    }

    /**
     * Returns the number of vertices connected to the source vertex <tt>s</tt>.
     * @return the number of vertices connected to the source vertex <tt>s</tt>
     */
    public int count() {
        return count;
    }

    /**
     * Unit tests the <tt>DepthFirstSearch</tt> data type.
     
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph G = new Graph(in);
        int s = Integer.parseInt(args[1]);
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.count() != G.V()) StdOut.println("NOT connected");
        else                         StdOut.println("connected");
    }
	*/
}

/**

				low[v] = Math.min(low[v], low[w]);

				// if it is a root node  in a cycle and it has more than one child
				if(parent[v] == -1 && children > 1)
					crit[v] = true;
				// if the it is not the root node and the low value of the child is is more than discovery of w.
				if(parent[v] != -1 && low[w] >= dis[v])
					crit[v] = true;
*/
