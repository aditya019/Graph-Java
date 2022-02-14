import java.util.*;

public class BidirectionalBFS {
    static Scanner sc = new Scanner(System.in);
    HashMap<Integer, List<Integer>> adjList;
    int vertices;
    int edges;
    BidirectionalBFS(int v, int e) {
        adjList = new HashMap<>();
        vertices = v;
        edges = e;
    }
    public void addVertices() {
        System.out.println("enter vertices");
        for(int i=0; i<this.vertices; i++) {
            int v = sc.nextInt();
            adjList.put(v, new ArrayList<>());
        }
    }
    public void addEdges() {
        System.out.println("enter edges");
        for(int i=0; i<edges; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            adjList.get(s).add(d);
            adjList.get(d).add(s);
        }
    }
    public void print() {
        System.out.println();
        for(int i : adjList.keySet()) {
            System.out.print(i + " : ");
            for(int j : adjList.get(i)) {
                System.out.print(j + " , ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public void biderectionalBFS(int s, int d) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        HashSet<Integer> visited1 = new HashSet<>();
        HashSet<Integer> visited2 = new HashSet<>();
        q1.offer(s);
        q2.offer(d);
        int c = 0;
        while(!q1.isEmpty() && !q2.isEmpty()) {
            int node1 = q1.poll();
            int node2 = q2.poll();
            visited1.add(node1);
            visited2.add(node2);
            if(visited1.contains(node2) && visited2.contains(node1)) {
                System.out.println("Connection found !! after " + c + " steps");
                return;
            }
            for(int i: adjList.get(node1)) {
                if(!visited1.contains(i) && !q1.contains(i)) {
                    q1.offer(i);
                }
            }
            for(int i: adjList.get(node2)) {
                if(!visited2.contains(i) && !q2.contains(i)) {
                    q2.offer(i);
                }
            }
            c++;
        }
        System.out.println("nodes are not connected ! ");
    }

    public static void main(String[] args) {
        System.out.println("Enter number vertices and edges");
        int v = sc.nextInt();
        int e = sc.nextInt();
        BidirectionalBFS graph = new BidirectionalBFS(v,e);
        graph.addVertices();
        graph.addEdges();
        graph.print();
        System.out.println("Eneter source and destination for bidirectional bfs");
        int s = sc.nextInt();
        int d = sc.nextInt();
        graph.biderectionalBFS(s,d);
    }
}
