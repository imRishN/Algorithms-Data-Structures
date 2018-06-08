import java.util.*;
import java.lang.Math;
import java.text.DecimalFormat;
import java.math.RoundingMode;
//PRIM's ALGORITHM
public class ConnectingPoints {
	private static double minimumDistance(Vertex vertex[]) {
		double result = 0.;
		vertex[0].cost=0;
		PriorityQueue<Vertex> pQueue=new PriorityQueue<Vertex>();
		for(int i=0;i<vertex.length;i++)
		{
			pQueue.add(vertex[i]);
		}
		Vertex v;
		while(!pQueue.isEmpty())
		{
			v=pQueue.poll();
			result+=v.cost;
			for(int i=0;i<vertex.length;i++)
			{
				if(pQueue.contains(vertex[i])&&vertex[i].cost>distanceBetweenPoints(v,vertex[i])){
					pQueue.remove(vertex[i]);
					vertex[i].cost=distanceBetweenPoints(v,vertex[i]);
					vertex[i].parent=v;
					pQueue.add(vertex[i]);

				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Vertex vertex[]=new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertex[i]=(Vertex)new Vertex();
			vertex[i].x = scanner.nextInt();
			vertex[i].y = scanner.nextInt();
		}
		double minDistance=minimumDistance(vertex);
		DecimalFormat df = new DecimalFormat("#.#######");
		df.setRoundingMode(RoundingMode.CEILING);
		System.out.println(df.format(minDistance));
	}


	private static double distanceBetweenPoints(Vertex v1, Vertex v2)
	{
		double distance=Math.sqrt(Math.pow((v2.x-v1.x),2)+Math.pow((v2.y-v1.y),2));
		return distance; 
	}
}
class Vertex implements Comparable<Vertex>{
	int x;
	int y;
	double cost;
	Vertex parent;
	@Override
	public int compareTo(Vertex other){
		return Double.compare(cost,other.cost);
	}
	public Vertex(){
		cost=Double.POSITIVE_INFINITY;
		parent=null;
	}
}

