package com.datastructure.array;

public class A09LeftRotateArrayByD {

    public static void main(String[] args) {
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5};
        int[] arr=new int[]{8,5,0,10,0,20};
//        int[] arr=new int[]{0,0,0,0,10,0};
        A09LeftRotateArrayByD.rotate2(arr,2);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

    //naive with theta(n*D)
    private static void rotate(int[] arr, int d) {
        if(arr.length==0) return ;
        if(d>arr.length-1)d=d/arr.length-1;
        int ind=arr[0];
        for (int i = 1; i <=d ; i++) {
            ind=arr[0];
            for (int j = 1; j <arr.length ; j++) {
                arr[j-1]=arr[j];
            }
            arr[arr.length-1]=ind;
        }
    }

    //naive with theta(D) space & theta(n) time
    private static void rotate1(int[] arr, int d) {
        if(arr.length==0) return ;
        if(d>arr.length-1)d=d-arr.length;
        int[] nums=new int[d];
        for(int i=0;i<d;i++){
            nums[i]=arr[i];
        }
        for (int j = d; j <arr.length ; j++) {
            arr[j-d]=arr[j];
        }
        for (int i = 0; i <d ; i++) {
            arr[arr.length-d+i]=nums[i];
        }
    }

    //efficient with theta(1) space & theta(n) time
    private static void rotate2(int[] arr, int d) {
        if(arr.length==0) return ;
        if(d>arr.length-1)d=d-arr.length;
        //for left rotate
        reverse(arr,0,d-1);
        reverse(arr,d,arr.length-1);
        reverse(arr,0,arr.length-1);
        //for right rotate
        reverse(arr,0,arr.length-1);
        reverse(arr,0,d-1);
        reverse(arr,d,arr.length-1);


    }

    private static void reverse(int[] arr, int low,int high) {
        while(low<high){
            int temp=arr[low];
            arr[low]=arr[high];
            arr[high]=temp;
            low++;
            high--;
        }

    }






}
