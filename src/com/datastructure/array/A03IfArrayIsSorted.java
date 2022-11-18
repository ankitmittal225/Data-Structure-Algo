package com.datastructure.array;

public class A03IfArrayIsSorted {

    public static void main(String[] args) {
        int[] arr=new int[]{10, 5, 2, 3, 6,0,0};
        System.out.println(A03IfArrayIsSorted.isSorted(new int[]{7, 7, 7, 7, 7}));

    }

    private static boolean isSorted(int[] arr) {
        if(arr.length==0) return true;
        for (int i=1;i<arr.length;i++){
            if(arr[i]<arr[i-1]) return false;
        }
        return true;
    }





}
