package com.problems.unionfind;

import java.util.Arrays;

public class Example {
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
            System.out.println("\n\tThe redundant connection in the graph is: " + Arrays.toString(redundantConnection(edges[i])));
            System.out.println(new String(new char[100]).replace("\0", "-"));
        }
    }

    private static int[] redundantConnection(int[][] edges) {
        DisjointSet ds=new DisjointSet(edges.length);
        for(int[] edge : edges){
            if(!ds.union(edge[0],edge[1]))
                return edge;
        }
        return new int[]{};
    }


}
