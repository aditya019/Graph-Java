import java.util.*;
public class UndirectedWeightedGraph {
    HashMap<Integer, List<Edge>> adjList;
    static Scanner sc = new Scanner(System.in);
    UndirectedWeightedGraph() {
        adjList = new HashMap<>();
    }
    public void addVertices(int n) {
        for(int i=0; i<n; i++) {
            System.out.println("Enter value of Vertex");
            int v = sc.nextInt();
            adjList.put(v, new LinkedList<>());
        }
    }

    public void addEdge(int n) {
        for(int i=0; i<n; i++) {
            System.out.println("Enter Source and Destination and Weight");
            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();
            addEdge(s, d, w);
        }
    }

    public void addEdge(int s, int d, int w) {
        Edge e1 = new Edge(s, d, w);
        Edge e2 = new Edge(d, s, w);
        adjList.get(s).add(e1);
        adjList.get(d).add(e2);
    }

    public void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        HashSet<Integer> visited = new HashSet<>();
        while(!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            visited.add(node);
            List<Edge> l = adjList.get(node);
            for(Edge i: l) {
                int j = i.destination;
                if(!q.contains(j) && !visited.contains(j)) q.add(j);
            }
        }
        System.out.println();
    }

    public void dfs(int s) {
        Stack<Integer> stack = new Stack<>();
        stack.add(s);
        HashSet<Integer> visited = new HashSet<>();
        while(!stack.isEmpty()) {
            int node = stack.pop();
            visited.add(node);
            System.out.print(node+" ");
            List<Edge> l = adjList.get(node);
            for(Edge i: l) {
                int j = i.destination;
                if(!stack.contains(j) && !visited.contains(j)) stack.add(j);
            }
        }
        System.out.println();
    }

    public void print() {
        for(int i: adjList.keySet()) {
            for(Edge e: adjList.get(i)) {
                System.out.print(i +"-"+e.weight+"->"+e.destination+"  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        UndirectedWeightedGraph graph = new UndirectedWeightedGraph();
        System.out.println("Enter number of vertices and edges");
        int v = sc.nextInt();
        int e = sc.nextInt();
        graph.addVertices(v);
        graph.addEdge(e);
        graph.print();
        System.out.println("Enter Source for BFS");
        int node = sc.nextInt();
        graph.bfs(node);
        System.out.println("Enter source for DFS");
        node = sc.nextInt();
        graph.dfs(node);
        sc.close();
    }
}
