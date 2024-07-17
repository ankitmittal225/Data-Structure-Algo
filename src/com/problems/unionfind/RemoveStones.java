package com.problems.unionfind;

import java.util.*;

/**
 * Given an array of n stones in a two-dimensional plane, where each stone is represented by a pair of x and y coordinates,
 * find the maximum number of stones we can remove with the following condition:
 *
 * A stone can be removed if it shares either the same row or the same column with another stone that has not been removed so far.
 *
 * Stones are provided as an array, stones, of length n, where stones[i]=[xi, yi] represents the ith
 * stone. Return the maximum possible number of stones that can be removed.
 */
public class RemoveStones {

    public static int removeStones(int[][] arr){
        if(arr==null || arr.length<2) return 0;
        /**
         * Since x and y can have the same values, e.g., [0,0] or [1,1], an offset value is added to the coordinate y to avoid any possible clash in the parents and ranks dictionaries.
         */
        UnionFindRemoveStones removeStones= new UnionFindRemoveStones();
        Set<Integer> set= new HashSet<>();
        int offset=100;
        for(int[] pair:arr){
            removeStones.union(pair[0],pair[1]+offset);
        }
        for(Map.Entry<Integer, Integer> pair:removeStones.getParents().entrySet()){
            set.add(removeStones.find(pair.getKey()));
        }
        return arr.length-set.size();
    }

    // Driver code
    public static void main(String[] args) {
        int[][][] stones = {
                {{0, 0}, {0, 1}, {1, 2}, {2, 2}, {3, 3}},
                {{0, 0}, {2, 2}, {3, 3}},
                {{0, 1}, {2, 1}, {3, 0}},
                {{1, 0}, {2, 1}, {2, 3}, {3, 1}, {3, 3}},
                {{1, 2}, {2, 0}, {2, 2}, {3, 3}}
        };

        for (int i = 0; i < stones.length; i++) {
            System.out.println((i + 1) + ".\tMaximum stones which can be removed from " +
                    Arrays.deepToString(stones[i]) + " are: " + removeStones(stones[i]));
            System.out.println(String.join("", Collections.nCopies(100, "-")));
        }
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
class UnionFindRemoveStones {

    public HashMap<Integer,Integer> parent;
    public HashMap<Integer,Integer> rank;

    // Constructor
    public UnionFindRemoveStones() {
        parent= new HashMap<>();
        rank= new HashMap<>();
    }

    // Function to find which subset a particular element belongs to
    // Returns FALSE if both vertices have the same parent, otherwise, updates the parent and rank lists by making a connection based on the passed edge
    // Returns TRUE if no cycle exits in the graph
    public int find(int v) {
        if(parent.get(v)!=v){
            parent.put(v, find(parent.get(v)));
        }
        return parent.get(v);
    }

    // Function to join two subsets into a single subset
    // returning the parent to get total distinct subset
    public int union(int x, int y) {

        // Set the parent of each coordinate to itself
        // if not already present in the map
        parent.putIfAbsent(x,x);
        parent.putIfAbsent(y,y);

        // Set the ranks of each coordinate to 0
        // if not already present in the map
        rank.putIfAbsent(x, 0);
        rank.putIfAbsent(y, 0);

        // Find the root parents of v1 and v2
        int px = find(x);
        int py = find(y);

        // Updates the parent and rank lists otherwise
        if (rank.get(x) > rank.get(y)) {
            parent.put(find(y), find(x));
            return px;
        }
        else if(rank.get(y) > rank.get(x)) {
            parent.put(find(x), find(y));
            return py;
        }
        else {
            // If the ranks are equal, choose one coordinate
            // as the parent and increment its rank
            parent.put(find(x), find(y));
            rank.put(y, rank.get(y) + 1);
            return py;
        }
    }

    // Getter function to access the parents member
    public Map<Integer, Integer> getParents() {
        return parent;
    }
}

/**
 * Solution summary
 * To recap, the solution to this problem can be divided into the following three parts:
 *
 * Iterate over all the stones, and use the union() function to check if they share a row or column with other stones. Merge the stones into one group if they share a row or column.
 *
 * Use the find() function to get the number of groups to which the stones belong.
 *
 * Return the difference between the number of stones and the number of groups made.
 *
 * Time complexity
 * The time complexity of this solution is O(n×α(n)), where n is the number of coordinates given in input,
 * and O(α(n))
 *  is the time taken for performing union by rank and path compression.α(n)
 *  is almost constant time and grows very slowly with the input size.
 *
 * Therefore, the overall time complexity becomes O(n)
 * .
 *
 * Space complexity
 * The space complexity is O(n), where n is the number of coordinates.
 */


