package com.problems.graph;

import java.util.*;

/**
 * A maze consists of n rooms numbered from 1 to n, and some rooms are connected by corridors. You are given a 2D integer array corridors where corridors[i] = [room1i, room2i] indicates that there is a corridor connecting room1i and room2i, allowing a person in the maze to go from room1i to room2i and vice versa.
 *
 * The designer of the maze wants to know how confusing the maze is. The confusion score of the maze is the number of different cycles of length 3.
 *
 * For example, 1 → 2 → 3 → 1 is a cycle of length 3, but 1 → 2 → 3 → 4 and 1 → 2 → 3 → 2 → 1 are not.
 * Two cycles are considered to be different if one or more of the rooms visited in the first cycle is not in the second cycle.
 *
 * Return the confusion score of the maze.
 */
public class PathInMazeThatLeadToSameRoom {

    public static void main(String[] args) {
        int[] nList = {5, 4, 5, 5, 4};
        int[][][] corridorsList = {
                {{1, 2}, {5, 2}, {4, 1}, {2, 4}, {3, 1}, {3, 4}},
                {{1, 2}, {3, 4}},
                {{1, 2}, {5, 2}, {4, 1}, {3, 1}, {3, 4}},
                {{1, 2}, {5, 2}, {4, 1}, {2, 4}, {3, 1}, {3, 4}, {1, 5}},
                {{1, 2}, {2, 3}, {3, 4}}
        };

        for (int i = 0; i < nList.length; i++) {
            System.out.println((i + 1) + ".\t n: " + nList[i]);
            System.out.println("\t corridors: " + Arrays.deepToString(corridorsList[i]));
            System.out.println("\t cycles: " + confusionScoreApproach2(nList[i], corridorsList[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    /**
     * Naive Approach
     * The naive approach would involve checking every possible combination of three rooms to see if they form a triangle. This can be done using three nested loops:
     *
     * Loop through each node u.
     * For each node u, loop through each of its neighbors v.
     * For each node v, loop through each of its neighbors w.
     * Check if there is an edge from w to u. If there is, then u, v, and w form a triangle.
     *
     * Time Complexity: The time complexity of this approach is O(n*n*n) because of the three nested loops.
     * Space Complexity: The space complexity is O(n+e), where
     * e is the number of edges, to store the adjacency list.
     */
    public static int confusionScoreApproach1(int n, int[][] corridors) {
        // Create adjacency list
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] corridor : corridors) {
            graph.putIfAbsent(corridor[0], new HashSet<>());
            graph.putIfAbsent(corridor[1], new HashSet<>());
            graph.get(corridor[0]).add(corridor[1]);
            graph.get(corridor[1]).add(corridor[0]);
        }

        int count = 0;

        // Iterate through each node u
        for (int u = 1; u <= n; u++) {
            if (!graph.containsKey(u)) continue;

            // Iterate through each neighbor v of u
            for (int v : graph.get(u)) {
                if (v <= u) continue; // Avoid double counting

                // Iterate through each neighbor w of v
                for (int w : graph.get(v)) {
                    if (w <= v || w == u) continue; // Avoid double counting and self loop

                    // Check if w is a neighbor of u
                    if (graph.get(u).contains(w)) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /**
     * the naive approach for finding cycles of length 3 in a given set of rooms is based on DFS traversal. We apply DFS on a room and traverse until we visit its neighbors up to a depth of 3. If the next neighbor is the source room, we have found a cycle of length 3
     * . Otherwise, we have not. Repeat this process for all the other rooms, and count all the cycles of length 3.
     *
     * The time complexity for DFS traversal on a single node is O(V+E)
     * , where V represents the number of vertices and E represents the number of edges in the graph.
     * Since we perform DFS traversal on every node in the graph, the overall time complexity can be expressed as O(V×(V+E)). However, the space complexity of this approach is O(V).
     *
     * Explanation of the Code
     * Adjacency List: We first create an adjacency list to represent the graph.
     * DFS Traversal: We perform DFS from each node, keeping track of the depth of the traversal. If the depth reaches 3, we check if we are back at the start node.
     * Cycle Detection: If a cycle is detected (depth is 3 and we are back at the start node), we increment the cycle count.
     * Avoiding Double Counting: Each cycle is counted multiple times, so we divide the final count by 6 (since each triangle is counted 6 times due to permutations and directions).
     * Complexity Analysis
     * Time Complexity: The time complexity of this approach is O(V×(V+E)), where
     * V is the number of vertices and
     * E is the number of edges.
     * Space Complexity: The space complexity is
     * O(V) due to the recursive call stack and the visited set.
     */
    public static int confusionScoreApproach2(int n, int[][] corridors) {
        // Create adjacency list
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int[] corridor : corridors) {
            graph.putIfAbsent(corridor[0], new HashSet<>());
            graph.putIfAbsent(corridor[1], new HashSet<>());
            graph.get(corridor[0]).add(corridor[1]);
            graph.get(corridor[1]).add(corridor[0]);
        }

        int count = 0;
        // Perform DFS for each node
        for (int i = 1; i <= n; i++) {
            count += dfs(graph, i, i, 0, new HashSet<>());
        }

        // Each cycle is counted 6 times (3 nodes in 2 directions), so divide by 6
        return count / 6;
    }

    private static int dfs(Map<Integer, Set<Integer>> graph, int start, int current, int depth, Set<Integer> visited) {
        if (depth == 3) {
            // If we have reached a depth of 3, check if we are back at the start
            return current == start ? 1 : 0;
        }

        visited.add(current);
        int count = 0;

        for (int neighbor : graph.getOrDefault(current, Collections.emptySet())) {
            if (depth == 2 && neighbor == start) {
                count++;
            } else if (!visited.contains(neighbor)) {
                count += dfs(graph, start, neighbor, depth + 1, visited);
            }
        }

        visited.remove(current);
        return count;
    }


    /**
     * Optimized approach using graph algo
     * We solve this problem using the adjacency matrix. We initialize a map, neighbors, containing all rooms as keys and their neighboring rooms as corresponding values. Since corridors[i]=[room1,room2] represents a corridor showing that the two rooms are connected,
     * we update the adjacency matrix as neighbors[room1] = room2 and neighbors[room2] = room1.
     * While updating neighbors with the corridor [room1,room2], we know that room1 and room2 are directly connected.
     * After updating neighbors with this corridor, we can look for any common room that exists in both neighbors[room1]
     * and neighbors[room2]. This can be done by taking the intersection of neighbors[room1] and neighbors[room2].
     * If we find any common room, say room3, we conclude that room3 is connected to both room1 and room2.
     * Therefore, we come to know that room1, room2, and room3 are connected with each other,forming a cycle of length 3.
     *
     * Let’s look at the following illustration to get a better understanding of the solution:
     * Solution summary
     *
     * Initialize a map, neighbors, to store the neighbor rooms of every room and a variable, cycles, to store the number of cycles.
     *
     * Start iterating over corridors and store the neighbors of room1 and room2 at neighbors[room1] and neighbors[room2], respectively.
     *
     * Take the intersection of neighbors[room1] and neighbors[room2] and add the length of the result to cycles.
     *
     * Repeat this process until we have iterated over all corridors.
     *
     * Time complexity
     * The time complexity of this solution is O(n×m), where n is the number of rooms and m represents the number of corridors.
     *
     * Space complexity
     * The space complexity of this solution is O(n*n)
     * .
     */
    public static int confusionScoreApproach3(int n, int[][] corridors) {
        Map<Integer, Set<Integer>> neighbors = new HashMap<>();
        int cycles = 0;

        for (int[] corridor : corridors) {
            int room1 = corridor[0];
            int room2 = corridor[1];

            neighbors.putIfAbsent(room1, new HashSet<>());
            neighbors.putIfAbsent(room2, new HashSet<>());
            neighbors.get(room1).add(room2);
            neighbors.get(room2).add(room1);

            cycles += intersectionLength(neighbors.get(room1), neighbors.get(room2));
        }

        return cycles;
    }

    private static int intersectionLength(Set<Integer> set1, Set<Integer> set2) {
        int count = 0;
        for (int element : set1) {
            if (set2.contains(element)) {
                count++;
            }
        }
        return count;
    }
}
