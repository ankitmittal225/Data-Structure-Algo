package com.problems;

public class Terrain {
    private int[][] grid;
    private int rows;
    private int cols;
    private DSU dsu;
    private int islandCount;

    public Terrain(int m, int n) {
        grid = new int[m][n];
        rows = m;
        cols = n;
        dsu = new DSU(m * n);
        islandCount = 0;
    }

    public boolean isLand(int x, int y) {
        return grid[x][y] == 1;
    }

    public void addLand(int x, int y) {
        if (grid[x][y] == 1) return; // Already land

        grid[x][y] = 1;
        islandCount++;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int currentIndex = x * cols + y;

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 1) {
                int newIndex = newX * cols + newY;
                if (dsu.find(currentIndex) != dsu.find(newIndex)) {
                    dsu.union(currentIndex, newIndex);
                    islandCount--;
                }
            }
        }
    }

    public int countIslands() {
        return islandCount;
    }

    // Union-Find (Disjoint Set Union) class
    class DSU {
        private int[] parent;
        private int[] rank;

        public DSU(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Terrain terrain = new Terrain(4, 5);

        terrain.addLand(0, 0);
        terrain.addLand(0, 1);
        terrain.addLand(1, 0);
        System.out.println(terrain.countIslands()); // Output: 1

        terrain.addLand(2, 2);
        System.out.println(terrain.countIslands()); // Output: 2

        terrain.addLand(2, 3);
        System.out.println(terrain.countIslands()); // Output: 2

        terrain.addLand(2, 4);
        System.out.println(terrain.countIslands()); // Output: 2

        terrain.addLand(3, 3);
        terrain.addLand(3, 4);
        System.out.println(terrain.countIslands()); // Output: 2
    }
}
