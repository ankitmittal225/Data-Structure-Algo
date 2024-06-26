package com.problems.graph;

import java.util.*;

/**
 * Statement
 * You are given an array, routes, representing bus routes where routes[i] is a bus route that the ith
 * bus repeats forever. Every route contains one or more stations. You have also been given the source station,
 * src, and a destination station, des.
 *
 * Return the minimum number of buses someone must take to travel from src to dest, or return -1 if there is no route.
 *
 * Constraints:
 * 1≤ routes.length ≤ 500
 * 1≤ routes[i].length ≤ 10000
 *
 * Solution
 * The solution starts by creating an adjacency list that maps each station to the buses that travel through that station.
 * This is done by iterating through routes and, for each route, adding the route's index to the adjacency list
 * for each station in that route.
 *
 * Next, we use the BFS algorithm to find a path from src to dest using the minimum number of buses since BFS always
 * finds the shortest path in an unweighted graph. The BFS algorithm is implemented using a queue that stores the
 * current station and the number of buses taken to reach that station. The queue is initialized with src and
 * a bus count of 0.
 *
 * Then, we repeatedly dequeue the next station from the queue, and if it is equal to the dest,
 * we return the number of buses. Otherwise, we iterate through the buses that travel through the current station and,
 * for each unvisited bus, we enqueue all the stations in that bus route along with the bus count incremented by 1
 * and mark that bus as visited by adding it into the set of visited buses. If the queue becomes empty and no station
 * is equal to the dest, we return -1 because there is no route available from src to dest.
 */
class MinimumBuses {
  public static int minimumBuses(int[][] routes, int src, int dest) {
    Map<Integer, List<Integer>> adjList = new HashMap<>();
    for (int i = 0; i < routes.length; i++) {
      for (int station: routes[i]) {
        if (!adjList.containsKey(station)) {
          adjList.put(station, new ArrayList<>());
        }
        adjList.get(station).add(i);
      }
    }

    Deque< int[] > queue = new ArrayDeque < > ();
    queue.add(new int[] {src, 0});

    Set< Integer > visitedBuses = new HashSet < > ();

    while (!queue.isEmpty()) {
      int[] current = queue.peek();
      int station = current[0];
      int busesTaken = current[1];
      queue.poll();


      if (station == dest) {
        return busesTaken;
      }

      if (adjList.containsKey(station)) {
        for (int bus: adjList.get(station)) {
          if (!visitedBuses.contains(bus)) {
            for (int s: routes[bus]) {
              queue.add(new int[] {
                s,
                busesTaken + 1
              });
            }
            visitedBuses.add(bus);
          }
        }
      }
    }

    return -1;
  }


  // Driver code
  public static void main(String[] args) {
    int[][][] routes = {
            {{2, 5, 7}, {4, 6, 7}},
            {{1, 12}, {4, 5, 9}, {9, 19}, {10, 12, 13}},
            {{1, 12}, {10, 5, 9}, {4, 19}, {10, 12, 13}},
            {{1, 9, 7, 8}, {3, 6, 7}, {4, 9}, {8, 2, 3, 7}, {2, 4, 5}},
            {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
        };
        int[] src = {2, 9, 1, 1, 4};
        int[] dest = {6, 12, 9, 5, 6};

    for (int i = 0; i < routes.length; i++) {
      System.out.print((i + 1) + ".\tBus Routes: ");
      System.out.print(Arrays.deepToString(routes[i]));
      System.out.println();
      System.out.println("\tSource: " + src[i]);
      System.out.println("\tDestination: " + dest[i]);
      System.out.println("\n\tMinimum Buses Required: " + minimumBuses(routes[i], src[i], dest[i]));
      System.out.println(new String(new char[100]).replace('\0', '-'));
    }
  }
}

/**
 * Time complexity
 * The time complexity of this solution is O(R×S), where R is the total number of routes and S is the number of stations.
 *
 * Space complexity
 * We are using an adjacency list, queue, and visited buses set as additional memory. So, the space complexity of the algorithm is
 * O(R×S), where R is the total number of routes, and S is the number of stations.
 */