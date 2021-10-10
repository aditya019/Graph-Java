import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class UndirectedUnweightedGraph {
    private HashMap<Integer, List<Integer>> adj;
    public static Scanner s = new Scanner(System.in);
    public UndirectedUnweightedGraph() {
        adj = new HashMap<>();
    }
    public void addVertices(int n) {
        for(int i=0; i<n; i++) {
            System.out.println("Enter the vertice : ");
            int v = s.nextInt();
            adj.put(v, new LinkedList<Integer>());
        }
    }
    public void addEdge(int n) {
        for(int i=0; i<n; i++) {
            System.out.println("Enter Source and Destination : ");
            int source = s.nextInt();
            int dest = s.nextInt();
            helpAddEdge(source, dest);
        }
    }

    public void helpAddEdge(int s, int d) {
        adj.get(s).add(d);
        adj.get(d).add(s);
    }

    public void bfs(int s) {
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.add(s);
        System.out.println("Bfs traversal from" + s + " is :");
        while(q.isEmpty() != true) {
            int node = q.poll();
            System.out.print(node + " ");
            visited.add(node);
            List<Integer> l = adj.get(node);
            for(int i: l) {
                if(visited.contains(i) == false && q.contains(i) == false) q.add(i);
            }
        }
        System.out.println();
    }

    public void dfs(int s) {
        HashSet<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(s);
        System.out.println("DFS Traversal from "+s+" is:");
        while(stack.empty() != true) {
            int node = stack.pop();
            System.out.print(node + " ");
            visited.add(node);
            List<Integer> l = adj.get(node);
            for(int i: l) {
                if(visited.contains(i) == false && stack.contains(i) == false) stack.push(i);
            }
        }
        System.out.println();
    }

    public void shortestPath(int source, int destination) {
        if(adj.containsKey(destination) == false ) {
            System.out.println("Graph does not contain dextination");
            return;
        }
        HashMap<Integer, Integer> h = new HashMap<>();
        Queue<Integer> q = new LinkedList<>();
        int path = 0;
        q.add(source);
        h.put(source, -1);
        boolean flag = false;
        while(q.isEmpty() != true) {
            int node = q.poll();
            List<Integer> l = adj.get(node);
            for(int i: l) {
                if(h.containsKey(i) == false) h.put(i, node);
                if(q.contains(i) == false) q.add(i);
                if(i == destination) {
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        int s = h.get(destination);
        System.out.println("Shortest Path is : ");
        System.out.print(destination + " -> ");
        while(true) {
            System.out.print(s + " -> ");
            path++;
            if(s == source) break;
            s = h.get(s);
        }
        System.out.println();
        System.out.println("Length of shortest path = : " + path);
    }

    public void print() {
        Set<Integer> set = adj.keySet();
        for(int i: set) {
            List<Integer> j = adj.get(i);
            System.out.print("Vertice :" + i + " edges :");
            for(int k: j) {
                System.out.print(k+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        UndirectedUnweightedGraph graph = new UndirectedUnweightedGraph();
        System.out.println("Enter the number of vertices :");
        int v = s.nextInt();
        System.out.println("Enter the number of edges : ");
        int e = s.nextInt();
        graph.addVertices(v);
        graph.addEdge(e);
        System.out.println("The adjecency list of the graph is : ");
        graph.print();
        System.out.println("Enter the node at which the bfs starts : ");
        int node = s.nextInt();
        graph.bfs(node);
        System.out.println("Enter the node at which the dfs starts : ");
        node = s.nextInt();
        graph.dfs(node);
        System.out.println("Enter the source and destination to find the shorted path between them");
        int source = s.nextInt();
        int destination = s.nextInt();
        graph.shortestPath(source, destination);
        s.close();
    }
}
