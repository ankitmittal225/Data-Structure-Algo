package com.datastructure.array;

public class A07LeftRotateArrayByOne {

    public static void main(String[] args) {
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5};
        int[] arr=new int[]{8,5,0,10,0,20};
//        int[] arr=new int[]{0,0,0,0,10,0};
        A07LeftRotateArrayByOne.rotate(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

    private static void rotate(int[] arr) {
        if(arr.length==0) return ;
        int ind=arr[0];
        for (int j = 1; j <arr.length ; j++) {
            arr[j-1]=arr[j];
        }
        arr[arr.length-1]=ind;
    }





}
