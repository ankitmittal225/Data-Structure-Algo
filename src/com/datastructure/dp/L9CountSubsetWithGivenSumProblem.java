package com.datastructure.dp;

/**
 * Count of subsets sum with a Given sum
 * Given an array arr[] of length N and an integer X, the task is to find the number of subsets with sum equal to X.
 * Example:
 *
 * Input: arr[] = {1, 2, 3, 3}, X = 6
 * Output: 3
 * All the possible subsets are {1, 2, 3},
 * {1, 2, 3} and {3, 3}
 */
public class L9CountSubsetWithGivenSumProblem {

    public static void main(String[] args) {
        System.out.println(getCountSubsetWithGivenSum(new int[]{},0,0));//false
        System.out.println(getCountSubsetWithGivenSum(new int[]{1,2,3,3},4,6));//3
        System.out.println(getCountSubsetWithGivenSum(new int[]{1,2,3,3},4,3));//3
        System.out.println(getCountSubsetWithGivenSum(new int[]{1,2,3,3},4,1));//3
        System.out.println(getCountSubsetWithGivenSum(new int[]{2,3,5,8,10},5,10));//3
    }


    private static int getCountSubsetWithGivenSum(int[] arr, int n, int sum) {
        // edge case
        if(arr==null || arr.length==0) return 0;
        int[][] t= new int[n+1][sum+1];
        //initialisation
        for (int i = 0; i <= n; i++) {
            t[i][0]=1;
        }
        for (int i = 1; i <=sum; i++) {
            t[0][i]=0;
        }

        //memoization
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                 //if sum of array elements is evener than only we can partition array in two subset otherwise not eg for 22 we can have two subset of sum 11 but for 23 its not possible
                if(arr[i-1]<=j)
                    t[i][j]=t[i-1][j] + t[i-1][j-arr[i-1]];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[n][sum];

    }


}
