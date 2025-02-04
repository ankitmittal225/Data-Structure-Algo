package com.datastructure.dp;

/**
 * Problem Type : 0-1 KnapSack problem (either will pick a value or we won't)
 * Given a set of non-negative integers, and a value sum, determine if there is a subset of the given set with sum equal to given sum.
 * Example:
 *
 * Input:  set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output:  True  //There is a subset (4, 5) with sum 9.
 */
public class L7SubsetSumProblem {

    public static void main(String[] args) {
        System.out.println(isSubsetSumExists(new int[]{},0, 11));//false
        System.out.println(isSubsetSumExists(new int[]{2,3,7,8,10},5, 11));//true
        System.out.println(isSubsetSumExists(new int[]{3, 34, 4, 12, 5, 2},6, 9));//true
        System.out.println(isSubsetSumExists(new int[]{3, 34, 4, 12, 5, 2},6, 30));//false
    }

    public static boolean isSubsetSumExistsRecursion(int[] arr, int n, int sum){
        if(arr==null || arr.length==0 || n<=0) return false;
        if(sum==0) return true;
        if(arr[n-1]<=sum){
            return isSubsetSumExistsRecursion(arr, n-1,sum-arr[n-1])||isSubsetSumExists(arr, n-1,sum);
        }
        else{
            return isSubsetSumExistsRecursion(arr, n-1,sum);
        }
    }

    private static boolean isSubsetSumExists(int[] arr, int n, int sum) {
        // edge case
        if(arr==null || arr.length==0) return false;
        boolean[][] t= new boolean[n+1][sum+1];
        //initialisation
        for (int i = 0; i <= n; i++) {
            t[i][0]=true;
        }
        for (int i = 1; i <=sum; i++) {
            t[0][i]=false;
        }

        //memoization
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                 //if sum of array elements is evener than only we can partition array in two subset otherwise not eg for 22 we can have two subset of sum 11 but for 23 its not possible
                if(arr[i-1]<=j)
                    t[i][j]=t[i-1][j] || t[i-1][j-arr[i-1]];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[n][sum];

    }


}
