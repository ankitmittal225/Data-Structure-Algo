package com.datastructure.array;

//In a sorted Array
public class A23PrefixSum {
    static int[] arr=new int[]{2,8,3,9,6,5,4};
    static int[] arr2=new int[]{5,2,4,1,6};
    //Find the maximum sum of k consecutive element
    public static void main(String[] args) {


        System.out.println(A23PrefixSum.getSum(1,3));
        System.out.println(A23PrefixSum.getSum(2,4));
        System.out.println(A23PrefixSum.getSum(0,5));
        for (int i = 1; i < arr.length; i++) {
            arr[i]=arr[i]+arr[i-1];
        }
        System.out.println(A23PrefixSum.getSum1(1,3));
        System.out.println(A23PrefixSum.getSum1(2,4));
        System.out.println(A23PrefixSum.getSum1(0,5));
    }

    /*'
    Naive Solution
     */
    private static int getSum(int l , int r) {
        int curr=0;
        for (int i = l; i <= r; i++) {
            curr=curr+arr[i];
        }
        return curr;
    }


    /**
     * Effiecient Solution :  do the preprocessing and store the sum of element till that index
     */
    private static int getSum1(int l, int r) {
        return l>0?arr[r]-arr[l-1]:arr[r];
    }


}
