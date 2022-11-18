package com.datastructure.array;

public class A10LeadersInAnArray {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5};
//        int[] arr=new int[]{7,10,4,3,6,5,2};
        int[] arr=new int[]{30,20,10};
        A10LeadersInAnArray.getLeaders(arr);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(arr[i]+" ");
//        }

    }

    private static void getLeaders(int[] arr) {
        if(arr.length==0) return ;
        int temp=arr[arr.length-1];
        System.out.print(temp+" ");
        for (int j = arr.length-2; j >=0 ; j--) {
            if(temp<arr[j]){
                temp=arr[j];
                System.out.print(temp+" ");
            }
        }

    }





}
