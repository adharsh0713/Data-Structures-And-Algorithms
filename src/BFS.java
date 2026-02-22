import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class BFS {
    //Unweighted Graph
    public void bfs(int start, List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    //Shortest Path
    public int shortestPath(int start, int target, List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;

        int steps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int node = queue.remove();

                if (node == target) return steps;

                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
            steps++;
        }

        return -1;
    }

    public int bfsGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{0,0});
        visited[0][0] = true;

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0], c = curr[1];

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr>=0 && nc>=0 && nr<m && nc<n && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr,nc});
                }
            }
        }

        return 0;
    }
}
