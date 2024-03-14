import java.util.LinkedList;

public class Graph {
    LinkedList<Integer>[]adj;

    public Graph(int nodes){
        this.adj = new LinkedList[nodes];
        for(int i = 0; i< nodes; i++){
            this.adj[i] = new LinkedList<>();
        }
    }
    public void addEdge(int u, int v){
        this.adj[u].add(v);
        this.adj[v].add(u);
    }
    public static void main (String[] args){
        Graph grafo1 = new Graph(4);
        grafo1.addEdge(0, 1);
        grafo1.addEdge(1,2);
        grafo1.addEdge(2, 3);
        grafo1.addEdge(3, 0);
    }
}
