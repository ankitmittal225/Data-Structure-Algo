package com.datastructure.array;

public class A01OperationOnArray {

    public static void main(String[] args) {
        int[] arr=new int[]{10, 5, 2, 3, 6,0,0};
        System.out.println(A01OperationOnArray.search(new int[]{10, 5, 2, 3, 6},5));
//        A01OperationOnArray.insert(arr,2,6,7,5);
        A01OperationOnArray.delete(arr,5,7,5);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

    private static int search(int[] ints, int num) {
        if(ints.length==0) return -1;
        for (int i=0;i<ints.length;i++){
            if(ints[i]==num) return i;
        }
        return -1;
    }
    private static void insert(int[] arr, int index,int num,int len,int cap) {
        if(index>len) return ;
        if(cap==len) return;
        for (int i = arr.length-2; i >=index-1; i--) {
            arr[i+1]=arr[i];
        }
        arr[index-1]=num;
    }

    private static void delete(int[] arr,int num,int len,int cap) {
        int ind=0;
        for (int i = 0; i <cap; i++) {
            if(arr[i]==num) {
               ind=i;
               break;
            }
        }
        for (int i = ind+1; i <=cap ; i++) {
            arr[i-1]=arr[i];
        }
    }


}
