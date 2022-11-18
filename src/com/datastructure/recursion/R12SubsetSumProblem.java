package com.datastructure.recursion;

public class R12SubsetSumProblem {

    public static void main(String[] args) {

        System.out.println(R12SubsetSumProblem.getCount(new int[]{10, 5, 2, 3, 6},8,4));

    }

    private static int getCount(int[] arr, int sum, int len) {
        if(len==0) return sum==0?1:0;
        return getCount(arr,sum-arr[len],len-1)+getCount(arr,sum,len-1);

    }



}
