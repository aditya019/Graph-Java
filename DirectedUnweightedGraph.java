import java.util.*;

public class DirectedUnweightedGraph {
    static Scanner sc = new Scanner(System.in);
    HashMap<Integer, List<Integer>> adjList;
    DirectedUnweightedGraph() {
        adjList = new HashMap<>();
    }

    public void addVertice(int n) {
        for(int i=0; i<n; i++) {
            System.out.println("Enter Vertice : ");
            int v = sc.nextInt();
            adjList.put(v, new ArrayList<>());
        }
    } 

    public void addEdge(int n) {
        for(int i=0; i<n; i++) {
            System.out.println("Enter Source and Destination");
            int s = sc.nextInt();
            int d = sc.nextInt();
            addEdge(s,d);
        }
    }

    public void addEdge(int s, int d) {
        if(!adjList.containsKey(s) || !adjList.containsKey(d)) {
            System.out.println("Source or Destination does not exist does not exist");
            return;
        }
        adjList.get(s).add(d);
    }

    public void bfs(int s) {
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        System.out.println("BFS traversal of the graph is : ");
        while(!q.isEmpty()) {
            int node = q.poll();
            System.out.print(node + " ");
            visited.add(node);
            List<Integer> l = adjList.get(node);
            for(int i: l) {
                if(!visited.contains(i) && !q.contains(i)) q.add(i);
            }
        }
        System.out.println();
    } 

    public void dfs(int s) {
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        System.out.println("DFS traversal of graph is : ");
        while(!stack.isEmpty()) {
            int node = stack.pop();
            visited.add(node);
            System.out.print(node + " ");
            List<Integer> l = adjList.get(node);
            for(int i: l) {
                if(!visited.contains(i) && !stack.contains(i)) stack.push(i);
            }
        }
        System.out.println();
    }

    public boolean hasPath(int s, int d) {
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        while(!stack.isEmpty()) {
            int node = stack.pop();
            if(node == d) return true;
            List<Integer> l = adjList.get(node);
            visited.add(node);
            for(int i: l) {
                if(!visited.contains(i) && !stack.contains(i)) stack.push(i);
            }
        }
        return false;
    }

    public void shortesPath(int s, int d) {
        if(!hasPath(s, d)) {
            System.out.println("Path does not exist between " + s + " and " + d);
            return ;
        }
        HashMap<Integer, Integer> h = new HashMap<>();
        h.put(s, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        while(!q.isEmpty()) {
            int node = q.poll();
            if(node == d) break;
            List<Integer> l = adjList.get(node);
            for(int i: l) {
                if(!h.containsKey(i) && !q.contains(i)) {
                    q.add(i);
                    h.put(i, node);
                }
            }
        }
        ArrayList<Integer> arr = new ArrayList<>();
        int pathLength = 0;
        arr.add(d);
        int temp = d;
        while(true) {
            if(temp == s) break;
            temp = h.get(temp);
            arr.add(temp);
            pathLength++;
        }
        System.out.println("Path length is : " + pathLength);
        Collections.reverse(arr);
        for(int i: arr) {
            System.out.print(i + " -> ");
        }
        System.out.println();
    }

    public void print() {
        for(int i: adjList.keySet()) {
            System.out.print(i + " -> ");
            for(int j: adjList.get(i)) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws Exception {
        DirectedUnweightedGraph graph = new DirectedUnweightedGraph();
        System.out.println("Enter Number of vertices and edges : ");
        int vertices = sc.nextInt();
        int edges = sc.nextInt();
        graph.addVertice(vertices);
        graph.addEdge(edges);
        graph.print();
        System.out.println("Enter the node from which to start bfs :");
        int node = sc.nextInt();
        graph.bfs(node);
        System.out.println("Enter the node from which to start dfs : ");
        node = sc.nextInt();
        graph.dfs(node);
        System.out.println("Enter the source and destination to find if there is a path between them :");
        int source = sc.nextInt();
        int destination = sc.nextInt();
        boolean path = graph.hasPath(source, destination);
        System.out.println("path " + source + " -> " + destination + " " + path);
        System.out.println("Enter the source and destination to find the shortest path between them : ");
        source = sc.nextInt();
        destination = sc.nextInt();
        graph.shortesPath(source, destination);
        sc.close();
    }
}