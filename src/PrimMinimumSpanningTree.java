
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class Graph {
    private int vertices;
    private List<List<Edge>> adjacencyList;

    public Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    public void addEdge(int start, int end, int weight) {
        Edge edge = new Edge(start, end, weight);
        adjacencyList.get(start).add(edge);
        // Добавляем также обратное ребро для неориентированного графа
        Edge reverseEdge = new Edge(end, start, weight);
        adjacencyList.get(end).add(reverseEdge);
    }

    public void minimumSpanningTreePrim() {
        boolean[] visited = new boolean[vertices];
        PriorityQueue<Edge> queue = new PriorityQueue<>(vertices, (e1, e2) -> e1.weight - e2.weight);

        visited[0] = true;
        for (Edge edge : adjacencyList.get(0)) {
            queue.add(edge);
        }

        int minimumSpanningTreeWeight = 0;
        while (!queue.isEmpty()) {
            Edge currentEdge = queue.poll();

            if (visited[currentEdge.end]) {
                continue;
            }

            System.out.println("Вершина: " + currentEdge.start + " - " + currentEdge.end + " | Остов: " + currentEdge.weight);
            minimumSpanningTreeWeight += currentEdge.weight;

            int endVertex = currentEdge.end;
            visited[endVertex] = true;

            for (Edge edge : adjacencyList.get(endVertex)) {
                if (!visited[edge.end]) {
                    queue.add(edge);
                }
            }
        }

        System.out.println("Вес минимального остова: " + minimumSpanningTreeWeight);
    }
}

class Edge {
    int start, end, weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}

public class PrimMinimumSpanningTree {
    public static void main(String[] args) {
        int numVertices = 7;
        Graph graph = new Graph(numVertices);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 3, 4);
        graph.addEdge(1, 4, 2);
        graph.addEdge(4, 5, 3);
        graph.addEdge(5, 6, 3);

        graph.minimumSpanningTreePrim();
    }
}
