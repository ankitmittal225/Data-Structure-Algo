package com.datastructure.array;

//In a sorted Array
public class A17LongestEvenOddSubarray {

    //Leaders are those element in array who has only smaller element in right of it.
    public static void main(String[] args) {
        int[] arr1=new int[]{10,12,4,7,8};
        int[] arr2=new int[]{7,10,13,14};
        System.out.println(A17LongestEvenOddSubarray.getMax(arr1));
        System.out.println(A17LongestEvenOddSubarray.getMax(arr2));
    }

    //kadane algorithm
    private static int getMax(int[] arr) {
        int max=0,count=1;
        for (int i = 1; i < arr.length; i++) {

            if((arr[i]%2==0 && arr[i-1]%2==1)|| (arr[i-1]%2==0 && arr[i]%2==1)){
                count++;
            }
            else{
                max=Integer.max(max,count);
                count=1;
            }
        }
        return Integer.max(max,count);
    }

}
