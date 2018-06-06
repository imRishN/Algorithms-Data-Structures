import java.util.ArrayList;
import java.util.Scanner;

public class NegativeCycle {
    private static boolean negativeCycle(Vertex vertex[], Edge edge[], int source) {

        boolean hasNegativeCycle=false;
        vertex[source].distance=0;
        int count=1;
        while(count<vertex.length){
            for(int i=0;i<edge.length;i++)
            {
                int u=edge[i].source;
                int v=edge[i].destination;
                int weight=edge[i].weight;            
                if(vertex[v].distance>(vertex[u].distance+weight)) {
                    vertex[v].distance=(vertex[u].distance+weight);
                    vertex[v].prev=vertex[u];
                }
            }
            count++;
        }

        for(int i=0;i<edge.length;i++)
        {
            int u=edge[i].source;
            int v=edge[i].destination;
            int weight=edge[i].weight;            
            if(vertex[v].distance>(vertex[u].distance+weight)) {
                hasNegativeCycle=true;
                return hasNegativeCycle;
            }
        }
        return hasNegativeCycle;
    }

    private static int BellmanFord(Vertex vertex[], Edge edge[])
    {
        for(int i=0;i<vertex.length;i++)
        {
            if(vertex[i].isVisited==false)
            {
                if(negativeCycle(vertex,edge,i))
                    return 1;
                for(int j=0;j<vertex.length;j++)
                {
                    if(vertex[j].distance!=Double.POSITIVE_INFINITY)
                        vertex[j].isVisited=true;
                }
            }

        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Vertex[] vertex = new Vertex[n];
        Edge edge[]=new Edge[m];
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
            edge[i]=new Edge(x-1,y-1,w);
        }
        System.out.println(BellmanFord(vertex,edge));
    }
}
class Vertex implements Comparable<Vertex>{
    ArrayList<Integer> adj;
    double distance;
    Vertex prev;
    ArrayList<Integer> cost;
    boolean isVisited=false;

    @Override
    public int compareTo(Vertex other){
        return Double.compare(distance,other.distance);
    }
    public Vertex(int n)
    {
        adj=new ArrayList<Integer>();
        cost= new ArrayList<Integer>();
        distance=Double.POSITIVE_INFINITY;
        prev = null;
        isVisited=false;
    }
}
class Edge{
    int source;
    int destination;
    int weight;
    public Edge(int s,int d, int w)
    {
        source=s;
        destination=d;
        weight=w;
    }
}

