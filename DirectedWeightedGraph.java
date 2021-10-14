import java.util.*;

public class DirectedWeightedGraph {
    private static Scanner sc = new Scanner(System.in);
    private HashMap<Integer, List<Edge>> adjList;

    DirectedWeightedGraph() {
        adjList = new HashMap<>();
    }

    public void addVertices(int n) {
        for(int i=0; i<n; i++) {
            System.out.println("Enter Vertice");
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
            addEdge(s,d, w);
        }
    }

    private void addEdge(int source, int destination, int weight) {
        Edge e = new Edge(destination, weight);
        adjList.get(source).add(e);
    }

    public void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        q.add(s);
        while(!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            visited.add(node);
            List<Edge> l = adjList.get(node);
            for(Edge e: l) {
                if(!q.contains(e.destination) && !visited.contains(e.destination)) q.add(e.destination);
            }
        }
        System.out.println();
    }

    public void dfs(int s) {
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();
        stack.push(s);
        while(!stack.isEmpty()) {
            int node = stack.pop();
            System.out.print(node + " ");
            visited.add(node);
            List<Edge> l = adjList.get(node);
            for(Edge e: l) {
                if(!stack.contains(e.destination) && !visited.contains(e.destination)) stack.push(e.destination);
            }
        }
        System.out.println();
    }

    public void print() {
        System.out.println("    Grapph");
        for(int i: adjList.keySet()) {
            for(Edge e: adjList.get(i)) {
                System.out.print(i+"--"+e.weight+"-->"+e.destination+"  ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        DirectedWeightedGraph graph = new DirectedWeightedGraph();
        System.out.println("Enter number of vertces and edges");
        int v = sc.nextInt();
        int e = sc.nextInt();
        graph.addVertices(v);
        graph.addEdge(e);
        graph.print();
        System.out.println("Enter node at which to start bfs");
        int node = sc.nextInt();
        graph.bfs(node);
        System.out.println("Enter node at which to start dfs");
        node = sc.nextInt();
        graph.dfs(node);
    }
}
