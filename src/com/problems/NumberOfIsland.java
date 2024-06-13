package com.problems;

import java.util.*;


/**
 * Problem statement :
 * Build a 2-dimensional terrain with some land and water parts.
 *
 * Part :1 We need a data-structure to efficiently represent in-memory. We need the following two functions to be efficient:
 * isLand(x, y): returns true fi a point is a land addLand(x, y): sets a point to land
 * Assumptions
 * • Assume everything is water at the beginning.
 * • Only 2 types of pointspoint can exist (land or water).
 * Solution Expectation
 * • Expect 2-D Array as a solution.
 */

/**
 * Part2:Extend the data-structure to add a method for countIslands() which returns the number of unique connected components of lands.
 *
 * #
 * Solution Expectation||
 * • 0(n*n): Brute force solution (DFS/Flood-fill on 2 D arrays).#
 *      • Follow up 1: If the candidate proposed a Set-based data-structures, follow-up on how the DFS works on
 *         the Set (as opposed t o the traditional matrix).1
 *      • Follow-up 2: Analyze space-time complexities on both Array/Set based solutions.$
 * • O(no. of lands) DFS on a sparse-matrix (HashSet of land points).$
 *      • Similar to brute-force, but optimizes the DFS on time by not storing water points at al. $
 * • 0 ( 1 ) Disjoint s e t s f
 *      • Instead of calculating the islands every time, the expectation is to understand the dynamic nature of the
 *      data-structure ot incrementally calculate (and save) the island Count on addLand(x, y). Model the structure as a union-find, where each point on an island belongs to a single set, to leverage addLand(x, y) to incrementally maintain the islandcount."
 *      • Follow-up 1: Represent a union-find structure o n a 2-D point (opposed t o the traditional 1 D array).$
 *          • Bonus points i f the candidate is able to reuse their sparse-matrix-based solution and convert it-
 *          into a Map<Point, Parent> to maintain this structure.
 *      • Follow-up 2: How are the number of islands computed efficiently (in 0(1)), rather than finding the unique-
 */
public class NumberOfIsland {


    public static void main(String[] args) {
        // Example grid
        // Test case 01
        List<List<Character>> grid1 = Arrays.asList(
                Arrays.asList('1', '1', '1'),
                Arrays.asList('0', '1', '0'),
                Arrays.asList('1', '0', '0'),
                Arrays.asList('1', '0', '1')
        );

        // Test case 02
        List<List<Character>> grid2 = Arrays.asList(
                Arrays.asList('1', '1', '1', '1', '0'),
                Arrays.asList('1', '0', '0', '0', '1'),
                Arrays.asList('1', '0', '0', '1', '1'),
                Arrays.asList('0', '1', '0', '1', '0'),
                Arrays.asList('1', '1', '0', '1', '1')
        );

        // Test case 03
        List<List<Character>> grid3 = Arrays.asList(
                Arrays.asList('1', '1', '1', '1', '0'),
                Arrays.asList('1', '0', '0', '0', '1'),
                Arrays.asList('1', '1', '1', '1', '1'),
                Arrays.asList('0', '1', '0', '1', '0'),
                Arrays.asList('1', '1', '0', '1', '1')
        );

        // Test case 04
        List<List<Character>> grid4 = Arrays.asList(
                Arrays.asList('1', '0', '1', '0', '1'),
                Arrays.asList('0', '1', '0', '1', '0'),
                Arrays.asList('1', '0', '1', '0', '1'),
                Arrays.asList('0', '1', '0', '1', '0'),
                Arrays.asList('1', '0', '1', '0', '1')
        );

        // Test case 05
        List<List<Character>> grid5 = Arrays.asList(
                Arrays.asList('1', '0', '1'),
                Arrays.asList('0', '0', '0'),
                Arrays.asList('1', '0', '1')
        );

        List<List<List<Character>>> inputs = Arrays.asList(grid1, grid2, grid3, grid4, grid5);

        for (int i = 0; i < inputs.size(); i++) {
            System.out.println((i + 1) + ".\t Grid: ");
            printGrid(inputs.get(i));
            System.out.println("\n\t Output: " + numIslandsDisjointSet2Way1(inputs.get(i)));
            System.out.println("\n\t Output: " + numIslandsUsingDisjointSet2Way2(inputs.get(i)));
            System.out.println("\n\t Output: " + numIslandsUsingDFS1(inputs.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int numIslandsUsingDFS1(List<List<Character>> grid) {
        // If the grid is empty, then return 0
        if (grid.isEmpty())
            return 0;

        // Get the number of rows and columns in the grid
        int cols = grid.get(0).size();
        int rows = grid.size();
        int count=0;

        // Iterate over each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // If the current cell is a land, then mark it as visited
                if (grid.get(r).get(c) == '1') {
                    // Check the horizontal and vertical neighbors of the current cell
                    // If any of the neighbors are also lands, then unite the current cell with the neighbor
                    count=count+dfs(grid, r,c);
                }
            }
        }

        return count;
    }

    private static int dfs(List<List<Character>> grid, int i, int j) {
        int m = grid.size();
        int n = grid.get(0).size();

        if (i < 0 || j < 0 || i >= m || j >= n || grid.get(i).get(j) == '0') {
            return 0;
        }

        grid.get(i).set(j,'0'); // Mark as visited

        dfs(grid, i - 1, j); // Up
        dfs(grid, i + 1, j); // Down
        dfs(grid, i, j - 1); // Left
        dfs(grid, i, j + 1); // Right
        //in case diagonal allowed
//        dfs(grid, i-1, j - 1);
//        dfs(grid, i+1, j - 1);
//        dfs(grid, i-1, j + 1);
//        dfs(grid, i+1, j + 1);

        return 1;
    }

    public static int numIslandsDisjointSet2Way1(List<List<Character>> grid) {
        // If the grid is empty, then return 0
        if (grid.isEmpty())
            return 0;

        // Get the number of rows and columns in the grid
        int cols = grid.get(0).size();
        int rows = grid.size();

        // Create a Union Find object to represent the islands in the grid
        UnionFind unionFind = new UnionFind(grid);

        // Iterate over each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // If the current cell is a land, then mark it as visited
                if (grid.get(r).get(c) == '1') {
                    // Check the horizontal and vertical neighbors of the current cell
                    // If any of the neighbors are also lands, then unite the current cell with the neighbor
                    //forward moving approach in which will consider bottom one and right one
                    if (r + 1 < rows && grid.get(r + 1).get(c) == '1')
                        unionFind.union(r * cols + c, (r + 1) * cols + c);
                    if (c + 1 < cols && grid.get(r).get(c + 1) == '1')
                        unionFind.union(r * cols + c, r * cols + c + 1);
                }
            }
        }
        // Return the number of islands in the grid
        int count = unionFind.getCount();

        return count;
    }

    public static int numIslandsUsingDisjointSet2Way2(List<List<Character>> grid) {
        // If the grid is empty, then return 0
        if (grid.isEmpty())
            return 0;

        // Get the number of rows and columns in the grid
        int cols = grid.get(0).size();
        int rows = grid.size();

        // Create a Union Find object to represent the islands in the grid
        UnionFind unionFind = new UnionFind(rows*cols);
        int islandCount=0;
        // Iterate over each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                // If the current cell is a land, then mark it as visited
                if (grid.get(r).get(c) == '1') {
                    unionFind.incrementCount();
                    // Check the horizontal and vertical neighbors of the current cell
                    // If any of the neighbors are also lands, then unite the current cell with the neighbor


                    if (r+1 < rows && grid.get(r+1).get(c)=='1')
                        unionFind.union(r*(cols)+c, (r+1)*(cols)+c);
                    if (r-1 >= 0 && grid.get(r-1).get(c)=='1')
                        unionFind.union(r*(cols)+c, (r-1)*(cols)+c);
                    if (c+1 < cols && grid.get(r).get(c+1)=='1')
                        unionFind.union(r*(cols)+c, (r)*(cols)+c+1);
                    if (c-1 >= 0 && grid.get(r).get(c-1)=='1')
                        unionFind.union(r*(cols)+c, (r)*(cols)+c-1);
                    //in case diagonal allowed
//                    if (r+1<rows && c+1<cols && grid.get(r+1).get(c+1)==1)
//                        unionFind.union(r*(cols)+c, (r+1)*(cols)+c+1);
//                    if (r+1<rows && c-1>=0 && grid.get(r+1).get(c-1)==1)
//                        unionFind.union(r*cols+c, (r+1)*(cols)+c-1);
//                    if (r-1>=0 && c+1<cols && grid.get(r-1).get(c+1)==1)
//                        unionFind.union(r*cols+c, (r-1)*cols+c+1);
//                    if (r-1>=0 && c-1>=0 && grid.get(r-1).get(c-1)==1)
//                        unionFind.union(r*cols+c, (r-1)*cols+c-1);
                }
            }
        }
        int count=unionFind.getCount();
        return count;
    }



    public static void printGrid(List<List<Character>> grid) {
        for (int i = 0; i < grid.size(); i++) {
            System.out.print("\t\t[");
            for (int j = 0; j < grid.get(i).size() - 1; j++) {
                System.out.print("'" + grid.get(i).get(j) + "', ");
            }
            System.out.println("'" + grid.get(i).get(grid.get(i).size() - 1) + "']");
        }
    }
}

/**
 * The union find pattern is used to group elements into sets based on a specified property. Each set is nonoverlapping, that is, it contains unique elements that are not present in any other set. The pattern uses a disjoint set data structure, such as an array, to keep track of which set each element belongs to.
 * Each set forms a tree data structure and has a representative element that resides at the root of the tree. Every element in this tree maintains a pointer to its parent. The representative’s parent pointer points to itself. If we pick any element in a set and follow its parent pointers, we’ll always reach the set representative.
 * The pattern is composed of two operations performed on the disjoint data structure:
 * find(v): Finds the representative of the set that contains v.
 * union(v1, v2): Merges the sets containing v1 and v2 into one.
 */
class UnionFind {
    // Initializing the parent list and count variable by traversing the grid
    private List<Integer> parent;
    private List<Integer> rank;
    private int count;

    public UnionFind(List<List<Character>> grid) {
        int m = grid.size();
        int n = grid.get(0).size();
        this.parent = new ArrayList<Integer>();
        this.rank = new ArrayList<Integer>();
        this.count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == '1') {
                    parent.add(i * n + j);
                    this.count += 1;
                } else {
                    this.parent.add(-1);
                }
                this.rank.add(1);
            }
        }
    }

    // Function to connect components
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 != p2) {
            // The absolute value of the root node represents the size of this union
            // Make the one with larger size be the new parent
            if (this.rank.get(p1) > this.rank.get(p2)) {
                this.parent.set(p2, p1);
            } else if (this.rank.get(p1) < this.rank.get(p2)) {
                this.parent.set(p1, p2);
            } else {
                this.parent.set(p2, p1);
                this.rank.set(p1, this.rank.get(p1) + 1);
            }
            count--;
        }
    }

    // Function to find the root parent of a node
    public int find(int v) {
        if (this.parent.get(v) != v) {
            this.parent.set(v, this.find(this.parent.get(v)));
        }
        return this.parent.get(v);
    }

    public List<Integer> getParent() {
        return this.parent;
    }




    public UnionFind(int n) {
        this.rank = new ArrayList<>(n);
        this.parent = new ArrayList<>(n);
        this.count = 0;
        for (int i = 0; i < n; i++) {
            parent.add(i, i);
            rank.add(i, 0);
        }
    }

    public void incrementCount() {
        this.count++;
    }

    // Function to return the number of connected components consisting of 1s
    public int getCount() {
        return this.count;
    }

    private int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int addLand(int x, int y,int r,int c) {
        int index = x * c + y;
        if (parent.get(index) != index) {  // Already land
            return count;
        }

        parent.set(index,index);
        rank.set(index,0);
        incrementCount();

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            if (newX >= 0 && newX < r && newY >= 0 && newY < c && parent.get(newX * r + newY) != newX * r + newY) {
                union(index, newX * c + newY);
            }
        }

        return getCount();
    }
}

