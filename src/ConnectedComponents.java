import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConnectedComponents {

    // Список смежности для представления графа
    private List<List<Integer>> adjList;
    // Массив для отслеживания посещенных вершин
    private boolean[] visited;
    // Общее количество вершин в графе
    private int numOfVertices;

    public ConnectedComponents(int vertices) {
        System.out.println("Орентированный граф:");
        numOfVertices = vertices;
        adjList = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
        visited = new boolean[vertices];
    }

    // Функция для добавления ребра в граф
    public void addEdge(int v, int w) {
        adjList.get(v).add(w); // Добавляем только одно направление
    }

    // Рекурсивная функция Поиск в глубину для посещения вершин
    private void DFS(int v) { //Depth First Search
        visited[v] = true; // Отмечаем вершину как посещенную
        System.out.print(v + " ");
        // Рекурсивно посещаем все смежные вершины
        for (int i : adjList.get(v)) {
            if (!visited[i]) {
                DFS(i);
            }
        }
    }

    // Функция для определения компонент связности
    public void connectedComponents() {
        for (int i = 0; i < numOfVertices; i++) {
            if (!visited[i]) {
                System.out.print("Компонента связности: ");
                DFS(i);
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int v, w;
        System.out.print("Введите кол-во вершин: ");
        Scanner scanner = new Scanner(System.in);
        int vertices = scanner.nextInt();
        ConnectedComponents graph = new ConnectedComponents(vertices);

        for (int i = 0; i < vertices; i++){
            System.out.print("Введите " + (i) + " вершин: ");
            v = scanner.nextInt();
            System.out.print("Введите " + (i) + " ребро: ");
            w = scanner.nextInt();
            graph.addEdge(v, w);
        }
        graph.connectedComponents();
    }
}
