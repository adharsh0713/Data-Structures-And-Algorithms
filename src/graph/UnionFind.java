package graph;

public class UnionFind {
    int[] parent;
    int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]); // path compression
        return parent[x];
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return false; // cycle

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }

        return true;
    }

    //cycle detection
    public boolean hasCycle(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);

        for (int[] e : edges) {
            if (!uf.union(e[0], e[1]))
                return true; // already connected
        }

        return false;
    }

    //count components
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        int components = n;

        for (int[] e : edges) {
            if (uf.union(e[0], e[1]))
                components--;
        }

        return components;
    }
}