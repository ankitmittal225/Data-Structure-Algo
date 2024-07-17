package com.problems.unionfind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * We’re given an undirected graph consisting of n nodes. The graph is represented as an array called edges, of length n, where edges[i] = [a, b] indicates that there is an edge between nodes a and b in the graph.
 * Return an edge that can be removed to make the graph a tree of n nodes. If there are multiple candidates for removal, return the edge that occurs last in edges.
 */
public class ReducdantConnection {

    public static void main(String[] args) {
        int[][][] edges = {
                {{1, 2}, {1, 3}, {2, 3}},
                {{1, 2}, {2, 3}, {1, 3}},
                {{1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}},
                {{1, 2}, {1, 3}, {1, 4}, {3, 4}, {2, 4}},
                {{1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}}
        };

        for (int i = 0; i < edges.length; i++) {
            System.out.println((i + 1) + ".\tEdges: " + Arrays.deepToString(edges[i]));
            System.out.println("\n\tThe redundant connection in the graph is: " + Arrays.toString(findRedundantConnectionDFS(edges[i])));
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }

    /**
     * Initialization:
     *
     * Create an adjacency list to represent the graph.
     * Use a boolean array to keep track of visited nodes during the DFS traversal.
     * Use a parent array to track the parent of each node during DFS to avoid revisiting the immediate parent.
     * Building the Graph:
     *
     * Iterate through the edges and populate the adjacency list.
     * Cycle Detection using DFS:
     *
     * For each edge, before adding it to the graph, check if there is already a path between the two nodes (u and v) using DFS.
     * If a path exists, then adding this edge will create a cycle.
     * Remove the last edge that forms the cycle and return it.
     * DFS Function:
     *
     * The DFS function checks if there is a path between two nodes by traversing the graph.
     * It marks nodes as visited and uses the parent array to ensure we don't revisit the immediate parent node.
     *
     * Time Complexity
     * Building the Graph: O(n)
     *
     * DFS Cycle Detection: For each edge, we perform a DFS which takes O(V+E),
     * but since we do this for each edge, the overall complexity for n edges is O(n×(V+E)).
     * Given that in a tree E=V−1, the complexity becomes O(n×(2V−1)), which simplifies to O(n×V).
     *
     * Since V (vertices) is roughly n for the given problem, the overall complexity is approximately
     * O(n*n).
     *
     * Space Complexity
     * Adjacency list:O(n+E)
     * Visited and parent arrays:O(n)
     * Overall, the space complexity is O(n+E), which simplifies to O(n) since E=V−1 in a tree.
     */
    public static int[] findRedundantConnectionDFS(int[][] edges) {
        int n = edges.length;
        List<List<Integer>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            // Reset visited and parent arrays for each edge
            boolean[] visited = new boolean[n + 1];
            int[] parent = new int[n + 1];
            // Check if adding the current edge will form a cycle
            if (dfs(u, v, graph, visited, parent)) {
                return edge;
            }
            // Add the edge to the graph
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        return new int[0]; // This should never be reached
    }

    private static boolean dfs(int current, int target, List<List<Integer>> graph, boolean[] visited, int[] parent) {
        visited[current] = true;
        if (current == target) {
            return true;
        }
        for (int neighbor : graph.get(current)) {
            if (!visited[neighbor]) {
                parent[neighbor] = current;
                if (dfs(neighbor, target, graph, visited, parent)) {
                    return true;
                }
            } else if (neighbor != parent[current]) {
                // If we find a visited node that is not the parent, it means a cycle exists
                return true;
            }
        }
        return false;
    }

    /**
     * Optimized approach using union find
     * We are going to solve this problem with the help of the union find pattern and will be using union by rank and path compression.
     *
     * Union by rank: We connect the nodes that come afterward to the nodes that came before them.
     * This allows us to add new nodes to the subset of the representative node of the larger connected component.
     * Using ranks to connect nodes in this manner helps reduce the depth of the recursion call stack of the find
     * function, since the trees within each disjoint set remain relatively shallow.
     *
     * Path compression: On each find operation on a node of a tree, we update the parent of that node to point directly to the root.
     * This reduces the length of the path of that node to the root, ensuring we don’t have to travel all the intermediate nodes on future find operations.
     *
     * Solution summary
     * To recap, the solution to this problem can be divided into the following two main parts:
     * - Initialize parent and rank arrays based on the length of the edges array.
     * - Traverse the edges array and for each edge, compare the parents of both vertices:
     *      - If the parents are the same, the current edge is redundant, so we return it.
     *      - Otherwise, we connect the two vertices based on their respective ranks.
     *
     * Time complexity
     * The time complexity of this solution is O(n), where n is the number of edges.
     *
     * Explanation:
     * We use a loop to traverse the edges array, resulting in O(n) iterations.
     *
     * The time complexity of the the Union and Find functions is O(α(n))
     * , since both path compression and union find by rank are being used. α(n)
     *  is almost constant time and grows very slowly with the input size, so the time complexity of both these functions can be considered as constant time for practical purposes.
     *
     * Therefore, the overall time complexity becomes O(n×α(n)), which simplifies to O(n).
     *
     * Space Complexity
     * The space complexity of this solution is O(n)
     * .
     */
    public static int[] redundantConnectionUnionFind(int[][] edges) {

        UnionFindReducdant connections = new UnionFindReducdant(edges.length);

        for (int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            if (!connections.union(v1, v2)) {
                return edge;
            }
        }
        return new int[]{};
    }
}

/**
 *
 * Union by rank:
 *
 * We initialize a rank array (set to the length of the edges array) with
 * 1
 * 1
 * s.
 *
 * In the union function, if the parents, p1 and p2 of the vertices are not the same, we make the connection based on the ranks of both parents. This is done in the following way:
 *
 * If p1's rank is greater than p2's rank, we’ll update p2's parent with p1, and add p2's rank to p1's rank. For example, if rank[p1] =5
 *  and rank[p2] =2, we’ll add p2 to p1's set and update its rank to 5+2=7.
 *
 * Similarly, if p2's rank is greater than p1's rank, we’ll update p1's parent with p2, and add p1's rank to p2's rank.
 *
 * Path compression:
 *
 * In the find function, for a node v, we make the found root as the parent of v so that we don’t have to traverse all the intermediate nodes again on further find operations.
 */
class UnionFindReducdant {

    public int[] parent;
    public int[] rank;

    // Constructor
    public UnionFindReducdant(int n) {

        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // Function to find which subset a particular element belongs to
    // Returns FALSE if both vertices have the same parent, otherwise, updates the parent and rank lists by making a connection based on the passed edge
    // Returns TRUE if no cycle exits in the graph
    public int find(int v) {
        if (parent[v] != v) {
            parent[v] = find(parent[v]);
        }
        return parent[v];
    }

    // Function to join two subsets into a single subset
    public boolean union(int v1, int v2) {

        // Find the root parents of v1 and v2
        int p1 = find(v1);
        int p2 = find(v2);

        if (p1 == p2) {
            return false;
        }

        // Updates the parent and rank lists otherwise
        else if (rank[p1] > rank[p2]) {

            parent[p2] = p1;
            rank[p1] += rank[p2];
        }

        else {

            parent[p1] = p2;
            rank[p2] += rank[p1];
        }

        return true;
    }
}

