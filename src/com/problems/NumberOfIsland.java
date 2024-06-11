package com.problems;


/**
 * The union find pattern is used to group elements into sets based on a specified property. Each set is nonoverlapping, that is, it contains unique elements that are not present in any other set. The pattern uses a disjoint set data structure, such as an array, to keep track of which set each element belongs to.
 * Each set forms a tree data structure and has a representative element that resides at the root of the tree. Every element in this tree maintains a pointer to its parent. The representative’s parent pointer points to itself. If we pick any element in a set and follow its parent pointers, we’ll always reach the set representative.
 * The pattern is composed of two operations performed on the disjoint data structure:
 * find(v): Finds the representative of the set that contains v.
 * union(v1, v2): Merges the sets containing v1 and v2 into one.
 */

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

import java.util.Arrays;
import java.util.List;

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
            System.out.println("\n\t Output: " + numIslands(inputs.get(i)));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static int numIslands(List<List<Character>> grid) {
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

    public static void printGrid(List<List<Character>> grid) {
        for (int i = 0; i < grid.size(); i++) {
            System.out.print("\t\t[");
            for (int j = 0; j < grid.get(i).size() - 1; j++) {
                System.out.print("'" + grid.get(i).get(j) + "', ");
            }
            System.out.println("'" + grid.get(i).get(grid.get(i).size() - 1) + "']");
        }
    }

    public static int numIslandsUsingDFS(List<List<Character>> grid) {
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
        dfs(grid, i-1, j - 1);
        dfs(grid, i+1, j - 1);
        dfs(grid, i-1, j + 1);
        dfs(grid, i+1, j + 1);

        return 1;
    }
}
