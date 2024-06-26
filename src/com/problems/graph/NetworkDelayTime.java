package com.problems.graph;

import java.util.*;

/**
 *You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.
 *
 * We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.
 */
public class NetworkDelayTime {

    public static void main(String[] args) {
        int[][][] times = {
                { {2, 1, 1}, {3, 2, 1}, {3, 4, 2} },
                { {2, 1, 1}, {1, 3, 1}, {3, 4, 2}, {5, 4, 2} },
                { {1, 2, 1}, {2, 3, 1}, {3, 4, 1} },
                { {1, 2, 1}, {2, 3, 1}, {3, 5, 2} },
                { {1, 2, 2} }
        };

        int[] n = {4, 5, 4, 5, 2};
        int[] k = {3, 1, 1, 1, 2};

        for (int i = 0; i < times.length; i++) {
            System.out.println((i + 1) + ".\t times = " + Arrays.deepToString(times[i]));
            System.out.println("\t number of nodes 'n' = " + n[i]);
            System.out.println("\t starting node 'k' = " + k[i] + "\n");
            System.out.println("\t Minimum amount of time required = " + networkDelayTimeApproach2(times[i], n[i], k[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    /**
     * Solution1: DFS looking for longest time among shortest path to each node from K
     * Based on the requirement of the question, we are actually looking for the maximum time among all the minimum times from K to each of the other nodes. Based on the abbstraction, we can get the following solution:
     *
     * First compute the shortest path from K to each of the other nodes.
     * In this process we have to check each possible path exhaustively since there could be paths from K to V, say, K->V and K->U->V where K->u->V is shorter than K->V. And thus during the computing process we should check every possibilities and only store the shortest one.
     * From all the shortest path from K to other nodes, return the max one which is the last node that receives the signal sent from K. Were there be a node whose shortest path is Integer.MAX_VALUE, indicating the node is not connected with K, we should return -1 as it is impossible for all nodes to get the signal.
     * In order to make the algorithm runs quicker and make sure it ends even there is a loop in the graph, we sort the adjacency list of the graph and start computing greedily from short edge to long edge, so that we always get the shortest path first, if possible.
     *
     * Time complexity: O(N^N + N*NlogN) in the worst case. Since we can possibly visit each node up to N-1 times and use NlogN time to sort each column of the adjacency list.
     *
     * Space complexity: O(N + E). O(E) for the graph and O(N) for the recursion stack.
     */
    public int networkDelayTimeApproach1(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        for (int[] time : times) {
            adjList.computeIfAbsent(time[0], key -> new ArrayList<>()).add(new int[]{time[1], time[2]});
        }
        for (int node : adjList.keySet()) {
            Collections.sort(adjList.get(node), (a, b) -> {
                return a[1] - b[1];
            });
        }

        Map<Integer, Integer> dist = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            dist.put(i, Integer.MAX_VALUE);
        }

        dfs(adjList, dist, K, 0);

        int ret = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if (dist.get(i) == Integer.MAX_VALUE) return -1;
            ret = Math.max(ret, dist.get(i));
        }

        return ret;
    }

    private void dfs(Map<Integer, List<int[]>> adjList, Map<Integer, Integer> dist, int start, int elapsed) {
        if (elapsed >= dist.get(start)) return;
        dist.put(start, elapsed);
        if (adjList.containsKey(start)) {
            for (int[] next : adjList.get(start)) {
                dfs(adjList, dist, next[0], elapsed + next[1]);
            }
        }
    }

    /**
     * Solution2: Dijkstra's algorithm
     * Considering that we are looking for the longest path among all shortest paths from the source node, which is K, to all other nodes in the graph, using Dijkstra's algorithm seems a natural choice since it is made for solveing shortest path problems.
     *
     * For a detailed explanation of Dijkstra's algorithm, check wiki page.
     *
     * Dijkstra’s algorithm is widely used for finding the shortest path between nodes in a graph. This makes it ideal for finding the minimum delay time in a network.
     *
     * We will use an adjacency dictionary. The source node will be used as key, and the value is a list of tuples that have the destination node and the time for the signal to travel from source to destination node. A priority queue is initialized with time initialized to 0
     *  and starting node k as a tuple. The priority queue ensures that the node with the minimum time is retrieved in each iteration. We will iterate over the priority queue to traverse the nodes in the network. If the node is not visited, the time of the retrieved node is compared to the current delay time and updated accordingly. The neighbors of the retrieved node are found using the adjacency dictionary and are added to the queue with their times updated by adding the delay time from the retrieved node.
     *  Finally, if all the network nodes have been visited, we will return the computed time. Otherwise,−1
     *  will be returned.
     *
     * Here we used a implementation of heap based dijkstra algorithm.
     *
     * Time complexity: O(ElogE) where E is the number of edges in the graph.
     *
     * Space complexity: O(N + E). O(N) for the dist. O(E) for the heap and adjacency list.
     */
    public static int networkDelayTimeApproach2(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adjacency = new HashMap<>();
        for (int[] time : times) {
            int source = time[0];
            int destination = time[1];
            int travelTime = time[2];
            adjacency.computeIfAbsent(source, key -> new ArrayList<>()).add(new int[]{destination, travelTime});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, k});
        Set<Integer> visited = new HashSet<>();
        int delays = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int time = current[0];
            int node = current[1];

            if (visited.contains(node))
                continue;

            visited.add(node);
            delays = Math.max(delays, time);
            List<int[]> neighbors = adjacency.getOrDefault(node, new ArrayList<>());

            for (int[] neighbor : neighbors) {
                int neighborNode = neighbor[0];
                int neighborTime = neighbor[1];
                if (!visited.contains(neighborNode)) {
                    int newTime = time + neighborTime;
                    pq.offer(new int[]{newTime, neighborNode});
                }
            }
        }

        if (visited.size() == n)
            return delays;

        return -1;
    }

}


