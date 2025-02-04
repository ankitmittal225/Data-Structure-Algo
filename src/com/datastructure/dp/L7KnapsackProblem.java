package com.datastructure.dp;

/**
 * Problem Type : 0-1 KnapSack problem (either will pick a value or we won't)
 * Given N items where each item has some weight and profit associated with it and also given a bag
 * with capacity W, [i.e., the bag can hold at most W weight in it]. The task is to put the items
 * into the bag such that the sum of profits associated with them is the maximum possible.
 */
public class L7KnapsackProblem {

    public static void main(String[] args) {
        System.out.println(getMaxProfitRecursive(new int[]{},new int[]{},0, 11));//0
        System.out.println(getMaxProfitRecursive(new int[]{1,3,4,5},new int[]{1,4,5,7},4, 7));//9
        System.out.println(getMaxProfitRecursive(new int[]{4,5,1},new int[]{1,2,3},3, 4));//3
        System.out.println(getMaxProfitRecursive(new int[]{4,5,6},new int[]{1,2,3},3, 3));//0
        System.out.println(getMaxProfitRecursive(new int[]{10, 20, 30 },new int[]{60, 100, 120},3, 50));//220

        System.out.println(getMaxProfitMemoize(new int[]{},new int[]{},0, 11));//0
        System.out.println(getMaxProfitMemoize(new int[]{1,3,4,5},new int[]{1,4,5,7},4, 7));//9
        System.out.println(getMaxProfitMemoize(new int[]{4,5,1},new int[]{1,2,3},3, 4));//3
        System.out.println(getMaxProfitMemoize(new int[]{4,5,6},new int[]{1,2,3},3, 3));//0
        System.out.println(getMaxProfitMemoize(new int[]{10, 20, 30 },new int[]{60, 100, 120},3, 50));//220
    }

    private static int getMaxProfitRecursive(int[] wt,int[] profit, int n, int w) {
        if(n==0 || w==0) return 0;
        if(wt[n-1]<=w){
            return Math.max(profit[n-1]+getMaxProfitRecursive(wt, profit,n-1,w-wt[n-1]),getMaxProfitRecursive(wt, profit,n-1,w));
        }
        else{
            return getMaxProfitRecursive(wt, profit,n-1,w);
        }

    }
    private static int getMaxProfitMemoize(int[] wt,int[] profit, int n, int w) {
//        // edge case
        if(wt==null || wt.length==0 ||profit==null || profit.length==0 || w==0) return 0;
        int[][] t= new int[n+1][w+1];
        //initialisation
        for (int i = 0; i <= n; i++) {
            t[i][0]=0;
        }
        for (int i = 1; i <=w; i++) {
            t[0][i]=0;
        }

        //memoization
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                 //if sum of array elements is evener than only we can partition array in two subset otherwise not eg for 22 we can have two subset of sum 11 but for 23 its not possible
                if(wt[i-1]<=j)
                    t[i][j]=Math.max(t[i-1][j], profit[i-1]+t[i-1][j-wt[i-1]]);
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[n][w];
    }


}
