import java.util.ArrayList;
import java.util.List;

public class DFS {
    public void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;

        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    //Counting connected components
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, graph, visited);
                count++;
            }
        }

        return count;
    }

    //DFS in grid
    int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

    public void dfs(int r, int c, char[][] grid) {
        int m = grid.length, n = grid[0].length;

        if (r<0 || c<0 || r>=m || c>=n || grid[r][c] != '1')
            return;

        grid[r][c] = '0'; // mark visited

        for (int[] d : dirs)
            dfs(r + d[0], c + d[1], grid);
    }
}
