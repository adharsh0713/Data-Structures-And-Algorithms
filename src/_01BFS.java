import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class _01BFS {
    // Standard 0–1 BFS (Adjacency List)
    public int[] zeroOneBFS(int n, List<List<int[]>> graph, int src) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerFirst(src);

        while (!deque.isEmpty()) {
            int node = deque.pollFirst();

            for (int[] edge : graph.get(node)) {
                int neighbor = edge[0];
                int weight = edge[1];

                if (dist[node] + weight < dist[neighbor]) {
                    dist[neighbor] = dist[node] + weight;

                    if (weight == 0)
                        deque.offerFirst(neighbor);
                    else
                        deque.offerLast(neighbor);
                }
            }
        }

        return dist;
    }

    // Grid 0–1 BFS
    public int minCost(int[][] grid) {

        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];

        for (int i = 0; i < m; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);

        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerFirst(new int[]{0, 0});
        dist[0][0] = 0;

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0], c = curr[1];

            for (int i = 0; i < 4; i++) {
                int nr = r + dirs[i][0];
                int nc = c + dirs[i][1];

                if (nr>=0 && nc>=0 && nr<m && nc<n) {
                    int weight = (grid[r][c] == i+1) ? 0 : 1;

                    if (dist[r][c] + weight < dist[nr][nc]) {
                        dist[nr][nc] = dist[r][c] + weight;

                        if (weight == 0)
                            deque.offerFirst(new int[]{nr,nc});
                        else
                            deque.offerLast(new int[]{nr,nc});
                    }
                }
            }
        }

        return dist[m-1][n-1];
    }
}
