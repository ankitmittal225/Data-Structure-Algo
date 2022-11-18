package com.datastructure.array;

public class A04ReverseAnArray {

    public static void main(String[] args) {
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5, 2, 3, 6};
        int[] arr=new int[]{10, 5, 3, 6};
        A04ReverseAnArray.reverse(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

    private static void reverse(int[] arr) {
        if(arr.length==0) return;
        int s=0,e=arr.length-1,temp=0;
        while(s<e){
           temp=arr[s];
           arr[s]=arr[e];
           arr[e]=temp;
           s++;
           e--;
        }
    }





}
