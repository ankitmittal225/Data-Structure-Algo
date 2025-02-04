package com.datastructure.dp;

/**
 * Problem Type : Unbounded problem (repreatation of item allowed)
 * In unbounded knapsack we can consider items multiple times to get the target result i.e.
 * multiple occurences : if we get the choice , if we don't want to consider an item than will
 * move to other, else if we can consider an item we can consider it multiple times.
 *
 * Coin Change Problem Minimum Numbers of coins
 * Given a value V, if we want to make change for V cents, and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins, what is the minimum number of coins to make the change?
 * Example:
 *
 * Input: coins[] = {25, 10, 5}, V = 30
 * Output: Minimum 2 coins required
 * We can use one coin of 25 cents and one of 5 cents
 *
 */
public class L15MinimumNumberOfCoinProblem {

    public static void main(String[] args) {
////        System.out.println(getMinimumCoinRecursion(new int[]{25,10,5}, 30));//2
//        System.out.println(getMinimumCoin(new int[]{25,10,5}, 30));//2
////        System.out.println(getMinimumCoinRecursion(new int[]{1,2,3,5}, 5));//1
//        System.out.println(getMinimumCoin(new int[]{1,2,3,5}, 5));//1
////        System.out.println(getMinimumCoinRecursion(new int[]{1}, 7));//7
//        System.out.println(getMinimumCoin(new int[]{1,2,3,5}, 4));//2
////        System.out.println(getMinimumCoinRecursion(new int[]{1}, 7));//7
//        System.out.println(getMinimumCoin(new int[]{1}, 7));//7
////        System.out.println(getMaximumWaysRecursion(new int[]{2,5,3,6}, 10));//5
        System.out.println(getMinimumCoin(new int[]{2,5,3,6}, 10));//3
//        System.out.println(getMaximumWaysRecursion(new int[]{10}, 10));//1
//        System.out.println(getMinimumCoin(new int[]{10}, 10));//1
////        System.out.println(getMaximumWaysRecursion(new int[]{4}, 5));//0
//        System.out.println(getMinimumCoin(new int[]{4}, 5));//0
    }


    //native using recursion
    private static int getMinimumCoinRecursion(int[] arr, int sum){
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
    private static int getMinimumCoin(int[] arr, int sum) {
        // edge case
        if(sum==0 || arr==null || arr.length==0) return 0;
        int n=arr.length;
        int[][] t= new int[n+1][sum+1];
        /**
         * initialisation in this case will be diff
         * for sum->0 to val and arr=[] value will be infinite
         * like for sum = 5 with arr=[] we will require infinite coin to get sum 5 which will be Integer.MAX_VALUE
         * let set to Integer.MAX_VALUE-1
         *
         */
        for (int i = 0; i <= n; i++) {
            t[i][0]=0;
        }
        for (int j = 0; j <=sum; j++) {
            t[0][j]=Integer.MAX_VALUE-1;
        }
        /**
         * here we need to set the second row as well :
         * consider case sum=4 and arr[0]=3  ways to get 4 sum using 3 no right i.e. Infinte ways
         * i.e 4/3=infite way & 4/4=1
         */
        for (int j = 1; j <=sum; j++) {
            int i=1;
            if(j%arr[i-1]==0)
                t[i][j]=j/arr[i-1];
            else
                t[i][j]=Integer.MAX_VALUE-1;
        }
        //memoization
        /**
         * Math.min(t[i-1][j],1+t[i][j-arr[i-1]]);
         * here we need to find minimum coin to get sum
         * if we are considering a coin it means we are adding 1 more coin thats why +1;
         * since we are adding +1 here thats why we set infinite as MAX_VALUE-1 because after adding one it should be max_Value
         */
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                 //if sum of array elements is evener than only we can partition array in two subset otherwise not eg for 22 we can have two subset of sum 11 but for 23 its not possible
                if(arr[i-1]<=  j)
                    t[i][j]= Math.min(t[i-1][j],1+t[i][j-arr[i-1]]);
                else
                    t[i][j]=t[i-1][j];
            }
        }
        return t[n][sum];

    }


}
