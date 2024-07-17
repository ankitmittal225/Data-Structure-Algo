package com.problems.unionfind;

import java.util.*;

/**
 * Given an unsorted array, nums, your task is to return the length of the longest consecutive sequence of elements.
 * The consecutive sequence of elements is such that there are no missing elements in the sequence.
 * The consecutive elements can be present anywhere in the input array.
 *
 * Naive approach
 * A naive approach to solve this problem would be to sort the input array and then iterate through the sorted array. For each element, we check if the next consecutive element exists in the sorted array. If it does, we keep incrementing until we reach the end of the sequence. After reaching the end of the sequence, we compare its length with the previous longest sequence found and update the result accordingly.
 * The time complexity of this approach is O(nlogn), and the space complexity is O(1).
 *
 * Optimized approach using union find
 * We will solve this problem with the help of the union find pattern and use the union by rank and path compression variation of the algorithm.
 * The goal is to find the consecutive elements in the array, which can be done by connecting the elements to a single parent element.
 * For this purpose, we create dictionaries to store the parent of each element and the size of each sequence.
 * We will initialize the parent of each element to itself and the length of each sequence to be 1 initially.
 */
public class LongestConsecutiveSequence {
    // driver code
    public static void main(String[] args) {
        int[][] inputNums = {
                {150, 14, 200, 1, 3, 2},
                {1, 2, 3, 4, 5, 6, 7},
                {1, 3, 5, 7},
                {7, 6, 5, 4, 3, 2, 1},
                {7, 6, 5, 1}
        };

        for (int i = 0; i < inputNums.length; i++) {
            System.out.println((i + 1) + ".\tnums = " + Arrays.toString(inputNums[i]));
            System.out.println("\tThe length of the longest consecutive sequence is: " + longestConsecutiveSequence(inputNums[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    /**
     * For each element
     * 𝑛
     * 𝑢
     * 𝑚
     * num
     *  in the input array, we’ll check if
     * 𝑛
     * 𝑢
     * 𝑚
     * +
     * 1
     * num+1
     *  is present in our parent dictionary. If it is present, we’ll apply a union method of the UnionFind class on num and num+1.
     *
     * Here are the steps that will be carried out in the union method:
     *
     * Find the roots of sequences containing the given elements using the find method. If they are not equal, combine the roots of these consecutive sequences by setting the parent of the root of the smaller sequence to the root of the larger sequence.
     *
     * Update the length of the larger sequence by adding the current sequence’s length and the smaller sequence’s length.
     *
     * Update maxLength as below. This compares the current value of maxLength with the updated length of the larger sequence (xRoot) and stores the larger of the two values in maxLength.
     */
    public static int longestConsecutiveSequence(int[] arr) {
        UnionFindLCS unionFindLCS= new UnionFindLCS(arr);
        for(int num: arr){
            if(unionFindLCS.parent.containsKey(num+1)){
                unionFindLCS.union(num, num+1);
            }
        }
        return unionFindLCS.maxLength;
    }
}

/**
 * Summary
 * This problem involves finding the length of the longest consecutive sequence, given an unsorted array of integers. We initialize the Union Find data structure with each element in the array as a separate component using dictionaries. Then, we iterate through the array, evaluating for each element if its neighbor with a value of one greater is present in the Union Find data structure. If found, the two components are combined. Once all the elements have been processed, the size of the largest connected component is returned as the length of the longest consecutive sequence.
 *
 * Time complexity
 * The time complexity of this solution is O(n), where n is the number of integers in the array.
 *
 * Space complexity
 * The space complexity of this solution is O(n)
 *  since we need to store the parent and size of each integer in the dictionaries.
 */

class UnionFindLCS {
    // Initializing the parent list and count variable by traversing the grid
    public HashMap<Integer,Integer> parent;
    public HashMap<Integer,Integer> size;
    public int maxLength;

    // Constructor
    public UnionFindLCS(int[] arr) {
        parent= new HashMap<>();
        size= new HashMap<>();
        maxLength=1;
        for (int num : arr) {
            parent.put(num, num);
            size.put(num, 1);
        }
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
        // Find the root parents of v1 and v2
        int px = find(x);
        int py = find(y);
        if(py!=px){
            if(size.get(px)<size.get(py)){
                int temp=px;
                px=py;
                py=temp;
            }
            parent.put(py,px);
            size.put(px,size.get(px) + size.get(py));
            maxLength=Math.max(maxLength, size.get(px));
        }
        return maxLength;
    }

    // Getter function to access the parents member
    public Map<Integer, Integer> getParents() {
        return parent;
    }
}
