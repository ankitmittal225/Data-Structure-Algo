package com.problems.unionfind;

public class DisjointSet {
    int[] rank;
    int[] parent;

    public DisjointSet(int n) {
//        declare the parent array with length based on the edges array.
        parent=new int[n+1];
        rank=new int[n+1];
        for (int i = 0; i <= n; i++) {
            parent[i]=i;
            rank[i]=1;
        }
    }

    int find(int v){
        //initial case when all vertices parent are itself only
        if(parent[v]!=v){
            // without Union by rank case
//            v=find(parent[v]);

            // with Union by rank case to provide path compression
            //In the find function, for a node v, we make the found root as the parent of v so that we don’t have to
            // traverse all the intermediate nodes again on further find operations.
            parent[v]=find(parent[v]);
        }
        // without Union by rank case
//        return v;
        // with Union by rank case to provide path compression
        return parent[v];
    }

    boolean union(int v1, int v2){
        //If both v1 and v2 have the same parent, i.e., p1 is equal to p2, the given edge is redundant, so we return FALSE.
        int p1=find(v1);
        int p2= find(v2);
        if(p1==p2)
            return false;
        else
            // without Union by rank case
//            parent[v2]=v1;

            //with Union by rank case
            if(rank[v1]>=rank[v2]){
                parent[v2]=v1;
                rank[v1]+=rank[v2];
            }
            else{
                parent[v1]=v2;
                rank[v2]+=rank[v1];
            }

        return true;
    }
}
