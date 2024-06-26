package com.problems.graph;

import java.util.*;

/**
 * Java Program to Given a reference of a node in a graph with data and a list of neighbors, create a deep copy of the graph. The graph has the following properties:
 *
 * Undirected: The edges of the graph are bidirectional.
 *
 * Connected: A path will always exist between any two nodes.
 *
 * In a deep copy, a new instance of every node is created with the same data as in the original graph. Any modifications made to the new graph are not reflected in the original graph.
 *
 * For simplicity, we are creating a graph using an adjacency list, where the data of each node is represented by its index in the adjacency list. Each list in the adjacency list describes the set of neighbors of a node in the graph. The index in the adjacency list starts from 1. For example, for [[2, 3], [1, 3], [1, 2]], there are three nodes in the graph:
 *
 * 1st node (data = 1): Neighbors are 2nd node (data = 2) and 3rd node (data = 3).
 * 2nd node (data = 2): Neighbors are 1st node (data = 1) and 3rd node (data = 3).
 * 3rd node (data = 3): Neighbors are 1st node (data = 1) and 2nd node (data = 2).
 *
 *
 * We use depth-first traversal and create a copy of each node while traversing the graph. We use a hash map to store each visited node to avoid getting stuck in cycles. We do not revisit nodes that exist in the hash map. The hash map key is a node in the original graph, and its value is the corresponding node in the cloned graph.
 *
 * In order to solve this problem using the methodology discussed above, we create a recursive function, cloneHelper, that takes two arguments: the current node being cloned and a hash map. The steps of this recursive function are given below:
 *
 * If graph is empty, return NULL. This will also work as a base case for our recursive function.
 * If the current node is not NULL, create a new Node with the same data as the current node, and add the current node as key and its clone as value to the hash map.
 * Iterate through all the neighbors of the current node. For each neighbor, check if the neighbor is already cloned by looking up the neighbor in the hash map:
 * If the neighbor is not cloned yet, recursively call the function with the neighbor as the current node.
 * If the neighbor is already cloned, add the cloned neighbor to the new node’s neighbors.
 * Finally, return the new node.
 * The clone function is the main function that creates a deep copy of the graph. It takes a single argument, which is a reference to the root node of the graph. The function creates an empty hash map to keep track of nodes that have already been cloned. Then it calls the cloneHelper function, passing in the root node and the hash map.
 *
 * Time and Space Complexity
 * Time Complexity: O(V+E)
 * O(V) for visiting each node.
 * O(E) for visiting each edge exactly once.
 *
 * Space Complexity: O(V)
 * O(V) for the HashMap storing the cloned nodes.
 * O(V) for the recursion stack in the worst case.
 */
public class cloneGraph {

    public static void main(String[] args) {
        int[][][] data = {
                {{2, 3}, {1, 3}, {1, 2}},
                {{2, 4}, {1, 3}, {2, 4}, {1, 3}},
                {{2, 5}, {1, 3}, {2, 4}, {3, 5}, {1, 4}},
                {{2}, {1}},
                {{2, 6}, {1, 3}, {2, 4}, {3, 5}, {4, 6}, {1, 5}},
                {{}}
        };

        for (int i = 0; i < data.length; i++) {
            Node node1 = buildGraph(data[i]);
            System.out.println((i + 1) + ".\t Original Graph: ");
            printGraph(node1);
            System.out.println();
            Node clonedRoot = cloneGraph(node1);
            printGraph(node1);
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static Node cloneGraph(Node node) {
        if (node == null) return null;

        // HashMap to store the mapping from original node to cloned node
        Map<Node, Node> map = new HashMap<>();

        // Perform DFS and clone the graph
        return cloneHelper(node, map);
    }

    private static Node cloneHelper(Node node, Map<Node, Node> map) {
        // If node is already cloned, return the clone
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create a clone for the current node
        Node cloneNode = new Node(node.data);
        map.put(node, cloneNode);

        // Iterate through all the neighbors of the current node
        for (Node neighbor : node.neighbors) {
            // Recursively clone the neighbors and add to the clone node's neighbors
            cloneNode.neighbors.add(cloneHelper(neighbor, map));
        }

        return cloneNode;
    }

    // Driver code
    private static void printGraph(Node node) {
        Set<Integer> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (!visited.contains(current.data)) {
                visited.add(current.data);
                System.out.print("Node " + current.data + " neighbors: ");
                for (Node neighbor : current.neighbors) {
                    System.out.print(neighbor.data + " ");
                    queue.add(neighbor);
                }
                System.out.println();
            }
        }
    }

    public static Node buildGraph(int[][] adjList) {
        Map<Integer, Node> nodes = new HashMap<>();
        for (int i = 1; i <= adjList.length; i++) {
            nodes.put(i, new Node(i));
        }
        for (int i = 1; i <= adjList.length; i++) {
            Node node = nodes.get(i);
            for (int neighborVal : adjList[i - 1]) {
                node.neighbors.add(nodes.get(neighborVal));
            }
        }
        return nodes.get(1);
    }
}

class Node {
    public int data;
    public List<Node> neighbors;

    public Node() {
        data = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int data) {
        this.data = data;
        neighbors = new ArrayList<>();
    }

    public Node(int data, ArrayList<Node> neighbors) {
        this.data = data;
        this.neighbors = neighbors;
    }
}
