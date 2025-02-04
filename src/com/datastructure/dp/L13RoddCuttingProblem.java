package com.datastructure.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Type : Unbounded problem (repreatation of item allowed)
 * In unbounded knapsack we can consider items multiple times to get the target result i.e.
 * multiple occurences : if we get the choice , if we don't want to consider an item than will
 * move to other, else if we can consider an item we can consider it multiple times.
 *
 * Rod Cutting Problem
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller
 * than n. Determine the  maximum value obtainable by cutting up the rod and selling the pieces.
 * Example:
 * if length of the rod is 8 and the values of different pieces are given as following, then the maximum
 * obtainable value is 22 (by cutting in two pieces of lengths 2 and 6)
 *
 * length   | 1   2   3   4   5   6   7   8
 * --------------------------------------------
 * price    | 1   5   8   9  10  17  17  20
 */
public class L13RoddCuttingProblem {

    public static void main(String[] args) {
        System.out.println(getMaximumProfitRecursion(new int[]{1,2,3,4,5,6,7,8},new int[]{1,5,8,9,10,17,17,20}, 8));//22
        System.out.println(getMaximumProfit(new int[]{1,2,3,4,5,6,7,8},new int[]{1,5,8,9,10,17,17,20}, 8));//22
        System.out.println(getMaximumProfitRecursion(new int[]{1,2,3,4,5,6},new int[]{3 , 5, 8, 9, 10, 17}, 7));//18
        System.out.println(getMaximumProfit(new int[]{1,2,3,4,5,6},new int[]{3 , 5, 8, 9, 10, 17}, 7));//18

        System.out.println(getMaximumProfit(new int[]{1,2,3,4,5,6,7,8},new int[]{1, 5, 8, 9, 10, 17, 17, 20}, 8)); // Expected: 22
        System.out.println(getMaximumProfit(new int[]{1,2,3,4,5,6,7,8},new int[]{3, 5, 8, 9, 10, 17, 17, 20}, 8)); // Expected: 24
        System.out.println(getMaximumProfit(new int[]{},new int[]{}, 5)); // Expected: 0 (Empty price array)
        System.out.println(getMaximumProfit(new int[]{},new int[]{2, 5}, 0)); // Expected: 0 (Rod length is zero)
        System.out.println(getMaximumProfit(new int[]{1,2,3},new int[]{1, 2, 3}, 5)); // Expected: 6 (Handling longer rod case)
    }


    //native using recursion
    private static int getMaximumProfitRecursion(int[] rodSize, int[] price, int rodLen){
        if(rodSize==null || rodSize.length==0 || rodLen==0) return 0;
        return recursion(rodSize,price,rodLen,rodSize.length-1);
    }

    private static int recursion(int[] rodSize, int[] price, int rodLen, int n) {
        if(n==0) return 0;
        if(rodSize[n]<=rodLen)
            return Math.max(recursion(rodSize, price, rodLen,n-1 ),price[n]+recursion(rodSize, price,rodLen-rodSize[n], n));
        else
            return recursion(rodSize, price,rodLen, n-1);
    }

    //Optimized solution using memoizatio
    private static int getMaximumProfit(int[] rodSize, int[] price, int rodLen) {
        // edge case
        if(rodLen==0 || rodSize==null || rodSize.length==0) return 0;
        int n=rodSize.length;
        int[][] t= new int[n+1][rodLen+1];
        //initialisation
        for (int i = 0; i <= n; i++) {
            t[i][0]=0;
        }
        for (int j = 1; j <=rodLen; j++) {
            t[0][j]=0;
        }

        //List to get range of valid s1 till half way
        List<Integer> list= new ArrayList<>();
        //memoization
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= rodLen; j++) {
                 //if sum of array elements is evener than only we can partition array in two subset otherwise not eg for 22 we can have two subset of sum 11 but for 23 its not possible
                if(rodSize[i-1]<=  j)
                    t[i][j]=Math.max( price[i-1]+t[i][j-rodSize[i-1]],t[i-1][j]);
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[n][rodLen];

    }


}
