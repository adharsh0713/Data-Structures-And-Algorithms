import java.util.*;

public class TopologicalSort {
    //Kahn
    public int[] topoSortUsingKahn(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        int[] indegree = new int[n];

        for (int[] e : edges) {
            graph.get(e[1]).add(e[0]); // prerequisite -> course
            indegree[e[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                queue.offer(i);

        int[] order = new int[n];
        int index = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            order[index++] = node;

            for (int neighbor : graph.get(node)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }

        if (index != n) return new int[0]; // cycle

        return order;
    }

    //Dfs
    public void dfs(int node,
                    List<List<Integer>> graph,
                    boolean[] visited,
                    Stack<Integer> stack) {

        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor])
                dfs(neighbor, graph, visited, stack);
        }

        stack.push(node);
    }

    public int[] topoSortUsingDfs(int n, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] e : edges)
            graph.get(e[1]).add(e[0]);

        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++)
            if (!visited[i])
                dfs(i, graph, visited, stack);

        int[] order = new int[n];
        int i = 0;

        while (!stack.isEmpty())
            order[i++] = stack.pop();

        return order;
    }
}
