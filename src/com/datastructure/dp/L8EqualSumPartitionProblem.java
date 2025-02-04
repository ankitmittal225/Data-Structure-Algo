package com.datastructure.dp;

/**
 * Problem Type : 0-1 KnapSack problem (either will pick a value or we won't)
 * The partition problem is to determine whether a given set can be partitioned into
 *  two subsets such that the sum of elements in both subsets is the same.
 */
public class L8EqualSumPartitionProblem {

    public static void main(String[] args) {
        System.out.println(isEqualSumSubsetPossible(new int[]{},0));//false
        System.out.println(isEqualSumSubsetPossible(new int[]{1,5,11,5},4));//true
        System.out.println(isEqualSumSubsetPossible(new int[]{1,5,3},3));//false
        System.out.println(isEqualSumSubsetPossible(new int[]{2,2,3,2,5,6},6));//true
    }


    //concept is sum1-sum2=0
    private static boolean isEqualSumSubsetPossible(int[] arr, int n){
        int sum=0;
        for(int num:arr){
            sum=sum+num;
        }
        return sum % 2 == 0 && isSubsetSumExists(arr, n, sum / 2);
//        return sum % 2 == 0 && isSubsetSumExists(arr, n, sum / 2);
    }

    private static boolean isSubsetSumExistsUsingRecursion(int[] arr, int n, int sum) {
        if(arr[n-1]<=sum){
            return isSubsetSumExistsUsingRecursion(arr,n-1,sum-arr[n-1]) || isSubsetSumExistsUsingRecursion(arr, n-1, sum);
        }
        else{
            return isSubsetSumExistsUsingRecursion(arr,n-1,sum);
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
