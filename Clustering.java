import java.util.*;
import java.lang.Math;
import java.text.DecimalFormat;
import java.math.RoundingMode;

public class Clustering {
	//implements MakeSet, Find and Union properties of disjoint sets
	private static double clustering(Vertex vertex[],PriorityQueue<VertexDistance> pQueue, int parent[], int k) {

		int clusterSize=vertex.length;
		VertexDistance vDistance;
		for(int i=0;i<pQueue.size();i++)
		{
			if(clusterSize==k)
				break;

			int v1=0,v2=0; 
			boolean v1_found=false,v2_found=false;
			vDistance=pQueue.poll();
			for(int j=0;j<vertex.length;j++)
			{
				if(vertex[j]==vDistance.v1)
				{
					v1=j;
					v1_found=true;
				}
				if(vertex[j]==vDistance.v2)
				{
					v2=j;
					v2_found=true;
				}
				if(v1_found&&v2_found)
					break;
			}
			int v1_find=find(parent, v1);
			int v2_find=find(parent, v2);
			if(v1_find!=v2_find)
			{
				union(parent, v1_find, v2_find);
				clusterSize--;				
			}
		}
		while(true){
			VertexDistance vdist= pQueue.poll();
			int v1=0,v2=0; 
			boolean v1_found=false,v2_found=false;
			for(int j=0;j<vertex.length;j++)
			{
				if(vertex[j]==vdist.v1)
				{
					v1=j;
					v1_found=true;
				}
				if(vertex[j]==vdist.v2)
				{
					v2=j;
					v2_found=true;
				}
				if(v1_found&&v2_found)
					break;
			}
			int v1_find=find(parent, v1);
			int v2_find=find(parent, v2);
			if(v1_find!=v2_find){
				return vdist.distance;
			}
		}
	}

	private static void union(int parent[], int i, int j)
	{
		parent[j]=i;
	}

	private static int find(int parent[],int i)
	{
		while(parent[i]!=i)
		{
			i=parent[i];
		}		
		return i;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Vertex vertex[]=new Vertex[n];
		PriorityQueue<VertexDistance> pQueue=new PriorityQueue<VertexDistance>();
		int parent[]=new int[n];
		for (int i = 0; i < n; i++) {
			parent[i]=i;
			vertex[i]=(Vertex)new Vertex();
			vertex[i].x = scanner.nextInt();
			vertex[i].y = scanner.nextInt();
			for(int j=0;j<i;j++){
				VertexDistance vDistance=new VertexDistance(vertex[i],vertex[j]);
				pQueue.add(vDistance);
			}
		}
		int k = scanner.nextInt();
		double minDist=clustering(vertex,pQueue,parent,k);
		DecimalFormat df = new DecimalFormat("#.#######");
		df.setRoundingMode(RoundingMode.CEILING);
		System.out.println(df.format(minDist));
	}
}
class Vertex{
	int x;
	int y;
	public Vertex(){
	}
}

class VertexDistance implements Comparable<VertexDistance>{
	Vertex v1;
	Vertex v2;
	double distance;
	public VertexDistance(Vertex v_1,Vertex v_2){
		v1=v_1;
		v2=v_2;
		distance=Math.sqrt(Math.pow((v2.x-v1.x),2)+Math.pow((v2.y-v1.y),2));
	}
	@Override
	public int compareTo(VertexDistance other){
		return Double.compare(distance,other.distance);
	}
}

