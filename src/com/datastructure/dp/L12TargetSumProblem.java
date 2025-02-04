package com.datastructure.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Type : 0-1 KnapSack problem (either will pick a value or we won't)
 * Target Sum Problem
 * Given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
 *
 * Find out how many ways to assign symbols to make sum of integers equal to target S.
 *
 * Example 1:
 * Input: nums is [1, 1, 1, 1, 1], S is 3.
 * Output: 5
 * Explanation:
 *
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 *
 * Example 2:
 * Input: nums is [1, 1, 2, 3], S is 1.
 * Output: 3
 * Explanation:
 *
 * +1-1-2+3 = 1
 * -1+1-2+3 = 1
 * +1+1+2-3 = 1
 *
 *
 * Approach toward solution :
 * We can consider we get two subset s1, s2 after assigning + & - operator whose sum needs to be given value Sum
 * i.e. s1-s2 = sum (s1 is all positive sign value sum & s2 is all negative sign value sum)
 * if we take a look at this statement its like subset sum problem
 * where we need to find count of subset with given diff as sum
 *
 * Equal to subset sum difference problem
 *
 */
public class L12TargetSumProblem {

    public static void main(String[] args) {
        System.out.println(getCountSubsetWithGivenDifference(new int[]{},0,0));//0
        System.out.println(getCountSubsetWithGivenDifference(new int[]{1, 1, 1, 1, 1},5,3));//5
        System.out.println(getCountSubsetWithGivenDifference(new int[]{1},1,1));//1
        System.out.println(getCountSubsetWithGivenDifference(new int[]{1,1,2,3},4,1));//3
    }


    //native using recursion
//    private static int getMinSubsetSumDifferenceRecursion(int[] arr, int n){
//        if(arr==null || arr.length==0) return 0;
//        int sum=0;
//        for(int num:arr){
//            sum=sum+num;
//        }
//        return recursion(arr, n-1, sum,sum);
//    }
//
//    private static int recursion(int[] arr, int n, int sum, int s1) {
//        if(n<0) return Math.abs(sum-2*s1);
//        return Math.min(recursion(arr, n-1,sum, s1),recursion(arr, n-1,sum,s1-arr[n]));
//    }

    //Optimized solution using memoization
    // approach is we need to get count of subset having difference of their sum is given value
    //so how will solve it let s1, s2 are sum of two subset s1 & s2
    // we need to count where s1-s2= diff
    //s1+s2=sum & s1-s2=diff => 2s1=sum+diff => s1=(sum+diff)/2
    // so count of s1-s2=diff will count of s1=(sum+diff)/2
    // where sum we can compute and difference is given
    private static int getCountSubsetWithGivenDifference(int[] arr, int n, int diff){
        if(arr==null || arr.length==0) return 0;
        int sum=0;
        for(int num:arr){
            sum=sum+num;
        }
        int s1=(sum+diff)/2;
        return getSubsetSumCount(arr, n, s1 );
    }


    /**
     * approach is two subset are s1 & s2 as in equal sum partition s1-s2=0 where s1=totalsum-s1 but
     * here we need to get min sum and that sum can be in range of 0 to totalsum
     * we can check for all value in range if subset exist with it  using subset sum problem
     * but will go till half range as s1 becuase s2=totalSUm-s1
     *
     * @param arr
     * @param n
     * @return
     */
    private static int getSubsetSumCount(int[] arr, int n, int s1) {
        // edge case
        if(arr==null || arr.length==0) return 0;
        int[][] t= new int[n+1][s1+1];
        //initialisation
        for (int i = 0; i <= n; i++) {
            t[i][0]=1;
        }
        for (int i = 1; i <=s1; i++) {
            t[0][i]=0;
        }

        //List to get range of valid s1 till half way
        List<Integer> list= new ArrayList<>();
        //memoization
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= s1; j++) {
                 //if sum of array elements is evener than only we can partition array in two subset otherwise not eg for 22 we can have two subset of sum 11 but for 23 its not possible
                if(arr[i-1]<=j)
                    t[i][j]=t[i-1][j] + t[i-1][j-arr[i-1]];
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[n][s1];

    }


}
