import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ShortestPath {

    static class Pair {
        int node;
        int dist;

        Pair(int node, int dist) {
            this.node = node;
            this.dist = dist;
        }
    }

    static class Cell {
        int row, col, dist;

        Cell(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    // Dijkstra for Graph
    public int[] dijkstra(int n, List<List<Pair>> graph, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> a.dist - b.dist);

        pq.offer(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            int node = curr.node;
            int d = curr.dist;

            if (d > dist[node]) continue;

            for (Pair neighbor : graph.get(node)) {
                int next = neighbor.node;
                int weight = neighbor.dist;

                if (dist[node] + weight < dist[next]) {
                    dist[next] = dist[node] + weight;
                    pq.offer(new Pair(next, dist[next]));
                }
            }
        }

        return dist;
    }

    // Dijkstra for Grid
    public int dijkstraGrid(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dist = new int[rows][cols];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);

        PriorityQueue<Cell> pq =
                new PriorityQueue<>((a, b) -> a.dist - b.dist);

        dist[0][0] = grid[0][0];
        pq.offer(new Cell(0, 0, grid[0][0]));

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!pq.isEmpty()) {
            Cell curr = pq.poll();
            int r = curr.row;
            int c = curr.col;
            int d = curr.dist;

            if (r == rows - 1 && c == cols - 1)
                return d;

            if (d > dist[r][c]) continue;

            for (int i = 0; i < 4; i++) {
                int newR = r + dr[i];
                int newC = c + dc[i];

                if (newR >= 0 && newR < rows &&
                        newC >= 0 && newC < cols) {

                    int newDist = d + grid[newR][newC];

                    if (newDist < dist[newR][newC]) {
                        dist[newR][newC] = newDist;
                        pq.offer(new Cell(newR, newC, newDist));
                    }
                }
            }
        }

        return -1; // no path
    }

    // Bellman-Ford
    public int[] bellmanFord(int n, int[][] edges, int src) {

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];

                if (dist[u] != Integer.MAX_VALUE &&
                        dist[u] + w < dist[v]) {

                    dist[v] = dist[u] + w;
                }
            }
        }

        return dist;
    }
}