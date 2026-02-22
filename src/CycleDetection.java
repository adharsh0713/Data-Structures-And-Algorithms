import java.util.List;

public class CycleDetection {
    //Undirected graph
    public boolean hasCycle(int node, int parent, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {

            if (!visited[neighbor]) {
                if (hasCycle(neighbor, node, graph, visited))
                    return true;
            }
            else if (neighbor != parent) {
                return true; // cycle detected
            }
        }
        return false;
    }

    //Directed Graph
    public boolean hasCycle(int node, List<List<Integer>> graph, int[] state) {

        if (state[node] == 1) return true;  // back edge
        if (state[node] == 2) return false;

        state[node] = 1; // visiting

        for (int neighbor : graph.get(node)) {
            if (hasCycle(neighbor, graph, state))
                return true;
        }

        state[node] = 2; // done
        return false;
    }
}
