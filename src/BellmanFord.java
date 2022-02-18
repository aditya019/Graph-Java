import java.util.*;

public class BellmanFord {
    private final static Scanner sc = new Scanner(System.in);
    HashMap<Integer, List<Edge>> adjList;
    BellmanFord() {
        adjList = new HashMap<>();
    }
    public void addVertices() {
        System.out.println("Enter number of vertices");
        int v = sc.nextInt();
        System.out.println("Enter vertices");
        for (int i=0; i<v; i++) {
            int newVertex = sc.nextInt();
            adjList.put(newVertex, new ArrayList<>());
        }
    }
    public void addEdges() {
        System.out.println("Enter the number of edges");
        int e = sc.nextInt();
        for(int i=0; i<e; i++) {
            System.out.println("Enter source destination and weight");
            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();
            Edge edge = new Edge(s,d,w);
            adjList.get(s).add(edge);
        }
    }
    public void dfs(int s) {
        HashSet<Integer> visited = new HashSet<>();
        List<Integer> dfsList = new ArrayList<>();
        dfs(s, visited, dfsList);
        System.out.println("DFS : " + dfsList);
    }
    public void dfs(int s, HashSet<Integer> visited, List<Integer> dfsList) {
        if(visited.contains(s)) {
            return;
        }
        dfsList.add(s);
        visited.add(s);
        for(Edge i: adjList.get(s)) {
            dfs(i.destination, visited, dfsList);
        }
    }

    //To find the shortest path between two nodes in a graph
    public int bellmanFordAlgorithm(int s, int d) {
        HashMap<Integer, Integer> res1 = bellmanFordAlgorithm(s);
        return res1.get(d);
    }

    private HashMap<Integer, Integer> bellmanFordAlgorithm(int s) {
        HashMap<Integer, Integer> minDistance = createDefaultMap(s);
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        HashSet<Integer> visited = new HashSet<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            visited.add(node);
            for (Edge e: adjList.get(node)) {
                int d = e.destination;
                if(!q.contains(d) && !visited.contains(d)) {
                    q.offer(d);
                }
                int currentDistance = minDistance.get(node) + e.weight;
                minDistance.put(d, Math.min(minDistance.get(d), currentDistance));
            }
        }
        return minDistance;
    }

    private HashMap<Integer, Integer> createDefaultMap(int s) {
        HashMap<Integer, Integer> res = new HashMap<>();
        for(int i: adjList.keySet()) {
            res.put(i, Integer.MAX_VALUE);
        }
        res.put(s, 0);
        return res;
    }


    public static void main(String[] args) {
        BellmanFord graph = new BellmanFord();
        graph.addVertices();
        graph.addEdges();
        graph.dfs(1);
        System.out.println("Enter source and destination for shortest path algorithm");
        int s = sc.nextInt();
        int d = sc.nextInt();
        System.out.println("Shortest path between "+s+ " and " + d + " : " + graph.bellmanFordAlgorithm(s,d));
    }
}
