import java.util.*;
import java.io.*;
public class tmv501
{
	/*Class Data Members*/
	static int n,m,k; //nodes, edges, size of nodes in set S
	static Graph G;
	public static void main(String[] args)
	{
		
		String file = args[0];
		try
		{
			//Load and read the specified test file
			if(file == null)
				file = new String("cnp.in");
			DataInputStream in = new DataInputStream(new FileInputStream(file)); //use the defualt cnp.in			
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = br.readLine(); //grab file header
			String header[] = line.split(" "); //parse header
			//checks the header
			System.out.println(Arrays.toString(header)); //returns [n, m, k] reference with header[0..3]
			
			//Assign the headers, n is nodes, m is edges, k is set
			n = Integer.parseInt(header[0]); m = Integer.parseInt(header[1]); k = Integer.parseInt(header[2]); 
			
			//initilize empty graph
			G = new Graph(n+1); /** CAREFUL, ONE EXTRA ISLAND NODE. REDUCE CONNECTED COMPONENTS BY 1 at end. */
			
			int u, v;
			//scan the next m lines
			for(int i = 1; i<=m; i++)
			{
				line = br.readLine();
				String sedge[] = line.split(" ");
				System.out.println(Arrays.toString(sedge));
				
				//assign edges to G
				u = Integer.parseInt(sedge[0]);
				v = Integer.parseInt(sedge[1]);
				G.addEdge(u,v);
			}
			//close reader
			br.close();
		}
		catch(Exception any){any.printStackTrace();}
		finally{cnp(G, k);} //call solution algorithm
	}
	
	/** Solution finding algorithm that runs DFS on the graph to generate */
	public static void cnp(Graph G, int k)
	{
		//Run Depth first search and save the start times and finishing times, throw out the first number of each...
		DepthFirstSearch search = new DepthFirstSearch(G);
   /*     for (int v = 0; v < G.V(); v++) {
            if (search.marked(v) == Color.BLACK)
                StdOut.print(v + " lol ");
        }*/
		StdOut.println();
		System.out.println("Discovery times: " + Arrays.toString(search.dis));
		System.out.println("Finishing times: " + Arrays.toString(search.fin));
		System.out.println("Critical Nodes: " + Arrays.toString(search.crit));
		System.out.println("Low point: " + Arrays.toString(search.low));
		System.out.println("parents: " + Arrays.toString(search.parent));
		System.out.println("COLOR: " + Arrays.toString(search.marked));
       		
	}
}