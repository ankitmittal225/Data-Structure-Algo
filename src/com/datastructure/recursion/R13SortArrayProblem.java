package com.datastructure.recursion;

import java.util.Arrays;

public class R13SortArrayProblem {

    public static void main(String[] args) {
        int[] arr=new int[]{10, 5, 2, 3, 6};
        int[] arr1=new int[]{};
        int[] arr2=null;
        int[] arr3=new int[]{1,1,1,1,1};

        R13SortArrayProblem.sort(arr,arr.length);
        System.out.println(Arrays.toString(arr));
        R13SortArrayProblem.sort(arr1,arr1.length);
        System.out.println(Arrays.toString(arr2));
        R13SortArrayProblem.sort(arr3,arr3.length);
        System.out.println(Arrays.toString(arr3));
    }

    public static void sort(int[] arr, int length){
        if(arr==null || length<=1) return;
        int val= arr[length-1];
        sort(arr, length-1);
        insert(arr,length-1, val);
    }

    public static void insert(int[] arr, int length,int num){
        if(length==0 || arr[length-1]<=num) {
            arr[length]=num;
            return;
        };
        int lastval= arr[length-1];
        insert(arr,length-1, num);
        arr[length]=lastval;
    }
}