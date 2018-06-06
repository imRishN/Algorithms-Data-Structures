import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {
    private static int bipartite(Vertex vertex[]) {
        int s=0;
        vertex[s].isVisited=true;
        Vertex v;
		Queue<Vertex> queue=new LinkedList<>();
		queue.add(vertex[s]);

		while(queue.size()!=0)
		{
			v=queue.remove();
			for(int i=0;i<v.adj.size();i++)
			{
				if(vertex[v.adj.get(i)].isVisited==false)
				{
					queue.add(vertex[v.adj.get(i)]);
					vertex[v.adj.get(i)].isVisited=true;
					vertex[v.adj.get(i)].x=1-v.x;
				}
				else
				{
					if(v.x==vertex[v.adj.get(i)].x)
						return 0;
				}
			}
		}
        return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		Vertex[] vertex = new Vertex[n];
		for (int i = 0; i < n; i++) {
			vertex[i] = (Vertex)new Vertex();
		}
        for (int i = 0; i < m; i++) {
			int x, y;
			x = scanner.nextInt();
			y = scanner.nextInt();
			vertex[x - 1].adj.add(y - 1);
			vertex[y - 1].adj.add(x - 1);
		}
        System.out.println(bipartite(vertex));
    }
}
class Vertex{
	ArrayList<Integer> adj;
	boolean isVisited;
	int x;
	public Vertex()
	{
		adj=new ArrayList<Integer>();
		isVisited=false;
		x=0;
	}
}

