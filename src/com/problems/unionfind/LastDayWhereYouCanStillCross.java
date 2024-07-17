package com.problems.unionfind;

import java.util.Arrays;

/**
 *You are given two integers, rows and cols, which represent the number of rows and columns in a 1-based binary matrix. In this matrix, each 0 represents land, and each 1 represents water.
 *
 * Initially, on day 0, the whole matrix will just be all 0s, that is, all land. With each passing day, one of the cells of this matrix will get flooded and, therefore, will change to water, that is, from 0 to 1
 * . This continues until the entire matrix is flooded. You are given a 1-based array, waterCells, that records which cell will be flooded on each day. Each element waterCells[i]=[ri,ci]
 *  indicates the cell present at the rith row and cith column of the matrix that will change from land to water on the ith day.
 *
 * We can cross any cell of the matrix as long as it’s land. Once it changes to water, we can’t cross it. To cross any cell, we can only move in one of the four cardinal directions. Given the number of rows and columns of a 1-based binary matrix and a 1-based array, waterCells, you are required to find the last day where you can still cross the matrix, from top to bottom, by walking over the land cells only.
 */
public class LastDayWhereYouCanStillCross {

    // driver code
    public static void main(String[] args) {
        int[][][] allWaterCells = {
                {{1, 2}, {2, 1}, {3, 3}, {2, 2}, {1, 1}, {1, 3}, {2, 3}, {3, 2}, {3, 1}},
                {{1, 1}, {2, 1}, {1, 2}, {2, 2}},
                {{1, 1}, {1, 2}, {1, 3}, {2, 1}, {3, 1}, {2, 2}, {3, 2}, {2, 3}, {3, 3}},
                {{1, 5}, {2, 5}, {2, 4}, {2, 3}, {2, 2}, {3, 2}, {4, 2}, {4, 1}, {3, 1}, {2, 1},
                        {1, 1}, {1, 2}, {1, 3}, {1, 4}, {3, 3}, {3, 5}, {3, 4}, {4, 5}, {4, 3}, {4, 4}},
                {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7}, {2, 5}, {2, 6}, {2, 7}, {3, 1},
                        {3, 2}, {3, 3}, {3, 7}, {4, 7}, {4, 5}, {4, 4}, {4, 3}, {4, 2}, {4, 1}, {5, 1}, {5, 2}, {5, 3}, {5, 4},
                        {5, 5}, {5, 7}, {6, 7}, {7, 7}, {7, 6}, {7, 5}, {7, 4}, {7, 3}, {7, 2}, {7, 1}, {6, 1}, {6, 2}, {6, 3},
                        {6, 4}, {6, 5}, {6, 6}, {5, 6}, {4, 6}, {3, 6}, {3, 5}, {3, 4}, {2, 4}, {2, 3}, {2, 2}, {2, 1}, {1, 1}},
                {{3, 2}, {1, 1}, {1, 2}, {3, 3}, {2, 3}, {1, 3}, {2, 1}, {2, 2}, {3, 1}}
        };

        int[] allRows = {3, 2, 3, 4, 7, 3};
        int[] allCols = {3, 2, 3, 5, 7, 3};

        for (int i = 0; i < allWaterCells.length; i++) {
            System.out.println(i + 1 + ". \tNumber of rows: " + allRows[i]);
            System.out.println("\tNumber of columns: " + allCols[i]);
            System.out.println("\n\tCells to be flooded: " + Arrays.deepToString(allWaterCells[i]));
            int lastDay = lastDayToCross(allRows[i], allCols[i], allWaterCells[i]);

            System.out.println("\n\tLast day where you can still cross: " + lastDay);
//            System.out.println(PrintHyphens.repeat("-", 100));
        }
    }

    private static int lastDayToCross(int rows, int cols, int[][] waterCells) {
        // create a variable to keep track of the number of days
        int day = 0;

        // create the matrix that needs to be crossed
        int[][] matrix = new int[rows][cols];

        // create the two virtual nodes, one before the first column and the other after the last column of the matrix
        int leftNode = 0;
        int rightNode = rows * cols + 1;

        // specify the directions where water can move
        int[][] waterDirections = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

        // convert the waterCells from 1-based indexing  to 0-based indexing array for the convenience like [3,2] 3rd row 2nd column will be [2,1] considering 0 row as 1st.
        int[][] convertedWaterCells = new int[waterCells.length][2];
        for (int i = 0; i < waterCells.length; i++) {
            convertedWaterCells[i] = new int[]{waterCells[i][0] - 1, waterCells[i][1] - 1};
        }

        // initialize the UnionFind object, this will create the disjoint set union datastructure, an array - reps
        UnionFindWaterCross uf = new UnionFindWaterCross(rows * cols + 2);

        // On each day, one cell of the matrix will get flooded
        for (int[] cell : convertedWaterCells) {
            int row = cell[0];
            int col = cell[1];

            // change the matrix's cell from land (0) to water (1)
            matrix[row][col] = 1;

            // check if the recently flooded cell connects with any of the existing water cells
            for (int[] direction : waterDirections) {
                int newRow = row + direction[0];
                int newCol = col + direction[1];

                if (withinBounds(newRow, newCol, rows, cols) && matrix[newRow][newCol] == 1) {
                    uf.union(findIndex(row, col, cols), findIndex(newRow, newCol, cols));
                }
            }

            if (col == 0) {
                uf.union(findIndex(row, col, cols), leftNode);
            }
            if (col == cols - 1) {
                uf.union(findIndex(row, col, cols), rightNode);
            }

            // check if we got a series of connected water cells from the left to the right side of the matrix
            if (uf.find(leftNode) == uf.find(rightNode)) {
                break;
            }
            day++;
        }
        return day;
    }

    // maps the index of the element in 2-D matrix to an index of the 1-D array (reps)
    public static int findIndex(int currentRow, int currentCol, int cols) {
        return currentRow * cols + (currentCol + 1);
    }


    // checks whether the water cells to be connected are within the bounds of the matrix as per given dimensions
    public static boolean withinBounds(int row, int col, int rows, int cols) {
        return col >= 0 && col < cols && row >= 0 && row < rows;
    }
}
class UnionFindWaterCross {
    private int[] reps;

    public UnionFindWaterCross(int n) {
        reps = new int[n];

        for (int i = 0; i < n; i++) {
            reps[i] = i;
        }
    }

    public int find(int x) {
        if (reps[x] != x) {
            reps[x] = find(reps[x]);
        }
        return reps[x];
    }

    public void union(int v1, int v2) {
        reps[find(v1)] = find(v2);
    }
}

/**
 * Time complexity
 * The time complexity of this solution is O((m⋅n)⋅α(m⋅n))
 * , where m represents the number of rows in the matrix, n
 *  represents the number of columns in the matrix, and α
 *  is the inverse Ackermann function that grows very slowly and whose maximum value for all practical purposes is 4.
 *
 * The initialization of the UnionFind object takes O(m⋅n)
 *  time, since it involves creating an array of size (m⋅n)+2
 *  and initializing every element to a unique index.
 *
 * There may be, in the worst case,O(m⋅n)
 *  entries in waterCells. For each entry, there can be a maximum of eight union operations to merge the flooded cell with adjacent water cells, and each union operation takes
 * O(α(m⋅n)) time on average.
 *
 * Therefore, the overall time complexity of this solution is O((m⋅n)⋅α(m⋅n)).
 *
 * Space complexity
 * The space complexity of the solution above is O(m⋅n).
 */

/**
 * Alternative solutions
 * Now, let’s see what are some other ways to solve this problem.
 *
 * Union find on land cells:
 *
 * This is similar to the solution presented above. Here, we start at the ending state of matrix with all the cells flooded. Then, moving from the end of the waterCells array towards its start, we start rolling back the flooding (one cell at a time), that is, changing that cell’s value from 1
 *  to 0. After changing a cell from water to land, we check the land cells adjacent to it in all four cardinal directions, and if any are found, we connect them to the recently reverted cell. To check whether we got a single connected component of land cells from top to bottom of matrix, we keep two virtual nodes—one at the top and the other at the bottom. The moment we find these virtual nodes connected to each other, we’ll return this day as the output.
 *
 * The time and space complexity of this solution is the same as the solution above.
 *
 * 2......
 * Breadth-first search (BFS) with binary search:
 *
 * In this solution, for each new flooded cell on the ith  day, we use BFS to figure out whether we have a path of land cells from top to bottom to cross matrix.
 * There is a total of (m⋅n) cells that needs to be flooded as per waterCells, and finding a path from the top to the bottom row for each of them would be highly inefficient.
 * Therefore, we use binary search to reduce this number. Our binary search method is guided by these two observations:
 *
 * If we can cross on day i, we can cross on any day before that day.
 * If we can’t cross on day i, we cannot cross on any day after that day.
 * For example, if there are 16 cells in waterCells, then, instead of searching for paths on each of the 16
 *  days, we start from the middle cell, the 8th cell. We flood all the cells mentioned in waterCells up to the
 *  8th day and then check if a path to cross matrix exists. If it does, then we flood all the cells mentioned up to
 *  the 12th day, and check again. If no path exists, we roll back the flooding to the 10 th day and check again, and so on.
 *
 * To find a continuous path from top to bottom to cross matrix, we start the BFS from the top row of matrix and insert all its land cells into a queue. Then, we start to dequeue the elements in the queue one by one until it’s empty. Whenever we dequeue a cell, we check all of its adjacent land cells, and if we have not visited them yet, we add them to the queue. After checking all of its adjacent land cells, we mark the cell as visited and process the next element in the queue. We repeat this until we reach a cell that is in the bottom row of matrix. At this point, we have found a path from the top row to the bottom row. Another possible terminating condition for the BFS is that we are unable to find a path from any of the cells in the top row that reaches a cell in the bottom row.
 *
 * Overall, the binary search takes log(m⋅n), and the BFS takes O(m⋅n)
 *  time, so the time complexity of this solution is O((m⋅n)⋅log(m⋅n)).
 * The space complexity of this solution is O(m⋅n).
 *
 * Depth-first search (DFS) with binary search:
 *
 * This solution is similar to the one we just discussed, except it uses DFS instead of BFS. On each day, for each cell in the top row of matrix, we explore its unvisited neighboring land cells recursively. We continue doing this until we reach the bottom row, or until all reachable land cells in matrix have been visited. To avoid revisiting the same cell, we just mark the cell as visited.
 *
 * The time and space complexity of this solution is the same as that of the BFS with binary search.
 */






