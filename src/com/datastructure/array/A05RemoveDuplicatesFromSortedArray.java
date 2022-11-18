package com.datastructure.array;

public class A05RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
//        int[] arr=new int[]{10, 5, 2, 3, 6};
//        int[] arr=new int[]{10, 5, 2, 3, 6};
        int[] arr=new int[]{10, 10,10,10};
//        int[] arr=new int[]{10,20,20,30,30,30};
        System.out.println(A05RemoveDuplicatesFromSortedArray.getSize(arr));
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }

    }

    private static int getSize(int[] arr) {
        if(arr.length==0) return 0;
        int ind=1, temp=0;
        for (int j = 1; j <arr.length ; j++) {
            if(arr[ind-1]!=arr[j] && ind!=j) {
                arr[ind] = arr[j];
                ind++;
            }
        }
        for (int i = ind; i <arr.length ; i++) {
            arr[i]=0;
        }
        return ind;
    }





}
