package com.datastructure.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Type : Unbounded problem (repreatation of item allowed)
 * In unbounded knapsack we can consider items multiple times to get the target result i.e.
 * multiple occurences : if we get the choice , if we don't want to consider an item than will
 * move to other, else if we can consider an item we can consider it multiple times.
 *
 * Coin Change Problem Maximum Number of ways
 * Given a value N, if we want to make change for N cents, and we have infinite supply of each of
 * S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change? The order of coins
 * doesn’t matter.
 * Example:
 * for N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}. So output should be 4.
 */
public class L14CoinChangeProblem {

    public static void main(String[] args) {
        System.out.println(getMaximumWaysRecursion(new int[]{1,2,3}, 4));//4
        System.out.println(getMaximumWays(new int[]{1,2,3}, 4));//4
        System.out.println(getMaximumWaysRecursion(new int[]{1,2,3}, 5));//5
        System.out.println(getMaximumWays(new int[]{1,2,3}, 5));//5
        System.out.println(getMaximumWaysRecursion(new int[]{1,2,3}, 7));//7
        System.out.println(getMaximumWays(new int[]{1,2,3}, 7));//7
        System.out.println(getMaximumWaysRecursion(new int[]{2,5,3,6}, 10));//5
        System.out.println(getMaximumWays(new int[]{2,5,3,6}, 10));//5
        System.out.println(getMaximumWaysRecursion(new int[]{10}, 10));//1
        System.out.println(getMaximumWays(new int[]{10}, 10));//1
        System.out.println(getMaximumWaysRecursion(new int[]{4}, 5));//0
        System.out.println(getMaximumWays(new int[]{4}, 5));//0
    }


    //native using recursion
    private static int getMaximumWaysRecursion(int[] arr, int sum){
        if(arr==null || arr.length==0 || sum==0) return 0;
        return recursion(arr,sum,arr.length-1);
    }

    private static int recursion(int[] arr, int sum, int n) {
        if(sum==0) return 1;
        if(n<0) return 0;
        if(arr[n]<=sum)
            return recursion(arr, sum,n-1 )+recursion(arr,sum-arr[n], n);
        else
            return recursion(arr,sum, n-1);
    }

    //Optimized solution using memoizatio
    private static int getMaximumWays(int[] arr, int sum) {
        // edge case
        if(sum==0 || arr==null || arr.length==0) return 0;
        int n=arr.length;
        int[][] t= new int[n+1][sum+1];
        //initialisation
        for (int i = 0; i <= n; i++) {
            t[i][0]=1;
        }
        for (int j = 1; j <=sum; j++) {
            t[0][j]=0;
        }
        //memoization
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                 //if sum of array elements is evener than only we can partition array in two subset otherwise not eg for 22 we can have two subset of sum 11 but for 23 its not possible
                if(arr[i-1]<=  j)
                    t[i][j]= t[i-1][j]+t[i][j-arr[i-1]];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[n][sum];

    }


}
