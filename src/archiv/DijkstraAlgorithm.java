package archiv;

import java.util.*;

public class DijkstraAlgorithm {
    private static final int INF = Integer.MAX_VALUE; // Бесконечность для представления несуществующих путей

    public static void dijkstra(int[][] graph, int startVertex) {
        int numVertices = graph.length; // Кол вершин в графе
        int[] shortestDistances = new int[numVertices]; // Массив для хранения кратчайших пут
        boolean[] visited = new boolean[numVertices]; // Массив для отслеживания посещенных вершин

        Arrays.fill(shortestDistances, INF); // Изначально заполним всех вершин как бесконечность
        shortestDistances[startVertex] = 0; // Расстояние от начальной вершины до себя  0

        for (int i = 0; i < numVertices - 1; i++) {
            int minDistanceVertex = findMinDistanceVertex(shortestDistances, visited); // Находим вершину с наименьшим расстоянием
            visited[minDistanceVertex] = true; // Помечаем вершину как посещенную

            for (int j = 0; j < numVertices; j++) {
                // Обновляем кратчайшие расстояния для смежных вершин, если нашли более короткий путь
                if (!visited[j] && graph[minDistanceVertex][j] != 0
                        && shortestDistances[minDistanceVertex] != INF
                        && shortestDistances[minDistanceVertex] + graph[minDistanceVertex][j] < shortestDistances[j]) {
                    shortestDistances[j] = shortestDistances[minDistanceVertex] + graph[minDistanceVertex][j];
                }
            }
        }

        printSolution(shortestDistances);
    }

    private static int findMinDistanceVertex(int[] distances, boolean[] visited) {
        int minDistance = INF;
        int minDistanceVertex = -1;

        for (int i = 0; i < distances.length; i++) {
            if (!visited[i] && distances[i] < minDistance) {
                minDistance = distances[i];
                minDistanceVertex = i;
            }
        }

        return minDistanceVertex; // вершину с наименьшим расстояния
    }

    private static void printSolution(int[] distances) {
        System.out.println("Вершина \t Расстояние от начальной вершины");
        for (int i = 0; i < distances.length; i++) {
            System.out.println(i + " \t\t\t\t\t\t " + distances[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0,10,3,0},
                {10,0,4,0},
                {3,4,0,11},
                {0,0,11,0}
        };

        dijkstra(graph, 0);
    }
}
