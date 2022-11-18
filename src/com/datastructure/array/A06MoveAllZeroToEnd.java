package com.datastructure.array;

public class A06MoveAllZeroToEnd {

    public static void main(String[] args) {
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5};
        int[] arr=new int[]{8,5,0,10,0,20};
//        int[] arr=new int[]{0,0,0,0,10,0};
        System.out.println(A06MoveAllZeroToEnd.getSize(arr));
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

    private static int getSize(int[] arr) {
        if(arr.length==0) return 0;
        int ind=0,temp;
        for (int j = 0; j <arr.length ; j++) {
            if(arr[j]!=0 ) {
//                if(ind!=j) {
                    temp=arr[ind];
                    arr[ind] = arr[j];
                    arr[j] = temp;
//                }
                ind++;
            }

        }
        for (int i = ind; i <arr.length ; i++) {
            arr[i]=0;
        }
        return ind;
    }





}
