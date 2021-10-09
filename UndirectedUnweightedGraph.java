package Graph;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Set;

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
        graph.addVertices(5);
        graph.addEdge(5);
        graph.print();
        s.close();
    }
}
