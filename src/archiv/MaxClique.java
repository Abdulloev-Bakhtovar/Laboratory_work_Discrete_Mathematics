package archiv;

import java.util.ArrayList;
import java.util.List;

public class MaxClique {
    private int[][] graph;              // Матрица смежности графа
    private int maxCliqueSize;          // Размер максимальной клики
    private List<Integer> maxClique;    // Максимальная клика

    public MaxClique(int[][] graph) {
        this.graph = graph;
        this.maxCliqueSize = 0;
        this.maxClique = new ArrayList<>();
    }

    public List<Integer> findMaximumClique() {
        List<Integer> candidates = new ArrayList<>();
        List<Integer> currentClique = new ArrayList<>();

        // Инициализация списка кандидатов для поиска максимальной клики
        for (int i = 0; i < graph.length; i++) {
            candidates.add(i);
        }

        // Начать поиск максимальной клики рекурсивно
        findMaximumCliqueRecursive(candidates, currentClique);

        return maxClique;
    }

    private void findMaximumCliqueRecursive(List<Integer> candidates, List<Integer> currentClique) {
        if (candidates.isEmpty()) {
            // Если список кандидатов пуст, проверить, является ли текущая клика максимальной
            if (currentClique.size() > maxCliqueSize) {
                maxCliqueSize = currentClique.size();
                maxClique = new ArrayList<>(currentClique); // Сохранить текущую клику как максимальную
            }
            return;
        }

        while (!candidates.isEmpty()) {
            int vertex = candidates.get(0);
            List<Integer> newCandidates = new ArrayList<>();

            // Выбрать вершины-кандидаты, смежные с текущей вершиной
            for (int candidate : candidates) {
                if (graph[vertex][candidate] == 1) {
                    newCandidates.add(candidate);
                }
            }

            // Добавить вершину в текущую клику и продолжить поиск
            currentClique.add(vertex);
            findMaximumCliqueRecursive(newCandidates, currentClique);

            // Убрать вершину из текущей клики для перебора других возможных клик
            currentClique.remove(currentClique.size() - 1);
            candidates.remove(0);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 1, 0,0,0},
                {0, 0, 0, 1,1,1},
                {0, 0, 0, 0,1,1},
                {0, 0, 0, 0,1,1},
                {0, 0, 0, 0,0,1},
                {0, 0, 0, 0,0,0}
        };

        archiv.MaxClique maxCliqueFinder = new archiv.MaxClique(graph);
        List<Integer> maxClique = maxCliqueFinder.findMaximumClique();
        System.out.println("Максимальная клика: " + maxClique);
    }
}
