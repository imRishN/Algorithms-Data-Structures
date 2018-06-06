import java.util.*;

public class Dijkstra {
	private static double distance(Vertex vertex[], int s, int t) {
		vertex[s].distance=0;
		PriorityQueue<Vertex> pQueue=new PriorityQueue<Vertex>();
		Vertex v;
		for(int i=0;i<vertex.length;i++)
		{
			pQueue.add(vertex[i]);
		}
		while(!pQueue.isEmpty())
		{
			v=pQueue.poll();
			for(int i=0;i<v.adj.size();i++)
			{
				if(vertex[v.adj.get(i)].distance>(v.distance+v.cost.get(i))) {
					Vertex vUpdate=vertex[v.adj.get(i)];
					pQueue.remove(vUpdate);
					vUpdate.distance=(v.distance+v.cost.get(i));
					vUpdate.prev=v;
					pQueue.add(vUpdate);
				}
			}
		}
		if(vertex[t].distance!=Double.POSITIVE_INFINITY)
			return vertex[t].distance;
		else
			return -1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		Vertex[] vertex = new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertex[i] = (Vertex)new Vertex(n);
		}
		for (int i = 0; i < m; i++) {
			int x, y, w;
			x = scanner.nextInt();
			y = scanner.nextInt();
			w = scanner.nextInt();
			vertex[x - 1].adj.add(y - 1);
			vertex[x - 1].cost.add(w);
		}
		int x = scanner.nextInt() - 1;
		int y = scanner.nextInt() - 1;
		int dist=(int)distance(vertex, x, y);
		System.out.println(dist);
	}
}
class Vertex implements Comparable<Vertex>{
	ArrayList<Integer> adj;
	double distance;
	Vertex prev;
	ArrayList<Integer> cost;

	@Override
	public int compareTo(Vertex other){
		return Double.compare(distance,other.distance);
	}
	public Vertex(int n)
	{
		adj=new ArrayList<Integer>();
		cost= new ArrayList<Integer>();
		distance=Double.POSITIVE_INFINITY;;
		prev = null;
	}
}

