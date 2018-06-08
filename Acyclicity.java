import java.util.ArrayList;
import java.util.Scanner;

public class Acyclicity {
    static boolean isCyclic=false;
    private static int acyclic(Vertex vertex[]) { 
        for(int i=0;i<vertex.length;i++)
        {
            if(isCyclic==true)
                break;
            if(!vertex[i].isVisited)
                explore(vertex,i,i);
        }
        if(isCyclic) return 1;
        else return 0;
    }

    private static void explore(Vertex vertex[], int x,int startNode)
    {
        if(isCyclic==true)
        {
            return;
        }
        vertex[x].isVisited=true;
        for(int i=0;i<vertex[x].adj.size();i++)
        {
            if(!vertex[vertex[x].adj.get(i)].isVisited)
                explore(vertex,vertex[x].adj.get(i),startNode);
            else
            {
                if(vertex[x].adj.get(i)==startNode)
                {
                    isCyclic=true;
                    return;
                }
            }      
        }
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
        }
        System.out.println(acyclic(vertex));
    }
}
class Vertex{
    ArrayList<Integer> adj;
    boolean isVisited;
    Vertex prev;
    public Vertex()
    {
        adj=new ArrayList<Integer>();
        isVisited=false;
        prev=null;
    }
}

